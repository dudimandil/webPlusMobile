package WorkFlowes;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Extantions.click;
import Extantions.update;
import Utilities.commonOps;

public class web_Search extends commonOps
{
	public static void go (String searchValue, String language) throws IOException, ParserConfigurationException, SAXException
	{
		update.text(wikiMain.searchBox, searchValue);
		update.dropDownText(wikiMain.selectLanguage, language);
		click.go(wikiMain.searchButton);
	}
}
