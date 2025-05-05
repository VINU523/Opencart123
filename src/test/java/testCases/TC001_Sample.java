package testCases;

import static org.testng.Assert.fail; 

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.Baseclass;

public class TC001_Sample extends Baseclass{
	
	
	
	@Test(groups = {"sanity","functional"})
	public void test()
	{
		logger.info("****TC001_is Started****");
		HomePage hp=new HomePage(driver);
		hp.clickOnMyAccount();
		logger.info("Clicked on my Account");
		
		hp.clickOnRegister();
		logger.info("Clicked on Register");
		
		
		RegisterPage rp=new RegisterPage(driver);
		
		logger.info("Provide the Registration Details");
		rp.enterFirstname(randomString());
		rp.enterLastname(randomString());
		String mail=randomNumbers();
		rp.enterEmail(mail+"@gmail.com");
		rp.enterTelephone(randomNumbers());
		String pass=randomAlphaNumberic();
		rp.enterPassword(pass);
		rp.enterConfirmPassword(pass);
		rp.clickOnConfirmcheckbox();
		rp.clickonContinueButton();
		logger.info("***TC001_is Completed");
		}
		
		
	}


