package com.spicejet.bookflight.modules;

import java.util.NoSuchElementException;

import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.reports.Reports;
import com.spicejet.bookflight.testbase.TestBase;
public class CreateHQAddress extends TestBase{
	public static boolean flag;


	public static boolean HQFillAddress(){
		flag=false;
		try{
			String pageName=getLocater("PageNamelbl_xpath").getText();
			if(pageName.equals("Head office mailing address")){
				Reports.pass("Address>> Step 1", "Page name verification Pass");
			}else{
				Reports.fail("Address>> Step 1", "Page name verification Fail");
			}

			flag=true;
		}catch(NoSuchElementException ex){
//			Log.error("Exception Occurred",ex);
			flag=false;

		}
		return flag;
	}
}
