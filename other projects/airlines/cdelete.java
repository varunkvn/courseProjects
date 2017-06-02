import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cdelete extends HttpServlet
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
        con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
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
     out.println("<center><form method=post action=\"http://localhost:8080/servlet/cdelete1\">");
     out.println("<h3><u>Select Concession Type To Be Deleted</u></h3>");
     out.println("Type Of Concession:<select size=multiple name=type>");
     try
     {
          st=con.createStatement();
          rst=st.executeQuery("select type from concession_master");
          rmt=rst.getMetaData();
          int count=rmt.getColumnCount();
          for(int i=1;i<=count;i++)
          out.println(rmt.getColumnName(i));
          while(rst.next())
          {
           out.println("<option>");
           for(int i=1;i<=count;i++)
           {
            out.println(rst.getString(i));
           }
          }
          out.println("</select>");
          out.println("<br><br><br>");
          out.println("<input type=submit value=\" Delete \">");
     }
     catch(Exception e1)
     {
      out.println("Exception in connection:"+e1);
     }
     out.println("<form> </center>");
     out.println("</body></html>");
 }
}

