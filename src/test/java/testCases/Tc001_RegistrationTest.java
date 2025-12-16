package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class Tc001_RegistrationTest extends BaseClass {
	// WebDriver driver;


	@Test(groups = { "Regression", "Master" })

	public void RegistrationTest() {
		logger.info("***Testing is started***");
		try {
		HomePage hp = new HomePage(driver);

		hp.MyAccount();
		hp.register();
		RegistrationPage reg = new RegistrationPage(driver);
		reg.txt_FirstName(RandomAlpha());
		reg.txt_LastName(RandomAlpha());
		reg.txt_Email(randomAlphaNumeric() + "@gmail.com");
		reg.txt_Telephone(randomNumber());
		String abc = randomAlphaNumeric();
		reg.txt_Password(abc);
		reg.txt_ConfirmPassword(abc);
		reg.btn_radio();
		reg.btn_Continue();
		String confmsg = reg.GetMessage();
		// Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		if (confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		} else {
			logger.error("error");
			logger.debug("debugging");
			Assert.assertTrue(false);
		}
	} catch (Exception e) {

		Assert.fail();
	}
	logger.info("test is finished");
	}

}
