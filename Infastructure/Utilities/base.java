package Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.Screen;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

// import PageObjects_Wkipedia.mainPage;
// import PageObjects_Wkipedia.resultsPage;

public class base 
{
	public static WebDriver driver;
	public static Screen screen;
	public static DesiredCapabilities dc = new DesiredCapabilities();
		
	public static PageObjects_Wkipedia.mainPage wikiMain;
	public static PageObjects_Wkipedia.resultsPage wikiResults;
	public static PgeObjects_Mortgage.mainPage mortgageMain;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static String timeStamp = new SimpleDateFormat("YYYY-MM-dd_HH-MM-SS").format(Calendar.getInstance().getTime());
	
	
	
}
