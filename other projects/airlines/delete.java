import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class delete extends HttpServlet
{
   Connection con;
   String Error;
   ResultSetMetaData rmt;
   ResultSet rst;
   Statement st;
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
         Error="Exception in init "+e;
      }
   }
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       out.println("<center><form method=post action=\"http://localhost:8080/servlet/delete1\">");
       out.println("<h3><u>Enter Flight Number To Be Deleted</u></h3>");
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
           out.println("<option>");
           for(int i=1;i<=count;i++)
           {
            out.println(rst.getString(i));
           }
          }
          out.println("</select>");
          out.println("<br><br><br>");
          out.println("<input type=submit value=submit>");
     }

       catch(Exception e)
       {
          out.println("Exception in connection "+e);
       }
   }
}
