package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.DataProviders;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class Tc_LoginDDT extends BaseClass{
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = "DDT")

	public void loginDDT(String email, String pwd, String exp) {
		try {
	HomePage hp = new HomePage(driver);
	hp.MyAccount();
	hp.ClickOnLogin();

	LoginPage lp = new LoginPage(driver);
	lp.SetEmail(email);
	lp.SetPasword(pwd);
	lp.Click_login();
	MyAccountPage Myaccount = new MyAccountPage(driver);
	boolean targetPage = Myaccount.Verify_Myaccount();
	if(exp.equalsIgnoreCase("valid")) {
		if(targetPage==true) {

			Myaccount.click_logout();
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	if(exp.equalsIgnoreCase("Invalid")) {
		if(targetPage==true) {
			Myaccount.click_logout();
			Assert.assertTrue(false);
		}
			
		}else {
			Assert.assertTrue(true);
		}
	}

	catch (Exception e) {
		Assert.fail();

	}
}
}
