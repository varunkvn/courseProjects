import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cancel extends HttpServlet
{
   Connection con;
   Statement st;
   ResultSet rst;
   String str;
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws  ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       try
       {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
            st=con.createStatement();
            rst=st.executeQuery("select 'c' || to_char(nvl(substr(max(code),2,5),100)+1) from cancellation_master");
            while(rst.next())
            str=rst.getString(1);
            out.println("<html><body>");
            out.println("<center>");
            out.println("<table border=0>");
            out.println("<form method=post action=\"http://localhost:8080/servlet/cinsert\">");
            out.println("<caption><font size=5><b><u> Cancellation Form </u></b></font></caption>");
            out.println("<tr><td><label>Code:</label></td><td><input size=20 name=code value='"+str+"' readonly></td>");
            out.println("<td><label>Description:</label></td><td><input size=20 name=desc></td></tr>");
            out.println("<tr><td><label>Percentage:</label></td><td><input size=20 name=percent></td></tr>");
            out.println("<table><tr><td align=center><input type=submit value=\" Insert \"></td></tr></table>");
            out.println("</form>");
            out.println("</table>");
            out.println("</body></html>");
       }
       catch(Exception e)
       {
           out.println("Exception in connection:"+e);
       }
   }
}


