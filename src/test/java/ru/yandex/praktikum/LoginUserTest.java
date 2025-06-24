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
import ru.yandex.praktikum.pageobject.PasswordRecoveryPage;
import ru.yandex.praktikum.pageobject.RegisterPage;

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

    @Step("Login test with click on login to account button on main page")
    @Test
    public void loginUserTestWithLoginButton(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickLogInToAccountButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitForOpenLoginPage();
        objLoginPage.loginUser(user);
        Assert.assertTrue(objMainPage.isMakeOrderButtonVisible());
    }


@Step("Login test with click on personal cabinet button on main page")
@Test
public void loginUserTestWithPersonalCabinetButton(){
    MainPage objMainPage = new MainPage(driver);
    objMainPage.openMainPage();
    objMainPage.clickPersonalCabinetButton();
    LoginPage objLoginPage = new LoginPage(driver);
    objLoginPage.waitForOpenLoginPage();
    objLoginPage.loginUser(user);
    Assert.assertTrue(objMainPage.isMakeOrderButtonVisible());
    }

@Step("Login test with click on enter button on registration page")
@Test
public void loginUserTestWithEnterButtonOnRegistrationPage(){
    RegisterPage objRegisterPage = new RegisterPage(driver);
    objRegisterPage.openRegisterPage();
    objRegisterPage.clickEnterButtonOnRegisterPage();
    LoginPage objLoginPage = new LoginPage(driver);
    objLoginPage.waitForOpenLoginPage();
    objLoginPage.loginUser(user);
    MainPage objMainPage = new MainPage(driver);
    Assert.assertTrue(objMainPage.isMakeOrderButtonVisible());
    }

    @Step("Login test with click on enter button on password recovery page")
    @Test
    public void loginUserTestWithEnterButtonOnPasswordrecoveryPagePage(){
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


