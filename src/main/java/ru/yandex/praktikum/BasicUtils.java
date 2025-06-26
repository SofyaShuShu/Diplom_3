package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class BasicUtils {
    private WebDriver driver;
    private BrowserFactory browserFactory;

    public BasicUtils() {
        this.browserFactory = new BrowserFactory();
    }
    @Step("Method for selecting the browser automatically based on system settings")
    public String chooseBrowser() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        String[] supportedBrowsers = {"chrome", "yandex"};

        boolean isSupported = Arrays.asList(supportedBrowsers).contains(browser);

        if (!isSupported) {
            System.out.println("Браузер не поддерживается, тесты будут выполнены Chrome по умолчанию.");
            browser = "chrome";
        }

        return browser;
    }


    @Step("Method for launching a web driver and configuring a browser window")
        public void startUp() {
        String browser = chooseBrowser();
        driver = browserFactory.getWebDriver(browser);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Step("Method for completes the web driver process")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Step("Method for getting the current web driver")
    public WebDriver getDriver() {
        return driver;
    }
}