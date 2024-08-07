package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    //--- Filling the Form
    @FindBy(name = "firstname")
    private WebElement firstNameInput;      //driver.findElement(By.name("firstname")).sendKeys("Judyta");

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    // SignUp Empty Form
    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;


    //--- potrzebne do Page Factory
    public SignUpPage (WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    //--- Filling the form - metody
    public void setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void setLasttName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void setPhone(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void confirmPasswordInput(String password) {
        confirmPasswordInput.sendKeys(password);
    }

    public void signUp() {
        signUpButton.click();
    }

    public List<String> getErrors() {
        return   errors.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        //List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
