import java.io.*;                        
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cancel4 extends HttpServlet
{
    String cno,cdt,rno,dd,ct,fno,sno,day,cst,str,s1,s2,dtt,dt,dt1;
    Connection con;
    int z,a,x,p,q,b,f,mo1,c,d;
    Statement st;
    ResultSet rs,rst,rst1,rst2;
    dates420 d1=new dates420();
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        cno=req.getParameter("cno");
        cdt=req.getParameter("cdt");
        dd=req.getParameter("ddt");
        ct=req.getParameter("ct");
        dt=req.getParameter("dt");
        fno=req.getParameter("fno");
        sno=req.getParameter("sno");
        rno=req.getParameter("rno");
        day=req.getParameter("day");
        dtt=d1.date5(dd,cdt,ct,dt);
        String dt1=d1.date5(dd,cdt,ct,dt);
        d=Integer.parseInt(dt1);
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
            st=con.createStatement();
            rst=st.executeQuery("select time_of_flight from flight_details where flight_no='"+fno+"' and day_of_flight='"+day+"'");
            while(rst.next())
            str=rst.getString("time_of_flight");
            if(d>=1 && d<=6)
            c=6;
            else if(d>7 && d<=11)
            c=11;
            else if(d>12 && d<=16)
            c=16;
            else if(d>17 && d<=20)
            c=21;
            else if(d>21 && d<=26)
            c=26;
            else if(d>27 && d<=30)
            c=30;
            rst1=st.executeQuery("select code,percentage from cancellation_master where description='"+c+"'");
            while(rst1.next())
            {
                s1=rst1.getString("code");
                s2=rst1.getString("percentage");
            }
            rst2=st.executeQuery("select cost from passenger_details where res_no='"+rno+"'");
            while(rst2.next())
            cst=rst2.getString("cost");
            out.println("<html><body>");

            out.println("<script>");
            out.println("function check()");
            out.println("{");
            out.println("var a="+cst);
            out.println("var b="+s2);
            out.println("var c=a*b/100");
            out.println("document.forms[0].amt.value=c;");
            out.println("}");
            out.println("</script>");

            out.println("<center><table>");
            out.println("<form method=post action=\"http://localhost:8080/servlet/cinsert2\">");
            out.println("<input type=hidden name=rno value="+rno+">");
            out.println("<input type=hidden name=fno value="+fno+">");
            out.println("<input type=hidden name=sno value="+sno+">");
            out.println("<input type=hidden name=ddt value="+dd+">");
            out.println("<caption><font size=5><b><u>Cancellation</u></b></font></caption>");
            out.println("<tr><td><label>Cancel No:</label></td><td><input size=20 name=cno value="+cno+" readonly>");
            out.println("<td><label>Code No:</label></td><td><input size=20 name=code value="+s1+" readonly></td><tr>");
            out.println("<tr><td><label>Cancellation Amount:</label></td><td><input size=20 name=amt onfocus=\"check();\" readonly></td></tr>");
            out.println("<table><tr><td align=center><input type=submit value=\" Cancel \"></td></tr></table>");
            out.println("</form>");
            out.println("</table></center>");
            out.println("</body></html>");
        }
        catch(Exception e)
        {
            out.println("Exception in connection:"+e);
        }
    }
}
