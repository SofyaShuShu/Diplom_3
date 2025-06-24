package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
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

    @Step("Test follow on personal cabinet for authorization user")
    @Test
    public void followOnPersonalCabinetForAuthorizationUser() {
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

    @Step("Test follow on personal cabinet for unauthorized user")
    @Test
    public void followOnPersonalCabinetForUnauthorizedUser() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickPersonalCabinetButton();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.waitForOpenLoginPage();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Страница авторизации не открылась", objLoginPage.LOGINPAGE_URL, currentUrl);
    }

    @Description("Constructor is located on the main page")
    @Step("Test switching from personal cabinet to the constructor")
    @Test
    public void followFromPersonalCabinetToConstructor() {
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

    @Step("Test switching from personal cabinet to the main page by logo")
    @Test
    public void followFromPersonalCabinetToMainPageByLogo() {
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

