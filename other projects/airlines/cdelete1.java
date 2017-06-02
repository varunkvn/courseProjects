import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cdelete1 extends HttpServlet
{
   Connection con;
   String type;
   Statement st;
   public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       type=req.getParameter("type");
       try
       {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
           st=con.createStatement();
           int x=st.executeUpdate("delete from concession_master where type='"+type+"'");
           con.commit();
           out.println("<html><body>");
           out.println("<center><br><br>");
           out.println("<h3>Do You Want To Delete Another Record</h3>");
           out.println("<a href=\"http://localhost:8080/servlet/cdelete\">[ Yes ]</a><a href=\"http://localhost:8080/servlet\">");
           out.println("</center>");
           out.println("</body></html>");
       }
       catch(Exception e)
       {
           out.println("Exception in connection:"+e);
       }
   }
}
