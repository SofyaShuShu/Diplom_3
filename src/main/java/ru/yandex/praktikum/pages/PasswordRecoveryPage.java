package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecoveryPage {
    private WebDriver driver;
    public static final String PASSWORDRECOVERYPAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор кнопки "Войти"
    private By entryButtonOnPasswordRecoveryPage = By.xpath(".//*[text()='Войти']");

    @Step("Click on entry button on password recovery page")
    public void clickEntryButtonOnPasswordRecoveryPage() {
        driver.findElement(entryButtonOnPasswordRecoveryPage).click();
    }
    @Step("Method for open password recovery page")
    public void openPasswordRecoveryPage(){
        driver.get(PASSWORDRECOVERYPAGE_URL);
    }
    @Step("Method for wait password recovery page")
    public void waitForOpenPasswordRecoveryPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(PASSWORDRECOVERYPAGE_URL));
    }
}
