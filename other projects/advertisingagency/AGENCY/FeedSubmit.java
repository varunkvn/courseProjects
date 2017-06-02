import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.*;
public class FeedSubmit extends HttpServlet
{
PreparedStatement pstmt;
Connection con;
ResultSet rs;
Statement stmt;
String firstname,lastname,msg;
public void init(ServletConfig sc)throws ServletException
{
super.init(sc);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:advt","scott","tiger");
System.out.println("connection established");
}catch(Exception e){}
}
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();
firstname=req.getParameter("firstname");
lastname=req.getParameter("lastname");
msg=req.getParameter("views");
try
{
pstmt=con.prepareStatement("insert into feedback   values(?,?,?,?,?,?,?,?,?,?,?)");
pstmt.setString(1,firstname);
pstmt.setString(2,lastname);
pstmt.setString(3,req.getParameter("email"));
pstmt.setString(4,req.getParameter("sexgroup"));
pstmt.setString(5,req.getParameter("address"));
pstmt.setString(6,req.getParameter("city"));
pstmt.setDouble(7,Double.valueOf(req.getParameter("pincode")).doubleValue());
pstmt.setString(8,req.getParameter("state"));
pstmt.setString(9,req.getParameter("country"));
pstmt.setString(10,req.getParameter("occupation"));
pstmt.setString(11,msg);
pstmt.executeUpdate();
pw.println("<html><body>");
pw.println("<font color=blue size=10><p>"+msg+"</p>");
pw.println("<h3 align=center>Thank for giving best suggesstion Mr./Mrs"+lastname+"   "+firstname+"</h3></font>");

pw.println("</body></html>");
}
catch(Exception e){pw.println(e);}

}
}