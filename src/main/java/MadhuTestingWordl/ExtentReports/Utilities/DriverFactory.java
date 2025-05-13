package MadhuTestingWordl.ExtentReports.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static String getBrowserName() {
        String systemBrowser = System.getProperty("browser");
        return (systemBrowser != null && !systemBrowser.isEmpty())
                ? systemBrowser.toLowerCase()
                : getBrowserFromConfig();
    }

    private static String getBrowserFromConfig() {
        Properties props = new Properties();
        try (InputStream input = DriverFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                System.err.println("Could not find config.properties in resources.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty("browser", "chrome").toLowerCase();
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = getBrowserName();

            switch (browser) {
                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    driver.set(new EdgeDriver());
                    break;

                case "chrome-headless":
                    ChromeOptions headlessOptions = new ChromeOptions();
                    headlessOptions.addArguments("--headless=new"); // or "--headless" for older versions
                    headlessOptions.addArguments("--disable-gpu");
                    headlessOptions.addArguments("--window-size=1920,1080");
                    driver.set(new ChromeDriver(headlessOptions));
                    break;

                case "chrome":
                default:
                    driver.set(new ChromeDriver());
                    break;
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

