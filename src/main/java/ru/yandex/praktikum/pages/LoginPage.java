package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.user.User;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    public static final String LOGINPAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор поля "Email"
    public By emailField =  By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");

    //Локатор поля "Пароль"
    public By passwordField = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");

    //Локатор кнопки "Войти"
    private By entryButton = By.xpath(".//button[text() = 'Войти']");

    //Локатор кнопки "Зарегистрироваться"
    public By registerButton = By.xpath(".//*[text()='Зарегистрироваться']");

    //Локатор кнопки "Восстановить пароль"
    public By recoveryPasswordButton = By.xpath(".//*[text()='Восстановить пароль']");

    @Step("Method for open login page")
    public void openLoginPage(){
        driver.get(LOGINPAGE_URL);
    }
    @Step("Method for wait open login page")
    public void waitForOpenLoginPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(LOGINPAGE_URL));
    }

    @Step("Method for fill login form")
    public void fillLoginForm(User user) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
       driver.findElement(emailField).click();
       driver.findElement(emailField).sendKeys(user.getEmail());

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    @Step("Method for click on the login button")
    public void clickLoginButton() {
        driver.findElement(entryButton).click();
    }

    @Step("Method for fill login form and click on the login button")
    public void loginUser(User user){
        fillLoginForm(user);
        clickLoginButton();
    }

    @Step("Method for click on the register button")
    public void clickRegisterButtonOnLoginPage() {
        driver.findElement(registerButton).click();
    }

    @Step("Method for click on the recovery password button")
    public void clickRecoveryPasswordButton() {
        driver.findElement(recoveryPasswordButton).click();
    }
}


