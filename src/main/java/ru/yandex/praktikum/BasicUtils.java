package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class BasicUtils {
    private WebDriver driver;
    private BrowserFactory browserFactory;

    public BasicUtils() {
        this.browserFactory = new BrowserFactory();
    }
    @Step("Method for for selecting the browser (Chrome or Yandex Browser)")
    public String chooseBrowser() {
          Scanner scanner = new Scanner(System.in);
         System.out.println("Введите название браузера (Chrome или Yandex) или нажмите Enter для использования Яндекс.браузера: ");
         String browser = scanner.nextLine().toLowerCase();
        // String browser = "yandex";

        if (browser.isEmpty()) {
            browser = "yandex";
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

    @Step("Method for waiting for a certain time")
    public void waitForCertainTime(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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