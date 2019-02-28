package mains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MCQConnection
{
   public static Connection con;
   public static Connection connect()
   {
//	  System.out.println("B".compareTo("A"));
      if(con==null)
      {
		  try
	      {
	    	    String fullConnectionString="jdbc:mysql://127.0.0.1:3306/mcq";
	    	    Class.forName("com.mysql.jdbc.Driver");
//	    	    String password=F16Login.password;
	    	    String password="";
				con = DriverManager.getConnection(fullConnectionString,"root",password);
				System.out.println("Connected");
	      }
	      catch(ClassNotFoundException cnfe)
	      {
	         System.out.println("Class Not Found Exception");
	      }
	      catch(SQLException se)
	      {
	    	  System.out.println("SQL Exception in Connection");
	    	  se.printStackTrace();
	      }
      }
      return con;
   }
   public static void main(String[] args) {
	   connect();
   }
}
