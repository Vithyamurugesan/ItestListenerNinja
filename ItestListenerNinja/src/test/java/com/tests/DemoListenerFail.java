package com.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class DemoListenerFail implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println(result.getName() + " test started");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test PASSED: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test FAILED: " + result.getName());

        Object testClass = result.getInstance();
        WebDriver driver = ((DemoTest) testClass).driver;

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String dirPath = System.getProperty("user.dir") + "/screenshots/";
            new File(dirPath).mkdirs(); // create folder if not exists

            String path = dirPath + result.getName() + "_" + System.currentTimeMillis() + ".png";

            FileUtils.copyFile(source, new File(path));

            System.out.println("Screenshot saved at: " + path);

        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test SKIPPED: " + result.getName());
    }
}