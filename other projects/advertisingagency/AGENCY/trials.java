import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
public class trials extends HttpServlet
{
Connection con;
Statement stmt,stmt1;
PreparedStatement pstmt,pstmt1; 
int appno,id;
String str;
ResultSet rs,rs2;
String applno,typeid,subtypeid;
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
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();
applno=req.getParameter("applno");
typeid=req.getParameter("typeid");
subtypeid=req.getParameter("r1");
try
{
pstmt1=con.prepareStatement("insert into netcharges(typeid,subtypeid) values(?,?)");
pstmt1.setString(1,typeid);
pstmt1.setString(2,subtypeid);
pstmt1.executeUpdate();

}catch(Exception e){}
if(typeid.startsWith("N"))
{
try
{
pstmt=con.prepareStatement("update subtypes1 set subtypeid=? where applno=?");
pstmt.setString(1,subtypeid);
pstmt.setInt(2,Integer.parseInt(applno));
pstmt.executeUpdate();
pw.println("<html><body bgcolor=olive background=\"/Beanco.jpg\">");
pw.println("<form name=f1 action="+"http://localhost:8080/servlet/trialapp"+" method=post>");
pw.println("<h1 align=center>TRIALS PAGE</h1>");
pw.println("<center><font color=blue>Applno:<input type=text name=applno value="+applno+">");
pw.println("SubtypeId:<input type=text name=subid value="+subtypeid+"><br><br>");
pw.println("<input type=radio name=r1 value=1 checked=true>Ist Trial<br><br>");
pw.println("<input type=radio name=r1 value=2>IInd Trial<br><br>");
pw.println("<input type=radio name=r1 value=3>IIIrd Trial<br><br>");
pw.println("<input type=radio name=r1 value=4>More Than Above<br><br>");
pw.println("<h5 align=center>Select images here</h6>");
pw.println("<select name=img size=1><option value=Globe>Globe</option>");
pw.println("<option value=dolphins>Dolphins</option><option value=disc>Disc</option><option value=butterfly>Butterfly</option></select><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a></font>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></form></body></html>");
}catch(Exception e){pw.println(e);}

}
else if(typeid.startsWith("V"))
{
try
{
pstmt=con.prepareStatement("update subtypes1 set subtypeid=? where applno=?");
pstmt.setString(1,subtypeid);
pstmt.setInt(2,Integer.parseInt(applno));
pstmt.executeUpdate();
pw.println("<html><body bgcolor=cyan background=\"/Beanco.jpg\">");
pw.println("<form name=f1 action="+"http://localhost:8080/servlet/trialapp"+" method=post>");
pw.println("<h1 align=center>TRIALS PAGE</h1>");
pw.println("<center><font color=blue>Applno:<input type=text name=applno value="+applno+">");
pw.println("SubtypeId:<input type=text name=subid value="+subtypeid+"><br><br>");
pw.println("<input type=radio name=r1 value=1 checked=true>Ist Trial<br><br>");
pw.println("<input type=radio name=r1 value=2>IInd Trial<br><br>");
pw.println("<input type=radio name=r1 value=3>IIIrd Trial<br><br>");
pw.println("<input type=radio name=r1 value=4>More Than Above<br><br>");
pw.println("<h5 align=center>Select images here</h6>");
pw.println("<select name=img size=1><option value=Globe>Globe</option>");
pw.println("<option value=dolphins>Dolphins</option><option value=disc>Disc</option><option value=butterfly>Butterfly</option></select><br>");
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
pstmt=con.prepareStatement("update subtypes1 set subtypeid=? where applno=?");
pstmt.setString(1,subtypeid);
pstmt.setInt(2,Integer.parseInt(applno));
pstmt.executeUpdate();
pw.println("<html><body bgcolor=red background=\"Beanco.jpg\">");
pw.println("<form name=f1 action="+"http://localhost:8080/servlet/trialapp"+" method=post>");
pw.println("<h1 align=center>TRIALS PAGE</h1>");
pw.println("<center><font color=blue>Applno:<input type=text name=applno value="+applno+">");
pw.println("SubtypeId:<input type=text name=subid value="+subtypeid+"><br><br>");
pw.println("<input type=radio name=r1 value=1 checked=true>Ist Trial<br><br>");
pw.println("<input type=radio name=r1 value=2>IInd Trial<br><br>");
pw.println("<input type=radio name=r1 value=3>IIIrd Trial<br><br>");
pw.println("<input type=radio name=r1 value=4>More Than Above<br><br>");
pw.println("<h5 align=center>Select images here</h6>");
pw.println("<select name=img size=1><option value=Globe>Globe</option>");
pw.println("<option value=dolphins>Dolphins</option><option value=disc>Disc</option><option value=butterfly>Butterfly</option></select><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a></font>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></form></body></html>");
}catch(Exception e){pw.println(e);}
}
}
}