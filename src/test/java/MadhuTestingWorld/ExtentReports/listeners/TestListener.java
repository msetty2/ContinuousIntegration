package MadhuTestingWorld.ExtentReports.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import MadhuTestingWordl.ExtentReports.Utilities.DriverFactory;
import MadhuTestingWordl.ExtentReports.Utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    private ExtentReports extent;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Automation Engineer");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getDescription();
        if (testName == null || testName.trim().isEmpty()) {
            testName = result.getMethod().getMethodName(); // fallback
        }
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName() + "_pass");
        test.get().pass("Test passed");
        test.get().addScreenCaptureFromPath(screenshotPath);
        DriverFactory.quitDriver();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName() + "_fail");
        test.get().fail(result.getThrowable());
        test.get().addScreenCaptureFromPath(screenshotPath);
        DriverFactory.quitDriver();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
