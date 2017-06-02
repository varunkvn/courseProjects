import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class reserve1 extends HttpServlet
{
    String month[]={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
    Statement st;
    ResultSet rst,rst1;
    String fno,date,week,time,rno,str,str1,c1;
    int da,mo,ye;
    Connection con;
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        fno=req.getParameter("fn");
        c1=req.getParameter("category");
        date=req.getParameter("d");
        week=req.getParameter("w");
        Calendar c=Calendar.getInstance();
        da=c.get(Calendar.DAY_OF_MONTH);
        mo=c.get(Calendar.MONTH);
        ye=c.get(Calendar.YEAR);
        str=(da+"-"+month[mo]+"-"+ye);
        try
        {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
           st=con.createStatement();
           rst=st.executeQuery("select time_of_flight from flight_details where flight_no="+fno+" and day_of_flight="+week+"");
           while(rst.next())                    
           time=rst.getString("time_of_flight");
           rst1=st.executeQuery("select nvl(max(res_no),0)+1 from passenger_master");
           while(rst1.next())
           str1=rst1.getString(1);
           out.println("<html><body>");
           out.println("<script>");
           out.println("function adr()");
           out.println("{");
           out.println("var a=document.forms[0].add.value;");   
           out.println("if(a.length == 0)");
           out.println("{");
           out.println("alert(\"Null field\");");
           out.println("document.forms[0].add.focus();");
           out.println("return;");
           out.println("}");  
           out.println("}");
           out.println("function state()");
           out.println("{");
           out.println("var a=document.forms[0].st.value;");   
           out.println("if(a.length == 0)");
           out.println("{");
           out.println("alert(\"Null field\");");
           out.println("document.forms[0].st.focus();");
           out.println("return;");
           out.println("}");
           out.println("else");
           out.println("{");
           out.println("for(i=0;i<a.length;i++)");
           out.println("{");
           out.println("var b=a.substring(i,i+1);");
           out.println("if(!(b >='a' && b <= 'z' || b>='A' && b<='Z'))");
           out.println("{");
           out.println("alert(\"Enter alphabets only\");");
           out.println("document.forms[0].st.focus();");
           out.println("document.forms[0].st.value=\"\";");
           out.println("return;");
           out.println("}");
           out.println("}");
           out.println("}");
           out.println("document.forms[0].st.value=a;");
           out.println("}");
           out.println("function ph()");
           out.println("{");
           out.println("var a=document.forms[0].pno.value;");   
           out.println("if(a.length == 0)");
           out.println("{");
           out.println("alert(\"Null field\");");
           out.println("document.forms[0].pno.focus();");
           out.println("return;");
           out.println("}");  
           out.println("else");
           out.println("{");
           out.println("for(i=0;i<a.length;i++)");
           out.println("{");
           out.println("var b=a.substring(i,i+1);");
           out.println("if(!(b >=0 || b <= 9))");
           out.println("{");
           out.println("alert(\"Enter the valid number only\");");
           out.println("document.forms[0].pno.focus();");
           out.println("document.forms[0].pno.value=\"\";");
           out.println("return;");
           out.println("}");
           out.println("}");
           out.println("}");
           out.println("document.forms[0].pno.value=a;");
           out.println("}");
           out.println("function em()");
           out.println("{");
           out.println("var a=document.forms[0].mail.value;");   
           out.println("if(a.length == 0)");
           out.println("{");
           out.println("alert(\"Null field\");");
           out.println("document.forms[0].mail.focus();");
           out.println("return;");
           out.println("}");  
           out.println("}");
           out.println("</script>");
           out.println("<center><table border=0>");
           out.println("<caption><font size=5><b><u>Reservation Form</u></b></font></caption>");
           out.println("<br>");
           out.println("<form method=post action=\"http://localhost:8080/servlet/insert4\">");
           out.println("<input type=hidden name=category value="+c1+">");
           out.println("<tr><td>Reservation No:</td><td><input size=20 name=rno value="+str1+" readonly></td>");
           out.println("<td>Reservation Date:</td><td><input size=20 name=rdt value="+str+" readonly></td></tr>");
           out.println("<tr><td>Flight No:</td><td><input size=20 name=fno value="+fno+" readonly></td>");
           out.println("<td>Departure Date:</td><td><input size=20 name=date value="+date+" readonly></td></tr>");
           out.println("<tr><td>Departure Time:</td><td><input size=20 name=date1 value="+time+" readonly></td>");
           out.println("<td>Address:</td><td><textarea rows=4 cols=15 name=add></textarea></td></tr>");
           out.println("<tr><td>State:</td><td><input size=20 name=st onfocus=adr()></td>");
           out.println("<td>Phone No:</td><td><input size=20 name=pno onfocus=state()></td></tr>");
           out.println("<tr><td>Email Address:</td><td><input size=20 name=mail onfocus=ph()></td></tr>");
           out.println("<table><tr><td align=center><input type=submit  onfocus=em() value=\" Insert \"></td></tr></table>");
        }
        catch(Exception e)
        {
           out.println("Exception in connection :"+e);
        }
    }
}
          


