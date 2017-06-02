import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class modify2 extends HttpServlet
{
    Connection con;
    String Error,fn,sql,sql1,sql2,fname,src,dest,tot,dur,cat,cost,start,end,day,time,v;
    ResultSet rst,rst1,rst2;
    ResultSetMetaData rmt,rmt1;
    Statement st,st1,st2;
    public void init(ServletConfig config) throws ServletException
    {
          super.init(config);
          try       
          {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               con = DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
          }
          catch(Exception e)
          {
               Error="Exception in connection:"+e;
          }
    }    
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
          fn=req.getParameter("fno");
          PrintWriter out=res.getWriter();
          res.setContentType("text/html");
          try
          {
               st=con.createStatement();
               rst=st.executeQuery("select * from flight_master where flight_no='"+fn+"'");
               while(rst.next())
               {
                    fname=rst.getString("flight_name");
                    src=rst.getString("source");
                    dest=rst.getString("detination");
                    tot=rst.getString("total_capacity");
                    dur=rst.getString("duration_of_flight");
               }                                  
               out.println("<html> <body>");
               out.println("<script>");
               out.println("function pp()");
               out.println("{");
               out.println("var s=document.forms[0].src.value;");
               out.println("var d=document.forms[0].dest.value;");
               out.println("if(s==d)");
               out.println("{");
               out.println("alert(\"Source And Destination Cannot Be Equal\")");
               out.println("document.forms[0].dest.focus();");
               out.println("}");
               out.println("return;");
               out.println("}");
               out.println("</script>");
               out.println("<form method=post action=\"http://localhost:8080/servlet/update\">");
               out.println("<center><table>");
               out.println("<tr><td><label>Flight No:</label></td><td><input type=text size=20 name=fn value='"+fn+"'readonly></td>");
               out.println("<td><label>Flight Name:</label></td><td><input type=text size=20 name=fname value='"+fname+"'></td></tr>");
               out.println("<tr><td><label>Source:</label></td><td><input type=text size=20 name=src value='"+src+"'></td>");
               out.println("<td><label>Destination:</label></td><td><input type=text size=20 name=dest  value='"+dest+"' onblur=pp()></td></tr>");
               out.println("<tr><td><label>Total_Capacity:</label></td><td><input type=text size=20 name=total value='"+tot+"' readonly></td>");
               out.println("<td><label>Duration_Of_Flight:</label></td><td><input type=text size=20 name=dur value='"+dur+"'></td></tr>");
               out.println("<tr><td><label>Category:</label></td><td><select size= multiple name=cat id= cat onClick=\"getValues();\">");
               st1=con.createStatement();
               sql1="select category,cost_of_ticket,starting_seatno,ending_seatno from flight_desc where flight_no='"+fn+"'";
               rst1=st1.executeQuery(sql1);
               rmt=rst1.getMetaData();
               int count=rmt.getColumnCount();
               String cost1[] =new String[100];
               String start1[] =new String[100];
               String end1[] =new String[100];
               int incr=0;
               while(rst1.next())
               {
                       String p=rst1.getString("category");
                       String start[] =new String[100];
                       cost1[incr]=rst1.getString("cost_of_ticket");
                       start1[incr]=rst1.getString("starting_seatno");
                       end1[incr]=rst1.getString("ending_seatno");
                       out.println("<option value="+p+">"+p+"</option>");
                       incr++;
               }
               out.println("</select></td>");
               out.println("<script>");
               out.println("var cost=new Array(100);");
               out.println("var start=new Array(100);");
               out.println("var end=new Array(100);");
               for(int i=0;i<=incr-1;i++)
               {
                    out.println("cost["+i+"]='"+cost1[i]+"'");
                    out.println("start["+i+"]='"+start1[i]+"'");
                    out.println("end["+i+"]='"+end1[i]+"'");
               }                                     
               out.println("function getValues()");
               out.println("{");
               out.println("document.forms[0].cost.value=cost[document.forms[0].cat.selectedIndex]");
               out.println("document.forms[0].start.value=start[document.forms[0].cat.selectedIndex]");
               out.println("document.forms[0].end.value=end[document.forms[0].cat.selectedIndex]");
               out.println("}");
               out.println("</script>");
               out.println("<td><label>Cost Of Ticket:</label></td><td><input type=text name=cost size=20 value=\"\"></td></tr>");
               out.println("<tr><td><label>Starting Seat No:</label></td><td><input type=text name=start size=20 value=\"\" readonly></td>");
               out.println("<td><label>Ending Seat No:</label></td><td><input type=text name=end size=20 value=\"\" readonly></td></tr>");
               out.println("<tr><td>Day_Of_Flight:</td><td><select size=multiple name=d1 onClick=\"check();\">");
               st2=con.createStatement();
               sql2="select day_of_flight,time_of_flight from flight_details where flight_no='"+fn+"'";
               rst2=st2.executeQuery(sql2);
               rmt1=rst2.getMetaData();
               int count1=rmt1.getColumnCount();
               String time1[] =new String[100];
               int incr1=0;
               while(rst2.next())
               {
                       String s=rst2.getString("day_of_flight");
                       time1[incr1]=rst2.getString("time_of_flight");
                       out.println("<option value="+s+">"+s+"</option>");
                       incr1++;
               }
               out.println("</select></td>");
               out.println("<script>");
               out.println("var time=new Array(100);");
               for(int i=0;i<=incr1-1;i++)
               {
                    out.println("time["+i+"]='"+time1[i]+"'");
               }                                     
               out.println(" function check()");
               out.println("{");
//               out.println("var i");
//               out.println("i=document.forms[0].d1.options(document.forms[0].d1.selectedIndex).text");
               out.println("document.forms[0].t1.value=time[document.forms[0].d1.selectedIndex]");
//               out.println("document.writeln(i)");
               out.println("}");
               out.println("</script>");
               out.println("<td><label>Time_Of_Flight:</label></td><td><input size=20 name=t1 value=\"\"></td></tr>");
               out.println("<table><tr><td align=center><input type=submit value=\" Update \"></td></tr></table>");
               out.println("</form>");
               out.println("</table></center>");
               out.println("</body></html>");
          }
          catch(Exception e2)
          {
              System.out.println("Exception In Second Post :"+e2);
          }
    }
}
