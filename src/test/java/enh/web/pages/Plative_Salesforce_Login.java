package enh.web.pages;

import utilities.KeywordUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class Plative_Salesforce_Login extends KeywordUtil {
	public static By salesforce_email = By.id("username");
	public static By salesforce_password = By.id("password");
	public static By login = By.id("Login");

	// Verification
	public static By clickonProfile = By
			.xpath("(//*[@class= 'tooltipTrigger tooltip-trigger uiTooltip'])[5]/span[1]/div/span");
	public static By profilename = By.xpath("(//*[@class= 'profile-card-indent'])/h1/a");
	public static By emailverify = By.xpath(
			"(//*[@class= 'slds-form-element slds-form-element_readonly slds-grow slds-hint-parent override--slds-form-element'])[8]/div[2]/span/a");

	public static void salesforce_Login(String email, String password) throws Exception {
		HtmlReportUtil.stepInfo(
				"<b style=\"color:brown;\">==================Salesforce login started=======================</b>");
		inputText(salesforce_email, email);
		HtmlReportUtil.stepInfo("Entered Email :- <b style=\"color:green;\">" + email + "</b>");
		inputText(salesforce_password, password);
		HtmlReportUtil.stepInfo("Entered Email :- <b style=\"color:green;\">" + password + "</b>");
		click(login);
		Thread.sleep(5000);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">login </b> button");
		// getDriver().switchTo().alert().accept();
		Thread.sleep(1000);
		HtmlReportUtil.stepInfo(
				"<b style=\"color:blue;\">==================Salesforce login Verfication started=======================</b>");

		click(clickonProfile);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">Profile </b> button");
		Thread.sleep(3000);
		click(profilename);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">Profile accountname </b> button");
		Thread.sleep(5000);
		WebElement element = getDriver().findElement(emailverify);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(100);
		String actual = getDriver().findElement(emailverify).getText();
		System.out.println(actual);
		HtmlReportUtil.stepInfo(" Actual  email :- <b style=\"color:green;\"> " + actual + "</b>");
		String Expected = "operations@plative.com";
		HtmlReportUtil.stepInfo(" Expected  email :- <b style=\"color:green;\"> " + Expected + "</b>");
		Assert.assertEquals(Expected, actual);
		HtmlReportUtil.stepInfo(
				"<b style=\"color:blue;\">==================Salesforce details verified Successfully=======================</b>");

		HtmlReportUtil.stepInfo(
				"<b style=\"color:brown;\">==================Salesforce login Ended =======================</b>");

	}
}
