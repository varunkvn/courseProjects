import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class concession extends HttpServlet
{
     Connection con;
     public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
         PrintWriter out=res.getWriter();
         res.setContentType("text/html");
         try
         {
             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
             out.println("<html><body>");
             out.println("<script>");
             out.println("function nam()");
             out.println("{");
             out.println("var a=document.forms[0].type.value;");
             out.println("if(a.length == 0)");
             out.println("{");
             out.println("alert(\"Null field\");");
             out.println("document.forms[0].type.focus();");
             out.println("return;");
             out.println("}");
             out.println("else");
             out.println("{");
             out.println("for(i=0;i<a.length;i++)");
             out.println("{");
             out.println("var b=a.substring(i,i+1);");
             out.println("if(!(b >='a' && b <= 'z' || b >='A' && b <= 'Z'))");
             out.println("{");
             out.println("alert(\"Enter alphabets only\");");
             out.println("document.forms[0].type.focus();");
             out.println("document.forms[0].type.value=\"\";");
             out.println("return;");
             out.println("}");
             out.println("}");
             out.println("}");
             out.println("document.forms[0].type.value=a;");
             out.println("}");

             out.println("function pr()");
             out.println("{");
             out.println("var a=document.forms[0].per.value;");
             out.println("if(a.length == 0)");
             out.println("{");
             out.println("alert(\"Null field\");");
             out.println("document.forms[0].per.focus();");
             out.println("return;");
             out.println("}");
             out.println("else");
             out.println("{");
             out.println("for(i=0;i<a.length;i++)");
             out.println("{");
             out.println("var b=a.substring(i,i+1);");
             out.println("if(!(b >=0 || b <=9))");
             out.println("{");
             out.println("alert(\"Enter numbers only\");");
             out.println("document.forms[0].per.focus();");
             out.println("document.forms[0].per.value=\"\";");
             out.println("return;");
             out.println("}");
             out.println("}");
             out.println("}");
             out.println("document.forms[0].per.value=a;");
             out.println("}");

             out.println("function ds()");
             out.println("{");
             out.println("var a=document.forms[0].des.value;");
             out.println("if(a.length == 0)");
             out.println("{");
             out.println("alert(\"Null field\");");
             out.println("document.forms[0].des.focus();");
             out.println("return;");
             out.println("}");
             out.println("else");
             out.println("{");
             out.println("for(i=0;i<a.length;i++)");
             out.println("{");
             out.println("var b=a.substring(i,i+1);");
             out.println("if(!(b >='a' && b <='z'|| b >='A' && b <='Z'))");
             out.println("{");
             out.println("alert(\"Enter alphabets only\");");
             out.println("document.forms[0].des.focus();");
             out.println("document.forms[0].des.value=\"\";");
             out.println("return;");
             out.println("}");
             out.println("}");
             out.println("}");
             out.println("document.forms[0].des.value=a;");
             out.println("}");


             out.println("</script>");
             out.println("<br><br><br>");
             out.println("<center><table>");
             out.println("<form method=post action=\"http://localhost:8080/servlet/concession1\">");
             out.println("<caption><font size=4><b><u>Concession Details</u></b></font></caption>");
             out.println("<tr><td><label>Type:</label></td><td><input size=20 name=type onblur=nam()></td>");
             out.println("<td><label>Percentage:</label></td><td><input size=20 name=per></td></tr>");
             out.println("<tr><td><label>Description:</label></td><td><input size=20 name=des onfocus=pr()></td></tr>");
             out.println("<table><tr><td align=center><input type=submit value=\" Insert \" onfocus=ds()></td></tr></table>");
             out.println("</table>");
             out.println("</center>");
             out.println("</body></html>");
         }
         catch(Exception e)
         {
             out.println("Exception in connection:"+e);
         }
     }
}
