package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.UserUtils.User;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    public final String REGISTERPAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор поля "Имя"
    public By nameField = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");

    //Локатор поля "Email"
    public By emailField = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");

    //Локатор поля "Пароль"
    public By passwordField = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");

    //Локатор сообщения об ошибке при неверном пароле
    public By incorrectPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");

    //Локатор кнопки "Зарегистрироваться"
    private By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    //После регистрации идет редирект на страницу логина, где поля входа уже предзаполнены, надо только кликнуть на "Вход"

    //Локатор кнопки "Войти"
    private By enterButton = By.xpath(".//*[text()='Войти']");

    @Step("Method for fill registration form")
    public void fillRegistrationForm(User user) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(user.getName());

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(user.getEmail());

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    @Step("Method for click on registration button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Method for fill registration form and click on registration button")
    public void registerUser(User user){
        fillRegistrationForm(user);
        clickRegisterButton();
    }
    @Step("Method for click on enter button")
    public void clickEnterButtonOnRegisterPage() {
        driver.findElement(enterButton).click();
    }

    @Step("Method for open registration page")
    public void openRegisterPage(){
        driver.get(REGISTERPAGE_URL);
    }
    @Step("Method for wait registration page")
    public void waitForOpenRegisterPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(REGISTERPAGE_URL));
    }

    @Step("Method for wait open password error message")
    public void waitForOpenPasswordErrorMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordMessage));
    }

    @Step("Method for get text of password error message")
    public String getTextOfPasswordErrorMessage(){
       String passwordMessage = driver.findElement(incorrectPasswordMessage).getText();
        return passwordMessage;
    }
}

