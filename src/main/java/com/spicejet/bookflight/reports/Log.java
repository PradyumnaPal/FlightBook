package com.spicejet.bookflight.reports;
import org.apache.log4j.Logger;
public class Log {
	public static Logger logger=Logger.getLogger(Reports.class.getName());

	public static void info(String desc) {
		logger.info(desc);
	}
	
	public static void error(String errMsg) {
		logger.error(errMsg);
	}
	
	public static void debug(String desc) {
		logger.debug(desc);
	}

	public static void startTest(String testName, String desc) {
		logger.info("**************--------------------------------------**********************");

		logger.info("###########################        " + desc + "     ###########################");

		logger.info("**************--------------------------------------**********************");
	}
	
	public static void endTest() {
		logger.info("---------------- END TEST ---------------------------------");
		logger.info("---------------- END TEST ---------------------------------");
		logger.info("---------------- (:)(:)(:) ---------------------------------");
	}
}
