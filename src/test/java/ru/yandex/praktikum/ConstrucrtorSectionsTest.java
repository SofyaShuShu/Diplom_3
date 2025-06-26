package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pages.MainPage;

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
    @DisplayName("The Bread section transition test")
    @Test
    public void breadTransitionTest(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickMenuSauces();
        objMainPage.clickMenuBread();

        objMainPage.waitBreadSectionActive();

        String selectedMenu = objMainPage.getSelectedSection();
        assertEquals("Должна быть активна секция Булки", "Булки", selectedMenu);
    }

    @DisplayName("The Souses section transition test")
    @Test
    public void sausesTransitionTest(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickMenuSauces();

        objMainPage.waitSouseSectionActive();

        String selectedMenu = objMainPage.getSelectedSection();
        assertEquals("Должна быть активна секция Соусы", "Соусы", selectedMenu);
    }

    @DisplayName("The fillings section transition test")
    @Test
    public void fillingsTransitionTest(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickMenuFillings();

        objMainPage.waitFillingsSectionActive();

        String selectedMenu = objMainPage.getSelectedSection();
        assertEquals("Должна быть активна секция Начинки", "Начинки", selectedMenu);
    }

    @After
    @Step("Method for completes the web driver process")
    public void tearDown() {
        utils.tearDown();
    }
}


