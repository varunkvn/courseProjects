import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class reserve2 extends HttpServlet
{
    String rno,cat,fno,str,start,end,dt,s,x,sql2;
    int start1,end1,n,y,cost;
    Statement st;
    ResultSet rst,rst1,rst2,rst3;
    ResultSetMetaData rmt;
    Connection con;
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        rno=req.getParameter("res_no");
        fno=req.getParameter("flight_no");
        cat=req.getParameter("category");
        dt=req.getParameter("date");
        try
        {
             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             con=DriverManager.getConnection("jdbc:odbc:DSN","tiger","scott");
             st=con.createStatement();
             rst=st.executeQuery("select starting_seatno,ending_seatno from flight_desc where flight_no='"+fno+"' and category='"+cat+"'");
             while(rst.next())
             {
                  start=rst.getString("starting_seatno");
                  end=rst.getString("ending_seatno");
             }
             start1=Integer.parseInt(start);
             end1=Integer.parseInt(end);
             n=start1-1;
             rst1=st.executeQuery("select nvl(max(seat_no),"+ n +")+1 from passenger_details where flight_no='"+fno+"' and doj='"+dt+"' and category='"+cat+"'");
             while(rst1.next())
             x=rst1.getString(1);
             y=Integer.parseInt(x);
             if(y > end1)
             s="n";
             else
             s="y";
             rst2=st.executeQuery("select cost_of_ticket from flight_desc where flight_no='"+fno+"' and category='"+cat+"'");
             while(rst2.next())
             cost=rst2.getInt(1);
             out.println("<html><body>");
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
             out.println("if(!(b >='a' && b <= 'z' || b>='A' && b<='Z'))");
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
             out.println("function pp()");
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
             out.println("function vt()");
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
             out.println("<form method=post action=\"http://localhost:8080/servlet/insert5\">");
             out.println("<input type=hidden name=end value="+end1+">");
             out.println("<input type=hidden name=cat value="+cat+">");
             out.println("<input type=hidden name=fno value="+fno+">");
             out.println("<input type=hidden name=date value="+dt+">");
             out.println("<input type=hidden name=start value="+start1+">");
             out.println("<caption><font size=5><b><u>Passenger_Details</u></b></font></caption>");
             out.println("<tr><td><label>Pass No:</label></td><td><input size=20 name=pno onblur=passno()></td>");
             out.println("<td><label>Pass Name:</label></td><td><input size=20 name=pname></td></tr>");
             out.println("<tr><td><label>Gender:</label></td><td><input type=radio name=gender value=M onfocus=pn()>:Male<input type=radio name=gender value=F>:Female</td>");
             out.println("<td><label>Age:</label></td><td><input size=20 name=age></td></tr>");
             out.println("<tr><td><label>Passport No:</label></td><td><input size=20 name=ppno onfocus=ag()></td>");
             out.println("<td><label>Visa Type:</label></td><td><input size=20 name=vtype onfocus=pp()></td></tr>");
             out.println("<tr><td><label>Res No:</label></td><td><input size=20 name=rno value="+rno+" readonly onfocus=vt()></td>");
             out.println("<td><label>Confirmed:</label></td><td><input size=20 name=con value="+s+" readonly></td></tr>");
             out.println("<tr><td><label>Seat No:</label></td><td><input size=20 name=sno value="+x+" readonly></td>");
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
              out.println(" function check()");
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
              out.println("<tr><td><label>Type Of Reservation:</td><td><input type=radio name=type value=group>:Group Reservation</td>");
              out.println("<td><input type=radio name=type value=individual>:Individual</td></tr>");
              out.println("<table><tr><td align=center><input type=submit value=\" Insert \"></td></tr></table>");
              out.println("</table></center>");
              out.println("</body></html>");
        }
        catch(Exception e)
        {
           out.println("Exception:"+e);
        }
    }
}
