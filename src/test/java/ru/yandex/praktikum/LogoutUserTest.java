package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.UserUtils.User;
import ru.yandex.praktikum.UserUtils.UserAPI;
import ru.yandex.praktikum.UserUtils.UserRandomCreate;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.PersonalCabinetPage;


public class LogoutUserTest {
    private WebDriver driver;
    private BasicUtils utils;
    private User user;

    @Before
    @Step("Method for launching a web driver and create new user")
    public void setUp() {
        utils = new BasicUtils();
        utils.startUp();
        driver = utils.getDriver();
        String baseURI = UserAPI.getAPIBaseURI();
        user = new UserRandomCreate().createNewUser();
        Response createResponse = UserAPI.userCreate(user);
    }
    @Step("Test logout from click on exit button on personal cabinet page")
    @Test
    public void logOutFromPersonalCabinet() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickPersonalCabinetButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitForOpenLoginPage();
        objLoginPage.loginUser(user);
        objMainPage.clickPersonalCabinetButton();
        PersonalCabinetPage objPersonalCabinetPage = new PersonalCabinetPage(driver);
        objPersonalCabinetPage.waitForOpenPersonalCabinetPage();
        objPersonalCabinetPage.clickOnExitButtonOnPersonalCabinetPage();
        objLoginPage.waitForOpenLoginPage();
        objMainPage.openMainPage();

        Assert.assertTrue("Кнопка 'Войти в аккаунт' должна быть видимой",objMainPage.isLogInToAccountButtonVisible());
    }



    @After
    @Step("Method for completes the web driver process and deleting user from database")
    public void tearDown() {
        utils.tearDown();
        String accessToken = UserAPI.getAccessToken(user);
        if(accessToken != null) {
            UserAPI.userDelete(accessToken);
        }
    }
}
