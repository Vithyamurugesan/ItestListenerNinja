package com.tests;

import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Listeners(DemoListenerFail.class)
public class DemoTest {

    public WebDriver driver;


    private static final Logger log = LogManager.getLogger(DemoTest.class);

    @BeforeMethod
    public void beforeTest() {
        log.info("Launching browser");
        driver = new ChromeDriver();

        log.info("Maximizing window");
        driver.manage().window().maximize();

        log.info("Setting implicit wait");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        log.info("Opening application URL");
        driver.get("https://tutorialsninja.com/demo/index.php");
    }

    @Test
    public void loginTest() {
        log.info("Clicking My Account");
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();

        log.info("Clicking Login");
        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

        log.info("Entering email");
        driver.findElement(By.id("input-email")).sendKeys("jennysenthil123@gmail.com");

        log.info("Entering password");
        driver.findElement(By.id("input-password")).sendKeys("jenny");

        log.info("Clicking Login button");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        log.info("Validating login result");
        Assert.assertTrue(driver.getPageSource().contains("My Account"));

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