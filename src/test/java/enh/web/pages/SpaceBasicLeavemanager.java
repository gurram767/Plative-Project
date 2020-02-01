package enh.web.pages;

import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Sleeper;

import utilities.KeywordUtil;

public class SpaceBasicLeavemanager extends KeywordUtil {
	public static int j;
	public static By leaveManager = By.xpath("/html/body/div[1]/aside/section/ul/li[3]/a/span");
	public static By addLeaveRecord = By.xpath("//*[@id=\"hide_before_load\"]/div[1]/div/div[1]/div[3]/a");
	public static By studentName = By.id("studentname");
	public static By type = By.id("type");
	public static By regularFromDate = By.id("fromdate");
	public static By regularToDate = By.id("todate");
	public static By clickonfromtime = By.id("deptimeval");
	public static By selectfromandTotime = By
			.xpath("//*[@class='ui-timepicker ui-widget ui-widget-content ui-menu ui-corner-all']/ul/li/a");
	public static By clickonToDate = By.id("totimeval");
	public static By category = By.id("category");
	public static By description = By.id("description");
	public static By parentName = By.id("contact_name");
	public static By parentMobileNumber = By.id("contact_phone");
	public static By parentAddress = By.id("contact_address");
	public static By modeofTransport = By.id("mode_of_transport");
	public static By submit = By.xpath("//*[@class ='btn   btn-block sb1-primary']");

	public static void addLeave(String xyz, String name) throws Exception {

		click(leaveManager);
		click(addLeaveRecord);

		WebElement es1 = getDriver().findElement(studentName);

		System.out.println("Student name entereed");

		click(studentName);
		inputText(studentName, xyz);
		Thread.sleep(2000);
		click(By.xpath("//*[contains(text(),'" + name + " ')]"));
		selectByVisibleText(type, "Regular");
		inputText(regularFromDate, "23-11-2071");
		inputText(regularToDate, "23-11-2071");
		click(clickonfromtime);

		List<WebElement> from = getDriver().findElements(selectfromandTotime);
		System.out.println("entering");
		j = from.size();
		System.out.println(j);

		for (int i = 1; i < j; i++) {
			WebElement ess = getDriver().findElement(
					By.xpath("//*[@class='ui-timepicker ui-widget ui-widget-content ui-menu ui-corner-all']/ul/li/a["
							+ i + "]"));
			Thread.sleep(1000);
			String input = ess.getText();
			Thread.sleep(1000);
			if (input.equals("12:00 AM")) {
				Thread.sleep(1000);
				ess.click();
				break;
			}

		}
		Thread.sleep(10000);

		click(clickonToDate);

		for (int i = 1; i < j; i++) {
			WebElement ess = getDriver().findElement(
					By.xpath("//*[@class='ui-timepicker ui-widget ui-widget-content ui-menu ui-corner-all']/ul/li/a["
							+ i + "]"));
			Thread.sleep(1000);
			String input = ess.getText();
			Thread.sleep(1000);
			if (input.equals("12:30 AM")) {
				Thread.sleep(1000);
				ess.click();
				break;
			}

		}

	}
}
