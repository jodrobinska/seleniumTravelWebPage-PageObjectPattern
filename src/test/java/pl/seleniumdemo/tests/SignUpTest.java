package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        //--- Sign Up
        //driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        //driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();


        //--- Filling the Form
        String lastName = "Oska";
        //--- Unikalny adres email
        int randomNumber = (int) (Math.random()*1000); //numer
        String email = "judit" + randomNumber + "@gmail.com"; //email


        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Judyta");
        signUpPage.setLasttName(lastName);
        signUpPage.setPhone("666111222");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Test123");
        signUpPage.confirmPasswordInput("Test123");
        signUpPage.signUp();

        /*
        //--- Filling the Form
        driver.findElement(By.name("firstname")).sendKeys("Judyta");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("666111222");
        driver.findElement(By.name("email")).sendKeys(email); // podajemy zmienną
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
         */

        /*
        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
         */

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        /*
        Assert.assertTrue(heading.getText().contains(lastName)); // czy heading zawiera nazwisko
        Assert.assertEquals(heading.getText(),"Hi, Judyta Oska"); // (aktualny ze strony, oczekiwany przez nas)
         */
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(),"Hi, Judyta Oska");

    }


    @Test
    public void signUpEmptyFormTest() {

        /*
        //--- Sign Up
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click(); // Sign Up na stronie głównej
         */
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        //driver.findElement(By.xpath("//button[text()=' Sign Up']")).click(); //Sign Up pod formularzem
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp(); //wywołujemy metodę 'SignUp'


        /*List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
         */
        List<String> errors = signUpPage.getErrors();

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

        /*
        // Sign Up
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
         */
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();


        // Unikalny adres email
        int randomNumber = (int) (Math.random()*1000); //numer
        String email = "judit" + randomNumber; // niezgodny format e-mail'a


        /*
        // Filling the Form
        driver.findElement(By.name("firstname")).sendKeys("Katarzyna");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("111222333");
        driver.findElement(By.name("email")).sendKeys(email); // podajemy zmienną
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
         */
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Judyta");
        signUpPage.setLasttName("Kowalska");
        signUpPage.setPhone("666111222");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Test123");
        signUpPage.confirmPasswordInput("Test123");
        signUpPage.signUp();


        /*List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
         */
        List<String> errors = signUpPage.getErrors();

        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));

        // można krócej
        //Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }

}
