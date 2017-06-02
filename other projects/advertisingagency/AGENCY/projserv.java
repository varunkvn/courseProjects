import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class projserv extends HttpServlet
{
Connection con;
Statement stmt;
PreparedStatement pstmt;
ResultSet rs;
String applno,trialno;
int appno,trial_no;
String str,str1;

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
trialno=req.getParameter("trialno");
appno=Integer.parseInt(applno);
trial_no=Integer.parseInt(trialno);
try
{
pstmt=con.prepareStatement("insert into trials values(?,?)");
pstmt.setInt(1,appno);
pstmt.setInt(2,trial_no);
pstmt.executeUpdate();
}catch(Exception e){}
pw.println("bill");

}
}