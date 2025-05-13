package MadhuTestingWorld.ExtentReports.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import MadhuTestingWordl.ExtentReports.Utilities.DriverFactory;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Retry up to 2 times

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            DriverFactory.quitDriver();    // Quit current browser session
            DriverFactory.getDriver();  
            return true; // Will re-run the test
        }
        return false;
    }
}
