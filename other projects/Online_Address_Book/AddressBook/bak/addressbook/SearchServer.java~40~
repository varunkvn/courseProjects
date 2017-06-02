package addressbook;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class SearchServer extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html";

  //Initialize global variables
  public void init() throws ServletException {
  }

  //Process the HTTP Get request
  String name="";
  String phone="";
  String address="";
  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    String s=request.getParameter("search");
    out.println("Print on servlet  "+s);
    ArrayList userinfo=searchDetail(s);


   HttpSession session=request.getSession();
   session.setAttribute("info",userinfo);
//   session.setAttribute("name",name);
//   session.setAttribute("ph",phone);
//   session.setAttribute("add",address);
   response.sendRedirect("http://localhost:8080/WebModule1/SearchDetail.jsp");
  }

  public ArrayList searchDetail(String sname){
    ArrayList ar=new ArrayList();
   try {

     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     Connection con = DriverManager.getConnection("jdbc:odbc:dsn","","");
     Statement st = con.createStatement();
     ResultSet rs = st.executeQuery("select * from record where name='"+sname+"' ");
     while (rs.next()) {
      // System.out.println(rs.getString("name"+"    "+"phone"+"    "+"address"));

      name=rs.getString("name");
      phone=rs.getString("phone");
      address=rs.getString("address");
      ar.add(0,name);
      ar.add(1,phone);
      ar.add(2,address);
      System.out.println(name);
      System.out.println(phone);
      System.out.println(address);

    }
  }
    catch (Exception ex) { }
    return ar;
   }

  //Clean up resources
  public void destroy() {
  }
}
