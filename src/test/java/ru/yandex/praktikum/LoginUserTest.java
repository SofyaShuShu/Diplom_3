package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.user.User;
import ru.yandex.praktikum.user.UserAPI;
import ru.yandex.praktikum.user.UserRandomCreate;
import ru.yandex.praktikum.pages.LoginPage;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.PasswordRecoveryPage;
import ru.yandex.praktikum.pages.RegisterPage;

public class LoginUserTest {
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

    @DisplayName("Login test with click on login to account button on main page")
    @Test
    public void loginUserTestWithLoginButtonTest(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickLogInToAccountButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitForOpenLoginPage();
        objLoginPage.loginUser(user);
        Assert.assertTrue(objMainPage.isMakeOrderButtonVisible());
    }


@DisplayName("Login test with click on personal cabinet button on main page")
@Test
public void loginUserTestWithPersonalCabinetButtonTest(){
    MainPage objMainPage = new MainPage(driver);
    objMainPage.openMainPage();
    objMainPage.clickPersonalCabinetButton();
    LoginPage objLoginPage = new LoginPage(driver);
    objLoginPage.waitForOpenLoginPage();
    objLoginPage.loginUser(user);
    Assert.assertTrue(objMainPage.isMakeOrderButtonVisible());
    }

@DisplayName("Login test with click on enter button on registration page")
@Test
public void loginUserTestWithEnterButtonOnRegistrationPageTest(){
    RegisterPage objRegisterPage = new RegisterPage(driver);
    objRegisterPage.openRegisterPage();
    objRegisterPage.clickEnterButtonOnRegisterPage();
    LoginPage objLoginPage = new LoginPage(driver);
    objLoginPage.waitForOpenLoginPage();
    objLoginPage.loginUser(user);
    MainPage objMainPage = new MainPage(driver);
    Assert.assertTrue(objMainPage.isMakeOrderButtonVisible());
    }

    @DisplayName("Login test with click on enter button on password recovery page")
    @Test
    public void loginUserTestWithEnterButtonOnPasswordRecoveryPagePageTest(){
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.openLoginPage();
        objLoginPage.clickRecoveryPasswordButton();
        PasswordRecoveryPage objPasswordRecoveryPage = new PasswordRecoveryPage(driver);
        objPasswordRecoveryPage.clickEntryButtonOnPasswordRecoveryPage();
        objLoginPage.waitForOpenLoginPage();
        objLoginPage.loginUser(user);
        MainPage objMainPage = new MainPage(driver);
        Assert.assertTrue(objMainPage.isMakeOrderButtonVisible());
    }

    @After
    @Step("Method for completes the web driver process and deleting user from database")
    public void tearDown() {
        utils.tearDown();
        String accessToken = UserAPI.getAccessToken(user);
        if(accessToken != null){
         UserAPI.userDelete(accessToken);
        }
    }
}


