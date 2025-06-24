package ru.yandex.praktikum.UserUtils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    @Step("Method for get API base URI")
    public static String getAPIBaseURI(){
    RestAssured.baseURI = BASE_URI;
    return RestAssured.baseURI;
    }

    @Step("Method for user create")
    public static Response userCreate(User user){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(user)
                        .when()
                        .post("/api/auth/register");
        return response;
    }

    @Step("Method for get user access token")
    public static String getAccessToken(User user){
        String accessToken =
                given()
                        .header("Content-Type", "application/json")
                        .body(user)
                        .post("api/auth/login")
                        .then()
                        .extract()
                        .path("accessToken");
        return accessToken;

    }

    @Step("Method for user delete")
    public static void userDelete(String accessToken) {
        Response response =
                given()
                        .header("Authorization", accessToken)
                        .when()
                        .delete("/api/auth/user");

    }
}