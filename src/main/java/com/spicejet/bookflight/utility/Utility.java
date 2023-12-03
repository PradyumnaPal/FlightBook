package com.spicejet.bookflight.utility;

import org.openqa.selenium.WebElement;

import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.reports.Reports;
import com.spicejet.bookflight.testbase.TestBase;


public class Utility extends TestBase{


	public static synchronized boolean verifyElementPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			 isDispalyed= element.isDisplayed();
			 Log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			Log.info("Element not found " + ex);
		}
		
		return isDispalyed;
	}
	
	public static synchronized boolean verifyElementNotPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			 element.isDisplayed();
			 Log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			Log.info("Element not found " + ex);
			isDispalyed = true;
		}
		
		return isDispalyed;
	}
	
	public static synchronized boolean verifyTextEquals( WebElement element,String expectedText) {
		boolean flag = false;
		try {
			String actualText=element.getText();
			if(actualText.equals(expectedText)) {
				Log.info("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag=true;
			}
			else {
				Log.info("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag;
			}
		}
		catch(Exception ex) {
			Log.info("actualText is :"+element.getText()+" expected text is: "+expectedText);
			Log.info("text not matching" + ex);
			return flag;
		}
	}
	
	public static synchronized boolean verifyWinowTitle(String ExpTitle) {
		boolean flag=false;
		try {
			String actualtitle=driver.getTitle();
			if(ExpTitle.equals(actualtitle)){
				flag=true;
				Log.info("verifyWinowTitle is passed for >>"+ExpTitle);
			}else{
				flag=false;
				Log.info("verifyWinowTitle is failed for >>"+ExpTitle);
			}
		}
		catch(Exception e) {
			Reports.fail("Exception >>", e.fillInStackTrace().toString());
		}
		return flag;
	}
	
	public static synchronized boolean verifyBreadCrumb( WebElement element,String expectedText) {
		boolean flag = false;
		try {
			String actualText=element.getText().replace(" ", "");
			expectedText=expectedText.replace(" ", "");
			if(actualText.equals(expectedText)) {
				Log.info("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag=true;
			}
			else {
				Log.info("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag;
			}
		}
		catch(Exception ex) {
			Log.info("actualText is :"+element.getText()+" expected text is: "+expectedText);
			Log.info("text not matching" + ex);
			return flag;
		}
	}
	
	
	public String readValueFromElement(WebElement element) {

		if (null == element){
			Log.info("weblement is null");
			return null;
		}

		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			Log.error(e.fillInStackTrace().toString());
//			Reporter.log(e.fillInStackTrace().toString());
			return null;
		}

		if (!displayed){
			return null;
		}
		String text = element.getText();
		Log.info("weblement valus is.."+text);
		return text;
	}
	
	public String readValueFromInput(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		String value = element.getAttribute("value");
		Log.info("weblement valus is.."+value);
		return value;
	}

	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			Log.info("element is displayed.."+element);
			return true;
		} catch (Exception e) {
			Log.error(e.fillInStackTrace().toString());
//			Reporter.log(e.fillInStackTrace().toString());
			return false;
		}
	}

	protected boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			Log.info("element is displayed.."+element);
			return false;
		} catch (Exception e) {
			Log.error(e.fillInStackTrace().toString());
//			Reporter.log(e.fillInStackTrace().toString());
			return true;
		}
	}

	protected String getDisplayText(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		return element.getText();
	}


	public static synchronized String getElementText( WebElement element) {
		if (null == element) {
			Log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
		} catch (Exception ex) {
			Log.info("Element not found " + ex);
//			Reporter.log(ex.fillInStackTrace().toString());
		}
		return elementText;
	}
	
	public static void setTextInTextbox(WebElement element, String strValue) {
		element.clear();
		element.sendKeys(strValue);
		Log.info("Value set in Textbox >>" + element);
	}
	
	public static void clickOnButton(WebElement element) {
		element.click();
		Log.info("Button successfully clicked >>" + element);
	}
	public static void SelectRadioOption(WebElement element) {
		element.click();
		Log.info("Radio option selected successfully >>" + element);
	}
	
	public static String getElementAttribute(WebElement element, String strAttributeName) {
		if (null == element) {
			Log.info("weblement is null");
			return null;
		}
		if (null==strAttributeName) {
			Log.info("strAttributeName is null");
			return null;
		}
		
		String attrbVal= element.getAttribute(strAttributeName);
		Log.info("Retrived Attribute value="+attrbVal);
		return attrbVal;
	}
	
	public static boolean VerifyElementAttribute(WebElement element, String strAttributeName,String strExpvalue) {
		flag=false;
		if (null == element) {
			Log.info("weblement is null");
			return flag;
		}
		if (null==strAttributeName) {
			Log.info("strAttributeName is null");
			return flag;
		}
		
		if (null==strExpvalue) {
			Log.info("strExpvalue is null");
			return flag;
		}
		
		String actattrbVal= element.getAttribute(strAttributeName);
		Log.info("Retrived Attribute value="+actattrbVal);
		if(strExpvalue.equals(actattrbVal)) {
			Log.info("actualText is :"+actattrbVal+" expected text is: "+strExpvalue);
			return flag=true;
		}
		else {
			Log.info("actualText is :"+actattrbVal+" expected text is: "+strExpvalue);
			return flag;
		}
	}
}