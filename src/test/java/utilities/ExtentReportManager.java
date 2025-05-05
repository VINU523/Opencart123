package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.Baseclass;

public class ExtentReportManager implements ITestListener {
	
	//3 classes 
	public ExtentSparkReporter reporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repname;
	
	public  void onStart(ITestContext context) 
	{
		/*
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repname="Test-Report"+timestamp+".html";	
		
	   reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repname);
	   reporter.config().setDocumentTitle("Automation Report");
	   reporter.config().setReportName("Functional Testing");
	   reporter.config().setTheme(Theme.DARK);
	   
	   extent=new ExtentReports();
	   extent.attachReporter(reporter);
	   
	   extent.setSystemInfo("Application","OpenCart");
	   extent.setSystemInfo("Module", "Admin");
	   extent.setSystemInfo("Sub Module","customers");
	   extent.setSystemInfo("Username", System.getProperty("user.name"));
	   extent.setSystemInfo("Environement", "QA");
	   
	   
	   //Extracting operating system from xml file   
	   String os= context.getCurrentXmlTest().getParameter("os");
	   extent.setSystemInfo("Operating System", os);
	   
	   //Extracting browser name from xml file
	   String browser=context.getCurrentXmlTest().getParameter("browser");
	   extent.setSystemInfo("Browser", browser);
	   
	   //Extracting groups
	   List<String> includedgroups=context.getCurrentXmlTest().getIncludedGroups();
	   if(!includedgroups.isEmpty())
	   {
		   extent.setSystemInfo("Groups", includedgroups.toString());
	   }
	   
	   
	}
	
	public  void onTestSuccess(ITestResult result)
	{
	   test= extent.createTest(result.getTestClass().getName());
	   test.assignCategory(result.getMethod().getGroups());//to display groups in the report
	   test.log(Status.PASS, result.getName()+"got successfully Excuted");
	}
	public  void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 
		test.log(Status.FAIL,result.getName()+"got Failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try
		{
			String imgpath=new Baseclass().captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(imgpath);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP, result.getName()+"got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context)
	{
		 extent.flush();
		 
		 
		
		 /*
		 //After the test Execution then Automatically opens the report
		 String pathofExtentReport=System.getProperty("user.dir")+"//reports//"+repname;
		 File extentreport=new File(pathofExtentReport);
		 
		 try
		 {
			 Desktop.getDesktop().browse(extentreport.toURI());
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
		
		 //sending the report to email
		 
		 try {
			URL url=new URL("File:///"+System.getProperty("user.dir")+"\\reports\\"+repname);
			
			//create the email message
			
			ImageHtmlEmail email=new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlegmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("vinodpesit523@gmail.com", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("vinodpesit523@gmail.com");
			email.setSubject("Test Results");
			email.setMsg("Please find the Attached Report");
			email.addTo("vinodvicky523@gmail.com");
			email.attach(url,"extent report","please check report");
			email.send();			
			
		} catch (MalformedURLException | EmailException e) {
			
		}
		*/
		 
	}

}
