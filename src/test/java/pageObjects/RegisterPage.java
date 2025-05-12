package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
	
	public RegisterPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") 
	WebElement firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']") 
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='input-confirm']") 
	WebElement confirmpassword;
	
	@FindBy(xpath="//input[@name='agree']") 
	WebElement confirmcheckbox;
	
	@FindBy(xpath="//input[@value='Continue']") 
	WebElement continueButton;
	
	public  void enterFirstname(String name)
	{
		firstname.sendKeys(name);
	}
	public  void enterLastname(String name)
	{
		lastName.sendKeys(name);
	}
	
	public  void enterEmail(String name)
	{
		email.sendKeys(name);
	}	
	
	public  void enterTelephone(String name)
	{
		telephone.sendKeys(name);
	}
	
	public  void enterPassword(String name)
	{
		password.sendKeys(name);
		
	}
	
	public  void enterConfirmPassword(String name)
	{
		confirmpassword.sendKeys(name);
	}	
	
	public void clickOnConfirmcheckbox()
	{
		confirmcheckbox.click();
	}
	
	public void clickonContinueButton()
	{
		continueButton.click();
	}

}
