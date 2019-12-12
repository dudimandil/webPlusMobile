package Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentReports;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;


public class commonOps extends base
{
	public static String getData (String nodeName) throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = new File("./Configuration/DataConfig.xml");
		DocumentBuilderFactory dbFactory =DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder =dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(nodeName).item(0).getTextContent();
					
	}
	
	public static void initBrowser(String BrowserType) throws ParserConfigurationException, SAXException, IOException
	{
		WebDriver driver=null;
		switch(BrowserType.toLowerCase())
		{
		case "chrome":
			driver = initChromDriver();
			break;
		case "firefox":
			driver = initFFDriver();
			break;
		case "ie":
			driver = initIEDriver();
			break;
		}
		
		driver.manage().window().maximize();
		driver.get(getData("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Screen screen = new Screen();
		

	}
	
	public static WebDriver initChromDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.chrome.driver", getData("ChromDriverPath"));
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	public static WebDriver initFFDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.gecko.driver", getData("FFDriverPath"));
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	public static WebDriver initIEDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.ie.driver", getData("IEDriverPath"));
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}
	
	public static void initMobile() throws ParserConfigurationException, SAXException, IOException
	{
		 dc.setCapability(MobileCapabilityType.UDID, getData("UDID"));
	     dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getData("APP_Package"));
	     dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getData("APP_Package"));
	     driver = new AndroidDriver (new URL(getData("Appium_Server_Adress")), dc);
	     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public static void InstanceReport() throws ParserConfigurationException, SAXException, IOException
	{
		extent = new ExtentReports(getData("ReportFilePath") + "Execution_" + timeStamp + "/" + getData("ReportFileName") + ".html");
	}
	
	public void initReportTest(String testName, String testDescription)
	{
		test = extent.startTest(testName, testDescription);
	}
	
	public void finilizeReportTest()
	{
		extent.endTest(test);
	}
	
	public static void finilizeExtentReport() 
	{
		extent.flush();
		extent.close();
	}
	
	public static String takeSS() throws IOException, ParserConfigurationException, SAXException
	{
		String SSPath = getData("ReportFilePath") + "Execution_" + timeStamp + "/" + "screenshot_" + getRandomNumber() + ".png";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(SSPath));
		return SSPath;
	}
	
	public static int getRandomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(999999) + 1;
	}
	
	@BeforeClass
	public void startsession() throws ParserConfigurationException, SAXException, IOException 
	{
		if (getData("AutomationType").toLowerCase().equals("web"))
			initBrowser(getData("BrowserType"));
		else if (getData("AutomationType").toLowerCase().equals("mobile"))
			initMobile();
			
			
		managePages.init();
		InstanceReport();
	}
	
	@AfterClass
	public void closeSession() 
	{
		driver.quit();
		finilizeExtentReport();
	}
	
	@BeforeMethod
	public void doBeforeMethod(Method method)
	{
		initReportTest(method.getName().split("_")[0], method.getName().split("_")[1]);
	}
	
	@AfterMethod
	public void doAfterTest() 
	{
		finilizeReportTest();
	}
	
}
