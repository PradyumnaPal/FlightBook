//package com.spicejet.bookflight.DBConnection;
//
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import com.spicejet.bookflight.reports.*;
//
///**
// * 
// * @author amitkumar.bhardwaj
// *
// */
//
//public class ConnectDB {
//
//	public static Statement stmnt;
//	public static Connection connection = null;
//	public static ResultSet rs;
//	public static List<HashMap<String,Object>> list ;
//	
//	public static HashMap<String, Object> row;
//	public static String finalquery;
//	public static List z,z1;
//
//	/**
//	 * This function will Establish connection with oracle database
//	 */
//	public static void EstablishConnection() {
//		
//
//		Log.info("-------- Establish Oracle JDBC Connection ---------");
//
//		try {
//
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			Log.fail("********************** Unable to Connect to Database ***********************");
//
//		}
//
//		try {
//			
//			connection = DriverManager.getConnection("jdbc:oracle:thin:@tbsubdb:1540:tbsub", "tqa_user", "tqa4d1sh");
//
//		} catch (SQLException e) {
//
//			Log.fail("******************** Connection Failed! ************************");
//			e.printStackTrace();
//
//		}
//
//		if (connection != null) {
//			Log.info("*************** Connection established sucessfully********************");
//		} else {
//			Log.fail("**********************Failed to make connection!*************************");
//		}
//		try {
//			stmnt = connection.createStatement();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	/**
//	 * 
//	 * @param query
//	 * this param will pass raw query
//	 * @return
//	 * this function will return the data from database as per query
//	 */
//	public static List<HashMap<String,Object>> getResult(String query,Map<String, String> parameterValue) {
//		try {
//			
//		String finalq1 = Query(query,parameterValue);
//			rs = stmnt.executeQuery(finalq1);
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int col = rsmd.getColumnCount();
//			 list = new ArrayList<HashMap<String,Object>>();
//			while (rs.next())
//			{
//				 row = new HashMap<String, Object>(col);
//				for(int i=1;i<=col;i++)
//				{
//					row.put(rsmd.getColumnName(i),rs.getObject(i));
//				}
//				list.add(row);
//			}
//			connection.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//
//	}
//	
//	public static String Query(String query,Map<String, String> parameterValue)
//	{
//	Map<String, String> parameterValue1 =  parameterValue;
//	int count =0;
//	 z = new ArrayList<Object>();
//	 z1 = new ArrayList<Object>();
//	//String x = "select * from sub_transfer_account WHERE new_account like ('#accountnum') order by transfer_date desc";
//	int a = query.length();
//	//System.out.println(a);
//	for (int i = 0; i < query.length(); i++) {
//	    char charAt = query.charAt(i);
//	    //System.out.println("testing2.main()"+charAt);
//	    if (charAt == '#')
//	    {
//	    for(int j=i;j<query.length();j++)
//	    {
//	    char charAt1 = query.charAt(j);
//	    if(charAt1 == ',' || charAt1 == ' ' || charAt1 == '\'')
//	    {
//	    String d = query.substring(i+1, j);
//	    z1.add(d);
//	    break;
//	    }
//	    }
//	    count++;
//	    z.add(i);
//	    }
//	}
//	//System.out.println("testing2.main()"+count);
//	//System.out.println("testing2.main()"+z);
//	//System.out.println("testing2.main()"+z1);
//	for(int i=0;i<z1.size();i++)
//	{
//	Iterator it = parameterValue1.entrySet().iterator();
//	    while (it.hasNext()) {
//	        Map.Entry pair = (Map.Entry)it.next();
//	        //System.out.println(pair.getKey() + " = " + pair.getValue());
//	        if(z1.get(i).toString().equalsIgnoreCase(pair.getKey().toString()))
//	        {
//	        	query = query.replace('#'+z1.get(i).toString(), pair.getValue().toString());
//	        }
//	    }
//	}
//	System.out.println("Final Query----------- "+query);
//	return query;
//	}
//
//}
