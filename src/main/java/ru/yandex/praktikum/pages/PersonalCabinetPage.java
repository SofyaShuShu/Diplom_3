package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalCabinetPage {
    private WebDriver driver;
    public static final String PERSONALCABINETPAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    public PersonalCabinetPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор кнопки "Конструктор"
    private By constructorButtonOnPersonalCabinetPage = By.xpath(".//*[text()='Конструктор']");

    //Локатор логотипа
    private By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    //Локатор кнопки «Выход»
    private By exitButton = By.xpath(".//*[text()='Выход']");

    @Step("Click on constructor button on personal cabinet page")
    public void clickConstructorButtonOnPersonalCabinetPage() {
        driver.findElement(constructorButtonOnPersonalCabinetPage).click();
    }

    @Step("Click on logo on personal cabinet page")
    public void clickOnLogoOnPersonalCabinetPage() {
        driver.findElement(logo).click();
    }
    @Step("Click on exit button on personal cabinet page")
    public void clickOnExitButtonOnPersonalCabinetPage() {
        driver.findElement(exitButton).click();
    }
    @Step("Method for open personal cabinet page")
    public void openPersonalCabinetPage(){
        driver.get(PERSONALCABINETPAGE_URL);
    }
    @Step("Method for wait personal cabinet page")
    public void waitForOpenPersonalCabinetPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(PERSONALCABINETPAGE_URL));
    }
}
