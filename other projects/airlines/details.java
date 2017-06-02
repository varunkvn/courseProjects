import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class details extends HttpServlet
{
   Connection con;
   String str1,str2,str3;
   Statement st;
   ResultSet rst;
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
          st=con.createStatement();
          out.println("<html><body>");
          out.println("<center><table width=300 border=1>");
          out.println("<caption><font size=5><u>Flight Details</u></font></caption>");
          out.println("<tr><th>Flight_No</th><th>Source</th><th>Destination</th></tr>");
          rst=st.executeQuery("select flight_no,source,detination from flight_master");
          while(rst.next())
          {
             str1=rst.getString("flight_no");
             str2=rst.getString("source");
             str3=rst.getString("detination");
             out.println("<tr><td><a href=\"http://localhost:8080/servlet/date?flight_no='"+str1+"'\">"+str1+"</a></td><td>"+str2+"</td><td>"+str3+"</td></tr>");
          }
       }
       catch(Exception e)
       {
          out.println("Exception in connection :"+e);
       }
   }
}

