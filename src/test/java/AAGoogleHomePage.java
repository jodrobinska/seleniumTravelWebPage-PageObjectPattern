import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AAGoogleHomePage {

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(name = "btnk")
    private WebElement searchButton;


    public void searchInGoogle(String phrase) {
        searchInput.sendKeys(phrase);
        searchButton.click();
    }

}
