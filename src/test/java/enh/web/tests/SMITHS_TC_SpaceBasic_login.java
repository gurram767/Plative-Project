package enh.web.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.web.pages.HomePage;

import enh.web.pages.SB_SpaceBasic_Login;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.KeywordUtil;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class SMITHS_TC_SpaceBasic_login  extends KeywordUtil{
	@Test(testName = "SMITHS_TC_SpaceBasic_login", groups = { "Regression" }, description = ".")

	public void login() throws Throwable  {
		try {
			setTestCaseID(getClass().getSimpleName());
			initTest();
			System.out.println("browser started");

			HomePage.openHomePage();
			System.out.println("url clicked");
			SB_SpaceBasic_Login.spaceBasicLogin("madhavi@spacebasic.com", "sb1office");
		} catch (Exception e) {

		}
	}
}

