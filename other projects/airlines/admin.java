
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class admin extends HttpServlet
{
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
       PrintWriter out=res.getWriter();
       res.setContentType("text/html");
       try
       {
          out.println("<html><body bgcolor=pink>");
          out.println("<center>");
          out.println("<br><br>");
          out.println("<table border=0>");
          out.println("<caption><font size=6 color=clock><u>Administration</font></u></caption><br><br>");
          out.println("<tr><td><a href=\"http://localhost:8080/servlet/add1\"><font size=3 color=>Add Flight Details</font></a></td></tr>");
          out.println("<tr><td><a href=\"http://localhost:8080/servlet/modify\"><font size=3 color=blue>Modify Flight Details</font></a></td></tr>");
          out.println("<tr><td><a href=\"http://localhost:8080/servlet/delete\"><font size=3 color=blue>Delete Flight Details</font></a></td></tr>");
          out.println("<tr><td><a href=\"http://localhost:8080/servlet/concession\"><font size=3 color=blue>Concession Details</font></a></td></tr>");
          out.println("<tr><td><a href=\"http://localhost:8080/servlet/cmodify\"><font size=3 color=blue>Modify Concession Details</font></a></td></tr>");
          out.println("<tr><td><a href=\"http://localhost:8080/servlet/cdelete\"><font size=3 color=blue>Delete Concession Details</font></a></td>");
          out.println("</table>");
          out.println("</body></html>");
       }
       catch(Exception e)
       {}
   }
}
