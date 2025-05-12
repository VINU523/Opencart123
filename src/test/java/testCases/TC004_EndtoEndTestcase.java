package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.RegisterPage;
import testBase.Baseclass;

public class TC004_EndtoEndTestcase extends Baseclass{
	
	@Test
	public void testEndtoEnd()
	{
		HomePage hp=new HomePage(driver);
		hp.clickOnMyAccount();
		hp.clickOnRegister();
		
		RegisterPage rp=new RegisterPage(driver);
		rp.enterFirstname(randomString());
		rp.enterLastname(randomString());		
		rp.enterEmail(randomString()+"@gmail.com");
		rp.enterTelephone(randomNumbers());
		String pwd=randomAlphaNumberic();
		rp.enterPassword(pwd);
		rp.enterConfirmPassword(pwd);
		rp.clickOnConfirmcheckbox();
		rp.clickonContinueButton();
		
		MyAccountPage mp=new MyAccountPage(driver);
		mp.clickonLogout();
		mp.clickonContinue();	
		
		//login with valid credentials
		
		hp.clickOnMyAccount();
		hp.clickOnLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(p.getProperty("email"));
		lp.enterPassword(p.getProperty("password"));
		lp.clickOnLogin();
		
		mp.clickonLogout();
	}

}
