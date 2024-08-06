import org.testng.annotations.Test;

public class AAGoogleTest {

    @Test
    public void googleSearchTest() {
        AAGoogleHomePage googleHomePage = new AAGoogleHomePage();
        googleHomePage.searchInGoogle("Selenium");
    }

    @Test
    public void googleSearchTest2() {
        AAGoogleHomePage googleHomePage = new AAGoogleHomePage();
        googleHomePage.searchInGoogle("Selenium");
    }

    @Test
    public void googleSearchTest3() {
        AAGoogleHomePage googleHomePage = new AAGoogleHomePage();
        googleHomePage.searchInGoogle("Selenium");
    }



}
