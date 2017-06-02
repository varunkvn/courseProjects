import java.rmi.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import submits.subif;
public class subifclient extends HttpServlet 
{
double amount;
String pr,sparty,sid,pid;

public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,ServletException
{
try
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();
subif sif=(subif)Naming.lookup("kalyan");
amount=sif.paid_amount(1001);
pid=sif.project_id();
//sparty=sif.submit_party();
sid=sif.submit_id();
pr=sif.paid_receipt();
pw.println("<html>");
pw.println("<body>");
pw.println("<center>");
pw.println("PaidAmount:&nbsp;&nbsp;&nbsp;&nbsp; <input type=text name=t1 value="+amount+"><br><br>");
pw.println("Project Id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text name=t3 value="+pid+"><br><br>");
/*pw.println("SubmitParty:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp <input type=text name=t4 value="+sparty+"><br><br>");*/
pw.println("SubmissionId&nbsp;&nbsp;&nbsp;&nbsp&nbsp<input type=text name=t5 value="+sid+"><br><br>");
pw.println("ProjectReceipt&nbsp;&nbsp;&nbsp;&nbsp<input type=text name=t6 value="+pr+"><br><br>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=submit value=cancel>");
pw.println("</center>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e){System.out.println(e);}
}
}