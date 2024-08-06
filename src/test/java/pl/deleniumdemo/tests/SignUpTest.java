package pl.deleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTest extends BaseTest {



    @Test
    public void signUpTest() {

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); // oczekiwanie na elementy

        // Sign Up
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        // Filling the Form
        String lastName = "Oska";

        // Unikalny adres email
        int randomNumber = (int) (Math.random()*1000); //numer
        String email = "judit" + randomNumber + "@gmail.com"; //email

        // Filling the Form
        driver.findElement(By.name("firstname")).sendKeys("Judyta");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("666111222");
        driver.findElement(By.name("email")).sendKeys(email); // podajemy zmienną
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(heading.getText().contains(lastName)); // czy heading zawiera nazwisko
        Assert.assertEquals(heading.getText(),"Hi, Judyta Oska"); // (aktualny ze strony, oczekiwany przez nas)

    }


    @Test
    public void signUpEmptyFormTest() {

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); // oczekiwanie na elementy

        // Sign Up
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click(); // Sign Up na stronie głównej

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click(); //Sign Up pod formularzem

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();

    }


    @Test
    public void signUpInvalidEmailTest() {

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); // oczekiwanie na elementy

        // Sign Up
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        // Filling the Form
        String lastName = "Kowalska";

        // Unikalny adres email
        int randomNumber = (int) (Math.random()*1000); //numer
        String email = "judit" + randomNumber; // niezgodny format e-mail'a

        // Filling the Form
        driver.findElement(By.name("firstname")).sendKeys("Katarzyna");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("111222333");
        driver.findElement(By.name("email")).sendKeys(email); // podajemy zmienną
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));


    }

}
