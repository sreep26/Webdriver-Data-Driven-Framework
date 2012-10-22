package Lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonFunc {
	private WebDriver driver;
	
	public CommonFunc(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElement(String elementIdentfier){
		String indentifierType;
		String identifierTxt = null;
		WebElement webElement = null;
		
		
		if (elementIdentfier.split(",").length > 1) {
			// path is other than id
			indentifierType = elementIdentfier.split(",")[0];
			identifierTxt = elementIdentfier.split(",")[1];
		} else {
			// contains id only.
			indentifierType = "id";

		}
		
		if(indentifierType.equalsIgnoreCase("name")){
			webElement = driver.findElement(By.name(identifierTxt));
		}else if (indentifierType.equalsIgnoreCase("id")) {
			 webElement = driver.findElement(By.id(identifierTxt));
		}else if (indentifierType.equalsIgnoreCase("linktext")){
			webElement = driver.findElement(By.linkText(identifierTxt));
		}
		
		return webElement;
		
	}
	

}
