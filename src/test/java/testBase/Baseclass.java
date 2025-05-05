package testBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class Baseclass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"sanity","datadriven","functional"})
	@Parameters({"browser","os"})
	public void OpenBrowser(String br,String os) throws IOException
	{	
		
		//loading properties file
		String path1="./src//test//resources//config.properties";	
		FileReader file=new FileReader(path1);
		p=new Properties();
		p.load(file);
		
		//Launching Remote Machine
		
		if(p.getProperty("exe_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capablities=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows"))
			{
				capablities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capablities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No Matching OS");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case"chrome":capablities.setBrowserName("chrome");break;
			case"edge":capablities.setBrowserName("MicrosoftEdge");break;
			case"firefox":capablities.setBrowserName("firefox");break;
			default:System.out.println("No Matching browser");return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capablities);
		}
		
		// If the machine is local
		if(p.getProperty("exe_env").equalsIgnoreCase("local"))
		{			
			switch(br.toLowerCase())
			{
			case"chrome":driver=new ChromeDriver();break;
			case"edge":driver=new EdgeDriver();break;
			case"firefox":driver=new FirefoxDriver();;break;
			default:System.out.println("Invalid Browser");return;
			}			
				
		}
					
		

		logger=LogManager.getLogger(this.getClass());
	
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("URL"));
	}
	
	@AfterClass(groups= {"Sanity","datadriver","functional"})
	public void tearDown()
	{
		driver.quit();
	}
	

	public String randomString()
	{
		
		String randomstring=RandomStringUtils.randomAlphabetic(7);
		return randomstring;
		
	}
	
	public String randomNumbers()
	{
		
		String numbers=RandomStringUtils.randomNumeric(10);
		return numbers;
	}
	
	public String randomAlphaNumberic()
	{
		
		String randomstring=RandomStringUtils.randomAlphabetic(3);		
		String numbers=RandomStringUtils.randomNumeric(3);
		return (randomstring+"@"+numbers);
		
	}
	
	public String captureScreenshot(String name)
	{
		
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot=(TakesScreenshot) driver;
		File sourcefile=takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath=System.getProperty("user.dir")+"\\screenshots\\"+name+"_"+timestamp+".png";
		File targetfile=new File(targetfilepath);
		sourcefile.renameTo(targetfile);
		return targetfilepath;
		
		
	}

}
