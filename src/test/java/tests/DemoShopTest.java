package tests;

import org.openqa.selenium.Cookie;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class DemoShopTest extends TestBase {

    @Test
    void wishlistCreateAndClear() {

//Extracting an user cookie for the session
        String cookieNopCustomer =
            when()
                .get("")
            .then()
                .statusCode(200)
                .extract().cookie("Nop.customer");

//Applying the user cookie to the webdriver
        open("/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer",cookieNopCustomer));
        open("");

//Adding 5 products to the wishlist
        for(int i = 0; i<5; i++) {
            given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer", cookieNopCustomer)
            .when()
                .post("/addproducttocart/details/43/2")
            .then()
                .statusCode(200)
                .body("success", is(true));
        }

//Opening the wishlist page and clearing all the items
        $("#topcartlink").sibling(0).click();
        $(".remove-from-cart").click();
        $(byName("updatecart")).click();
        $(byText("The wishlist is empty!")).shouldBe(visible);

    }

}
