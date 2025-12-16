package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {


	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement My_accnt;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement Register;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement login_btn;

	public void ClickOnLogin() {
		login_btn.click();
	}

	public void MyAccount() {
		My_accnt.click();
	}
	public void register() {
		Register.click();
	}

}
