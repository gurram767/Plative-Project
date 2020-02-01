package enh.web.pages;

import org.openqa.selenium.By;

import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class SB_SpaceBasic_Login extends KeywordUtil{
	
	public static By loginbutton = By.xpath("//*[contains(text(),'Login')]");
	public static By emailaddress = By.name("email");
	public static By password = By.name("password");
	public static By login = By.xpath("(//*[text()='Login'])[1]");
	
	
	public static void spaceBasicLogin(String email, String Password) {
		HtmlReportUtil.stepInfo(
				"<b style=\"color:blue;\">==================Space Basic login started=======================</b>");
		click(loginbutton);
		inputText(emailaddress, email);
		inputText(password, Password);
		click(login);
	}

}
