/*
 Project            :
 File Name          :

Author:
Date  :
Homepage:
*/



import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import javax.swing.*;

 public class AccessDbase  {

   Statement stmt,tmpStmt,stmt2;
   Connection conn1,conn2;
   ResultSet uprs,tmpuprs;
   PreparedStatement pstmt;

 public AccessDbase()
 {
  }


public void connectionDb()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn1=DriverManager.getConnection("jdbc:odbc:ServerDb");
			conn2=DriverManager.getConnection("jdbc:odbc:ServerDb");
			stmt=conn1.createStatement();
			stmt2 =  conn1.createStatement();
			tmpStmt=conn2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			System.out.println("----Successfully connected----");
		}
		catch(SQLException sqle)
		{
			System.out.println("Error:"+sqle);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Error:"+cnfe);
		}
	}
  }