package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
	WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement FirstName;
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement LastName;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email;
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement Telephone;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement paswd;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement confPswd;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continu;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement radio;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement sucesMsg;

	public void txt_FirstName(String fname) {
		FirstName.sendKeys(fname);
	}

	public void txt_LastName(String lname) {
		LastName.sendKeys(lname);
	}

	public void txt_Email(String Email) {
		email.sendKeys(Email);
	}

	public void txt_Telephone(String number) {
		Telephone.sendKeys(number);
	}

	public void txt_Password(String pas) {
		paswd.sendKeys(pas);
	}

	public void txt_ConfirmPassword(String pas) {
		confPswd.sendKeys(pas);
	}

	public void btn_radio() {
		radio.click();
	}

	public void btn_Continue() {
		continu.click();
	}

	public String GetMessage() {
		String Text = sucesMsg.getText();
		return Text;
	}

}
