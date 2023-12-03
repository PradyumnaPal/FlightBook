package com.spicejet.bookflight.modules;


import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;

import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.utility.Utility1;



public class AccountActions extends Utility1{
	public static boolean UserLogin(String UserID, String Password) throws IOException, InterruptedException {
		Log.info ("Preparing for log in with UserID="+UserID+" and Password="+Password);
		flag = false;
		try{
			waitForTextToBeDisplayed("LogInbtn_id");
			getLocater("UserName_name").clear();
			getLocater("UserName_name").sendKeys(pmpConfig.getProperty("userid"));
			getLocater("Password_name").clear();
			getLocater("Password_name").sendKeys(pmpConfig.getProperty("password"));
			getLocater("LogInbtn_id").click();
			Log.info("Log in button is clicked");
			flag=true;
		}catch(Exception e)
		{
			Log.info("UserLogin>> Log in Butoon not displayed on Login Screen");
		}
		
		return flag;
	}
	
}
