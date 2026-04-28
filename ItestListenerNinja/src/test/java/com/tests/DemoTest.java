package com.tests;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
        log.info("Launching browser");
        driver = new ChromeDriver();

        log.info("Maximizing window");
        driver.manage().window().maximize();

        log.info("Setting waits");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Opening application URL");
        driver.get("https://tutorialsninja.com/demo/index.php");
    }

    @Test
    public void loginTest() {

        log.info("Clicking My Account");

        WebElement myAccount = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[normalize-space()='My Account']")
                )
        );
        myAccount.click();

        log.info("Clicking Login");

        WebElement login = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[normalize-space()='Login']")
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

        boolean isPresent = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//h2[normalize-space()='My Account']")
                )
        ).isDisplayed();

        Assert.assertTrue(isPresent);

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