import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class insert6 extends HttpServlet
{
      Connection con;
      ResultSet rst;
      Statement st;
      PreparedStatement pst;
      String Error,fno,cat,dt,start,end,pno,pname,gender,age,ppno,vtype,rno,confirm,sno,cst,tot;
      public void init(ServletConfig config)throws ServletException
      {
          super.init(config);
          try
          {
              Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
              con=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
          }
          catch(Exception e)
          {
              Error="Exception in init:"+e;
          }
      }
      public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
      {
          PrintWriter out=res.getWriter();
          res.setContentType("text/html");
          fno=req.getParameter("fno");
          cat=req.getParameter("cat");
          dt=req.getParameter("date");
          start=req.getParameter("start");
          end=req.getParameter("end");
          pno=req.getParameter("pno");
          pname=req.getParameter("pname");
          gender=req.getParameter("gender");
          age=req.getParameter("age");
          ppno=req.getParameter("ppno");
          vtype=req.getParameter("vtype");
          rno=req.getParameter("rno");
          confirm=req.getParameter("con");
          sno=req.getParameter("sno");
          cst=req.getParameter("cost");
          tot=req.getParameter("t1");
          try
          {
               if(confirm.equals("y"))
               {
               pst=con.prepareStatement("insert into passenger_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
               pst.setString(1,pno);
               pst.setString(2,pname);
               pst.setString(3,gender);
               pst.setString(4,age);
               pst.setString(5,ppno);
               pst.setString(6,vtype);
               pst.setString(7,rno);
               pst.setString(8,confirm);
               pst.setString(9,sno);
               pst.setString(10,cst);
               pst.setString(11,fno);
               pst.setString(12,dt);
               pst.setString(13,cat);
               pst.setString(14,tot);
               pst.executeUpdate();
               out.println("<html><body>");
               out.println("<center>");
               out.println("<br><br>");
               out.println("<h3>Do you want to continue</h3>");
               out.println("<a href=http://localhost:8080/servlet/reserve3?rno="+rno+"&fno="+fno+"&cat="+cat+"&date="+dt+"&end="+end+"&start="+start+"&cost="+tot+">[ Yes ]</a><a href=http://localhost:8080/servlet/payment?rno="+rno+"&total="+tot+">[ No ]</a>");
               out.println("</center>");
               out.println("</body></html>");
               }
               else
               {
                   res.sendRedirect("http://localhost:8080/servlet/wait?rno="+rno+"&fno="+fno+"&pno="+pno+"&cat="+cat+"&date="+dt+"&end="+end+"&start="+start+"&cost="+tot);
               }
          }
          catch(Exception e)
          {
               out.println("Exception in connection:"+e);
          }
      }
}
