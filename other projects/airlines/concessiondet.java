import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class concessiondet extends HttpServlet
{
     public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
          PrintWriter out=res.getWriter();
          res.setContentType("text/html");
          out.println("<html><body><center>");
          out.println("<h3><u>Concession Details</u></h3>");
          out.println("</center>");
          out.println("<form method=post action=\"http://localhost:8080/servlet/\">");
          out.println("<table><center>");
          out.println("<tr><td><label>Type:</lable></td><td><input size=20 name=type></td>");
          out.println("<td><label>Age:</lable></td><td><input size=20 name=age></td></tr>");
          out.println("<tr><td><label>Pass_No:</lable></td><td><input size=20 name=pass></td>");
          out.println("<td><label>Concession_Amt:</lable></td><td><input size=20 name=con></td></tr>");
          out.println("<tr><td><label>Res_No:</lable></td><td><input size=20 name=res></td></tr>");
          out.println("<table><tr><td align=center><input type=submit name=submit></td></tr></table>");
          out.println("</center></table>");
          out.println("</body></html>");
     }
}

