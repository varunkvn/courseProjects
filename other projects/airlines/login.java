import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class login extends HttpServlet
{
    Connection con;
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
            out.println("<html><body>");
            out.println("<center><pre>");
            out.println("<form method=post action=\"http://localhost:8080/servlet/login1\">");
            out.println("<h3>Enter the password </h3>");
            out.println("<br>");
            out.println("User-Id :<input size=20 name=user value=administration readonly>");
            out.println("Password:<input type=password size=20 name=pass>");
            out.println("<input type=submit value=\" Login \">");
            out.println("</form>");
            out.println("</center></pre>");
            out.println("</body></html>");
        }
        catch(Exception e)
        {
            out.println("Exception in connection :"+e);
        }
    }
}
