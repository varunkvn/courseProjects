import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class update1 extends HttpServlet
{
    Connection con;
    Statement st;
    ResultSet rst;
    String code,str,str1;

    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       code=req.getParameter("code");
       System.out.println(code);
       try
       {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
           st=con.createStatement();
           rst=st.executeQuery("select description,percentage from cancellation_master where code='"+code+"'");
           while(rst.next())
           {
               str=rst.getString("description");
               str1=rst.getString("percentage");
           }
           out.println("<html><body>");
           out.println("<form method=post action=http://localhost:8080/servlet/update2 name=form1>"); 
           //out.println("<form method=post action=\"http://localhost:8080/servlet/update2\">");
           out.println("<center><table>");
           out.println("<caption><font size=5><b><u>Cancellation Details</u></b></font></caption>");
           out.println("<tr><td><label>Code:</label></td><td><input size=20 name=code value="+code+" readonly></td>");
           out.println("<td><label>Description:</label></td><td><input size=20 name=desc value="+str+"></td></tr>");
           out.println("<tr><td><label>Percentage:</label></td><td><input size=20 name=percent value="+str1+"></td></tr>");
           out.println("<table><tr><td align=center><input type=submit value=\" Insert \"></td></tr></table>");
           out.println("</table></center>");
           out.println("</body></html>");
           out.println("hello");
       }
       catch(Exception e)
       {
           out.println("Exception in connection:"+e);
       }
    }
}
