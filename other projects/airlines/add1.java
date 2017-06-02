import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class add1 extends HttpServlet
{
     String Error,fno;
     Statement st;
     ResultSet rst;
     Connection con;
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
          out.println("<html>");
          out.println("<head><script>");
          out.println("function ff()");
          out.println("{");
          out.println("var a=document.forms[0].fname.value;");   
          out.println("if(a.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].fname.focus();");
          out.println("return;");
          out.println("}");  
          out.println("}");
          out.println("function sc()");
          out.println("{");
          out.println("var a=document.forms[0].src.value;");   
          out.println("if(a.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].src.focus();");
          out.println("return;");
          out.println("}");  
          out.println("else");
          out.println("{");
          out.println("for(var i=0;i<a.length;i++)");
          out.println("{");
          out.println("var b=a.substring(i,i+1);");
          out.println("if(b >= 0 || b <= 9)");
          out.println("{");
          out.println("alert(\"Enter Characters Only\");");
          out.println("document.forms[0].src.focus();");
          out.println("document.forms[0].src.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("function check(){");
          out.println("var x=document.forms[0].src.value;");
          out.println("var y=document.forms[0].dest.value;");
          out.println("var z=x.toUpperCase();");
          out.println("if(y.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].dest.focus();");
          out.println("return;");
          out.println("}");  
          out.println("else if(x == y || z == y){");
          out.println("alert(\"Source and Destination cannot be equal\");");
          out.println("document.forms[0].dest.focus();");
          out.println("document.forms[0].dest.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("else");
          out.println("{");
          out.println("for(var i=0;i<y.length;i++)");
          out.println("{");
          out.println("var d=y.substring(i,i+1);");
          out.println("if(d >= 0 || d <= 9)");
          out.println("{");
          out.println("alert(\"Enter Characters Only\");");
          out.println("document.forms[0].dest.focus();");
          out.println("document.forms[0].dest.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("function total()");
          out.println("{");
          out.println("var a=document.forms[0].cap.value;");   
          out.println("if(a.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].cap.focus();");
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
          out.println("document.forms[0].cap.focus();");
          out.println("document.forms[0].cap.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("document.forms[0].cap.value=a;");
          out.println("}");
          out.println("function tt()");
          out.println("{");
          out.println("var a=document.forms[0].dur.value;");   
          out.println("if(a.length == 0)");
          out.println("{");
          out.println("alert(\"Null field\");");
          out.println("document.forms[0].dur.focus();");
          out.println("return;");
          out.println("}");  
          out.println("else");
          out.println("{");
          out.println("for(i=0;i<a.length;i++)");
          out.println("{");
          out.println("if(!(a >=0.0 || a <= 9.9))");
          out.println("{");
          out.println("alert(\"Enter the valid number only\");");
          out.println("document.forms[0].dur.focus();");
          out.println("document.forms[0].dur.value=\"\";");
          out.println("return;");
          out.println("}");
          out.println("}");
          out.println("}");
          out.println("document.forms[0].dur.value=a;");
          out.println("}");
          out.println("</script></head>");
          out.println("<body>");
          out.println("<h4><center><u>ADD FLIGHT DETAILS </u></center></h4>");
          try
          {
               st=con.createStatement();
               rst=st.executeQuery("select 'ia' || to_char(nvl(substr(max(flight_no),3,5),100)+1) from flight_master");
               while(rst.next())
               {
                    fno=rst.getString(1);
               }
               out.println("<form method=post action=\"http://localhost:8080/servlet/insert1\">");
               out.println("<table><center>");
               out.println("<tr><td><label>Flight No:</label></td><td><input size=20 name=fno value='"+fno+"' readonly></td>");
               out.println("<td><label>Flight Name:</label></td><td><input size=20 name=fname></td></tr>");
               out.println("<tr><td><label>Source:</label></td><td><input size=20 name=src onfocus=\"ff()\"></td>");
               out.println("<td><label>Destination:</label></td><td><input size=20 name=dest onfocus=\"sc()\"></td></tr>");
               out.println("<tr><td><label>Total Capacity:</label></td><td><input size=20 name=cap onfocus=\"check()\"></td>");
               out.println("<td><label>Duration Of Flight:</label></td><td><input size=20 name=dur onfocus=\"total()\"></td></tr>");
               out.println("<table><tr><td><input type=submit onfocus=\"tt()\"value=insert></td></tr></table>");
               out.println("</table></center></form>");
          }
          catch(Exception e1)
          {
           out.println("Exception in connection:"+e1);
          }
          out.println("</body></html>");
     }
}
