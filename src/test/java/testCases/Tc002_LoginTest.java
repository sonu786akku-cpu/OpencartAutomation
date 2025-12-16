package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class Tc002_LoginTest extends BaseClass {

	@Test(groups = { "Sanity", "Master" })
	public void LoginTest() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.MyAccount();
		hp.ClickOnLogin();

		LoginPage lp = new LoginPage(driver);
		lp.SetEmail(p.getProperty("email"));
		lp.SetPasword(p.getProperty("pasword"));
		lp.Click_login();
		MyAccountPage Myaccount = new MyAccountPage(driver);
		boolean targetPage = Myaccount.Verify_Myaccount();
		Assert.assertTrue(targetPage);

	}

}
