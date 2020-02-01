package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.itextpdf.text.html.HtmlUtilities;

import utilities.ConfigReader;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;
import utilities.LogUtil;
import utilities.Utility;

/**
 * @author EN
 *
 */
public class LoginPage extends HomePage {
	
	public static final By useAnotherAccount = By.id("otherTileText"); 
	public static final By linkLoginWithEmail = By.xpath("//input[@type='email']");
	public static final By nextBtn = By.xpath("//input[@value='Next']");
	public static final By txtPassword = By.xpath("//input[@type='password']");
	public static final By siginBtn = By.xpath("//input[@value='Sign in']"); 
	public static final By noBtn = By.xpath("//input[@value='No']"); 
	public static final By userName = By.xpath("//span[contains(@class, 'username ')]");
	
	
	/**
	 * @param driver
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public static void Login(String userName, String password) throws Exception {
		
		KeywordUtil.inputText(linkLoginWithEmail, userName);
		HtmlReportUtil.stepInfo("Entered user Name :- "+userName );
		click(nextBtn);	
		HtmlReportUtil.stepInfo("Clicked on next button");
		KeywordUtil.inputText(txtPassword, password);	
		HtmlReportUtil.stepInfo("Entered Password :- ********** " );
		click(siginBtn);
		HtmlReportUtil.stepInfo("Clicked on SignIn button");
		click(noBtn);
		HtmlReportUtil.stepInfo("Clicked on No button");
		String str_userName=KeywordUtil.getElementText(LoginPage.userName);
		System.out.println(str_userName);
		HtmlReportUtil.stepInfo("Expected UserName :- "+str_userName);
		HtmlReportUtil.stepInfo("Actual UserName :- Surya Kiran Reddy Tiyyagura");
		KeywordUtil.verifyTextContains(LoginPage.userName, "Surya Kiran Reddy Tiyyagura");
		HtmlReportUtil.stepInfo(" UserName Surya Kiran Reddy Tiyyagura verifed");
		HtmlReportUtil.stepInfo(" Sucessfully loginto application");
		
		
		
	}
	
	
	
	 
	
}//
