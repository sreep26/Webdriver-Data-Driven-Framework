package Pages;
import org.openqa.selenium.WebDriver;


public class Navigate {

	WebDriver driver;
    static String baseURL;

    public static String LOGIN_PAGE;
	

    public Navigate(WebDriver driver, String baseURL)
    {
        this.driver = driver;
        Navigate.baseURL = baseURL;
        LOGIN_PAGE = baseURL+"mercuryreservation.php";

    }
    public Object to(String URL)
    {
        driver.navigate().to(URL);
        if (URL == LOGIN_PAGE)
            return LoginPage.getLoginPage(driver);

        return new Object();
    }
}
