package com.spicejet.bookflight.reports;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.spicejet.bookflight.constants.Constants;
import com.spicejet.bookflight.testbase.TestBase;

public class Reports{
	public static ExtentReports reports = new ExtentReports(Constants.htmlReport_pmp, false);

	public static ExtentTest test;

	/**
	 * @param testName : This param will the test case name for particular test
	 *                 case.
	 * @param desc     : This param is the description of the test cases
	 */
	public static void startTest(String testName, String desc) {
		test = reports.startTest(testName, desc);
		addSystemInfo();
		assignAuthor(System.getProperty("user.name"));
		TestScriptCategeory("Smoke Suite");
	}

	public static void addSystemInfo() {
		Map<String, String> systemInfo = new HashMap<>();
		systemInfo.put("Selenium Version", "2.53.1");
		reports.addSystemInfo(systemInfo);
	}

	public static void assignAuthor(String authorName) {
		test.assignAuthor(authorName);
	}

	public static void TestScriptCategeory(String testscriptType) {
		test.assignCategory(testscriptType);
	}

	
	/* This function will end the test case */
	public static void endTest() {
		reports.endTest(test);
	}

	/*** This function will flush all logs in report */
	public static void flush() {
		reports.flush();
	}
	
	
	public static void pass(String pagename, String desc) {
		String imagepath = TestBase.getPassedScreenshot();
		test.log(LogStatus.PASS, pagename, desc + test.addScreenCapture(imagepath));
		Log.info(pagename + " :: " + desc);
	}

	public static void fail(String pagename, String desc) {
		String imagepath = TestBase.getFailedScreenshot();
		test.log(LogStatus.FAIL, pagename, desc + test.addScreenCapture(imagepath));
		Log.error(pagename + ">> " + desc);
	}

	public static void info(String Stepname, String desc) {
		test.log(LogStatus.INFO, Stepname, desc);
		Log.info(Stepname + " :: " + desc);
	}
	public static void warning(String details) {
		// test.log(LogStatus.WARNING, details,"p{font-size:20px;}
		// .test{background-color:#000 !important;color:#fff !important;}");
		test.log(LogStatus.INFO, " ", "This will be <span style='font-weight:bold;'>" + details + "</span>");
	}


	public static void DifferentiateTestScipt(String desc) {
		test.log(LogStatus.INFO, "", "<span style='font-weight:bold;color:red'>" + desc + "</span>");
	}

	public static void attachScreenshot(String details, String imagePath) {
		test.log(LogStatus.PASS, details + test.addScreenCapture(imagePath));
	}

	public static void attachfailScreenshot(String pagename, String details, String imagepath) {
		test.log(LogStatus.FAIL, pagename, details + test.addScreenCapture(imagepath));
	}

}
