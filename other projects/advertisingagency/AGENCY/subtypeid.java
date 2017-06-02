import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
public class subtypeid extends HttpServlet
{
Connection con;
Statement stmt,stmt1;
PreparedStatement pstmt; 
int appno,id;
String str;
ResultSet rs,rs2;
String applno,type,subtype;

public void init(ServletConfig sc)throws ServletException
{
super.init(sc);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:advt","scott","tiger");
}catch(Exception e){}
}
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();

applno=req.getParameter("applno");
type=req.getParameter("type");
appno=Integer.parseInt(applno);
subtype=req.getParameter("r1");
Cookie cookies=new Cookie(applno,subtype);
//cookies.setMaxAge(100);
res.addCookie(cookies);
if(type.equals("news"))
{
try
{
stmt=con.createStatement();
rs=stmt.executeQuery("select typeid from subtypes1 where applno="+appno);
rs.next();
str=rs.getString(1);
pstmt=con.prepareStatement("update subtypes1 set subtype=? where applno=?");

pstmt.setString(1,subtype);
pstmt.setInt(2,appno);
pstmt.executeUpdate();
con.commit();
pw.println("<html><body bgcolor=orange background=\"/Beanco.jpg\">");
pw.println("<form name=f1 action="+"http://localhost:8080/servlet/trials"+" method=post>");
pw.println("<h1 align=center>SUBTYPE ID</h1>");
pw.println("<center><font color=blue>Applno:<input type=text name=applno value="+applno+">");
pw.println("TypeId:<input type=text name=typeid value="+str+"><br>");
pw.println("halfqtr:<input type=radio name=r1 value=halfqtr><br><br>");
pw.println("qtr:<input type=radio name=r1 value=qtr><br><br>");
pw.println("half:<input type=radio name=r1 value=half><br><br>");
pw.println("full:<input type=radio name=r1 value=full><br><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a></font>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></form></body></html>");
}catch(Exception e){pw.println(e);}
}

else if(type.equals("vmedia"))
{
try
{
stmt=con.createStatement();
rs=stmt.executeQuery("select typeid from subtypes1 where applno="+appno);
rs.next();
str=rs.getString(1);
pstmt=con.prepareStatement("update subtypes1 set subtype=? where applno=?");
pstmt.setString(1,subtype);
pstmt.setInt(2,appno);
pstmt.executeUpdate();

pw.println("<html><body background=\"/Beanco.jpg\">");
pw.println("<form name=f1 action="+"http://localhost:8080/servlet/trials"+" method=post>");
pw.println("<h1 align=center>SUBTYPE ID</h1>");
pw.println("<center><font color=blue>Applno:<input type=text name=applno value="+applno+">");
pw.println("TypeId:<input type=text name=typeid value="+str+"><br>");
pw.println("15sec:<input type=radio name=r1 value=15sec><br><br>");
pw.println("30sec:<input type=radio name=r1 value=30sec><br><br>");
pw.println("45sec:<input type=radio name=r1 value=45sec><br><br>");
pw.println("60sec:<input type=radio name=r1 value=60sec><br><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a></font>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></form></body></html>");
}catch(Exception e){pw.println(e);}
}
else
{
try
{
stmt=con.createStatement();
rs=stmt.executeQuery("select typeid from subtypes1 where applno="+appno);
rs.next();
str=rs.getString(1);
pstmt=con.prepareStatement("update subtypes1 set subtype=? where applno=?");
pstmt.setString(1,subtype);
pstmt.setInt(2,appno);
pstmt.executeUpdate();

pw.println("<html><body bgcolor=yellow background=\"/Beanco.jpg\">");
pw.println("<form name=f1 action="+"http://localhost:8080/servlet/trials"+" method=post>");
pw.println("<h1 align=center>SUBTYPE ID</h1>");
pw.println("<center><font color=blue>Applno:<input type=text name=applno value="+applno+">");
pw.println("Typeid:<input type=text name=typeid value="+str+"><br>");
pw.println("15days:<input type=radio name=r1 value=15days><br><br>");
pw.println("30days:<input type=radio name=r1 value=30days><br><br>");
pw.println("45days:<input type=radio name=r1 value=45days><br><br>");
pw.println("60days:<input type=radio name=r1 value=60days><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a></font>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></form></body></html>");
}catch(Exception e){pw.println(e);}
}
}
}