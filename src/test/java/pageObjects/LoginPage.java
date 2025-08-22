package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// find webelements
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btn_login;
	
	//Action methods
	
	public void set_email(String email) {
		
		txt_email.sendKeys(email);
	}
	
	public void set_password(String pwd) {
		
		txt_password.sendKeys(pwd);
	}
	
	public void click_login() {
		
		btn_login.click();
	}
}
