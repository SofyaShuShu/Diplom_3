package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BrowserFactory {
    @Step("Factory method for Chrome and Yandex Browser")
    public WebDriver getWebDriver(String browserName){
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "yandex":
                // Путь к WebDriver для Яндекс.Браузера
                System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver-25.4.0.1973-win64/yandexdriver.exe");

                // Путь к Яндекс.Браузеру
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:/Program Files (x86)/Yandex/YandexBrowser/Application/browser.exe");

                driver = new ChromeDriver(options);
                break;
            default:
                driver = new ChromeDriver();
        }
        return driver;
    }
}