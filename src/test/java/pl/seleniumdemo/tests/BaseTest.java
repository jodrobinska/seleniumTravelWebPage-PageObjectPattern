package pl.seleniumdemo.tests;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "ie":
                return new InternetExplorerDriver();
            default:
                throw new InvalidArgumentException("Invalid browser name");
        }
    }


    protected WebDriver driver; // wszystkie metody klasy mają dostęp do tego pola

    // before
    @BeforeMethod
    public void setup() {
        driver = getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

    // after
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
