package Extantions;

import static org.testng.Assert.fail;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.commonOps;

public class update extends commonOps
{
	public static void text(WebElement elem, String value) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			elem.sendKeys(value);
			test.log(LogStatus.PASS, "Text fied updated successfully");
			}
		catch (Exception e)
		{
			test.log(LogStatus.FAIL, "Failed to update Text Field, see details: " + e + "see screenshot: " + test.addScreenCapture(takeSS()));
			fail("Failed to update Text Field:");
		}
	}


	public static void dropDownText(WebElement elem, String value) throws IOException, ParserConfigurationException, SAXException
	{
		try
		{
			Select myValue = new Select(elem);
			myValue.deselectByVisibleText(value);
			test.log(LogStatus.PASS, "DropDown updated successfully");
		}
		catch (Exception e)
		{
			test.log(LogStatus.FAIL, "Failed to update DropDown, see details: " + e + "see screenshot: " + test.addScreenCapture(takeSS()));
			fail("Failed to update DropDown");
		}
	}
}
