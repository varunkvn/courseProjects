import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class add extends HttpServlet
{
     String Error,str,str1,fno,end,s1,cap;
     int end1,end2,cap1;
     Connection con;
     Statement st;
     ResultSet rst;
     public void init(ServletConfig config)throws ServletException
     {
       super.init(config);
       try
       {
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
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
          fno=req.getParameter("flight_no");
          end=req.getParameter("ending_seatno");
          end1=Integer.parseInt(end);
          end2=end1+1;
          cap=req.getParameter("capacity");
          out.println("<html><body>");
          out.println("<script>");

          out.println("function nam()");
          out.println("{");
          out.println("var a=document.forms[0].cat.value;");
          out.println("if(a.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].cat.focus();");
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
          out.println("document.forms[0].cat.focus();");
          out.println("document.forms[0].cat.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("document.forms[0].cat.value=a;");
          out.println("}");





          out.println("function total()");
          out.println("{");
          out.println("var a=document.forms[0].ticket.value;");
          out.println("if(a.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].ticket.focus();");
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
          out.println("document.forms[0].ticket.focus();");
          out.println("document.forms[0].ticket.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("document.forms[0].ticket.value=a;");
          out.println("}");

          out.println("function check(){");
          out.println("var p= document.forms[0].end.value");
          out.println("if(p.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].end.focus();");
          out.println("document.forms[0].end.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("else");
          out.println("{");
          out.println("var x=parseInt(document.forms[0].start.value);");
          out.println("x.toString();");
          out.println("var y=parseInt(document.forms[0].end.value);");
          out.println("y.toString();");
          out.println("if(y<=x)");
          out.println("{");
          out.println("alert(\"ending seat no is less than starting seat no verify!\");");
          out.println("document.forms[0].end.focus();");
          out.println("document.forms[0].end.value=\"\";");
          out.println("}");
          out.println("var a="+cap);
          out.println("a.toString();");
          out.println("var b=parseInt(document.forms[0].end.value);");
          out.println("b.toString();");
          out.println("if(b>a){");
          out.println("alert(\"ending seat no is greater than total capacity\");");
          out.println("document.forms[0].end.focus();");
          out.println("document.forms[0].end.value=\"\";");
          out.println("}");
          out.println("else");
          out.println("{");
          out.println("for(i=0;i<p.length;i++)");
          out.println("{");
          out.println("var b=p.substring(i,i+1);");
          out.println("if(!(b >=0 && b <=9))");
          out.println("{");
          out.println("alert(\"Enter numbers only\");");
          out.println("document.forms[0].end.focus();");
          out.println("document.forms[0].end.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("return;}");







          out.println("</script>");
          out.println("<h2><center><u>Add Flight Details </u></center></h2>");
          try
          {
                   st=con.createStatement();
                   out.println("<center><form method=post action=\"http://localhost:8080/servlet/insert2\">");
                   out.println("<table>");
                   out.println("<tr><td><label>Flight No:</label></td><td><input size=20 name=fno value="+fno+" readonly></td>");   
                   out.println("<td><label>Category:</label></td><td><input size=20 name=cat></td></tr>");
                   out.println("<tr><td><label>Cost Of Tickets:</label></td><td><input size=20 name=ticket onfocus=\"nam()\"></td>");
                   out.println("<td><label>Start Seat No:</label></td><td><input size=20 name=start value="+end2+" readonly onfocus=\"total()\"></td></tr>");
                   out.println("<tr><td><label>Ending Seat No:</label></td><td><input size=20 name=end onblur=\"check()\"></td></tr></table>");
                   out.println("<input type=hidden name=cap value="+cap+">");
                   out.println("<input type=submit value=submit>");
                   out.println("</form></center>");
          }
          catch(Exception e1)
          {
           out.println("Exception in connection:"+e1);
          }
          out.println("</body></html>");
     }
}

