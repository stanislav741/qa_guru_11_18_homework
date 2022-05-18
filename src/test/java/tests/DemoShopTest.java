package tests;

import org.openqa.selenium.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class DemoShopTest {

    @Test
    void wishlistCreateAndEmail() {

        String cookieNopCustomer = "d1d4d828-f6dc-422e-809a-c956d84f56c1";
                when()
                        .get("http://demowebshop.tricentis.com/")
                .then()
//                        .log().all()
                        .statusCode(200);
//                        .extract().cookie("Nop.customer");

//        System.out.println(cookieNopCustomer);

        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer",cookieNopCustomer));
        open("http://demowebshop.tricentis.com/");

        for(int i = 0; i<5; i++) {
        given().
                contentType("application/x-www-form-urlencoded; charset=UTF-8").
             cookie("Nop.customer", cookieNopCustomer).
             when().
            post("http://demowebshop.tricentis.com/addproducttocart/details/43/2").
               then().
               log().all().
               statusCode(200).
               body("success", is(true));
   }
        refresh();
        refresh();
    }
}
//                extract().cookie("");

////        String NopCustomer = cookieResponse.get("");
//
//        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
//        getWebDriver().manage().addCookie(new Cookie("Nop.customer","67f44180-2aef-4cc9-ae52-f4826dc565d7"));
////        getWebDriver().manage().addCookie(new Cookie("ARRAffinity","1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687"));
//        open("http://demowebshop.tricentis.com/");
//
//    for(int i = 0; i<5; i++) {
//        given().
//                contentType("application/x-www-form-urlencoded; charset=UTF-8").
////                cookies(cookieResponse).
////        cookie("ARRAffinity", "1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687").
//                cookie("Nop.customer", "67f44180-2aef-4cc9-ae52-f4826dc565d7").
//                when().
//                post("http://demowebshop.tricentis.com/addproducttocart/details/43/2").
//                then().
//                log().all().
//                statusCode(200).
//                body("success", is(true));
//    }
//        refresh();
//        refresh();
////                body("message", is("The product has been added to your " +
////                        "\\u003ca href=\\\"/wishlist\\\"\\u003ewishlist\\u003c/a\\u003e"));
//    }
//
////    @Test
////    void setUserCookie() {
////
////        Map<String, String> cookieResponse =
////        when().
////                get("/Themes/DefaultClean/Content/images/logo.png").
////        then().
//////                log().all().
////                statusCode(200).
////                extract().cookies();
////
////        String cookie = cookieResponse.get("ARRAffinity");
////
////        System.out.println(cookie);
////
////    }
//
//
//}
