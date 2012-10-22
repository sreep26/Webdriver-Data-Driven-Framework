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
	public void validLogin(){
		
		System.out.println( System.getProperty("user.dir"));
		Navigate navigate = new Navigate(driver, baseURL);
		LoginPage loginPage = (LoginPage) navigate.to(Navigate.LOGIN_PAGE);
		loginPage.login("testuser33", "password123");
		
		
	}
}
