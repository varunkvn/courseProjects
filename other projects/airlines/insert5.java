import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class insert5 extends HttpServlet
{
      String Error,pno,pname,gender,age,ppno,vtype,rno,con,sno,cost,type,end,fno,date,cat,start;
      Connection con1;
      ResultSet rst;
      Statement st;
      PreparedStatement pst;
      public void init(ServletConfig config)throws ServletException
      {
          super.init(config);
          try
          {
             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             con1=DriverManager.getConnection("jdbc:odbc:DSN","psrini","ssi");
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
          pno=req.getParameter("pno");
          pname=req.getParameter("pname");
          gender=req.getParameter("gender");
          age=req.getParameter("age");
          ppno=req.getParameter("ppno");
          vtype=req.getParameter("vtype");
          rno=req.getParameter("rno");
          con=req.getParameter("con");
          sno=req.getParameter("sno");
          cost=req.getParameter("cost");
          type=req.getParameter("type");
          end=req.getParameter("end");
          fno=req.getParameter("fno");
          cat=req.getParameter("cat");
          date=req.getParameter("date");
          start=req.getParameter("start");
          try
          {
             if(con.equals("y"))
             {
                pst=con1.prepareStatement("insert into passenger_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1,pno);
                pst.setString(2,pname);
                pst.setString(3,gender);
                pst.setString(4,age);
                pst.setString(5,ppno);
                pst.setString(6,vtype);
                pst.setString(7,rno);
                pst.setString(8,con);
                pst.setString(9,sno);
                pst.setString(10,cost);
                pst.setString(11,fno);
                pst.setString(12,date);
                pst.setString(13,cat);
                pst.setString(14,cost);
                pst.executeUpdate();
                if(type.equals("group"))
                {
                    res.sendRedirect("http://localhost:8080/servlet/reserve3?&rno="+rno+"&fno="+fno+"&cat="+cat+"&date="+date+"&end="+end+"&start="+start+"&cost="+cost);
                }
                else
                {
                    res.sendRedirect("http://localhost:8080/servlet/payment?&rno="+rno+"&total="+cost+"");
                }
             }
             else 
             { 
                res.sendRedirect("http://localhost:8080/servlet/wait?rno="+rno+"&fno="+fno+"&cat="+cat+"&date="+date+"&end="+end+"&start="+start+"&pno="+pno+"&type="+type+"&cost="+cost);
             }
          }
          catch(Exception e1)
          {
           out.println("Exception in connection:"+e1);
          }
       }
}
