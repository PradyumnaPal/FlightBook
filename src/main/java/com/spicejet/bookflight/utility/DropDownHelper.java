
package com.spicejet.bookflight.utility;

import java.util.LinkedList;
import java.util.List;

//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.spicejet.bookflight.reports.Log;

public  class DropDownHelper {
	
//	private WebDriver driver;

//	public DropDownHelper(WebDriver driver) {
//		this.driver = driver;
//		Log.info("DropDownHelper : " + this.driver.hashCode());
//	}

	
	public  static void SelectUsingVisibleValue(WebElement element,String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		Log.info("Locator : " + element + " Value : " + visibleValue);
	}

	public static String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		Log.info("WebELement : " + element + " Value : "+ value);
		return value;
	}
	
	public static void SelectUsingIndex(WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		Log.info("Locator : " + element + " Value : " + index);
	}
	
	public static void SelectUsingVisibleText(WebElement element,String text){
		
		Select select = new Select(element);
		select.selectByVisibleText(text);
		Log.info("Locator : " + element + " Value : " + text);
		}
	
	
	public static List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
			Log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
}
