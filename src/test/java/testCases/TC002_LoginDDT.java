package testCases;

import javax.security.auth.login.LoginContext;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.Baseclass;
import utilities.DataProviders;

public class TC002_LoginDDT extends Baseclass {
	
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = {"datadriven","functional"} )
	public void testLogin(String email,String pwd,String exp)
	{
		try
		{
		logger.info("TC002_LoginDDT is Started");
		HomePage hp=new HomePage(driver);
		hp.clickOnMyAccount();
		hp.clickOnLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(pwd);
		lp.clickOnLogin();
		
		MyAccountPage ma=new MyAccountPage(driver);
		boolean targetpage=ma.isDisplayedMyaccount();
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				ma.clickonLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		else if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				ma.clickonLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		logger.info("Test is finished");
		
		}
		catch(Exception e)
		{
			
		}
		
		
	}

}
