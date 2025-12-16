package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement login;

	public void SetEmail(String emailInput) {
		email.sendKeys(emailInput);
	}

	public void SetPasword(String paswInput) {
		password.sendKeys(paswInput);
	}

	public void Click_login() {
		login.click();
	}

}
