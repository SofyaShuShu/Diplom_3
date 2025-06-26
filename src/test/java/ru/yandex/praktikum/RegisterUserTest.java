package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
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
import ru.yandex.praktikum.pages.RegisterPage;

public class RegisterUserTest {
    private WebDriver driver;
    private BasicUtils utils;
    private User user;
    private static final String EXPECTED_MESSAGE = "Некорректный пароль";

    @Before
    @Step("Method for launching a web driver and API base URI")
    public void StartUp() {
        utils = new BasicUtils();
        utils.startUp();
        driver = utils.getDriver();
        String baseURI = UserAPI.getAPIBaseURI();
    }

    @DisplayName("Method for testing successful registration with valid user data.")
    @Test
    public void registerUserTest(){
        user = new UserRandomCreate().createNewUser();
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickLogInToAccountButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickRegisterButtonOnLoginPage();
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.registerUser(user);
        objLoginPage.waitForOpenLoginPage();
        objLoginPage.loginUser(user);
        Assert.assertTrue(objMainPage.isMakeOrderButtonVisible());
    }

    @DisplayName("Method checks the error message when the password is less than 6 characters long.")
    @Test
    public void shortPasswordErrorMassageTest(){
        user = new UserRandomCreate().createNewUserWithShortPassword();
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickLogInToAccountButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickRegisterButtonOnLoginPage();
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.registerUser(user);
        objRegisterPage.waitForOpenPasswordErrorMessage();


        String actualMessage = objRegisterPage.getTextOfPasswordErrorMessage();

        Assert.assertTrue("Сообщение об ошибке не появилось", actualMessage.equals(EXPECTED_MESSAGE));
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

