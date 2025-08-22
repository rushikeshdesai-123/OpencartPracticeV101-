package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass{

	// Validate login
	@Test(groups = {"regression", "master"})
	public void Verify_login() {
		
		logger.info("********** Starting TC02_LoginTest ********");
		
		try {
		// create object of homepage
		HomePage hp = new HomePage(driver);
		
		hp.click_myaccount();
		hp.click_login();
		
		// create object of login page
		LoginPage lP = new LoginPage(driver);
		
		lP.set_email(p.getProperty("email"));   // read value from config.properties file
		lP.set_password(p.getProperty("password"));
		logger.info("********** click on login ********");
		lP.click_login();
		
		//create object of myaccount page
		MyAccountPage map = new MyAccountPage(driver);
		
		boolean targetpage = map.disp_myAccount();
		Assert.assertTrue(targetpage);		
		}
		catch(Exception e) {
		
			Assert.fail();
		}
		
		logger.info("********** Finishing TC02_LoginTest ********");
	}
	
}

