package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginDDT extends BaseClass{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "datadriven")  // here data providers is not in same class. that is why we have to declare that
	public void verify_loginDDT(String email, String pwd, String expt) {  // receive data which data provider returns
		
		try {
		HomePage hp = new HomePage(driver);
		hp.click_myaccount();
		hp.click_login();
		
		LoginPage lp = new LoginPage(driver);
		lp.set_email(email);
		lp.set_password(pwd);
		lp.click_login();
		
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetpage = map.disp_myAccount();
		
		if(expt.equalsIgnoreCase("valid")) {
			
			if(targetpage==true) {
				
				map.click_logout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(expt.equalsIgnoreCase("invalid")) {
			
			if(targetpage==true) {
				
				Assert.assertTrue(false);
				map.click_logout();
			}
			else {
				
				Assert.assertTrue(true);
			}
		}
		
	}
		catch(Exception e) {
			
			Assert.fail();
		}
		
	}
	
}
