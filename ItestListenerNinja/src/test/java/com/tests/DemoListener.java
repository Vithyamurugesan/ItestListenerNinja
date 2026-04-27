package com.tests;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class DemoListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println(result.getName() + " test started");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test PASSED: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test FAILED: " + result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test SKIPPED: " + result.getName());
	}
}