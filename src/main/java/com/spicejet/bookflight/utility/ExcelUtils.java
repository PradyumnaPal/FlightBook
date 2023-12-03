package com.spicejet.bookflight.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spicejet.bookflight.constants.*;
import com.spicejet.bookflight.reports.Log;
import com.spicejet.bookflight.reports.Reports;


public class ExcelUtils {
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFCell excelData;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static InputStream file;
	public static String value, data, filepath;
	public static FileOutputStream fout;



	public static void intialiseExcel(String filepath ) {
		file = null;
		Log.info("**************** Loading Test Data ******************");

		try {
			file = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			Reports.fail("",e.toString());
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(file);

		} catch (IOException e) {
			Reports.fail("",e.toString());
			e.printStackTrace();
		}

	}


	/**
	 * This function will set status of Test Execution status in Excel sheet
	 * 
	 * @param sheetname
	 * @param Excelpath
	 * @param rownum
	 * @param status
	 */
	public static void setScriptStatus(String Excelpath,String sheetname,int rownum, String status) {
		file = null;
		try {
			file = new FileInputStream(Excelpath);
		} catch (FileNotFoundException e) {
			Reports.fail("",e.toString());
			e.printStackTrace();

		}
		try {
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetname);
		} catch (IOException e) {
			Reports.fail("",e.toString());
			e.printStackTrace();
		}
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells() - 1;
		row = sheet.getRow(rownum);
		cell = row.createCell(colCount);
		cell.setCellValue(status);
		try {
			fout = new FileOutputStream(Excelpath);

		} catch (FileNotFoundException e) {
			Reports.fail("",e.toString());
			System.out.println("Unable to locate Excel ");
			e.printStackTrace();

		}
		try {
			workbook.write(fout);
		} catch (IOException e) {
			Reports.fail("",e.toString());
			System.out.println("unable to set Excel Data");
		}
	}

	/***************************************************************************************************************
	 * This function will write data in particular cell defined by specific row
	 * number and column number
	 * 
	 * @param Result
	 *            this is Result that needs to be write in sheet number
	 * @param rownum
	 *            This param is specfic rownum where result need to be set
	 * @param colnum
	 *            This param is specific column number where result needs to be
	 *            set
	 * @exception FileNotFoundException,
	 *                IOException
	 **************************************************************************************************************/
	public static void SetExcelData(String Result, int rownum, int colnum) {
		row = sheet.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(Result);
		try {
			fout = new FileOutputStream(Constants.TC_data_path_pmp);

		} catch (FileNotFoundException e) {
			Reports.fail("",e.toString());
			System.out.println("Unable to locate Excel ");
			e.printStackTrace();

		}
		try {
			workbook.write(fout);
		} catch (IOException e) {
			Reports.fail("",e.toString());
			System.out.println("unable to set Excel Data");
		}
	}


	/**************************************************************************************************************
	 * This will return data of particular row number and column number in
	 * intialised excel file
	 * 
	 * @param rownum
	 *            This rownum is the specific row number in intialised sheet
	 * @param colnum
	 *            This colnum is specfic column number in intialised sheet
	 * @return It will return result in passed rownum and colnum
	 * @throws IOException
	 *************************************************************************************************************/
	public static String getExcelData(int rownum, int colnum) throws IOException {
		data = null;
		excelData = sheet.getRow(rownum).getCell(colnum);
		try {
			data = excelData.getStringCellValue();

		} catch (Exception e) {
			// e.printStackTrace();
			DataFormatter formatter = new DataFormatter(); // creating formatter
			// using the default
			// locale
			Cell cell = sheet.getRow(rownum).getCell(colnum);
			data = formatter.formatCellValue(cell);
		}

		return data;
	}

	/**************************************************************************************************************
	 * Read Excel Data
	 ************************************************************************************************************* */
	public static Object[][] readData(String sheetname) {

		Object[][] Exceldata = null;
		try {
			sheet = workbook.getSheet(sheetname);
			int startrow = 1;
			int startcol = 0;
			int totalrow = sheet.getLastRowNum();
			int totalcol = sheet.getRow(0).getPhysicalNumberOfCells();
			// System.out.println(totalcol);
			int ci, cj;
			Exceldata = new Object[totalrow][totalcol];
			ci = 0;
			for (int i = startrow; i <= totalrow; i++, ci++) {
				cj = 0;
				for (int j = startcol; j < totalcol; j++, cj++) {

					Exceldata[ci][cj] = getExcelData(i, j);
					System.out.println("Data store at index-- " + "Data[" + ci + "]" + "[" + cj + "]==>>" + "[" + i
							+ "]" + "[" + j + "]" + "--->" + Exceldata[ci][cj]);

				}

			}
			Log.info("*************** Test Data Lodaed *******************");
		} catch (Exception e) {
			Reports.fail("",e.toString());
			e.printStackTrace();
		}
		return (Exceldata);
	}


}

