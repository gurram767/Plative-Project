package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.mail.MessagingException;

import org.testng.IExecutionListener;

import utilities.ConfigReader;
import utilities.Delete;
import utilities.GlobalUtil;
import utilities.LogUtil;
import utilities.ReportFactoryDB;
import utilities.SendMail;
import utilities.Utility;
import utilities.WriteToFile;

public class ExecutionStartEndListner extends Utility implements IExecutionListener {



	//This method will be the Starting point for whole test process with TestNG
	@Override
	public void onExecutionStart() {
		
		
		
		//delete video before running test
		Delete.DeleteAFile();
		
		/*//Video Recording
		try {
			recordTest = new ScreenRecorder();
			recordTest.startRecording("AutomationExecution");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	/*This method will be the executed at last when for whole test process with TestNg is finished*/
	@Override
	public void onExecutionFinish() {
		
		// TODO Auto-generated method stub
		LogUtil.infoLog("TestProcess End", "Test process has ended");
		
		/*//End video record
		try {
			recordTest.stopRecording();
			recordTest.releaseRecordingResources();
		
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		
	//1. Send Mail functionality
		/*if (GlobalUtil.getCommonSettings().getEmailOutput().equalsIgnoreCase("Y"))
			try {
				SendMail.sendEmailToClient();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
	//Extent Report Finish
	
	//3. Report open for view

		String htmlReportFile = System.getProperty("user.dir") + "\\" + Utility.getValue("HtmlReportFullPath");
		File f = new File(htmlReportFile);
		if (f.exists()) {

			try {
				Process p = Runtime.getRuntime()
						.exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe \"" + htmlReportFile
								+ "\"");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//4. Generate Comparison sheet 
				ReportFactoryDB.switchOn();
				// Local flat file SQLite database
				ReportFactoryDB.initSqlite(Paths.get("").toAbsolutePath().toString() + "\\ExecutionReports\\DB");
				ReportFactoryDB.getComparisonReport(GlobalUtil.getCommonSettings().getProjectName());
						
		//5. Rename test file
		GlobalUtil.renameFile();
		
		try {
			moveFile("appLog", ConfigReader.getValue("applogSourcePath"), ConfigReader.getValue("applogDestinationPath"), ".txt");
			moveFile("TestReport", ConfigReader.getValue("htmlSourcePath"), ConfigReader.getValue("htmlDestinationPath"), ".html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Write "Completed" on Teststatus file
				WriteToFile.writetoFile("Completed");
				//delDirectory("./ExecutionReports/ExeVideo");
		
	}

}
