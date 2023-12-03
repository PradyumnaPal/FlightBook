package com.spicejet.bookflight.modules;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.reports.Reports;
import com.spicejet.bookflight.testbase.TestBase;
import com.spicejet.bookflight.utility.Utility;

public class Login extends TestBase {
	public static boolean UserLogin(String UserID, String Password) {
		Log.info("Preparing for log in with UserID=" + UserID + " and Password=" + Password);
		flag = false;
		try {
			waitForTextToBeDisplayed("btnLOGIN_name");
			Utility.setTextInTextbox(getLocater("txtBoxUserName_id"), ConfigFile.getProperty("userid"));
			Utility.setTextInTextbox(getLocater("txtBoxPassword_id"), ConfigFile.getProperty("password"));
			Utility.clickOnButton(getLocater("btnLOGIN_name"));
			if (Utility.verifyWinowTitle("Provider Management Portal")) {
				Log.info("User logged into PMP portal successfully");
				flag = true;
			}else{
				Reports.info("verifyWinowTitle", "verifyWinowTitle Failed");
			}

		} catch (Exception e) {
			Log.error(e.fillInStackTrace().toString());
			Reports.fail("EXCEPTION >>", e.fillInStackTrace().toString());
		}
		return flag;
	}
	
	public static void VerifyLoginPageSections( ) {
		if (Utility.verifyTextEquals(getLocater("tabBook_xpath"), "BOOK")) {
			Reports.pass("LogIn Page", "BOOK tab is selected");
		}else {
			Reports.fail("LogIn Page", "BOOK tab is NOT selected");
		}
	}
	public static void verifyBookFlightsLabel( ) {
		List<WebElement> li=driver.findElements(By.xpath("//*[text()[contains(.,'"+"Book Flight"+"')]]"));
		if (li.size()>0) {
			Reports.pass("LogIn Page", "Book Flight text is displayed");
		}else {
			Reports.fail("LogIn Page", "Book Flight text is displayed");
		}
	}
	
	
	
	
	

}
