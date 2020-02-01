package utilities;

//======  START A BLOCK OF RELATED FUNCTIONALITIES
// ~~~~~  END A BLOCK OF RELATED FUNCTIONALITIES

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Properties;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * @author EN
 */
public class Utility {
	private static String testCaseID = "";
	private static WebDriver driver = null;
	public static final boolean isRun = false;
	public static final String SUITENAME = Utility.getValue("suiteName");
	public static final int CELLNUMBER = 0;
	public static final boolean colFlag = false;
	public static final String USERDIR = "user.dir";

	// Set folder path for test results
	private static String RESULT_FOLDER_NAME = System.getProperty(USERDIR) + "\\ExecutionReports\\ExecutionReports";

	/*
	 * Property file handling functionalities
	 * ===========================================================
	 */
	/**
	 * @param filePath
	 * @return
	 */

	public static Properties loadPropertyFile(String filePath) {
		File file = new File(filePath);
		Properties prop = new Properties();
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
			prop.load(fileInput);
		} catch (Exception e) {

			LogUtil.errorLog("UtilityClass", e.getMessage());
		}
		return prop;
	}

	public static String getTestCaseID() {
		return testCaseID;
	}

	public static void setTestCaseID(String testCaseID) {
		Utility.testCaseID = testCaseID;
	}

	/**
	 * @param key
	 * @return
	 */
	public static int getIntValue(String key) {
		Properties prop = loadPropertyFile("src\\main\\resources\\ConfigFiles\\config.properties");
		String strKey = prop.getProperty(key);
		return Integer.parseInt(strKey);
	}

	/**
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		Properties prop = loadPropertyFile("src\\main\\resources\\ConfigFiles\\config.properties");

		return prop.getProperty(key);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * Initialize test case
	 * ===========================================================
	 */
	/**
	 * @param testCaseID
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void initTest() throws Exception {
		// Set Test browser name for reporting
		// Initialize HTML Report
		HtmlReportUtil.startTest(
				GlobalUtil.getCurrentSuiteName() + "-" + testCaseID + "-" + GlobalUtil.getCurrentBrowser(),
				GlobalUtil.getTestData().getTestDesc(), GlobalUtil.getCurrentSuiteName());
		// Check Y/N for this test from excel file based on suiteID

	}// End initTest()

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * Log Steps for report
	 * ===========================================================
	 */
	/**
	 * @param logStep
	 */
	public static void logStep(String logStep) {
		LogUtil.infoLog(Utility.class, logStep);
		HtmlReportUtil.stepInfo(logStep);
	}

	public static void logStepIndent(String logStep) {
		LogUtil.infoLog(Utility.class, logStep);
		HtmlReportUtil.stepInfoIdented(logStep);
	}

	public static void logStepError(String logStep) {
		LogUtil.infoLog(Utility.class, logStep);
		HtmlReportUtil.stepInfoWithError(logStep);
	}

	public static void logStepFail(String logStep) {
		LogUtil.infoLog(Utility.class, logStep);
		HtmlReportUtil.stepFail(logStep);
	}

	public static void logStepPass(String logStep) {
		LogUtil.infoLog(Utility.class, logStep);
		HtmlReportUtil.stepPass(logStep);
	}

	/**
	 * @param status
	 * @param logStep
	 */
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * Common functions for date time and file IO
	 * ===========================================================
	 */
	/**
	 * @return
	 */
	public static String getDateTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 
	 */
	public static void renameFile() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		String timeStamp = dateFormat.format(date);
		try {
			File oldFile = new File(System.getProperty(USERDIR) + Utility.getValue("testResultExcelPath"));
			String newFilePath = oldFile.getAbsolutePath().replace(oldFile.getName(), "") + "\\ReportHistory\\"
					+ timeStamp + "-TestResult.xls";
			File newFile = new File(newFilePath);

			FileUtils.copyFile(oldFile, newFile);
			LogUtil.infoLog(Utility.class, "History File successfully created... ");

		} catch (IOException e) {
			LogUtil.errorLog(Utility.class, "Exception caught", e);
		}
	}

	/**
	 * 
	 */
	public static void checkFileOpen() {
		String fileName = System.getProperty(USERDIR) + "\\ExecutionReports\\ExcelReport\\TestResult.xls";
		File file = new File(fileName);
		File sameFileName = new File(fileName);

		if (file.renameTo(sameFileName)) {

			LogUtil.infoLog(testCaseID, "**********TestResult.xls is closed**********");
		} else {
			JOptionPane.showMessageDialog(null, "TestResult.xls is opened");
			Thread.currentThread().stop();
		}
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public static String createZipFile() throws IOException {
		RESULT_FOLDER_NAME = RESULT_FOLDER_NAME.replace("\\", "/");
		String outputFile = RESULT_FOLDER_NAME + ".zip";
		FileOutputStream fos = new FileOutputStream(outputFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		packCurrentDirectoryContents(RESULT_FOLDER_NAME, zos);
		zos.closeEntry();
		zos.close();
		fos.close();
		return outputFile;
	}

	/**
	 * @param directoryPath
	 * @param zos
	 * @throws IOException
	 */
	public static void packCurrentDirectoryContents(String directoryPath, ZipOutputStream zos) throws IOException {
		for (String dirElement : new File(directoryPath).list()) {
			String dirElementPath = directoryPath + "/" + dirElement;
			if (new File(dirElementPath).isDirectory()) {
				packCurrentDirectoryContents(dirElementPath, zos);
			} else {
				ZipEntry ze = new ZipEntry(dirElementPath.replaceAll(RESULT_FOLDER_NAME + "/", ""));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(dirElementPath);
				byte[] bytesRead = new byte[512];
				int bytesNum;
				while ((bytesNum = fis.read(bytesRead)) > 0) {
					zos.write(bytesRead, 0, bytesNum);
				}

				fis.close();
			}
		}
	}

	/**
	 * @param dir
	 */
	public static void delDirectory(File dir) {
		File[] currList;
		Stack<File> stack = new Stack<File>();
		stack.push(dir);
		while (!stack.isEmpty()) {
			if (stack.lastElement().isDirectory()) {
				currList = stack.lastElement().listFiles();
				if (currList.length > 0) {
					for (File curr : currList) {
						stack.push(curr);
					}
				} else {
					stack.pop().delete();
				}
			} else {
				stack.pop().delete();
			}
		}
		//if (new File(System.getProperty(USERDIR) + "/ExecutionReports/ExecutionReports.zip").exists()) {
		//	delDirectory(new File(System.getProperty(USERDIR) + "/ExecutionReports/ExecutionReports.zip"));
		//}
	}

	/**
	 * @param driver
	 * @param testCaseID
	 * @return
	 * @throws IOException
	 */
	public static String takeScreenshot(WebDriver driver, String testCaseID) throws IOException {

		/*Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = dateFormat.format(date);
		timeStamp = timeStamp + "_";

		String path = System.getProperty(USERDIR) + "\\ExecutionReports\\HtmlReport\\"
				+ ConfigReader.getValue("screenshotPath") + "\\" + timeStamp + testCaseID + ".jpg";
		// Screenshot screenshot = new AShot().shootingStrategy(new
		// ViewportPastingStrategy(100)).takeScreenshot(driver);

	
		Screenshot screenshot = new AShot().takeScreenshot(driver);
	   // Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		File src = new File(path);
		LogUtil.infoLog(Utility.class, "Screenshot image path: " + src.getPath());
		ImageIO.write(screenshot.getImage(), "PNG", src);
		return ConfigReader.getValue("screenshotPath") + "\\" + timeStamp + testCaseID + ".jpg";*/
		TakesScreenshot ts = (TakesScreenshot) driver;
        String source = ts.getScreenshotAs(OutputType.BASE64);            
        return source;
	}

	public static String takeScreenshotWebElement(WebElement element, String testCaseID) throws IOException {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = dateFormat.format(date);
		timeStamp = timeStamp + "_";
		String path = System.getProperty(USERDIR) + "\\ExecutionReports\\HtmlReport\\"
				+ ConfigReader.getValue("screenshotPath") + "\\" + timeStamp + testCaseID + ".jpg";

		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.simple()).takeScreenshot(driver,
				element);
		File src = new File(path);
		LogUtil.infoLog(Utility.class, "Screenshot Image Path: " + src.getPath());
		ImageIO.write(screenshot.getImage(), "PNG", src);
		return ConfigReader.getValue("screenshotPath") + "\\" + timeStamp + testCaseID + ".jpg";
	}

	/**
	 * @param a
	 * @throws InterruptedException
	 */
	public static void pause(long a) throws InterruptedException {
		Thread.sleep(a);
	}

	/**
	 * @return the driver
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * @param driver
	 * the driver to set
	 */
	public static void setDriver(WebDriver driver) {
		Utility.driver = driver;
	}
	
	public static void moveFile(String outFileName, String sourcePath, String destinationPath, String fileextention) throws IOException {
	    File source = new File(sourcePath);    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
	    String ts=sdf.format(source.lastModified());
	    File destination=new File(destinationPath+outFileName + ts);
	    FileUtils.copyFile(source,destination);
	    System.out.println(" new file name is "+outFileName);
	}

	public static void moveFile( String sourcePath, String destinationPath, String fileextention) throws IOException {
	    File source = new File(sourcePath);    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
	    String ts=sdf.format(source.lastModified());
	    File destination=new File(destinationPath);
	    FileUtils.copyFile(source,destination);
	    System.out.println(" new file name is ");
	}
	
	
	
	   
}// End of class
