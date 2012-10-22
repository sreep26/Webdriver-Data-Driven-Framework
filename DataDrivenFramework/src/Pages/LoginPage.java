package Pages;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Lib.CommonFunc;

public class LoginPage {

	
	private static LoginPage loginPage; 
	
	public Properties properties;
	public WebDriver driver;
	private CommonFunc commonFunc;
	
	
	//WebElements 
	private WebElement usernameEditbox;
	private WebElement passwordEditbox;
	
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
		
	}
	
	
	public void login(String Username, String Password) {
		// driver = new InternetExplorerDriver();
		
		usernameEditbox.sendKeys(Username);
		passwordEditbox.sendKeys(Password);
		
	}
}
