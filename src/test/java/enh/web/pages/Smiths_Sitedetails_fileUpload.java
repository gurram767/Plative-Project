package enh.web.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class Smiths_Sitedetails_fileUpload extends KeywordUtil {

	public static By clickOnHomeDashboard = By.xpath("//*[@id=\"menu\"]/li[3]/a/i");
	public static By clickOnfileUpload = By.xpath("//span[contains(.,'File Upload')]");
	public static By clickOnSitedetails = By.xpath("//span[contains(.,'Site Details')]");
	public static By chooseMenu = By.id("fu_menuId");
	public static By fileUpload = By.xpath("(//*[@class='input-group-btn'])[2]/div");
	public static By Upload = By.xpath("(//span[contains(.,'Upload')])[2]");
	public static By Nextbutton = By.xpath("//*[@class= 'btn-group navbar-btn sw-btn-group pull-right']/button[2]");
	public static By steptwoNext = By.xpath("//*[@class ='btn-group navbar-btn sw-btn-group pull-right']/button[2]");
	public static By Verifysitename = By.xpath("//*[@id='global_search_txt_GSC_VW_DIM_SITE_NAME']");
	public static By finsishbutton = By.id("finishBtn");
	public static By clickOnGobutton = By.id("global_search_btn_GSC_VW_DIM_SITE_NAME");

	public static void fileupload(String sitedetials, String sitename) throws Exception {
		Thread.sleep(1500);
		HtmlReportUtil.stepInfo(
				"<b style=\"color:brown;\">************** File Upload for sitedetails Functionality Started *****************</b>");
		click(clickOnHomeDashboard);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">clicked on homedashboard </b> link clicked");
		Thread.sleep(2500);
		click(clickOnfileUpload);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">clicked on fileupload </b> link clicked");
		click(chooseMenu);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">chooseMenu </b> button");
		selectByVisibleText(chooseMenu, sitedetials);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">Selected the Sitedetails from dropdown </b>");
		HtmlReportUtil.stepInfo("Entered Site details :- <b style=\"color:green;\">" + sitedetials + "</b>");
		Thread.sleep(1000);
		Robot robot = new Robot();
		ClipboardOwner owner = null;
		click(fileUpload);
		Thread.sleep(1500);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">fileUpload </b> button");
		StringSelection stringselection = new StringSelection("C:\\CsvFileupload\\Sitedetailsupload.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, owner);
		robot.setAutoDelay(7000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.setAutoDelay(7000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(500);
		click(Upload);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">Upload selected files </b> button");
		Thread.sleep(1500);
		click(Nextbutton);
		Thread.sleep(7000);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">Nextbutton </b> button");
		click(steptwoNext);
		Thread.sleep(10000);
		click(steptwoNext);
		Thread.sleep(7000);
		click(finsishbutton);
		Thread.sleep(3000);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">Succesfully uploaded a file </b>");
		getDriver().switchTo().alert().accept();
		Thread.sleep(5000);
		hoverElement(clickOnHomeDashboard);
		Thread.sleep(1500);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">clicked on homedashboard </b> link clicked");
		click(clickOnSitedetails);
		Thread.sleep(1000);
		inputText(Verifysitename, sitename);
		HtmlReportUtil.stepInfo(" Searched Sitename :- <b style=\"color:green;\"> " + sitename + "</b>");
		click(clickOnGobutton);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">GO </b>button");
		Thread.sleep(1000);

		List<WebElement> headers = getDriver()
				.findElements(By.xpath("//*[@class= 'ui-jqgrid-view']/div[2]/div/table/thead/tr/th"));
		int header = headers.size();

		for (int i = 1; i < header; i++) {
			// Below is the x path for the table header- so that it will Print all the
			// header
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
				"<b style=\"color:brown;\">****************file upload for sitedetails Functionality Ended *****************</b>");

	}

}
