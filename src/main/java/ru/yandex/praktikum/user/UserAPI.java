package ru.yandex.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER_USER_PATH = "/api/auth/register";
    public static final String LOGIN_USER_PATH = "/api/auth/login";
    public static final String USER_INFO_PATH = "/api/auth/user";

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
                        .post(REGISTER_USER_PATH);
        return response;
    }

    @Step("Method for get user access token")
    public static String getAccessToken(User user){
        String accessToken =
                given()
                        .header("Content-Type", "application/json")
                        .body(user)
                        .post(LOGIN_USER_PATH)
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
                        .delete(USER_INFO_PATH);

    }
}