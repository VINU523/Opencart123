package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends BasePage{
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement mail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pwd;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement login;
	
	public void enterEmail(String email)
	{
		mail.sendKeys(email);
	}
	
	public void enterPassword(String password)
	{
		pwd.sendKeys(password);
	}
	public void clickOnLogin()
	{
		login.click();
	}

}
