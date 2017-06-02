import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class projserv1 extends HttpServlet
{
Connection con;
Statement stmt;
PreparedStatement pstmt;
ResultSet rs;
String applno,msg;
int appno;;

public void init(ServletConfig sc)throws ServletException
{
super.init(sc);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:advt","scott","tiger");
}catch(Exception e){System.out.println(e);}
}
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{

res.setContentType("text/html");
PrintWriter pw=res.getWriter();
applno=req.getParameter("applno");
appno=Integer.parseInt(applno);
try
{
stmt=con.createStatement();
rs=stmt.executeQuery("select message from user_applications where applno="+appno);
rs.next();
msg=rs.getString(1);
}catch(Exception e){pw.println(e);}
pw.println(msg);

}
}