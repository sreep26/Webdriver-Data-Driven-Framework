package Pages;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Lib.CommonFunc;
import Tests.FlightFinderPage;

public class LoginPage {

	
	private static LoginPage loginPage; 
	private WebDriver driver;
	
	private Properties properties;
	private CommonFunc commonFunc;
	
	
	//WebElements 
	private WebElement usernameEditbox;
	private WebElement passwordEditbox;
	private WebElement submitButton;
	
	public static LoginPage getLoginPage(WebDriver driver){
	
		if(loginPage == null){
			return new LoginPage(driver);
		}else{
			return loginPage;
		}
	}
	
	private LoginPage(WebDriver driver) {
		
		this.driver = driver;
		properties = new Properties();
		commonFunc = new CommonFunc(driver);
		
		try {
			properties.load(new FileInputStream( System.getProperty("user.dir")+"\\src\\CSV\\LoginPage.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		usernameEditbox = commonFunc.getElement((String)properties.get("username"));
		passwordEditbox = commonFunc.getElement((String)properties.get("password"));
		submitButton	= commonFunc.getElement((String)properties.get("submit"));
		
	}
	
	/***
	 * @return FlightFinder Page Object
	 * @param Username - username to login to site
	 * @param Password - Password to login to site.
	 */
	public Object login(String Username, String Password) {
		// driver = new InternetExplorerDriver();
		
		usernameEditbox.sendKeys(Username);
		passwordEditbox.sendKeys(Password);
		submitButton.click();
		if (driver.findElements(By.xpath("//form[@name='findflight']/table/tbody/tr[1]")).size() != 0 ) {
			return FlightFinderPage.getFlightFinderPage(driver);
		}
			return getLoginPage(driver);
			
	}
}
