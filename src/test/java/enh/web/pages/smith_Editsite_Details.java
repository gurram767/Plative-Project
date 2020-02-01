package enh.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class smith_Editsite_Details extends KeywordUtil {
	public static By clickOnHomeDashboard = By.xpath("//*[@id=\"menu\"]/li[3]/a/i");
	public static By clickOnSitedetails = By.xpath("//span[contains(.,'Site Details')]");
	public static By searchsiteName = By.xpath("//*[@id='global_search_txt_GSC_VW_DIM_SITE_NAME']");
	public static By clickOnGobutton = By.id("global_search_btn_GSC_VW_DIM_SITE_NAME");
	public static By selectSiteName= By.xpath("//*[@class ='ui-jqgrid-bdiv']");
	public static By clickOnEdit = By.xpath("(//*[@class ='btn btn-default btn-sm crud-grp '])[2]");
	public static By editSiteName = By.id("SITE_NAME");
	public static By editSiteRank = By.id("SITE_RANK");
	public static By Submit = By.xpath("//*[@id='sData']");
	public static By refresh = By.id("refresh_btn_GSC_VW_DIM_SITE_NAME");
	public static By close = By.xpath("(//span[@class='input-group-btn'])[4]/button");
	
	public static void editsite(String sitename, String editSitename, String editedRank) throws Exception {
		Thread.sleep(1000);
		HtmlReportUtil.stepInfo(
				"<b style=\"color:brown;\">****************Edit Site Details Functionality Started *****************</b>");
		hoverElement(clickOnHomeDashboard);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">clicked on homedashboard </b> link clicked");
		click(clickOnSitedetails);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">SiteDetails </b> button");
		Thread.sleep(1000);
		inputText(searchsiteName, sitename);
		HtmlReportUtil.stepInfo(" Searched Sitename :- <b style=\"color:green;\"> " + sitename + "</b>");
		click(clickOnGobutton);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">GO </b>button");
		Thread.sleep(1000);
		click(selectSiteName);
		HtmlReportUtil.stepInfo("Selected  the searched Sitedetails <b style=\"color:green;\">" + sitename + "  </b>");
		click(clickOnEdit);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">Edit </b> button");
		inputText(editSiteName, editSitename);
		HtmlReportUtil.stepInfo("Edited Sitename :- <b style=\"color:green;\">" + editSitename + "</b>");
		inputText(editSiteRank, editedRank);
		HtmlReportUtil.stepInfo("Edited Siterank :- <b style=\"color:green;\">" + editedRank + " </b>");
		click(Submit);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">submit </b> button");
		Thread.sleep(1500);
		click(close);
		Thread.sleep(1500);
		click(refresh);
		Thread.sleep(1500);
		HtmlReportUtil.stepInfo(" <b style=\"color:blue;\">SiteDetails Verification started </b>");
		inputText(searchsiteName, editSitename);
		HtmlReportUtil.stepInfo(" Searched Edited Sitename for verification:- <b style=\"color:green;\"> " + editSitename + "</b>");
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
			if (output.contains(editSitename)) {
				String actual = editSitename;
				Assert.assertEquals(output, actual);
				HtmlReportUtil.stepInfo(" Actual sitename :- <b style=\"color:green;\"> " + actual + "</b>");
				HtmlReportUtil.stepInfo(" Expected Sitename :- <b style=\"color:green;\"> " + output + "</b>");
				HtmlReportUtil.stepInfo(" <b style=\"color:blue;\">SiteDetails Edited & verifed sucessfully </b>");
			}
		}
			HtmlReportUtil.stepInfo(
					"<b style=\"color:brown;\">****************Edit Site Details Functionality Ended *****************</b>");

		
		

}
	
	
	
}
