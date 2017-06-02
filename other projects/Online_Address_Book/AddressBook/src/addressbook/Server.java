package addressbook;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Server extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html";

  //Initialize global variables
  public void init() throws ServletException {
  }

  //Process the HTTP Get request

  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    String name=request.getParameter("AdminName");
    String pass= request.getParameter("AdminPassword");
    System.out.println(name);

    HttpSession session=request.getSession();
    session.setAttribute("username",name);

    //response.sendRedirect("http://localhost:8080/WebModule1/EnterDetail.jsp");

//            setAttribute("UserName",name);


    boolean verify=authUser(name,pass);
    if(verify==true){
     response.sendRedirect("http://localhost:8080/WebModule1/usermain.jsp");
      System.out.println("User Authenticated");
    }
    else{
    out.println("You enter invalid User Name and PAss word pls try again");
   response.sendRedirect("http://localhost:8080/WebModule1/Relogin.jsp");
    System.out.println("Authentication Failed");
    }

  }

  public boolean authUser(String name,String pass){
    boolean flag=false;
      String user="";
      String pwd="";
    ResultSet rs;
    try {

      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      Connection con = DriverManager.getConnection("jdbc:odbc:dsn","","");
      Statement st = con.createStatement();
      rs = st.executeQuery("select * from info where user='"+name+"'");
      while (rs.next()) {
//        System.out.println(rs.getString("user"));
//        System.out.println(rs.getString("password"));
       user=rs.getString("user");
       pwd=rs.getString("password");
//
     if (user.equals(name) && pwd.equals(pass)){
       flag=true;
      // break;
      // System.out.println("User Authenticated");
     }
     else
       flag=false;

 //      System.out.println("Authentication Failed");

      }
   }
    catch (Exception ex) { }
    return flag;
  }

 //Clean up resources
  public void destroy() {
  }
}
