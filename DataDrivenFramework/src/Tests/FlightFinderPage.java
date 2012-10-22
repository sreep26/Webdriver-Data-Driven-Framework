package Tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Lib.CommonFunc;
import Pages.LoginPage;

public class FlightFinderPage {

	private static FlightFinderPage flightFinderPage;
	private Properties properties;
	private CommonFunc commFunc;
	private WebDriver driver;
	
	//WebElements 
	
	private WebElement logoutLink;
	
	public static FlightFinderPage getFlightFinderPage(WebDriver driver){
		if(flightFinderPage == null){
			return new FlightFinderPage(driver);
		}
		
		return flightFinderPage;
		
	}
	
	private FlightFinderPage(WebDriver driver){ 
		this.driver = driver;
		properties = new Properties();
		commFunc = new CommonFunc(driver);
		
		try {
			properties.load(new FileInputStream( System.getProperty("user.dir")+"\\src\\CSV\\FlightFinderPage.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Initialize the WebElements 
		logoutLink = commFunc.getElement(properties.getProperty("logout"));
		
	}
	
	public Object Logout() {
		logoutLink.click();
		return LoginPage.getLoginPage(driver);
		
	}
}
