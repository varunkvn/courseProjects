import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cmodify2 extends HttpServlet
{
    Connection con;
    String type,per,des;
    PreparedStatement pst;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       type=req.getParameter("type");
       per=req.getParameter("per");
       des=req.getParameter("des");
       try
       {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
            pst=con.prepareStatement("update concession_master set percentage=?,description=? where type='"+type+"'");
            pst.setString(1,per);
            pst.setString(2,des);
            pst.executeUpdate();
            con.commit();
            out.println("<html><body>");
            out.println("<center><br><br>");
            out.println("<h3>Do You Want To Modify Another Record</h3>");
            out.println("<a href=\"http://localhost:8080/servlet/cmodify\">[ Yes ]</a><a href=\"http://localhost:8080/servlet/\">[ No ]</a>");
            out.println("</center>");
            out.println("</body></html>");
       }
       catch(Exception e)
       {
            out.println("Exception in connection:"+e);
       }
    }
}
