package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.Navigate;

public class LoginTest {

	private WebDriver driver;
	private String baseURL= "http://newtours.demoaut.com/";
		
	
	@BeforeClass
	public void setup() {
		driver = new FirefoxDriver();
		
	}
	@Test
	public void loginLogout(){

		System.out.println( System.getProperty("user.dir"));
		LoginPage loginPage;
		Navigate navigate = new Navigate(driver, baseURL);
		loginPage = (LoginPage) navigate.to(Navigate.LOGIN_PAGE);
		FlightFinderPage flightFinderPage = (FlightFinderPage)loginPage.login("testuser33", "password123");
		loginPage = (LoginPage)flightFinderPage.Logout();
		
		
	}
}
