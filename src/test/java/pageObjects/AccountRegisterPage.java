package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegisterPage {

	public WebDriver driver;
	
	public AccountRegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// find the webelements
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txt_firstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txt_lastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txt_confirmpassword;
		
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chechbox;
		
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btn_continue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement txt_confirmationmsg;
	
	// Action methods
	
	public void setfirstname(String fname) {
		
		txt_firstName.sendKeys(fname);
	}
	
	public void setlastname(String lname) {
		
		txt_lastName.sendKeys(lname);
	}
	
	public void setemail(String email) {
		
		txt_email.sendKeys(email);
	}
	
	public void settelephone(String telephone) {
		
		txt_telephone.sendKeys(telephone);
	}
	
	public void setpassword(String pwd) {
		
		txt_password.sendKeys(pwd);
	}
	
	public void setconfirmpassword(String cfmpwd) {
		
		txt_confirmpassword.sendKeys(cfmpwd);
	}
	
	
	public void click_checkbox() {
		
		chechbox.click();
	}
	
	public void click_continue() {
		
		btn_continue.click();
	}
	
	public String getconfirmationmsg() {
		try {
		return (txt_confirmationmsg.getText());
		
	}
		catch(Exception e) {
			
			return (e.getMessage());
		}
		
}
	
}
