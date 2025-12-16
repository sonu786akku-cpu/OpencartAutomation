package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement My_acc;
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout;

	public boolean Verify_Myaccount() {

		try {
			return (My_acc.isDisplayed());
		} catch (Exception e) {
			return false;
		}

	}

	public void click_logout() {
		logout.click();
	}

}
