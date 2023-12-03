package com.spicejet.bookflight.CreateHeadoffice;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.spicejet.bookflight.modules.Login;
import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.reports.Reports;
import com.spicejet.bookflight.testbase.TestBase;

public class ATS002_SpiceJet_VerifyLogInPage extends TestBase {

	@BeforeMethod
	public void setUp() {
		DOMConfigurator.configure("log4j.xml");
		Reports.startTest("ATS001_SpiceJet_VerifyLogInPage", "ATS001_SpiceJet_VerifyLogInPage started");
		Log.startTest("ATS001_SpiceJet_VerifyLogInPage", "ATS001_SpiceJet_VerifyLogInPage started");
	}

	@Test()
	public void VerifyLogInPage() throws Exception {
		Assert.assertTrue(LaunchBrowser(ConfigFile.getProperty("browser")), "Launching Browser.");
		Assert.assertTrue(NavigateToUrl(ConfigFile.getProperty("url")), "Navigated to SpiceJet Url.");
		Login.verifyBookFlightsLabel();
	}

		
}
