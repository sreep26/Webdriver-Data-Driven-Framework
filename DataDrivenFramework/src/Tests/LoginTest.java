package Tests;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Lib.Excel;
import Pages.LoginPage;
import Pages.Navigate;

public class LoginTest {

	private WebDriver driver;
	private String baseURL= "http://newtours.demoaut.com/";
		
	
	@BeforeClass
	public void setup() {
		driver = new FirefoxDriver();
		
	}

	//@Test
	public void loginLogout(){

		System.out.println( System.getProperty("user.dir"));
		LoginPage loginPage;
		Navigate navigate = new Navigate(driver, baseURL);
		
		loginPage = (LoginPage) navigate.to(Navigate.LOGIN_PAGE);
		
		Excel data = new Excel("smokeTest.xls");
		HashMap< String, String> userdata = data.getRow("validLogin");
		String username = userdata.get("UserName");
		String password = userdata.get("Password");
		FlightFinderPage flightFinderPage = (FlightFinderPage)loginPage.login("testuser33", "password123");
		loginPage = (LoginPage)flightFinderPage.Logout();
		
		
	}
	
	@DataProvider(name = "loginData")
	public Object[][] loginData() {
		Excel data = new Excel("smokeTest.xls");
		return data.getMultipleRows("negativeLogin");
	}
	
	@Test(dataProvider="loginData")
	public void login(String Username, String Password, String errMsg){
		System.out.println(Username);
	}
}
