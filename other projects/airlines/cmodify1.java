import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cmodify1 extends HttpServlet
{
    Connection con;
    String type,str,str1;
    Statement st;
    ResultSet rst;
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        type=req.getParameter("type");
        try
        {
             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
             st=con.createStatement();
             rst=st.executeQuery("select percentage,description from concession_master where type='"+type+"'");
             while(rst.next())
             {
                str=rst.getString("percentage");
                str1=rst.getString("description");
             }
             out.println("<html><body>");
             out.println("<script>");
             out.println("function ps()");
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
             out.println("if(!(b >=0 ||  b <= 9))");
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
             out.println("if(!(b >='a' && b <= 'z' || b >='A' && b <= 'Z'))");
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
             out.println("<center><table>");
             out.println("<form method=post action=\"http://localhost:8080/servlet/cmodify2\">");
             out.println("<tr><td><label>Type:</label></td><td><input size=20 name=type value="+type+" readonly></td>");
             out.println("<td><label>Percentage:</label></td><td><input size=20 name=per value="+str+" onblur=ps()></td></tr>");
             out.println("<tr><td><label>Description:</label></td><td><input size=20 name=des value="+str1+"></td></tr>");
             out.println("<table><tr><td align=center><input type=submit value=\" Modify \" onfocus=ds()></td></tr></table>");
             out.println("</table></center>");
             out.println("</body></html>");
        }
        catch(Exception e)
        {
             out.println("Exception in connection:");
        }
    }
}
