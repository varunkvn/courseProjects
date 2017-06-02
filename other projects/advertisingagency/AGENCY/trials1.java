import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
public class trials1 extends HttpServlet
{
String applno,subid,trialno;
public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();
applno=req.getParameter("applno");
subid=req.getParameter("subid");
trialno=req.getParameter("trialno");
if(trialno.equals("1"))
{
pw.println("<html><body bgcolor=fuchsia background=\"/Beanco.jpg\">");
pw.println("<head><h1 align=center>TRIALS PAGE</h1></head>");
pw.println("<form  action="+"http://localhost:8080/servlet/trialapp "+"method=post>");
pw.println("<center>Applno:<input type=text name=applno value="+applno+">");
pw.println("SubtypeId:<input type=text name=subid value="+subid+"><br><br>");
pw.println("<input type=radio name=r1 value=1 >Ist Trial<br><br>");
pw.println("<input type=radio name=r1 value=2 checked=true>IInd Trial<br><br>");
pw.println("<input type=radio name=r1 value=3>IIIrd Trial<br><br>");
pw.println("<input type=radio name=r1 value=4>More Than Above<br><br>");
pw.println("<select name=img size=1><option value=Globe>Globe</option>");
pw.println("<option value=dolphins>Dolphins</option><option value=disc>Disc</option><option value=butterfly>Butterfly</option></select><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></form></body></html>");
}
else if(trialno.equals("2"))
{
pw.println("<html><body bgcolor=navy background=\"/Beanco.jpg\">");
pw.println("<head><h1 align=center>TRIALS PAGE</h1></head>");
pw.println("<form  action="+"http://localhost:8080/servlet/trialapp "+"method=post>");
pw.println("<center>Applno:<input type=text name=applno value="+applno+">");
pw.println("SubtypeId:<input type=text name=subid value="+subid+"><br><br>");
pw.println("<input type=radio name=r1 value=1 >Ist Trial<br><br>");
pw.println("<input type=radio name=r1 value=2 >IInd Trial<br><br>");
pw.println("<input type=radio name=r1 value=3 checked=true>IIIrd Trial<br><br>");
pw.println("<input type=radio name=r1 value=4>More Than Above<br><br>");
pw.println("<select name=img size=1><option value=Globe>Globe</option>");
pw.println("<option value=dolphins>Dolphins</option><option value=disc>Disc</option><option value=butterfly>Butterfly</option></select><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></form></body></html>");
}
else
{
pw.println("<html><body bgcolor=gray background=\"/Beanco.jpg\">");
pw.println("<head><h1 align=center>TRIALS PAGE</h1></head>");
pw.println("<form  action="+"http://localhost:8080/servlet/trialapp "+"method=post>");
pw.println("<center>Applno:<input type=text name=applno value="+applno+">");
pw.println("SubtypeId:<input type=text name=subid value="+subid+"><br><br>");
pw.println("<input type=radio name=r1 value=1 >Ist Trial<br><br>");
pw.println("<input type=radio name=r1 value=2>IInd Trial<br><br>");
pw.println("<input type=radio name=r1 value=3>IIIrd Trial<br><br>");
pw.println("<input type=radio name=r1 value=4 checked=true>More Than Above<br><br>");
pw.println("<select name=img size=1><option value=Globe>Globe</option>");
pw.println("<option value=dolphins>Dolphins</option><option value=disc>Disc</option><option value=butterfly>Butterfly</option></select><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></form></body></html>");
}
}
}