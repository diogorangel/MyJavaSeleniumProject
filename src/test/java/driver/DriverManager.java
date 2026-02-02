package driver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    public static void waitSeconds(int seconds) {
        try {
            // Multiplicamos por 1000 porque o Java conta em milissegundos
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            // Caso ocorra um erro na interrupção da thread
            Thread.currentThread().interrupt();
            System.err.println("Erro durante a espera: " + e.getMessage());
        }
    }
    // Screenshot method
    public static void takeScreenshot(String name) throws IOException {
        File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("evidence/" + name + ".png"));
    }
}