import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class concession1 extends HttpServlet
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
            pst=con.prepareStatement("insert into concession_master values(?,?,?)");
            pst.setString(1,type);
            pst.setString(2,per);
            pst.setString(3,des);
            pst.executeUpdate();
            con.commit();
            out.println("<html><body>");
            out.println("<center><br>");
            out.println("<h3>Do You Want To Continue</h3>");
            out.println("<a href=\"http://localhost:8080/servlet/concession\">[ Yes ]</a><a href=\"http://localhost:8080/servlet/\">[ No ]</a>");
            out.println("</center>");
            out.println("</body></html>");
       }
       catch(Exception e)
       {
            out.println("Exception in connection:"+e);
       }
    }
}
