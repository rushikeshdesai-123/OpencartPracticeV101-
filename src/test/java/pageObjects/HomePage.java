package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	// for POM = 1st is constructor . We can use PageFactory for finding the webelement
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;  // initialize the driver
		PageFactory.initElements(driver, this);  
	}
	
	// Locate the webelements by using @findBy
	
	@FindBy(xpath = "//span[normalize-space()='My Account']") 
	WebElement lnk_myAccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnk_register;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")    // Locate login element
	WebElement lnk_login;
	
	// Action methods 
	// here we perrform actions on above webelements
	
	public void click_myaccount() {
		
		lnk_myAccount.click();
	}
	
	public void click_register() {
		
		lnk_register.click();
	}
	
	//here we click login
	public void click_login() {
		
		lnk_login.click();
	}
}
