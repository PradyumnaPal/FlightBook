package com.spicejet.bookflight.constants;

import com.spicejet.bookflight.testbase.*;

public class Constants {
	public static final String chromedriver_path = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";
	public static final String IEdriver_path = System.getProperty("user.dir") + "\\Drivers\\IEDriverServer.exe";

	public static final String TC_data_path_pmp=System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\ExcelData\\SpiceJetData.xlsx";
	public static int rownum  = 0;
	
	public static final String config_pmp = System.getProperty("user.dir")+ "\\src\\test\\resources\\objectRepository\\config.properties";
	public static final String DBQuery_pmp = System.getProperty("user.dir")+ "\\src\\test\\resources\\objectRepository\\DBQuery.properties";
	public static final String locater_pmp = System.getProperty("user.dir")+ "\\src\\test\\resources\\objectRepository\\locators.properties";
	
	public static final String htmlReport_pmp = System.getProperty("user.dir") + "\\ExecutionReports\\"+TestBase.getDate()+"\\"+TestBase.getDatetime()+".html";
	public static final String Screenshot_fail = System.getProperty("user.dir") + "\\Screenshots\\FailedScreenshots";
	public static final String Screenshot_pass = System.getProperty("user.dir") +"\\Screenshots\\SuccessScreenshots";
	
	}