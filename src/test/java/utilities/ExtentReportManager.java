package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		//here we create time stamp in formatted manner.
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timestamp + ".html";
		
		// here we create object of Extentsparkreporter and give the location where we generate extent reports
		sparkreporter = new ExtentSparkReporter(".//reports//" + repName);
		
		//here we set UI of report
		sparkreporter.config().setDocumentTitle("Opencart Automation Report");
		sparkreporter.config().setReportName("Automation Functional Testing");
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();   
		extent.attachReporter(sparkreporter);  // here we attach sparkreporter to extent
		
		// here we set common system info
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating system", os);
		
		String br = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser name", br);
		
		List<String> includedgroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedgroups.isEmpty()) {
			
			extent.setSystemInfo("Groups", includedgroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());  //Create entry in report with test class name
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ " got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());  //Create entry in report with test class name
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+ " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		// Attach failure ss to report
		
		  try {
			  String imgPath = new BaseClass().captureScreen(result.getName());
			  test.addScreenCaptureFromPath(imgPath);
			  }
			  catch(Exception e) {
				  
				  e.printStackTrace();
			  }
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());  //Create entry in report with test class name
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();   //mandatory method to call.... All things which we done above will reflect after this
		
		  String pathextentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		  File extentReport = new File(pathextentReport);
		  
		  try {
		  Desktop.getDesktop().browse(extentReport.toURI());
		  }
		  catch(Exception e) {
			  
			  e.printStackTrace();
		  }
	}
}
