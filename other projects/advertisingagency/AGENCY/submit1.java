import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.*;
public class submit1 extends HttpServlet
{
Connection con;
Statement stmt,stmt1;
PreparedStatement pstmt; 
int appno,id;
String str;
ResultSet rs,rs2;
String party,submitid,projectid,preceipt,amount;
double pamount;
static Integer ano;

public void init(ServletConfig sc)throws ServletException
{
super.init(sc);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:advt","scott","tiger");
}catch(Exception e){}
}
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();
try
{
party=req.getParameter("party");
submitid=req.getParameter("submitid");
projectid=req.getParameter("projectid");
amount=req.getParameter("pamount");
pamount=Double.valueOf(amount).doubleValue();
preceipt=req.getParameter("preceipt");
pstmt=con.prepareStatement("insert into submit  values(?,?,?,?,?)");
pstmt.setString(1,submitid);
pstmt.setString(2,projectid);
pstmt.setString(3,party);
pstmt.setDouble(4,pamount);
pstmt.setString(5,preceipt);
pstmt.executeUpdate();
pw.println("<html><body background=\"/Beanco.jpg\">");
pw.println("<p><font color=blue size=24>amount  paid by the advt agency to the "+party+"amount of--> "+pamount+"</font></p>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("</body></html>");
}catch(Exception e){pw.println(e);}
}
}

