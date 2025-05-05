package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.Baseclass;

public class TC003_LoginTest extends Baseclass {
	
	
	@Test(groups = {"sanity","Regresssion"})
	public void testLogin()
	{
		logger.info("***TC003_LoginTest is Started***");
		HomePage hp=new HomePage(driver);
		hp.clickOnMyAccount();
		logger.info("Clicked on Myaccount");
		hp.clickOnLogin();
		logger.info("Clicked on Login Link");
		
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(p.getProperty("email"));
		logger.info("Email is Entered");
		lp.enterPassword(p.getProperty("password"));
		logger.info("Password is entered");
		lp.clickOnLogin();
		
		MyAccountPage mp=new MyAccountPage(driver);
		mp.clickonLogout();
		logger.info("TC003_LoginTest is finished");
		
	}

}
