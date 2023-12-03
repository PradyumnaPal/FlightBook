package com.spicejet.bookflight.TestNG;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.spicejet.bookflight.constants.Constants;
import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.reports.Reports;
import com.spicejet.bookflight.testbase.*;
import com.spicejet.bookflight.utility.ExcelUtils;


public class IntializeFiles {
	public static Properties pmpConfig;
	public static Properties pmpRepository;

//	@BeforeSuite
//	public static void IntialiseAllFiles()
//	{
//		DOMConfigurator.configure("log4j.xml");
//		Log.info("**************** Basic Automation Services Started********************");
//		Log.info("******************** Log4j Intialised **********************");
//		Log.info("******************** Extent Report Intialised **********************");
//		ExcelUtils.intialiseExcel(Constants.TC_data_path_pmp);
//		pmpConfig = TestBase.loadProperty(Constants.config_pmp);
//		pmpRepository = TestBase.loadProperty(Constants.locater_pmp);
//
//	}

	@AfterTest
	public void FlushReport() throws Exception {
		Reports.flush();
	}
}
