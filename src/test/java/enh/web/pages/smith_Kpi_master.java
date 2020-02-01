package enh.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.KeywordUtil;

public class smith_Kpi_master extends KeywordUtil {
	public static By clickOnHomeDashboard = By.xpath("//*[@id=\"menu\"]/li[3]/a/i");
	public static By clickOnKpiMaster = By.xpath("//span[contains(.,'Kpi Master')]");
	public static By clickOnAdd = By.id("add_btn_GSC_VW_KPI_MASTER");
	public static By enterKpiName = By.id("KPI_DETAIL_ID");
	public static By enterYear = By.id("FISCAL_YEAR");
	public static By enterPeriod = By.id("FISCAL_PERIOD");
	public static By entersiteName = By.id("KPI_SITE_ID");
	public static By enterKpiValue = By.id("KPI_VALUE");
	public static By enterKpiMasterId = By.id("KPI_MASTER_ID");
	
	public static By Submitbutton = By.xpath("(//*[@class= 'btn btn-sm btn-default btn-outline'])[1]");
	// verification
	
	public static By Search  = By.id("global_search_txt_GSC_VW_KPI_MASTER");
	public static By clickOnGobutton = By.id("global_search_btn_GSC_VW_KPI_MASTER");
	
	
	public static void addKpiDetails (String KPiName,String Year,String period,String sitename,String kpivalue, String kpimaster, String searchdata  ) throws Exception {
		Thread.sleep(1000);
		hoverElement(clickOnHomeDashboard);
		click(clickOnKpiMaster);
		click(clickOnAdd);
		click(enterKpiName);
		Thread.sleep(1000);
		selectByVisibleText(enterKpiName, KPiName);
		click(enterYear);
		Thread.sleep(1000);
		selectByVisibleText(enterYear, Year);
		click(enterPeriod);
		Thread.sleep(1000);
		selectByVisibleText(enterPeriod, period);
		click(entersiteName);
		Thread.sleep(1000);
		selectByVisibleText(entersiteName, sitename);
		inputText(enterKpiValue, kpivalue);
		inputText(enterKpiMasterId, kpimaster);
		click(Submitbutton);
		Thread.sleep(5000);
		inputText(Search, searchdata);
		click(clickOnGobutton);
		Thread.sleep(3000);
		
		List rows = getDriver().findElements(By.xpath("//*[@class= 'ui-jqgrid-bdiv']/div/table/tbody/tr"));
		int rowssize = rows.size();
		System.out.println(rowssize);
	List<WebElement> col = getDriver().findElements(By.xpath("//*[@class= 'ui-jqgrid-bdiv']/div/table/tbody/tr[2]/td"));
		int colcount  = col.size();
			
		for ( int i=2;i< colcount;i++)	{
		String last =	getDriver().findElement(By.xpath("//*[@class= 'ui-jqgrid-bdiv']/div/table/tbody/tr["+rowssize+"]/td["+i+"]")).getText();
		System.out.println(last);
		if (last.contains("FY 2024")){
			String expected = last;
			String actual = "FY 2024";
			Assert.assertEquals(actual, expected);
			//KeywordUtil.verifyInputText(locator, data);
		}
		
		
			
		
		
		
		}
		
		
		
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*List<WebElement> headerValues = getListElements(By.xpath("//table[@role=\"presentation\"]/thead/tr/th"));
		for (int i = 0; i < headerValues.size(); i++) {
			int asdad = i+1;
			String ddfg = getElementText(By.xpath("//table[@role=\"presentation\"]/thead/tr/th["+asdad+"]"));
			if(ddfg.equalsIgnoreCase("created on")) {
			List<WebElement> rowValuesCount = getListElements(By.xpath("//table[@id='grid_GSC_VW_KPI_MASTER']/tbody/tr"));
			for (int j = 1; j < rowValuesCount.size(); j++) {
			int nhshfd = j+1;
			
				String bhmdb = getElementText(By.xpath("//table[@id=\"grid_GSC_VW_KPI_MASTER\"]/tbody/tr["+nhshfd+"]/td["+asdad+"]"));
				System.out.println(bhmdb);
		}
			}
			*/
			
		
			
		/*List<WebElement> rows = getDriver().findElements(By.xpath("//*[@id ='gbox_grid_GSC_VW_KPI_MASTER']/div[3]/div[4]/div/table/tbody/tr"));
		 int sizeofrows = rows.size();
		List<WebElement> col = getDriver().findElements(By.xpath("//*[@id ='gbox_grid_GSC_VW_KPI_MASTER']/div[3]/div[4]/div/table/tbody/tr/td"));
		int sizeofcol = rows.size();
		
		for ( int i=2;i<sizeofrows;i++) {
			for ( int j=2;j<sizeofcol;j++) {
			
		
	    String result  = getDriver().findElement(By.xpath("//*[@id ='gbox_grid_GSC_VW_KPI_MASTER']/div[3]/div[4]/div/table/tbody/tr["+i+"]/td["+j+"]")).getText();
	    if (result.equalsIgnoreCase("")){
	    	String expected = result;
	    	String actual  = "15-Oct-2019 02:42:22 PM";
	    }
	    }
		
		}*/
		
		
	
	
	
	
	
	
	
	

