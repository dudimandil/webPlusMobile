package Extantions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.commonOps;

public class verify extends commonOps
{
	public static void textInElement (WebElement elem,String expectedValue) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			 // Can implement ExplicityWait here
			assertEquals (elem.getText(), expectedValue);
			test.log(LogStatus.PASS, "Text found in Element");
		}
		catch (Exception e)
		{
			test.log(LogStatus.FAIL, "Error with finding Text, see details:" + e + "see screenshot: " + test.addScreenCapture(takeSS()));
			fail ("Error with finding Text");
		}
		catch (AssertionError e)
		{
			test.log(LogStatus.FAIL, "Text NOT found in Element, see details: " + e + "see screenshot: " + test.addScreenCapture(takeSS()));
			fail("Text NOT found in Element");
		}
	}
	
	public static void image(String imagePath) throws FindFailed, IOException, ParserConfigurationException, SAXException
	{
		try
		{
			screen.find(imagePath);
			test.log(LogStatus.PASS, "Image found Successfully");
		}
		catch (Exception e)
		{
			test.log(LogStatus.FAIL, "Failed To Finf Image, see details:" + e + "see screenshot: " + test.addScreenCapture(takeSS()));
			fail ("Failed with finding Image");
		}
	}
}