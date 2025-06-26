package ru.yandex.praktikum.pages;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    public static final String MAINPAGE_URL = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор кнопки "Личный кабинет"
    public By personalCabinetButton = By.xpath("//p[text()='Личный Кабинет']");

    //Локатор кнопки "Войти в аккаунт"
    private By logInToAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");

    //Локатор кнопки "Оформить заказ"
    private By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    //Локатор раздела "Булки"
    private By menuBread = By.xpath(".//span[text()='Булки']");

    //Локатор раздела "Начинки"
    private By menuFillings = By.xpath(".//span[text()='Начинки']");

    //Локатор раздела "Соусы"
    private By menuSauces = By.xpath(".//span[text()='Соусы']");

    //Локатор выбранного раздела "Булки"
    private By currentMenuBread = By.xpath("//div[contains(@class,'tab_type_current__2BEPc')]//span[text()='Булки']");

    //Локатор выбранного раздела "Соусы"
    private By currentMenuSauces = By.xpath("//div[contains(@class,'tab_type_current__2BEPc')]//span[text()='Соусы']");

    //Локатор выбранного раздела "Начинки"
    private By currentMenuFillings = By.xpath("//div[contains(@class,'tab_type_current__2BEPc')]//span[text()='Начинки']");

    //Локатор выбранного раздела конструктора
    private By currentSection = By.xpath(".//div[contains(@class,'tab_type_current__2BEPc')]");

    @Step("Method for open main page")
    public void openMainPage(){
        driver.get(MAINPAGE_URL);
    }
    @Step("Method for wait open main page")
    public void waitForOpenMainPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(MAINPAGE_URL));
    }

    @Step("Click on personal cabinet button")
    public void clickPersonalCabinetButton(){
        driver.findElement(personalCabinetButton).click();
    }

    @Step("Click on make order button")
    public void clickMakeOrderButton(){
        driver.findElement(makeOrderButton).click();
    }

    @Step("Click on log in to account button")
    public void clickLogInToAccountButton() {
        driver.findElement(logInToAccountButton).click();
    }

    @Step("Click on Bread section")
    public void clickMenuBread() {
        driver.findElement(menuBread).click();
    }

    @Step("Click on Sauces section")
    public void clickMenuSauces() {
         driver.findElement(menuSauces).click();
    }

    @Step("Click on Fillings section")
    public void clickMenuFillings() {
        driver.findElement(menuFillings).click();
    }

    @Description("Since the make order button only appears on the main page of an authorized user, the method is used to verify successful authorization.")
    @Step("Method for check the visibility of the makeOrderButton button")
    public boolean  isMakeOrderButtonVisible(){
    return driver.findElement(makeOrderButton).isDisplayed();
    }

    @Step("Method for check the visibility of the log in to account button")
    public boolean  isLogInToAccountButtonVisible(){
        return driver.findElement(logInToAccountButton).isDisplayed();
    }

    @Step("Method for determining selected section")
    public String getSelectedSection(){
        String selectedSectionName = driver.findElement(currentSection).getAttribute("textContent");
        return selectedSectionName;
    }

    @Step("Method for determining locator of selected section of constructor")
    public By getCurrentSectionLocator() {
        return currentSection;
    }

    @Step("Method for determining locator of make order button")
    public By getMakeOrderButtonLocator() {
    return makeOrderButton;
    }

    @Description("The text color of the selected menu section changes to \"#fff\" according to the CSS, and the method waits for the color to change in the selected section.")
    @Step("Method for waiting, that bread section is active")
    public void waitBreadSectionActive(){
        WebElement element = driver.findElement(currentMenuBread);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> {
            String color = element.getCssValue("color");
            return color.equals("rgba(255, 255, 255, 1)");
        });
    }

    @Description("The text color of the selected menu section changes to \"#fff\" according to the CSS, and the method waits for the color to change in the selected section.")
    @Step("Method for waiting, that souse section is active")
    public void waitSouseSectionActive(){
        WebElement element = driver.findElement(currentMenuSauces);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> {
            String color = element.getCssValue("color");
            return color.equals("rgba(255, 255, 255, 1)");
        });
    }

    @Description("The text color of the selected menu section changes to \"#fff\" according to the CSS, and the method waits for the color to change in the selected section.")
    @Step("Method for waiting, that fillings section is active")
    public void waitFillingsSectionActive(){
        WebElement element = driver.findElement(currentMenuFillings);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> {
            String color = element.getCssValue("color");
            return color.equals("rgba(255, 255, 255, 1)");
        });
    }
}