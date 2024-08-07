package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {



    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("20/07/2025","28/07/2025");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

    /*    // set city name
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
     */


        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();

        /*
        //--- list of hotels
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b")).stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
        System.out.println(hotelNames.size());
        hotelNames.forEach(el -> System.out.println(el));
         */

        //--- Asercje
        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel"); // Expected - wpisany przeze mnie; Actual - znajdujący się na stronie
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3),"Hyatt Regency Perth");

    }


    @Test
    public void searchHotelWithoutNameTest() {

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        //------ set check in
        driver.findElement(By.name("checkin")).sendKeys("05/08/2024");
        //------ set check out
        driver.findElement(By.name("checkout")).sendKeys("09/08/2024");

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
