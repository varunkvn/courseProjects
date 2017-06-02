import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.*;
public class GoBack extends HttpServlet
{
public void init(ServletConfig sc)throws ServletException
{
super.init(sc);
}
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();
res.sendRedirect("http://localhost:8080/title2.html");
}
}