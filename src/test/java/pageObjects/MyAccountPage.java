package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

	public WebDriver driver;
	
	public MyAccountPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	// find webelements
	
	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement txt;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnk_logout;    // for Login DDT
	
	//Action methods
	
	public boolean disp_myAccount() {
		
		try {
		return txt.isDisplayed();
	}
		catch(Exception e) {
			
			return false;
		}
     }	
	
	public void click_logout() {
		
		lnk_logout.click();
	}
}
