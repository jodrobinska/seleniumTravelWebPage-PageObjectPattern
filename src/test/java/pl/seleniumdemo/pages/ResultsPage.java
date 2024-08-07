package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage {

    // 1.PageObjectPattern
    @FindBy(xpath = "//h4[contains(@class,'list_title')]//b")
    private List<WebElement> hotelList;

    @FindBy(xpath = "//h2[text()='No Results Found']")
    public WebElement resultHeading;

    // 2.Page Factory
    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }


    // 1.PageObjectPattern
    public List<String> getHotelNames() {
        return hotelList.stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
    }

    public String getHeadingText() {
        return resultHeading.getText();
    }
}
