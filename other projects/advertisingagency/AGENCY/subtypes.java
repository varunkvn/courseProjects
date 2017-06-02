import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class subtypes extends HttpServlet
{
Connection con;
Statement stmt;
PreparedStatement pstmt,pstmt1;
ResultSet rs;
String applno,pname,type,msg,img;
int appno,receipt;
String str,str1;

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
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();
try
{
pstmt=con.prepareStatement("insert into user_applications(applno,pname,type,message,images) values(?,?,?,?,?)");

applno=req.getParameter("applno");
appno=Integer.parseInt(applno);//1
pname=req.getParameter("pname");//2
System.out.println(pname);
type=req.getParameter("type");//3
msg=req.getParameter("msg");
img=req.getParameter("img");
pstmt.setInt(1,appno);
pstmt.setString(2,pname);
pstmt.setString(3,type);
pstmt.setString(4,msg);
pstmt.setString(5,img);
pstmt.executeUpdate();

if(type.equals("news"))
{
stmt=con.createStatement();
rs=stmt.executeQuery("select 'N'||lpad(to_char(nvl(to_number(max(substr(typeid,3))),0)+1),3,'0') from subtypes1 where typeid like 'N%'");
rs.next();
str=rs.getString(1);
pstmt=con.prepareStatement("insert into subtypes1(applno,typeid) values(?,?)");
pstmt.setInt(1,appno);
pstmt.setString(2,str);
pstmt.executeUpdate();

pstmt1=con.prepareStatement("insert into maintypes1 values(?,?)");
pstmt1.setString(1,type);
pstmt1.setString(2,str);
pstmt1.executeUpdate();

pw.println("<html><center><head><H1>SUBTYPES</H1><body  bgcolor=magenta background=\"/Beanco.jpg\">");
pw.println("<form name=f1 action="+"http://localhost:8080/servlet/subtypeid"+" method=post>");
pw.println("<font color=blue>Applno:<input type=text name=applno value="+applno+">");
pw.println("Type:<input type=text name=type value="+type+"><br><br>");
pw.println("Vartha<input type=radio name=r1 value=var><br><br>");
pw.println("DC<input type=radio name=r1 value=dc><br><br>");
pw.println("Enadu<input type=radio name=r1 value=ena><br><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a></font>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></body></html>");
}//if news
else if(type.equals("vmedia"))
{
stmt=con.createStatement();
rs=stmt.executeQuery("select 'V'||lpad(to_char(nvl(to_number(max(substr(typeid,3))),0)+1),3,'0') from subtypes1 where typeid like 'V%'");
rs.next();
str=rs.getString(1);
pstmt=con.prepareStatement("insert into subtypes1(applno,typeid) values(?,?)");
pstmt.setInt(1,appno);
pstmt.setString(2,str);
pstmt.executeUpdate();

pstmt1=con.prepareStatement("insert into maintypes1 values(?,?)");
pstmt1.setString(1,type);
pstmt1.setString(2,str);
pstmt1.executeUpdate();
pw.println("<html><head><center><h1>SUBTYPES</h1><body bgcolor=green background=\"/Beanco.jpg\">");
pw.println("<form name=f1 action="+"http://localhost:8080/servlet/subtypeid"+" method=post>");
pw.println("<font color=blue>Applno:<input type=text name=applno value="+applno+">");
pw.println("Type:<input type=text name=type value="+type+"><br><br>");
pw.println("DoorDarsh<input type=radio name=r1 value=DoorDarshan><br><br>");
pw.println("CitiCable<input type=radio name=r1 value=CitiCable><br><br>");
pw.println("SonyTv<input type=radio name=r1 value=SonyTv><br><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a></font>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></body></html>");

}//vmedia
else
{
stmt=con.createStatement();
rs=stmt.executeQuery("select 'I'||lpad(to_char(nvl(to_number(max(substr(typeid,3))),0)+1),3,'0') from subtypes1 where typeid like 'I%'");
rs.next();
str=rs.getString(1);
pstmt=con.prepareStatement("insert into subtypes1(applno,typeid) values(?,?)");
pstmt.setInt(1,appno);
pstmt.setString(2,str);
pstmt.executeUpdate();

pstmt1=con.prepareStatement("insert into maintypes1 values(?,?)");
pstmt1.setString(1,type);
pstmt1.setString(2,str);
pstmt1.executeUpdate();

pw.println("<html><center><head><h1>SUBTYPES</h1></head><body bgcolor=aqua background=\"/Backgrd.gif\">");
pw.println("<form name=f1 action="+"http://localhost:8080/servlet/subtypeid"+" method=post>");
pw.println("<font color=blue>Applno:<input type=text name=applno value="+applno+">");
pw.println("Type:<input type=text name=type value="+type+"><br>");
pw.println("Yahoo<input type=radio name=r1 value=yahoo><br>");
pw.println("Rediff<input type=radio name=r1 value= rediff><br>");
pw.println("Usa.net<input type=radio name=r1 value=unet><br>");
pw.println("<a href='"+"GoBack"+"'>GoBack</a></font>"); 
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</center></body></html>");
}//inter net
}catch(Exception e){pw.println(e);}
}
}