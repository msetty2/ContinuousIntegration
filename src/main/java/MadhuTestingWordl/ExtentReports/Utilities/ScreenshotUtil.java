package MadhuTestingWordl.ExtentReports.Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String path = "screenshots/" + testName + "_" + UUID.randomUUID() + ".png";
            Files.createDirectories(Paths.get("screenshots"));
            File destination = new File(path);
            Files.copy(source.toPath(), destination.toPath());
            return path;
        } catch (Exception e) {
            return "Failed to capture screenshot: " + e.getMessage();
        }
    }
}
