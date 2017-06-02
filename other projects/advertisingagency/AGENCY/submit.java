import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import submits.subif;
import java.rmi.*;
public class submit extends HttpServlet
{
Connection con;
Statement stmt,stmt1;
PreparedStatement pstmt; 
int appno,id;
String str;
ResultSet rs,rs2;
String name,value,pid,sid,pr;
double amount;
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
 Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
             value = c.getName();
             name = c.getValue();
            
        }
try
{
subif sif=(subif)Naming.lookup("kalyan");
amount=sif.paid_amount(Integer.parseInt(value));
pid=sif.project_id();
sid=sif.submit_id();
pr=sif.paid_receipt();
}catch(java.rmi.NotBoundException e){pw.println(e);}
pw.println(value);
pw.println("<html>");
pw.println("<head><title>FOR OFFICE USE ONLY</title>");
pw.println("<body bgcolor=lime background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit1"+">");
pw.println("<h1 align=center>SUBMISSION DETAILS</h1>");
pw.println("<h6 align=center>FOR OFFICE USE ONLY</h6>");


pw.println("SubmissionId:<input type=text name=submitid value="+sid+"><br><br>");
pw.println("ProjectId:<input type=text name=projectid value="+pid+"><br><br>"); pw.println("PaidAmount:<input type=text name=pamount value="+amount+"><br><br>"); 
pw.println("PaidReceipt:<input type=text name=preceipt value="+pr+"><br><br>"); 
pw.println("PartyName:<input type=text name=party value="+name+"><br><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</form></center></body></html>");
}
}
