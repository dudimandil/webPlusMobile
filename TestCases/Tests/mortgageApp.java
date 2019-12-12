package Tests;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import Extantions.verify;
import Utilities.commonOps;
import WorkFlowes.mobile_mortgage;

public class mortgageApp extends commonOps
{
	@Test
	public void test01_verifyRepayment() throws IOException, ParserConfigurationException, SAXException, FindFailed
	{
		mobile_mortgage.calculate("1000", "3", "4");
		verify.textInElement(mortgageMain.repayment, "£30.03");
		
	}
}
