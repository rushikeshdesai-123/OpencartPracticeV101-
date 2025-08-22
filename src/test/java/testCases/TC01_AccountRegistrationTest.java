package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC01_AccountRegistrationTest extends BaseClass{

	@Test(groups = {"sanity", "master"})
	public void verify_account_registration() throws InterruptedException {
		
		//putting log statements
		logger.info("******* Starting TC01_AccountRegistrationTest **********");
		
		try {
		// Here we create object of homepage class
		HomePage hp = new HomePage(driver);
		
		logger.info("click on my account");
		hp.click_myaccount();
		logger.info("click on register");
		hp.click_register();
		
		// Create object of AccountRegistration class
		AccountRegisterPage arp = new AccountRegisterPage(driver);
		
		logger.info("providing customer's details");
		arp.setfirstname(randomString().toUpperCase());  
		arp.setlastname(randomString().toUpperCase());
		arp.setemail(randomString() + "@gmail.com");
		arp.settelephone(randomNumber());
		
		// For password we have same password and confirmpassword. So we store it in one variable and then pass this variable
		String pwd = randomAlphanumeric();
		arp.setpassword(pwd);
		Thread.sleep(2000);
		arp.setconfirmpassword(pwd);
		arp.click_checkbox();
		arp.click_continue();
		Thread.sleep(2000);
		
		//Validation
		logger.info("Validating registration");
        String cfmmsg = arp.getconfirmationmsg();
        
        if(cfmmsg.equals("Your Account Has Been Created!")) {
        	
        	logger.info("Test passed");
        	Assert.assertTrue(true);
        }
        else {
        	
        	logger.error("Test failed");
			Assert.fail();
        }
        
 //       Assert.assertEquals(cfmmsg, "Your Account Has Been Created!!");
		
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("******* Finishing TC01_AccountRegistrationTest **********");
	}


}
