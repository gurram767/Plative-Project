package enh.web.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.web.pages.HomePage;
import enh.web.pages.LoginPage;
import enh.web.pages.Smith_AddSite_details;
import enh.web.pages.smith_Editsite_Details;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;
import utilities.TestDataProvider;

@Listeners({CustomListeners.class,ExecutionStartEndListner.class})
public class SMITHS_TC_EditSitedetails extends KeywordUtil {
	String stepInfo="";
	int retryCount=getIntValue("retryCount");
	static int retryingNumber=1;
	@DataProvider
	public Object[][] testData() throws Exception {
		Object data[][] = TestDataProvider.excelReader(ConfigReader.getValue("testDataExcelPath"), "Edit_site_details");

		// System.out.println(Repository.getProperty("SSTestDataFilePath"));
		return data;
	}

	@Test( dataProvider = "testData", testName="SMITHS_TC_EditSitedetails",
			groups={"Regression"}, 
			description="Login : Verify whether user is able to login with valid username and password."
			)
	public void test(String sitename,String siterank, String editSitename, String editedRank) throws Throwable {
		try{
			setTestCaseID(getClass().getSimpleName());
			//======================BASIC SETTING FOR TEST==========================================================
			if(retryingNumber==1)
				initTest();
			//================== END BASIC SETTING ============================================================
			/*
				How to Test steps
			 		1. Define step info
			 		2. Log to report and Logger
			 		3. Perform Action
			 		4. Verify Action
			 		
			 		==================================
			 		Login Steps
			 		==================================
			 			stepInfo="Login to application";
						logStep(stepInfo);
						LoginPage.doLogin(ConfigReader.getValue("loginUser"), ConfigReader.getValue("loginPassword"));
						verifyStep(LoginPage.isLogin(), stepInfo);
					==================================
			 		Logout Steps
			 		==================================
		 				stepInfo="Logout from application";
		 				logStep(stepInfo);
		 				LoginPage.logOut();
		 				verifyStep(LoginPage.isLogout(), stepInfo);
		 	
			*/
			
		
			//.........Script Start...........................
			
			stepInfo="Open home page";
			logStep(stepInfo);
			HomePage.openHomePage();
			LoginPage.Login(ConfigReader.getValue("loginUser"), ConfigReader.getValue("loginPassword"));
			Smith_AddSite_details.addingNewSite(sitename,siterank);
			smith_Editsite_Details.editsite(sitename,editSitename,editedRank );
			
			
			
			//.........Script Start...........................
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
				   test(sitename,siterank, editSitename,editedRank);
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
}//End Test
	
	 
	

}
