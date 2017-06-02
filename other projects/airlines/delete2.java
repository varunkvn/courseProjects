import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class delete2 extends HttpServlet
{
    Connection con;
    Statement st;
    String code;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       code=req.getParameter("code");
       try
       {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
           st=con.createStatement();
           int x=st.executeUpdate("delete from cancellation_master where code='"+code+"'");
           con.commit();
           out.println("<html><body>");
           out.println("<center>");
           out.println("<h3>Do You Want To Continue</h3>");
           out.println("<a href=\"http://localhost:8080/servlet/cancel2\">[ Yes ] </a><a href=\"http://localhost:8080/servlet/admin\">[ No ]</a>");
           out.println("</body></html>");

       }
       catch(Exception e)
       {
          out.println("Exception in connection:"+e);
       }
    }
}
