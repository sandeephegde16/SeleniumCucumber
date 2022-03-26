package pageObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.BasePage;

public class LoginPage extends BasePage{
	
	@FindBy(xpath = "usernameXapth")
	private WebElement username;
	
	protected WebElement getUserName() {
		return username;
	}
	
	@FindBy(xpath = "passwordXpath")
	private WebElement password;
	
	protected WebElement getPassword() {
		return password;
	}

}
