package com.spicejet.bookflight.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.spicejet.bookflight.constants.Constants;
import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.reports.Reports;
import com.spicejet.bookflight.testbase.TestBase;

public class Utility1 extends TestBase {

	/***************************************************************************************
	 * This function will give you System date time in string format
	 * 
	 * @return This function will return date time in String format.
	 **************************************************************************************/
	public static String getDatetime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String founddate = dateFormat.format(date);
		// System.out.println(dateFormat.format(date)); // 2014/08/06 15:59:48

		String[] parts = founddate.split(" ");
		// String part1 = parts[0]; // 004
		String[] appenderpart1 = parts[0].split("/");
		String[] appenderpart2 = parts[1].split(":");
		String appender = appenderpart1[1] + appenderpart1[2] + appenderpart2[0] + appenderpart2[1] + appenderpart2[2];
		// System.out.println(appender);
		return appender;
	}

	/***************************************************************************************
	 * This function will give you System date time in string format
	 * 
	 * @return This function will return date time in String format.
	 **************************************************************************************/
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String founddate = dateFormat.format(date);
		String[] parts = founddate.split(" ");
		String[] appenderpart1 = parts[0].split("/");
		String appender = appenderpart1[1] + "-" + appenderpart1[2] + "-" + appenderpart1[0];
		return appender;
	}

	// ***************************************************************************
	/**
	 * This function loads the property file in buffer
	 * 
	 * @param filelocation Enter the file location of the property file as
	 *                     parameter.
	 * @return Properties. This function will return the object of the loaded
	 *         property file.
	 * @exception FileNotFoundException, IOException
	 */
	// ***************************************************************************
	public static Properties prop;
	public static InputStream file;

	public static Properties loadProperty(String filelocation) {
		prop = new Properties();
		try {
			file = new FileInputStream(filelocation);
			// Log.info("Location for Property file :"+filelocation);
		} catch (FileNotFoundException e) {
			Reports.fail("", e.toString());
			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (IOException e) {
			Reports.fail("", e.toString());
			e.printStackTrace();
		}
		return prop;
	}

	/************************************************************************************************
	 * This function will define specific locater type for keys in locaters property
	 * file
	 * 
	 * @param key Pass Locater key from Locaters property file
	 * @return Webelement This function will return WebElement based on specific
	 *         locater type.
	 * @see Key should exist in locaters file ending with specfic locater type eg.
	 *      _xpath, _id, _lnktxt.
	 * @exception ElementNotFoundException
	 *************************************************************************************************/
	protected static Properties pmpConfig = Utility1.loadProperty(Constants.config_pmp);
	public static Properties pmpRepository = Utility1.loadProperty(Constants.locater_pmp);
	public static String locvalue;
	public static String loctype;
	public static WebElement loct;

	public static WebElement getLocater(String key) {
		try {
			locvalue = pmpRepository.getProperty(key);
			if (key.endsWith("_id")) {
				loct = driver.findElement(By.id(locvalue));
				// ElementHighlight(loct);
				Log.info("Searching locater '" + locvalue + "' for locater Key '" + key + "'");
			} else if (key.endsWith("_xpath")) {
				loct = driver.findElement(By.xpath(locvalue));
				// ElementHighlight(loct);
				Log.info("Searching locater '" + locvalue + "' and locater type '" + key + "'");
			} else if (key.endsWith("_lnktxt")) {
				loct = driver.findElement(By.linkText(locvalue));
				// ElementHighlight(loct);
				Log.info("Searching locater '" + locvalue + "' and locater type '" + key + "'");
			} else if (key.endsWith("_name")) {
				loct = driver.findElement(By.name(locvalue));
				Log.info("Searching locater '" + locvalue + "' and locater type '" + key + "'");
				// ElementHighlight(loct);
			} else if (key.endsWith("_partlnktxt")) {
				Log.info("Searching locater '" + locvalue + "' and locater type '" + key + "'");
				// ElementHighlight(loct);
				Log.info("Searching locater '" + locvalue + "' and locater type '" + key + "'");
			} else if (key.endsWith("css")) {
				loct = driver.findElement(By.cssSelector(locvalue));
				// ElementHighlight(loct);
				Log.info("Searching locater '" + locvalue + "' and locater type '" + key + "'");
			} else if (key.endsWith("tagname")) {
				loct = driver.findElement(By.tagName(locvalue));
				// ElementHighlight(loct);
				Log.info("Searching locater '" + locvalue + "' and locater type '" + key + "'");
			} else {
				Log.info("Locaters not matched");
			}
		} catch (ElementNotFoundException e) {
			Reports.fail("", e.toString());
			e.printStackTrace();

		}
		return loct;

	}
	/***************************************************************************************
	 * This function will close the current session
	 * 
	 * @throws IOException
	 **************************************************************************************/
//	public static void closeBrowser() throws IOException {
//		try {
//			Log.info("Closing Browser");
//			driver.quit();
//
//		} catch (Exception e) {
//			Reports.fail("",e.toString());
//			e.printStackTrace();
//
//		}

	/***************************************************************************************
	 * This function will take screenshot on faliure of test cases.
	 * 
	 * @return filepath
	 * @see Use this function on negative secnarios.
	 * 
	 **************************************************************************************/

	public static String getfailScreenshot() {
		try {
			filepath = null;
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			filepath = System.getProperty("user.dir") + "\\Screenshots\\FailedScreenshot\\" + System.currentTimeMillis()
					+ ".png";
			FileUtils.copyFile(file, new File(filepath));
		} catch (IOException e) {
			Reports.fail("", e.toString());
			e.printStackTrace();
		}
		return filepath;
	}

	/***************************************************************************************
	 * This function is used to highlight element on GUI where the current and
	 * previous action has been performed.
	 * 
	 * @param element
	 **************************************************************************************/
	public static void ElementHighlight(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Reports.fail("", e.toString());
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: solid 2px green ');",
				element);
	}

	/*****************************************************************************************
	 * This function will dynamically wait for text on element to be present.
	 * 
	 * @param Key
	 * @param text
	 * @throws InterruptedException
	 * @exception Exception
	 ****************************************************************************************/
	public static void waitForTextToBeDisplayed(String Key) throws InterruptedException {
		Thread.sleep(2000);
		Log.info("Waiting for the Element to be visible");
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.withTimeout(200, TimeUnit.SECONDS);
		wait.pollingEvery(2, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(InvalidSelectorException.class);
		wait.until(ExpectedConditions.visibilityOf(Utility1.getLocater(Key)));
	}

	public static void waitForTextToBeDisplayed(String Key, String text) throws InterruptedException {

		Thread.sleep(2000);
		Log.info("Waiting for the text '" + text + "' to display");
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.withTimeout(200, TimeUnit.SECONDS);
		wait.pollingEvery(2, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(InvalidSelectorException.class);
		wait.until(ExpectedConditions.textToBePresentInElement(getLocater(Key), text));
	}

	public static void waitForElementClickable(String Key) throws InterruptedException {

		Thread.sleep(2000);
		Log.info("Waiting for the Element to be clickable");
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.withTimeout(200, TimeUnit.SECONDS);
		wait.pollingEvery(2, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(InvalidSelectorException.class);
		wait.ignoring(WebDriverException.class);
		wait.until(ExpectedConditions.elementToBeClickable(getLocater(Key)));
	}

	/*****************************************************************************************
	 * This function wait implicitly for mentioned time duration.
	 * 
	 * @exception Exception
	 ****************************************************************************************/
	public static void waitImplicit() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	/*****************************************************************************************
	 * This function will dynamically wait for pageload.
	 * 
	 * @exception Exception
	 ****************************************************************************************/
	public static void Elementwait() throws Exception {

		Log.info("Waiting for page load");
		Thread.sleep(1000);
		WebDriverWait explicitwait = new WebDriverWait(driver, 60);
		explicitwait.withTimeout(60, TimeUnit.SECONDS);
		explicitwait.pollingEvery(1, TimeUnit.SECONDS);
		explicitwait.ignoring(NoSuchElementException.class);
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img.timer.center-block")));
		Thread.sleep(500);
		// css=img.timer.center-block
		// css=.loading-panel
		// css=p.text-center
		// css=img.large-logo.center-block
	}

	/*******************************************************************************************
	 * This function will clean the framework and will delete the files and folder
	 * for specific mentioned time duration
	 * 
	 * @param daysBack
	 * @param dirWay
	 ******************************************************************************************/
	public static void FrameworkcleanUp(int daysBack, String dirWay) {

		File directory = new File(dirWay);
		if (directory.exists()) {

			File[] listFiles = directory.listFiles();
			long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);
			for (File listFile : listFiles) {
				if (listFile.lastModified() < purgeTime) {
					if (!listFile.delete()) {
						System.err.println("Unable to delete file: " + listFile);
					}
				}
			}
		}
	}

	public static List<WebElement> ElementCollection(String xpath) {
		List<WebElement> collections = driver.findElements(By.xpath(xpath));
		return collections;
	}

	public static void InitialiseExtentReport(String testcaseName, String testcaseDesc) {
		Reports.startTest(testcaseName, testcaseDesc);
	}

	public static void getCurrentDate() {
//		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		Date date = new Date();
		System.out.println(date);
		// String founddate = dateFormat.format(date);
		// System.out.println(founddate);
		// System.out.println(dateFormat);// 2014/08/06 15:59:48
		// return founddate;
		/*
		 * sys
		 * 
		 * String[] parts = founddate.split(" "); // String part1 = parts[0]; // 004
		 * String[] appenderpart1 = parts[0].split("/"); String[] appenderpart2 =
		 * parts[1].split(":"); String appender = appenderpart1[1] + appenderpart1[2] +
		 * appenderpart2[0] + appenderpart2[1] + appenderpart2[2];
		 */
		// System.out.println(appender);
		// return appender;
	}
}
