import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class sign_up extends HttpServlet
{
String first_name,middle_name,last_name,address,e_mail,occupation,
user_name,password,retype_password,income,age_age,phone_no,year,month,day;
int  age,count,yyyy,mm,dd;
double phoneno,income_per_month;
Connection con;
PreparedStatement pstmt,pstmt1,pstmt2;
Statement stmt,stmt1;
ResultSet rs,rs1;
String str,str1,str2;
public void init(ServletConfig sc)throws ServletException
{
super.init(sc);
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:aruna","scott","tiger");
System.out.println("connection established");
}catch(Exception e){System.out.println(e);}
}
public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();


try
{
pstmt=con.prepareStatement("insert into sign_up(first_name,middle_name,last_name,age,phone_no,e_mail,occupation,income_per_month,user_name,password,retype_password) values(?,?,?,?,?,?,?,?,?,?,?)");

first_name=req.getParameter("first_name");
middle_name=req.getParameter("middle_name");
last_name=req.getParameter("last_name");
age_age=req.getParameter("age");//change to integer by using paraseInt
phone_no=req.getParameter("phoneno");//change to double by using parseDouble
e_mail=req.getParameter("e_mail");
occupation=req.getParameter("occupation");
income=req.getParameter("income_per_month");//change to double by using paraseDouble
user_name=req.getParameter("user_name");
password=req.getParameter("password");
retype_password=req.getParameter("retype_password");

year=req.getParameter("year");
month=req.getParameter("month");
day=req.getParameter("day");
System.out.println(year+month+day);
yyyy=Integer.parseInt(year);
mm=Integer.parseInt(month);
dd=Integer.parseInt(day);
java.sql.Date jsd=new java.sql.Date(yyyy,mm,dd);


phoneno=Double.valueOf(phone_no).doubleValue();
income_per_month=Double.valueOf(income).doubleValue();
age=Integer.parseInt(age_age);

pstmt.setString(1,first_name);
pstmt.setString(2,middle_name);
pstmt.setString(3,last_name);
pstmt.setInt(4,age);
//pstmt.setString(5,);
//pstmt.setDate(null);
address=req.getParameter("address");
System.out.println(address);

pstmt.setDouble(5,phoneno);
pstmt.setString(6,e_mail);
pstmt.setString(7,occupation);
pstmt.setDouble(8,income_per_month);
pstmt.setString(9,user_name);
pstmt.setString(10,password);
pstmt.setString(11,retype_password);

pstmt.executeUpdate();

System.out.println("record inserted");


stmt=con.createStatement();
stmt1=con.createStatement();
rs=stmt.executeQuery("select count(*) from sign_up where user_name='"+user_name+"'");
rs1=stmt1.executeQuery("select password,retype_password from sign_up where user_name='"+user_name+"'");
System.out.println("resultset executed");
rs.next();
count=rs.getInt(1);
System.out.println(count);
rs1.next();
str1=rs1.getString(1);
str2=rs1.getString(2);
System.out.println(str1+str2);
if(count==1)
{
if(str1.equals(str2))
{
pstmt1=con.prepareStatement("insert into advt_users values(?,?)");
pstmt1.setString(1,user_name);
pstmt1.setString(2,password);
pstmt1.executeUpdate();
System.out.println("advt_users record inserted");
res.sendRedirect("http://localhost:8080/servlet/application?party="+user_name);
}
else
res.sendRedirect("http://localhost:8080/logout.html");
}
else
res.sendRedirect("http://localhost:8080/logout.html");
}//try
catch(Exception e){pw.println(e);}
}
}