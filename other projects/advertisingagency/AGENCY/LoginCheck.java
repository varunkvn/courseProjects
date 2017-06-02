import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
public class LoginCheck extends HttpServlet
{
Connection con;
Statement st;
public void init(ServletConfig c) throws ServletException
{
try
{
super.init(c);
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:advt","scott","tiger");
System.out.println("connection established");
}
catch(Exception e){System.out.println(e);}
}
public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
String user;
String pwd;
try
{
user=req.getParameter("username");
pwd=req.getParameter("password");
System.out.println("user "+user+" pwd "+pwd);
st=con.createStatement();
System.out.println("statement created");
ResultSet rs=st.executeQuery("select count(*) from advt_users where user_name='"+user+"' and password='"+pwd+"'");
System.out.println("result set executed");
int cnt=0;
while(rs.next())
{
 cnt=rs.getInt(1);
}
if(cnt==1)
{

res.sendRedirect("http://localhost:8080/servlet/application?party="+user);
}
else
 res.sendRedirect("http://localhost:8080/logout.html");
}
catch(Exception e){System.out.println(e);}
}
}