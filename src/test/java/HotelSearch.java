import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HotelSearch {

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

    @Test
    public void searchHotel() {

        WebDriver driver = getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        // set city name
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");

        Duration duration = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver,duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-match' and text()='Dubai']")));
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();

        //------ set check in
        driver.findElement(By.name("checkin")).sendKeys("20/07/2025");
        //------ set check out
        driver.findElement(By.name("checkout")).sendKeys("28/07/2025");

        // jeśli korzystamy z kalendarza
        //--- check in
        driver.findElement(By.name("checkin")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());

        //--- check out
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='31']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());



    }

}
