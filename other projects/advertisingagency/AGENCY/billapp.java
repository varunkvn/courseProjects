import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class billapp extends HttpServlet
{
Connection con;
Statement stmt,stmt1,stmt2,stmt3,stmt4;
PreparedStatement pstmt,pstmt1,pstmt2,pstmt3;
ResultSet rs,rs1,rs2,rs3;
String applno,trialno,party,subid;
java.sql.Date bdate;
int appno,trial_no;
String str,str1,billno,amount,typeid,name,value;

StringTokenizer stk;

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
try
{

applno=req.getParameter("applno");
appno=Integer.parseInt(applno);
trialno=req.getParameter("trialno");
subid=req.getParameter("subid");
trial_no=Integer.parseInt(trialno);
stmt=con.createStatement();
rs=stmt.executeQuery("select pname,sysdate from user_applications where applno="+appno);
rs.next();
party=rs.getString(1);
bdate=rs.getDate(2);
//rs.close();
stmt.close();

stmt2=con.createStatement();
rs2=stmt2.executeQuery("select 'B'||lpad(to_char(nvl(to_number(max(substr(receiptno,3))),0)+1),3,'0') from user_applications");

while(rs2.next())
{
billno=rs.getString(1);
}
stmt2.close();
stmt3=con.createStatement();
int x=stmt3.executeUpdate("update user_applications set receiptno='"+billno+"'  where applno="+appno);
stmt3.close();
stmt4=con.createStatement();
rs3=stmt4.executeQuery("select typeid from subtypes1 where applno="+appno);
rs3.next();
typeid=rs.getString(1);
stmt4.close();
pstmt2=con.prepareStatement("update user_applications set receipt_date=? where applno=?");
pstmt2.setDate(1,bdate);
pstmt2.setInt(2,appno);
pstmt2.executeUpdate();
pstmt2.close();
if(subid.equals("60sec"))
{
if(trial_no==4)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*80 where typeid=?");
pstmt3.setString(1,typeid);
pstmt3.executeUpdate();
amount=String.valueOf(80000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=maroon background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"'http://localhost:8080/servlet/submit'"+"  method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else if(trial_no==3)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*60 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(60000);
pw.println("<html><head>");

pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=silver background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<table border=1 align=center>");
pw.println("<tr><th><hl> BILL RECEIPT </hl></th></tr></table><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else if(trial_no==2)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*50 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(50000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=teal background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*40 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(40000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=silver background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
}
else if(subid.equals("45sec"))
{
if(trial_no==4)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*70 where typeid=?");
pstmt3.setString(1,typeid);
pstmt3.executeUpdate();
amount=String.valueOf(70000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=maroon background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"'http://localhost:8080/servlet/submit'"+"  method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else if(trial_no==3)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*50 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(50000);
pw.println("<html><head>");

pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=silver background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<table border=1 align=center>");
pw.println("<tr><th><hl> BILL RECEIPT </hl></th></tr></table><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else if(trial_no==2)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*40 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(40000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=teal background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*30 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(30000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=silver background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}}
else if(subid.equals("30sec"))
{
if(trial_no==4)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*65 where typeid=?");
pstmt3.setString(1,typeid);
pstmt3.executeUpdate();
amount=String.valueOf(65000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=maroon background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"'http://localhost:8080/servlet/submit'"+"  method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else if(trial_no==3)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*55 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(55000);
pw.println("<html><head>");

pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=silver background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<table border=1 align=center>");
pw.println("<tr><th><hl> BILL RECEIPT </hl></th></tr></table><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else if(trial_no==2)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*45 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(45000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=teal background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*40 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(40000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=silver background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
}
else
{
if(trial_no==4)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*50 where typeid=?");
pstmt3.setString(1,typeid);
pstmt3.executeUpdate();
amount=String.valueOf(50000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=maroon background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"'http://localhost:8080/servlet/submit'"+"  method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else if(trial_no==3)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*45 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(45000);
pw.println("<html><head>");

pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<table border=1 align=center>");
pw.println("<tr><th><hl> BILL RECEIPT </hl></th></tr></table><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else if(trial_no==2)
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*40 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(40000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=teal background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
else
{
pstmt3=con.prepareStatement("update netcharges set charges=nvl(charges,1000)*35 where subtypeid=?");
pstmt3.setString(1,subid);
pstmt3.executeUpdate();
amount=String.valueOf(35000);
pw.println("<html><head>");
pw.println("<Title> ADVT AGENCY CUSTOMER BILL</title></head>");
pw.println("<body bgcolor=silver background=\"/Beanco.jpg\"><center>");
pw.println("<form action="+"http://localhost:8080/servlet/submit"+" method=get>");
pw.println("<hl> BILL RECEIPT </hl><br><br><br>");
pw.println("<table border=5 align=center>");
pw.println("<tr><th>Applno</th><th>Amount+ </th> <th> PartyName </th><th>BillDate</th><th>BillNo</th></tr>");
pw.println("<tr><td>"+applno+"</td> <td>"+amount+"</td><td>"+ party+"</td><td>"+bdate+"</td><td>"+billno+"</td></tr></table>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a>"); 
pw.println("<input type=submit value=paid>");
pw.println("</form></center></body></html>");
}
}
}catch(Exception e){pw.println(e);
}
}
}