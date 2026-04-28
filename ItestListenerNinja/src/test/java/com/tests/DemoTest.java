package com.tests;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Listeners(DemoListenerFail.class)
public class DemoTest {

    public WebDriver driver;
    public WebDriverWait wait;

    private static final Logger log = LogManager.getLogger(DemoTest.class);

    @BeforeMethod
    public void beforeTest() {

        log.info("Launching browser in headless mode (Jenkins)");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); 
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        log.info("Setting waits");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        log.info("Opening application URL");
        driver.get("https://tutorialsninja.com/demo/index.php");
    }

    @Test
    public void loginTest() {

        log.info("Clicking My Account");

        WebElement myAccount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[@title='My Account']")
                )
        );
        myAccount.click();

        log.info("Clicking Login");

        WebElement login = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.linkText("Login")
                )
        );
        login.click();

        log.info("Entering email");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")))
                .sendKeys("jennysenthil123@gmail.com");

        log.info("Entering password");

        driver.findElement(By.id("input-password")).sendKeys("jenny");

        log.info("Clicking Login button");

        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@value='Login']")
                )
        );
        loginBtn.click();

        log.info("Validating login result");

        boolean isDisplayed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[normalize-space()='My Account']")
                )
        ).isDisplayed();

        Assert.assertTrue(isDisplayed);

        log.info("Login test completed successfully");
    }

    @AfterMethod
    public void afterTest() {
        log.info("Closing browser");
        if (driver != null) {
            driver.quit();
        }
    }
}