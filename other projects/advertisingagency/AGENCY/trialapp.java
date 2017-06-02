import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.util.*;
public class trialapp extends HttpServlet
{
String trialno,applno,subid,msg="    ",img;
int trial_no,appno;
ResultSet rs,rs1;
Statement stmt,stmt1;
PreparedStatement pstmt;
Connection con;
String str,str1,str2;
public void init(ServletConfig sc)throws ServletException
{
super.init(sc);
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:advt","scott","tiger");
System.out.println("connection established");
}catch(Exception e){}
}
public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter pw=res.getWriter();
applno=req.getParameter("applno");
appno=Integer.parseInt(applno);
trialno=req.getParameter("r1");
subid=req.getParameter("subid");
trial_no=Integer.parseInt(trialno);
img=req.getParameter("img")+".gif";
System.out.println(img);
try
{
stmt=con.createStatement();
rs=stmt.executeQuery("select typeid from subtypes1 where applno="+appno);
rs.next();
str=rs.getString(1);
stmt.close();
stmt1=con.createStatement();
rs1=stmt1.executeQuery("select images from user_applications where applno="+appno);
rs1.next();
str1=rs1.getString(1);
stmt1.close();
if(trial_no==1)
{



         if(str.startsWith("N"))
{
                              if(subid.equals("halfqtr"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                          else if(subid.equals("qtr"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=200 height=300></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                         else if(subid.equals("half"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=300 height=400></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                            else
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=400 height=500></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
}//1
         else if(str.startsWith("V"))
{
                              if(subid.equals("15sec"))
{
      if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp1 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp1 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//15sec
                                else if(subid.equals("30sec"))
{
            if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp1 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else 
{
pw.println("<html><body>");
pw.println("<applet code=projapp1 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//30sec
                              else if(subid.equals("45sec"))
{
        if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp1 width=600 height=500>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp1 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//45sec
                               else
{
    if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp1 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp1 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//60sec
}
         else
{
                                  if(subid.equals("15days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                 else if(subid.equals("30days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                else if(subid.equals("45days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                else
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
}//1st trial
}
else if(trial_no==2)
{



         if(str.startsWith("N"))
{
                              if(subid.equals("halfqtr"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                          else if(subid.equals("qtr"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=200 height=300></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                         else if(subid.equals("half"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=300 height=400></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                            else
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=400 height=500></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
}//1
         else if(str.startsWith("V"))
{
                              if(subid.equals("15sec"))
{
      if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp2 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp2 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//15sec
                                else if(subid.equals("30sec"))
{
            if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp2 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else 
{
pw.println("<html><body>");
pw.println("<applet code=projapp2 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//30sec
                              else if(subid.equals("45sec"))
{
        if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp2 width=600 height=500>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp2 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//45sec
                               else
{
    if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp2 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp2 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//60sec
}
         else
{
                                  if(subid.equals("15days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                 else if(subid.equals("30days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                else if(subid.equals("45days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                else
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
}
}//2
else if(trial_no==3)
{



         if(str.startsWith("N"))
{
                              if(subid.equals("halfqtr"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                          else if(subid.equals("qtr"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=200 height=300></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                         else if(subid.equals("half"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=300 height=400></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                            else
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=400 height=500></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
}//1
         else if(str.startsWith("V"))
{
                              if(subid.equals("15sec"))
{
      if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp3 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp3 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//15sec
                                else if(subid.equals("30sec"))
{
            if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp3 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else 
{
pw.println("<html><body>");
pw.println("<applet code=projapp3 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//30sec
                              else if(subid.equals("45sec"))
{
        if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp3 width=600 height=500>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp3 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//45sec
                               else
{
    if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp3 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp3 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//60sec
}
         else
{
                                  if(subid.equals("15days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                 else if(subid.equals("30days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                else if(subid.equals("45days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                else
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
}
}//3
else 
{



         if(str.startsWith("N"))
{
                              if(subid.equals("halfqtr"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                          else if(subid.equals("qtr"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=200 height=300></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                         else if(subid.equals("half"))
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=300 height=400></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                            else
{
pw.println("<html><body>");
pw.println("<applet code=drawapplet width=400 height=500></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
}//1
         else if(str.startsWith("V"))
{
                              if(subid.equals("15sec"))
{
      if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp4 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp4 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//15sec
                                else if(subid.equals("30sec"))
{
            if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp4 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else 
{
pw.println("<html><body>");
pw.println("<applet code=projapp4 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//30sec
                              else if(subid.equals("45sec"))
{
        if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp4 width=600 height=500>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp4 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//45sec
                               else
{
    if(str1.equals("yes"))
{
pw.println("<html><body>");
pw.println("<applet code=projapp4 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("<param name=img value=\"/"+img+"\">");
pw.println("</applet>");
pw.println("</body></html>");
}
else
{
pw.println("<html><body>");
pw.println("<applet code=projapp4 width=600 height=550>");
pw.println("<param name=applno value="+applno+">");
pw.println("<param name=subid value="+subid+">");
pw.println("<param name=trialno value="+trialno+">");
pw.println("</applet>");
pw.println("</body></html>");
}
}//60sec
}
         else
{
                                  if(subid.equals("15days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                 else if(subid.equals("30days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                else if(subid.equals("45days"))
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
                                else
{
pw.println("<html><body>");
pw.println("<applet code=BallAnim width=100 height=200></applet>");
pw.println("<input type=submit value=submit>");
pw.println("<input type=reset value=cancel>");
pw.println("</body></html>");
}
}
}//4
}catch(Exception e){pw.println(e);}
}
}
