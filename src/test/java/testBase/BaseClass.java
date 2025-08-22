package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class BaseClass {

	public Logger logger;
	public static WebDriver driver;   //in extentreportmanager class, we can create object of baseclass. thst is why we have to make driver as static
	public Properties p;
	
	@BeforeClass(groups = {"sanity", "regression", "master", "datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException, InterruptedException {
		
		//loading config.properties file
		// for loading properties file we have special class Properties
		FileInputStream fis = new FileInputStream("./src//test//resources//config.properties");  //open file in reading mode
		p = new Properties();
		p.load(fis);   // loading properties file
		
		// Load logger here
		logger = LogManager.getLogger(this.getClass());  // give class name randomly
		
		// setting grid environment for remote execution
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				
				capabilities.setPlatform(Platform.WIN10);
				
			}
			else if(os.equalsIgnoreCase("mac")) {
				
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				
				System.out.println("invlid os");
				return;
			}
			
			//br
		     switch (br.toLowerCase()) {
		     
			case "chrome": capabilities.setBrowserName("chrome"); 	break;
								
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;

			default: System.out.println("no matching"); return;
			
			}
		     
		     // launch remote web driver
		     
		     driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			//Launch browser
	        switch(br.toLowerCase()) {
	        
	        case "chrome" : driver = new ChromeDriver(); break;
	        case "edge"   : driver = new EdgeDriver(); break;
	        case "firefox": driver = new FirefoxDriver(); break;
	        default : System.out.println("Invalid browser"); return;   // totaly exit from the execution
	      }
			
		}
		
		driver.get(p.getProperty("appurl"));  //reading url from properties file
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();		
	}
	
	@AfterClass(groups = {"sanity", "regression", "master", "datadriven"})
	public void teardown() {
		
		// close the driver
		driver.close();
	}
	
	
	// Randomly generated data
	public String randomString() {
		
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String randomNumber() {
		
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String randomAlphanumeric() {
		
		String genString = RandomStringUtils.randomAlphabetic(3);
		String genNumber = RandomStringUtils.randomNumeric(3);
		return (genString+ "@" +genNumber);
	}
	
	public String captureScreen(String tcname) {
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + tcname + "_" + timestamp + ".png";
		File targetFile = new File(targetFilepath);
		
		srcfile.renameTo(targetFile);
		return targetFilepath;
	}
	
	
}
