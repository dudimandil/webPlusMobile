package Utilities;

import org.openqa.selenium.support.PageFactory;


public class managePages extends base
{
	public static void init()
	{
		wikiMain = PageFactory.initElements(driver, PageObjects_Wkipedia.mainPage.class);
		wikiResults = PageFactory.initElements(driver,PageObjects_Wkipedia.resultsPage.class);
		
		mortgageMain = PageFactory.initElements(driver, PgeObjects_Mortgage.mainPage.class);
	}
	
}
