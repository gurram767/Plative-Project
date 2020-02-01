package enh.web.pages;

import org.openqa.selenium.By;

import junit.framework.Assert;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class smith_deletesite_Details   extends KeywordUtil {
	
	public static By clickOnHomeDashboard = By.xpath("//*[@id=\"menu\"]/li[3]/a/i");
	public static By clickOnSitedetails = By.xpath("//span[contains(.,'Site Details')]");
	public static By searchsiteName = By.xpath("//*[@id='global_search_txt_GSC_VW_DIM_SITE_NAME']");
	public static By clickOnGobutton = By.id("global_search_btn_GSC_VW_DIM_SITE_NAME");
	public static By selectSiteName= By.xpath("//*[@class ='ui-jqgrid-bdiv']");
	public static By clickOndeletebutton = By.xpath("(//*[@class ='btn btn-default btn-sm crud-grp '])[3]");
	public static By deleteconformationbutton  = By.xpath("//*[@class='DelButton EditButton EditButton-ltr']/a[1]");
	public static By recordstoView  = By.xpath("//span[contains(.,'No records to view')]");
	public static By close = By.xpath("(//span[@class='input-group-btn'])[4]/button");
	
	public static void deletesite(String sitename, String output) throws Exception {
		Thread.sleep(1000);
		HtmlReportUtil.stepInfo(
				"<b style=\"color:brown;\">**************** Site delete  Functionality Started *****************</b>");
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
		HtmlReportUtil.stepInfo("selected Sitename to delete :- <b style=\"color:green;\">" + sitename + "</b>");
		Thread.sleep(500);
		click(clickOndeletebutton);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">delete </b>button");
		Thread.sleep(500);
		click(deleteconformationbutton);
		HtmlReportUtil.stepInfo(
				"<b style=\"color:brown;\">**************** Sucessfully deleted sitedetails *****************</b>");
		Thread.sleep(500);
		click(close);
		Thread.sleep(1000);
		HtmlReportUtil.stepInfo(" <b style=\"color:blue;\">SiteDetails Verification started </b>");
		inputText(searchsiteName, sitename);
		HtmlReportUtil.stepInfo(" Searched deleted Sitename :- <b style=\"color:green;\"> " + sitename + "</b>");
		click(clickOnGobutton);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">GO </b>button");
		Thread.sleep(1000);
		String actual= getElementText(recordstoView);
		System.out.println(actual);
		String expected  = output;
		Assert.assertEquals(expected, actual);
		HtmlReportUtil.stepInfo(" Actual result :- <b style=\"color:green;\"> " + actual + "</b>");
		HtmlReportUtil.stepInfo(" Expected result :- <b style=\"color:green;\"> " + expected + "</b>");
		HtmlReportUtil.stepInfo(" <b style=\"color:blue;\">SiteDetails deleted  & verifed sucessfully </b>");
		HtmlReportUtil.stepInfo(
				"<b style=\"color:brown;\">****************Site delete  Functionality Ended *****************</b>");
}
}