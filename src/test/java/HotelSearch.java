import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

        // jesli wpisujemy date
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

        //--- travellers
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();

        //--- search
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        //--- list of hotels
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b")).stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
        System.out.println(hotelNames.size());
        hotelNames.forEach(el -> System.out.println(el));

        //--- Asercje
        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

    }


    @Test
    public void searchHotelWithoutName() {

        WebDriver driver = getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");


        //------ set check in
        driver.findElement(By.name("checkin")).sendKeys("05/08/2024");
        //------ set check out
        driver.findElement(By.name("checkout")).sendKeys("09/08/2024");

        // jeśli korzystamy z kalendarza
        //--- check in
        driver.findElement(By.name("checkin")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());

        //--- check out
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='31']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());

        //--- travellers
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();

        //--- search
        driver.findElement(By.xpath("//button[text()=' Search']")).click();


        WebElement noResultHeading = driver.findElement(By.xpath("//h2[text()='No Results Found']"));

        //--- Asercje
        Assert.assertTrue(noResultHeading.isDisplayed());
        Assert.assertEquals(noResultHeading.getText(),"No Results Found");


    }

}
