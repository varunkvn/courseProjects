import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class wait extends HttpServlet
{
     Connection con;
     Statement st;
     ResultSet rst;
     PreparedStatement pst;
     String fno,cat,date,pno,wno,type,rno,end,start,cost;
     public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
        PrintWriter out=res.getWriter();
        res.setContentType("text/plain");
        rno=req.getParameter("rno");
        fno=req.getParameter("fno");
        cat=req.getParameter("cat");
        date=req.getParameter("date");
        end=req.getParameter("end");
        start=req.getParameter("start");
        pno=req.getParameter("pno");
//        type=req.getParameter("type");
        cost=req.getParameter("cost");
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con=DriverManager.getConnection("jdbc:odbc:DSN","scott","tiger");
            st=con.createStatement();
        //    out.println("sdasd");
            rst=st.executeQuery("select nvl(max(waiting_no),0)+1 from waiting_list");
          //  out.println("tyrtyrty");
            while(rst.next())
            wno=rst.getString(1);
         //   out.println(wno);
            pst=con.prepareStatement("insert into waiting_list values(?,?,?,?,?)");
            pst.setString(1,pno);
            pst.setString(2,fno);
            pst.setString(3,date);
            pst.setString(4,cat);
            pst.setString(5,wno);
            pst.executeUpdate();
            out.println("<html><body>");
            out.println("<center><br><br>");
            out.println("<h3>Do you want to continue </h3>");
            out.println("<a href=\"http://localhost:8080/servlet/reserve3?rno="+rno+"&fno="+fno+"&cat="+cat+"&date="+date+"&end="+end+"&start="+start+"&cost="+cost+"\">[ Yes ]</a><a href=\"http://localhost:8080/servlet/\">[ No ]</a>");
            out.println("</body></html>");
       /*     if(type.equals("group"))
            {
                res.sendRedirect("http://localhost:8080/servlet/reserve3?rno="+rno+"&fno="+fno+"&cat="+cat+"&date="+date+"&end="+end+"&start="+start+"&cost="+cost);
            }     */
        }
        catch(Exception e)
        {
            out.println("Exception in connection:"+e);
        }
     }
}
