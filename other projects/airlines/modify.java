import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class modify extends HttpServlet
{
 Connection con;
 Statement st;
 ResultSetMetaData rmt;
 ResultSet rst;
 String Error;
 public void init(ServletConfig config)throws ServletException
 {
     super.init(config);
     try
     {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
     }
     catch(Exception e)
     {
       Error="Exception in init"+e;
     }
 }
 public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
 {
     PrintWriter out=res.getWriter();
     res.setContentType("text/html");
     out.println("<center><form method=post action=\"http://localhost:8080/servlet/modify2\">");
     out.println("<h3><u>Select Flight Number To Be Modified</u></h3>");
     out.println("Flight Number:<select size=multiple name=fno>");
     try
     {
          st=con.createStatement();
          rst=st.executeQuery("select flight_no from flight_master");
          rmt=rst.getMetaData();
          int count=rmt.getColumnCount();
          for(int i=1;i<=count;i++)
          out.println(rmt.getColumnName(i));
          while(rst.next())
          {
//           out.println("<option>");
           for(int i=1;i<=count;i++)
           {
            out.println("<option>");
            out.println(rst.getString(i));
           }
          }
          out.println("</select>");
          out.println("<br><br><br>");
          out.println("<input type=submit value=submit>");
     }
     catch(Exception e1)
     {
      out.println("Exception in connection:"+e1);
     }
     out.println("<form> </center>");
     out.println("</body></html>");
 }
}
