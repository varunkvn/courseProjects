import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class add3 extends HttpServlet
{
     String Error,str,fno;
     Connection con;
     Statement st;
     ResultSet rst;
     public void init(ServletConfig config)throws ServletException
     {
       super.init(config);
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
       }
       catch(Exception e)
       {
        Error="Exception in init:"+e;
       }
     }
     public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
          PrintWriter out=res.getWriter();
          res.setContentType("text/html");
          out.println("<html><body>");
          out.println("<script>");
          out.println("function ff()");
          out.println("{");
          out.println("var a=document.forms[0].dof.value;");   
          out.println("if(a.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].dof.focus();");
          out.println("return;");
          out.println("}");
          out.println("else if((a!=\"sun\")&&(a!=\"mon\")&&(a!=\"tue\")&&(a!=\"wed\")&&(a!=\"thu\")&&(a!=\"fri\")&&(a!=\"sat\"))");
          out.println("{");
          out.println("alert(\"Enter weekdays as sun,mon,tue,wed,thu,fri,sat\");");
          out.println("document.forms[0].dof.focus();");
          out.println("document.forms[0].dof.value=\"\";");
          out.println("}");
          out.println("}");

          out.println("function tf()");
          out.println("{");
          out.println("var a=document.forms[0].tof.value;");   
          out.println("if(a.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].tof.focus();");
          out.println("return;");
          out.println("}");
          out.println("else");
          out.println("{");
          out.println("for(i=0;i<a.length;i++)");
          out.println("{");
          out.println("var b=a.substring(i,i+1);");
          out.println("if(!(b >=0 ||  b <= 9))");
          out.println("{");
          out.println("alert(\"Enter numbers only\");");
          out.println("document.forms[0].tof.focus();");
          out.println("document.forms[0].tof.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("document.forms[0].tof.value=a;");
          out.println("}");
          out.println("</script>");
          out.println("<h2><center><u>ADD Flight Details </u></center></h2>");
          try
          {
               fno=req.getParameter("flight_no");
               st=con.createStatement();
               rst=st.executeQuery("select flight_no from flight_master where rowid=(select max(rowid)from flight_master)");
               while(rst.next())
               {
               str=rst.getString(1);
               out.println("<center><form method=get action=\"http://localhost:8080/servlet/insert3\">");
               out.println("<table>");
               out.println("<tr><td><lable>Flight No:</lable></td><td><input size=20 name=fno value="+fno+" readonly></td>");
               out.println("<td><lable>Day Of Flight:</lable></td><td><input size=20 name=dof></td></tr>");
               out.println("<tr><td><lable>Time Of Flight:</lable></td><td><input size=20 name=tof onfocus=\"ff()\"></td></tr>");
               out.println("<table><tr><td><input type=submit value=submit onfocus=\"tf()\"></td></tr></table>");
               out.println("</table></form></center>");
               }
          }
          catch(Exception e1)
          {
           out.println("Exception in connection:"+e1);
          }
          out.println("</body></html>");
     }
}
