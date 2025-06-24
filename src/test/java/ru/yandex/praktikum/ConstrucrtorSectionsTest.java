package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pageobject.MainPage;
import static org.junit.Assert.assertEquals;

public class ConstrucrtorSectionsTest {
    private WebDriver driver;
    private BasicUtils utils;

    @Before
    @Step("Method for launching a web driver")
    public void setUp() {
        utils = new BasicUtils();
        utils.startUp();
        driver = utils.getDriver();
    }
    @Description("When you navigate to the main page, this section is selected by default, so to test the navigation, you first navigate to the Sauces section and then test the ability to navigate to the Buns section.")
    @Step("The Bread section transition test")
    @Test
    public void breadTransitionTest(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickMenuSauces();
        objMainPage.clickMenuBread();

        utils.waitForCertainTime(500);

        String selectedMenu = objMainPage.getSelectedSection();
        assertEquals("Должна быть активна секция Булки", "Булки", selectedMenu);
    }

    @Step("The Souses section transition test")
    @Test
    public void sausesTransitionTest(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickMenuSauces();

        utils.waitForCertainTime(500);

        String selectedMenu = objMainPage.getSelectedSection();
        assertEquals("Должна быть активна секция Соусы", "Соусы", selectedMenu);
    }

    @Step("The fillings section transition test")
    @Test
    public void fillingsTransitionTest(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickMenuFillings();

        utils.waitForCertainTime(500);

        String selectedMenu = objMainPage.getSelectedSection();
        assertEquals("Должна быть активна секция Начинки", "Начинки", selectedMenu);
    }

    @After
    @Step("Method for completes the web driver process")
    public void tearDown() {
        utils.tearDown();
    }
}


