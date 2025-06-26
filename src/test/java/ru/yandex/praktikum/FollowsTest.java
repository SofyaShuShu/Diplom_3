package ru.yandex.praktikum;

import io.qameta.allure.Description;
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
import ru.yandex.praktikum.pages.PersonalCabinetPage;


public class FollowsTest {
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

    @DisplayName("Test follow on personal cabinet for authorization user")
    @Test
    public void followOnPersonalCabinetForAuthorizationUserTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickPersonalCabinetButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitForOpenLoginPage();
        objLoginPage.loginUser(user);
        objMainPage.clickPersonalCabinetButton();
        PersonalCabinetPage objPersonalCabinetPage = new PersonalCabinetPage(driver);
        objPersonalCabinetPage.waitForOpenPersonalCabinetPage();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Страница личного кабинета не открылась", objPersonalCabinetPage.PERSONALCABINETPAGE_URL, currentUrl);
    }

    @DisplayName("Test follow on personal cabinet for unauthorized user")
    @Test
    public void followOnPersonalCabinetForUnauthorizedUserTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickPersonalCabinetButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitForOpenLoginPage();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Страница авторизации не открылась", objLoginPage.LOGINPAGE_URL, currentUrl);
    }

    @Description("Constructor is located on the main page")
    @DisplayName("Test switching from personal cabinet to the constructor")
    @Test
    public void followFromPersonalCabinetToConstructorTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickPersonalCabinetButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitForOpenLoginPage();
        objLoginPage.loginUser(user);
        objMainPage.clickPersonalCabinetButton();
        PersonalCabinetPage objPersonalCabinetPage = new PersonalCabinetPage(driver);
        objPersonalCabinetPage.waitForOpenPersonalCabinetPage();
        objPersonalCabinetPage.clickConstructorButtonOnPersonalCabinetPage();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Главная страница не открылась", objMainPage.MAINPAGE_URL, currentUrl);
    }

    @DisplayName("Test switching from personal cabinet to the main page by logo")
    @Test
    public void followFromPersonalCabinetToMainPageByLogoTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickPersonalCabinetButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitForOpenLoginPage();
        objLoginPage.loginUser(user);
        objMainPage.clickPersonalCabinetButton();
        PersonalCabinetPage objPersonalCabinetPage = new PersonalCabinetPage(driver);
        objPersonalCabinetPage.waitForOpenPersonalCabinetPage();
        objPersonalCabinetPage.clickOnLogoOnPersonalCabinetPage();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Главная страница не открылась", objMainPage.MAINPAGE_URL, currentUrl);
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

