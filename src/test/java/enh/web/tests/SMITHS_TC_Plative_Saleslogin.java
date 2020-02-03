package enh.web.tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.web.pages.HomePage;
import enh.web.pages.LoginPage;
import enh.web.pages.Plative_Salesforce_Login;
import enh.web.pages.Smith_AddSite_details;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;
import utilities.TestDataProvider;
@Listeners({CustomListeners.class,ExecutionStartEndListner.class})
public class SMITHS_TC_Plative_Saleslogin extends KeywordUtil {
	String stepInfo="";
	int retryCount=getIntValue("retryCount");
	static int retryingNumber=1;
	@DataProvider
	public Object[][] testData() throws Exception {
		Object data[][] = TestDataProvider.excelReader(ConfigReader.getValue("testDataExcelPath"), "salesforcelogin");

		// System.out.println(Repository.getProperty("SSTestDataFilePath"));
		return data;
	}

	@Test( dataProvider = "testData", testName="SMITHS_TC_Plative_Saleslogin",
			groups={"Regression"}, 
			description="Login : Verify whether user is able to login with valid username and password."
			)
	public void test(String email, String password) throws Throwable {
		try{
			setTestCaseID(getClass().getSimpleName());
			//======================BASIC SETTING FOR TEST==========================================================
			if(retryingNumber==1)
				initTest();
		
			//.........Script Start...........................
			
			stepInfo="Open home page";
			logStep(stepInfo);
			HomePage.openHomePage();
			Plative_Salesforce_Login.salesforce_Login(email, password);
			System.out.println("Login success");
			
			
			
	
	

	}
		catch (Exception e){
			   if(retryCount>0)
			   {
				   String imagePath = takeScreenshot(getDriver(), getTestCaseID()+"_"+ retryingNumber);

				   logStepFail(stepInfo+" - "+KeywordUtil.lastAction);
				   logStepError(e.getMessage());
				   HtmlReportUtil.attachScreenshot(imagePath,false);
			    
				   GlobalUtil.getTestResult().setScreenshotref(imagePath);
			    
				   HtmlReportUtil.stepInfo("Trying to Rerun" + " "+getTestCaseID() +" for " + retryingNumber + " time");
				   retryCount--;
				   retryingNumber++;
				   utilities.LogUtil.infoLog(getClass(), "****************Waiting for " + getIntValue("retryDelayTime") +" Secs before retrying.***********");
				   delay(getIntValue("retryDelayTime"));
			    //Rerun same test
				   test(email, password);
			   }
			   else{
				   String imagePath = takeScreenshot(getDriver(), getTestCaseID());
				   logStepFail(stepInfo+" - "+KeywordUtil.lastAction);
				   logStepError(e.getMessage());
				   HtmlReportUtil.attachScreenshot(imagePath,false);
			    
				   GlobalUtil.getTestResult().setScreenshotref(imagePath);
				   GlobalUtil.setTestException(e);
				   throw e;
			   }
		  }
}
	}

