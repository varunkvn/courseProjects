import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class reserve3 extends HttpServlet
{
    Connection con;
    Statement st;
    ResultSet rst,rst1,rst3;
    ResultSetMetaData rmt;
    String rno,start,end,str,s,fno,cat,dt,total,sql2;
    int st1,end1,n,y,x,cost,c,j;
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        rno=req.getParameter("rno");
        fno=req.getParameter("fno");
        cat=req.getParameter("cat");
        dt=req.getParameter("date");
        start=req.getParameter("start");
        st1=Integer.parseInt(start);
        n=st1-1;
        end=req.getParameter("end");
        end1=Integer.parseInt(end);
        total=req.getParameter("cost");
        c=Integer.parseInt(total);
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
            st=con.createStatement();
            rst=st.executeQuery("select nvl(max(seat_no),"+ n +")+1 from passenger_details where flight_no='"+fno+"' and doj='"+dt+"' and category='"+cat+"'");
            while(rst.next())
            str=rst.getString(1);
            x=Integer.parseInt(str);
            if(x > end1)
            s="n";
            else
            s="y";
            rst1=st.executeQuery("select cost_of_ticket from flight_desc where flight_no='"+fno+"' and category='"+cat+"'");
            while(rst1.next())
            cost=rst1.getInt(1);
            out.println("<html><body >");
            out.println("<script>");
            out.println("function passno()");
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
            out.println("function pn()");
            out.println("{");
            out.println("var a=document.forms[0].pname.value;");   
            out.println("if(a.length == 0)");
            out.println("{");
            out.println("alert(\"Null field\");");
            out.println("document.forms[0].pname.focus();");
            out.println("return;");
            out.println("}");
            out.println("else");
            out.println("{");
            out.println("for(i=0;i<a.length;i++)");
            out.println("{");
            out.println("var b=a.substring(i,i+1);");
            out.println("if(!(b >='a' && b <= 'z'|| b >= 'A' && b <= 'Z'))");
            out.println("{");
            out.println("alert(\"Enter alphabets only\");");
            out.println("document.forms[0].pname.focus();");
            out.println("document.forms[0].pname.value=\"\";");
            out.println("return;");
            out.println("}");
            out.println("}");
            out.println("}");
            out.println("document.forms[0].pname.value=a;");
            out.println("}");
            out.println("function ag()");
            out.println("{");
            out.println("var a=document.forms[0].age.value;");   
            out.println("if(a.length == 0)");
            out.println("{");
            out.println("alert(\"Null field\");");
            out.println("document.forms[0].age.focus();");
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
            out.println("document.forms[0].age.focus();");
            out.println("document.forms[0].age.value=\"\";");
            out.println("return;");
            out.println("}");
            out.println("}");
            out.println("}");
            out.println("document.forms[0].age.value=a;");
            out.println("}");
            out.println("function pop()");
            out.println("{");
            out.println("var a=document.forms[0].ppno.value;");   
            out.println("if(a.length == 0)");
            out.println("{");
            out.println("alert(\"Null field\");");
            out.println("document.forms[0].ppno.focus();");
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
            out.println("document.forms[0].ppno.focus();");
            out.println("document.forms[0].ppno.value=\"\";");
            out.println("return;");
            out.println("}");
            out.println("}");
            out.println("}");
            out.println("document.forms[0].ppno.value=a;");
            out.println("}");
            out.println("function vtp()");
            out.println("{");
            out.println("var a=document.forms[0].vtype.value;");   
            out.println("if(a.length == 0)");
            out.println("{");
            out.println("alert(\"Null field\");");
            out.println("document.forms[0].vtype.focus();");
            out.println("return;");
            out.println("}");  
            out.println("}");
            out.println("</script>");
            out.println("<center><table border=0>");
            out.println("<form method=post action=\"http://localhost:8080/servlet/insert6\">");
            out.println("<input type=hidden name=fno value="+fno+">");
            out.println("<input type=hidden name=cat value="+cat+">");
            out.println("<input type=hidden name=date value="+dt+">");
            out.println("<input type=hidden name=start value="+st1+">");
            out.println("<input type=hidden name=end value="+end1+">");
            out.println("<caption><font size=5><b><u>Reservation</u></b></font></caption>");
            out.println("<tr><td><label>Pass No:</label></td><td><input size=20 name=pno onblur=passno()></td>");
            out.println("<td><label>Pass Name:</label></td><td><input size=20 name=pname></td></tr>");
            out.println("<tr><td><label>Gender:</label></td><td><input type=radio name=gender value=M onfocus=pn()>:Male<input type=radio name=gender value=F>:Female</td>");
            out.println("<td><label>Age:</label></td><td><input size=20 name=age></td></tr>");
            out.println("<tr><td><label>Passport No:</label></td><td><input size=20 name=ppno onfocus=ag()></td>");
            out.println("<td><label>Visa Type:</label></td><td><input size=20 name=vtype onfocus=pop()></td></tr>");
            out.println("<tr><td><label>Res No:</label></td><td><input size=20 name=rno value="+rno+" readonly onfocus=vtp()></td>");
            out.println("<td><label>Confirmed:</label></td><td><input size=20 name=con value="+s+" readonly onblur=call()></td></tr>");
            out.println("<tr><td><label id=sn>Seat No:</label></td><td><input size=20 name=sno value="+str+" readonly></td>");
            out.println("<td><label>Type Of Concession:</label></td><td><select size=multiple name=concession onchange=\"check();\">");
            sql2="select type,percentage from concession_master";
            rst3=st.executeQuery(sql2);
            rmt=rst3.getMetaData();
            int count1=rmt.getColumnCount();
            String percent[] =new String[5];
            int incr1=0;
            while(rst3.next())
            {
                str=rst3.getString("type");
                percent[incr1]=rst3.getString("percentage");
                out.println("<option value="+str+">"+str+"</option>");
                incr1++;
            }
            out.println("</select></td>");
            out.println("<script>");
            out.println("var p=new Array(5);");
            for(int i=0;i<=incr1-1;i++)
            {
                out.println("p["+i+"]='"+percent[i]+"'");
            }                                     
            out.println("function check()");
            out.println("{");
            out.println("document.forms[0].p1.value=p[document.forms[0].concession.selectedIndex]");
            out.println("}");
            out.println("</script>");
            out.println("<script>");
            out.println("function cc()");
            out.println("{");
            out.println("var a=document.forms[0].p1.value;");
            out.println("var b="+cost);
            out.println("var c=b*a/100;");
            out.println("var d=b-c;");
            out.println("document.forms[0].cost.value=d;");
            out.println("}");
            out.println("</script>");
            out.println("<tr><td><label>Cost:</label></td><td><input size=20 name=cost onfocus=\"cc();\"></td>");
            out.println("<input type=hidden name=p1 value=\"\">");
            out.println("<script>");
            out.println("function pp()");
            out.println("{");
            out.println("var p=document.forms[0].cost.value;");
            out.println("var q="+total);
            out.println("var r=parseInt(p)+parseInt(q);");
            out.println("document.forms[0].t1.value=r;");
            out.println("}");
            out.println("</script>");
            out.println("<td><label>Total:</label></td><td><input size=20 name=t1 onfocus=\"pp();\"></td></tr>");
            out.println("<table><tr><td align=center><input type=submit value=\" Insert \"></td></tr></table>");
            out.println("</table></center>");
            out.println("<script>");
            out.println("function call()");
            out.println("{");
            out.println("var c= window.document.forms[0].con.value");
            out.println("if(c=='n')");
            out.println("{");
            out.println("window.document.forms[0].sno.value=\"\"");
            out.println("}");
            out.println("}");
            
            out.println("</script>");
            out.println("</body></html>");
       }
       catch(Exception e)
       {
           out.println("Exception in connection:"+e);
       }
   }
}