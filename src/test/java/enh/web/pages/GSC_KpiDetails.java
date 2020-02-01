package enh.web.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.ConfigReader;
import utilities.HtmlReportUtil;

public class GSC_KpiDetails extends HomePage {
	
	public static final By objectConfig = By.xpath("//a[@data-module='OBJECT_CONFIGURATION']");
	public static final By kpiDetailsLink = By.xpath("//a[@data-title='KPI Details']");
	public static final By searchBox = By.id("global_search_txt_GSC_VW_DIM_KPI_DETAIL");
	public static final By goBtn = By.id("global_search_btn_GSC_VW_DIM_KPI_DETAIL");
	public static final By kpiNameCount = By.xpath("//span[contains(.,'No records to view')]");
	public static final By addKpiDeatils = By.xpath("//button[@title='Add']");
	public static final By domain = By.id("DOMAIN");
	public static final By kpiName = By.id("KPI_NAME");
	public static final By frequency = By.id("FREQUENCY");
	public static final By units = By.id("UNITS");
	public static final By submitBtn = By.xpath("//*[@id='sData']");
	
	
	public static void kpiDetails() throws InterruptedException {
		
		hoverElement(objectConfig);
		click(kpiDetailsLink);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">Kpi Details </b> link clicked");
		inputText(searchBox, ConfigReader.getValue("KpiName"));
		HtmlReportUtil.stepInfo("Searched KPI Name :-<b style=\"color:green;\"> "+ConfigReader.getValue("KpiName")+"<b>");
		click(goBtn);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">Go </b> button");
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\">****************Add KPI Details *****************</b>");
		boolean kpiCount = getListElements(kpiNameCount).size()>0;
		if(kpiCount) {
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">"+ConfigReader.getValue("KpiName") +"</b> not found");
		click(addKpiDeatils);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">Add </b> button");
		inputText(domain, "Enhops_Domain_1");
		HtmlReportUtil.stepInfo("Entered Domain Name :- <b style=\"color:green;\">"+"Enhops_Domain_1 </b>" );
		inputText(kpiName, "EnhopsAutomation_KpiName_1");
		HtmlReportUtil.stepInfo("Entered KPI Name :- <b style=\"color:green;\"> "+"EnhopsAutomation_KpiName_1 </b>" );
		inputText(frequency, "Enhops_Frequency_1");
		HtmlReportUtil.stepInfo("Entered Frequency :- <b style=\"color:green;\"> "+"Enhops_Frequency_1 </b>" );
		inputText(units, "Enhops_Units_1");
		HtmlReportUtil.stepInfo("Entered units :- <b style=\"color:green;\"> "+"Enhops_Units_1 </b>" );
		click(submitBtn);
		delay(3000);
		getDriver().navigate().refresh();
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">Submit </b> button" );
		inputText(searchBox, ConfigReader.getValue("KpiName"));
		HtmlReportUtil.stepInfo(" Searched KPI Name :- <b style=\"color:green;\"> "+ConfigReader.getValue("KpiName")+"</b>" );
		delay(3000);
		click(goBtn);
		delay(3000);
		HtmlReportUtil.stepInfo("Clicked on <b style=\"color:green;\">GO </b>button");
		List<WebElement> kpiDetailsHeaderCount = getListElements(By.xpath("//table/thead/tr/th"));
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\">********************* Added KPI Details Verfication *****************</b>");		
		for (int i = 0; i < kpiDetailsHeaderCount.size(); i++) {
			int addedCount = i+1;			
			String headerValue= getElementText(By.xpath("//table/thead/tr/th["+addedCount+"]"));
				/*
				 * boolean valueCount = getListElements(By.xpath(
				 * "//table[@id='grid_GSC_VW_DIM_KPI_DETAIL']/tbody/tr[2]/td["+addedCount+"]")).
				 * size()>0; if(valueCount) {
				 * HtmlReportUtil.stepInfo("<b style=\"color:green;\">"+ConfigReader.
				 * getValue("KpiName") +" </b>found"); }else {
				 * HtmlReportUtil.stepInfo("<b style=\"color:green;\">"+ConfigReader.getValue(
				 * "KpiName") +"</b> not found"); }
				 */
			String value= getElementText(By.xpath("//table[@id='grid_GSC_VW_DIM_KPI_DETAIL']/tbody/tr[2]/td["+addedCount+"]"));
			HtmlReportUtil.stepInfo("<b style=\"color:black;\">"+headerValue+ " </b>- <b style=\"color:green;\">'"+value+"'</b>");					
		}		
			
		}else {
			HtmlReportUtil.stepInfo("<b style=\"color:green;\">"+ConfigReader.getValue("KpiName") +"</b> found");
			
		}
		HtmlReportUtil.stepInfo(" <b style=\"color:blue;\">KPI Details added & verifed sucessfully </b>");
		
	}
	
	
	
	
	

}
