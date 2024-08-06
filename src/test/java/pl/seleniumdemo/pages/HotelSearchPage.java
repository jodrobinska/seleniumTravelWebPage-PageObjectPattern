package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchPage {

    // 1.PageObjectPattern
    @FindBy(xpath ="//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement hotelMatch;

    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;



    // 2.Page Factory
    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }



    // 1.Page Object Pattern
    public void setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        hotelMatch.click();
    }

    public void setDates(String checkin, String checkout) {
        checkinInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
    }

    public void setTravellers() {
        travellersInput.click();
        adultPlusBtn.click();
        childPlusBtn.click();
    }

    public void performSearch() {
        searchButton.click();
    }



/*
    // set city name
        driver.findElement(By.xpath()).click();
        driver.findElement(By.xpath()).sendKeys("Dubai");

    Duration duration = Duration.ofSeconds(5);
    WebDriverWait wait = new WebDriverWait(driver,duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-match' and text()='Dubai']")));
        driver.findElement(By.xpath()).click();

    // jesli wpisujemy date
    //------ set check in
        driver.findElement(By.name("checkin")).sendKeys("20/07/2025");
    //------ set check out
        driver.findElement(By.name("checkout")).sendKeys("28/07/2025");


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
 */

}
