import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class update2 extends HttpServlet
{
   Connection con;
   String code,desc,per;
   PreparedStatement pst;
   public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       code=req.getParameter("code");
       desc=req.getParameter("desc");
       per=req.getParameter("percent");
       try
       {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
            pst=con.prepareStatement("update cancellation_master set description=?,percentage=? where code='"+code+"'");
            pst.setString(1,desc);
            pst.setString(2,per);
            pst.executeUpdate();
            con.commit();
            out.println("<html><body>");
            out.println("<center>");
            out.println("<br><br>");
            out.println("<h3>Do you want to continue</h3>");
            out.println("<a href=\"http://localhost:8080/servlet/cancel1\">[ Yes ]</a><a href=\"http://localhost:8080/servlet/admin\">[ No ]</a>");
            out.println("</center>");
            out.println("</body></html>");
       }
       catch(Exception e)
       {
           out.println("Exception in connection:"+e);
       }
   }
}
