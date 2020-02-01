package enh.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;
import utilities.ConfigReader;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class Smith_AddSite_details extends KeywordUtil {

	public static By clickOnHomeDashboard = By.xpath("//*[@id=\"menu\"]/li[3]/a/i");
	public static By clickOnSitedetails = By.xpath("//span[contains(.,'Site Details')]");
	public static By clickOnAdd = By.id("add_btn_GSC_VW_DIM_SITE_NAME");
	public static By enterSiteName = By.id("SITE_NAME");
	public static By enterSiteRank = By.id("SITE_RANK");
	public static By Submit = By.xpath("//*[@id='sData']");

	// Verification

	public static By Verifysitename = By.xpath("//*[@id='global_search_txt_GSC_VW_DIM_SITE_NAME']");
	public static By clickOnGobutton = By.id("global_search_btn_GSC_VW_DIM_SITE_NAME");

	public static void addingNewSite(String sitename, String siterank) throws Exception {
		Thread.sleep(1000);
		HtmlReportUtil.stepInfo(
				"<b style=\"color:brown;\">****************Add Site Details Functionality Started *****************</b>");
		hoverElement(clickOnHomeDashboard);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">clicked on homedashboard </b> link clicked");
		click(clickOnSitedetails);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">SiteDetails </b> button");
		Thread.sleep(1000);
		click(clickOnAdd);
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\">****************Add Site Details *****************</b>");
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">Add </b> button");
		Thread.sleep(1000);
		inputText(enterSiteName, sitename);
		HtmlReportUtil.stepInfo("Entered Sitename :- <b style=\"color:green;\">" + sitename + "</b>");
		inputText(enterSiteRank, siterank);
		HtmlReportUtil.stepInfo("Entered Siterank :- <b style=\"color:green;\">" + siterank + " </b>");
		click(Submit);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">Submit </b> button");
		Thread.sleep(3000);
		HtmlReportUtil.stepInfo(" <b style=\"color:blue;\">SiteDetails Verification started </b>");
		inputText(Verifysitename, sitename);
		HtmlReportUtil.stepInfo(" Searched Sitename :- <b style=\"color:green;\"> " + sitename + "</b>");
		click(clickOnGobutton);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">GO </b>button");
		Thread.sleep(1000);

		List<WebElement> headers = getDriver()
				.findElements(By.xpath("//*[@class= 'ui-jqgrid-view']/div[2]/div/table/thead/tr/th"));
		int header = headers.size();

		for (int i = 1; i < header; i++) {
			// Below is the x path for the table header- so that it will Print all the header
			// name with columns data

			String headerValue = getElementText(By.xpath("//table/thead/tr/th[" + i + "]"));
			// below is the x path for column

			String output = getDriver()
					.findElement(By.xpath("//*[@id='grid_GSC_VW_DIM_SITE_NAME']/tbody/tr[2]/td[" + i + "]")).getText();
			// Below will print all the header name with columns data

			HtmlReportUtil.stepInfo("<b style=\"color:black;\">" + headerValue + " </b>- <b style=\"color:green;\">'"
					+ output + "'</b>");
			if (output.contains(sitename)) {
				String actual = sitename;
				Assert.assertEquals(output, actual);
				HtmlReportUtil.stepInfo(" Actual sitename :- <b style=\"color:green;\"> " + actual + "</b>");
				HtmlReportUtil.stepInfo(" Expected Sitename :- <b style=\"color:green;\"> " + output + "</b>");
				HtmlReportUtil.stepInfo(" <b style=\"color:blue;\">SiteDetails added & verifed sucessfully </b>");
			}
		}
			HtmlReportUtil.stepInfo(
					"<b style=\"color:brown;\">****************Add Site Details Functionality Ended *****************</b>");

		
	}
}