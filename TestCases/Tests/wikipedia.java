package Tests;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;


import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import Extantions.verify;
import Utilities.commonOps;
import WorkFlowes.web_Search;

public class wikipedia extends commonOps
{
	
	@Test
	public void test01_searchValue() throws IOException, ParserConfigurationException, SAXException, FindFailed
	{
		web_Search.go("dudi mandil", "simple English");
		verify.textInElement(wikiResults.resultFiled,"There were no results matching the query.");
		verify.image("./ImageRepository/logo.png");
	}
	
	
	
}
