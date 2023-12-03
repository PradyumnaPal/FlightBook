package com.spicejet.bookflight.testbase;

import java.net.URL;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.spicejet.bookflight.constants.Constants;
import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.reports.Reports;
import com.spicejet.bookflight.utility.WebEventListener;

public class BrowserHelper {
	public static WebDriver driver;
	public static boolean flag;
	public static Properties ConfigFile = TestBase.loadProperty(Constants.config_pmp);
	public static Properties RepositoryFile = TestBase.loadProperty(Constants.locater_pmp);

	public static boolean LaunchBrowser(String browser) {

		try {
			Log.info("Launching Environment as per pre-condition");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			DesiredCapabilities cap1 = DesiredCapabilities.internetExplorer();

			if (browser.equalsIgnoreCase("chrome")) {
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				System.setProperty("webdriver.chrome.driver", Constants.chromedriver_path);
				driver = new ChromeDriver();
				Log.info("**********Chrome Browser Launched*************");
				Reports.info("Launching Browser", "Chrome Browser Launched successfully");
			} else if (browser.equalsIgnoreCase("IE")) {
				cap1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				System.setProperty("webdriver.ie.driver", Constants.IEdriver_path);
				driver = new InternetExplorerDriver();
				Log.info("************IE Browser Launched**************");
				Reports.info("Launching Browser", "Internet Explorer browser launched successfully");
			}
			EventFiringWebDriver e_driver = new EventFiringWebDriver(driver);
			// Now create object of EventListerHandler to register it with
			// EventFiringWebDriver
			WebEventListener eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			Long pageloadtimeout = Long.parseLong(ConfigFile.getProperty("pageloadtimeout").trim());
			int ImplicitlyWait = Integer.parseInt(ConfigFile.getProperty("implicitwait").trim());

			if (pageloadtimeout < 60) {
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			} else {
				driver.manage().timeouts().pageLoadTimeout(pageloadtimeout, TimeUnit.SECONDS);
			}

			if (ImplicitlyWait < 60) {
				TestBase.SetImplicitlyWait(60);
			} else {
				TestBase.SetImplicitlyWait(ImplicitlyWait);
			}
			flag = true;
			Log.info("Envionment Launched");
		} catch (Exception e) {
			Log.error(e.fillInStackTrace().toString());
			Reports.fail(e.fillInStackTrace().toString(), "");
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean NavigateToUrl(String url) {
		flag = false;
		try {
			driver.get(url);
			Reports.info("Navigate to URL", url);
			flag = true;
		} catch (Exception e) {
			Reports.fail("", e.fillInStackTrace().toString());
			e.printStackTrace();
		}
		return flag;

	}

	public static boolean OpenMultiplesession(String browser, String nodeURL) {
		flag = false;
		try {
			Log.info("Launching Environment as per pre-condition");

			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", Constants.chromedriver_path);
				DesiredCapabilities cap_chrome = DesiredCapabilities.chrome();
				cap_chrome.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap_chrome.setPlatform(Platform.WINDOWS);

				driver = new RemoteWebDriver(new URL(nodeURL), cap_chrome);
				Log.info("**********Chrome Browser Launched*************");
			} else if (browser.equalsIgnoreCase("IE")) {
				DesiredCapabilities cap_IE = DesiredCapabilities.internetExplorer();
				cap_IE.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				System.setProperty("webdriver.ie.driver", Constants.IEdriver_path);
				driver = new RemoteWebDriver(new URL(nodeURL), cap_IE);
				Log.info("************IE Browser Launched**************");
			}
			driver.manage().window().maximize();

			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void goBack() {
		driver.navigate().back();
		Log.info("");
	}

	public void goForward() {
		driver.navigate().forward();
		Log.info("");
	}

	public void refresh() {
		driver.navigate().refresh();
		Log.info("");
	}

	public Set<String> getWindowHandles() {
		Log.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());

		if (index < 0 || index > windowsId.size()) {
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		driver.switchTo().window(windowsId.get(index));
		Log.info(String.valueOf(index));
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());
		driver.switchTo().window(windowsId.get(0));
		Log.info("");
	}

	public void switchToParentWithChildClose() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());

		for (int i = 1; i < windowsId.size(); i++) {
			Log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}

	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		Log.info(nameOrId);
	}
}
