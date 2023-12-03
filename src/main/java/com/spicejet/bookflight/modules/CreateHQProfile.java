package com.spicejet.bookflight.modules;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.reports.Reports;
import com.spicejet.bookflight.testbase.TestBase;
import com.spicejet.bookflight.utility.DropDownHelper;
import com.spicejet.bookflight.utility.Utility;

public class CreateHQProfile extends TestBase {
	public static boolean flag;

	public static boolean HQFillProfile() {
		flag = false;
		getLocater("OrganizationName_id").sendKeys("HQ121");
		Reports.pass("Profile>> Step 1.1", "OrganizationName entered");
		getLocater("LegalBusName_name").sendKeys("Lbusname");
		Reports.pass("Profile>> Step 1.2", "LegalBusName entered");
		getLocater("GSTNbr_id").sendKeys("GST@1");
		Reports.pass("Profile>> Step 1.3.1", "GSTNbr entered");
		getLocater("HSTNbr_id").sendKeys("HST@2");
		Reports.pass("Profile>> Step 1.3.2", "HSTNbr entered");

		getLocater("ContactsFirstName_id").sendKeys("Pradyumna");
		Reports.pass("Profile>> Step 2.1", "ContactsFirstName entered");
		getLocater("contactLastName_id").sendKeys("Pal");
		Reports.pass("Profile>> Step 2.2", "contactLastName entered");
		DropDownHelper.SelectUsingVisibleText(getLocater("Contactrole_xpath"), "Billing Clerk");
		Reports.pass("Profile>> Step 2.3", "Contactrole selected");

		if (AddUpdateContact(0, "U", true, "Cell", "9776123456", "4334")) {
			Reports.pass("Profile>> Step 4.4", "Contact updated for 0th row");
		} else {
			Reports.fail("Profile>> Step 4.4", "Contact not updated for 0th row");
		}
		getLocater("email_xpath").sendKeys("QA@yahoo.com");
		Reports.pass("Profile>> Step 4.4", "email entered");

		getLocater("Nextstep_id").click();
		Reports.pass("Profile>> Step 5", "Next step button is clicked");
		flag = true;

		return flag;
	}

	public static boolean AddUpdateContact(int rowIndex, String au, boolean preferred, String phoneType,
			String phoneNum, String ext) {
		flag = false;

		if (au.equalsIgnoreCase("A")) {
			getLocater("addaContact_xpath").click();
			Reports.pass("", "addaContact clicked");
		}
		if (preferred) {
			getLocater("preferred" + rowIndex + "_xpath").click();
			Reports.pass("", "preferred" + rowIndex + " selected");
		}

		DropDownHelper.SelectUsingVisibleText(getLocater("phoneType" + rowIndex + "_id"), phoneType);
		Reports.pass("Profile>> ", "phoneType" + rowIndex + " entered");
		getLocater("phoneNum" + rowIndex + "_id").sendKeys(phoneNum);
		Reports.pass("Profile>>", "phoneNum" + rowIndex + " entered");
		getLocater("ext" + rowIndex + "_id").sendKeys(ext);
		Reports.pass("Profile>>", "ext" + rowIndex + " entered");
		flag = true;
		return flag;
	}

	public static boolean VerifyHQProfilePageTitleandBreadCrumb() {
		flag = false;
		if (Utility.verifyTextEquals(getLocater("PageNamelblProfile_xpath"), "Head office information")) {
			Log.info("Page name is Head office information-Pass");
			if (Utility.verifyBreadCrumb(getLocater("ProfilePageBreadCrumb_xpath"),
					"Profile manager > Create a head office")) {
				Log.info("BreadCrumb Verification -Pass");
				flag = true;
			} else {
				Reports.info("BreadCrumb verification Fail", "Verification Failed");
			}

		} else {
			Reports.info("Page name is NOT Head office information", "Verification Failed");
		}
		return flag;
	}

	public static boolean VerifyProfileStepStatus() {
		flag = false;
		if ("Step 1 active".equals(getLocater("ProfileStepFlow_xpath").getAttribute("alt").trim()) ) {
			Log.info("Step Indicator is Step 1 active");
			flag = true;
		} else {
			Reports.info("Step Indicator is Not Step 1 active", "Verification Fail");
		}
		return flag;
	}
	public static boolean VerifyProfilePageInstruction() {
		flag = false;
		String Pageinst="Complete this workflow to create a head office. You will be required to associate an existing organization to this head office.";
		if (Pageinst.equals(getLocater("ProfileInstText_xpath").getText().trim()) ) {
			Log.info("Page instruction of Profile Page is correct");
			flag = true;
		} else {
			Reports.info("Page instruction of Profile Page is NOT correct", "Verification Fail");
		}
		return flag;
	}
	
	public static boolean VerifyProfilePageSections(String PageSection) {
		flag = false;
		List<WebElement> elements = driver.findElements(By.xpath(RepositoryFile.getProperty("PageSections_xpath")));

		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getText().equals(PageSection)) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			Reports.info("Page do not have section" + PageSection, "Verification Fail");
		} else {
			Log.info("Page have section" + PageSection);
		}
		return flag;
	}
}