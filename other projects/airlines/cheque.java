import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class cheque extends HttpServlet
{
   String ino,rno,amt;
   public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
      PrintWriter out=res.getWriter();
      res.setContentType("text/html");
      ino=req.getParameter("ino");
      rno=req.getParameter("rno");
      amt=req.getParameter("amt");
      out.println("<html><body>");
      out.println("<script>");

      out.println("function chk()");
      out.println("{");
      out.println("var a=document.forms[0].cno.value;");   
      out.println("if(a.length == 0)");
      out.println("{");
      out.println("alert(\"Null field\");");
      out.println("document.forms[0].cno.focus();");
      out.println("return;");
      out.println("}");
      out.println("else");
      out.println("{");
      out.println("for(i=0;i<a.length;i++)");
      out.println("{");
      out.println("var b=a.substring(i,i+1);");
      out.println("if(!(b >=0 && b <= 9))");
      out.println("{");
      out.println("alert(\"Enter valid numbers only\");");
      out.println("document.forms[0].cno.focus();");
      out.println("document.forms[0].cno.value=\"\";");
      out.println("return;");
      out.println("}");
      out.println("}");
      out.println("}");
      out.println("document.forms[0].cno.value=a;");
      out.println("}");

      out.println("function check()");
      out.println("{");
      out.println("var a=document.forms[0].cdt.value;");
      out.println("if(a.length == 0)");
      out.println("{");
      out.println("alert(\"Null field\");");
      out.println("document.forms[0].cdt.focus();");
      out.println("return;");
      out.println("}");
      out.println("var b=a.indexOf(\"-\");");
      out.println("var c=a.lastIndexOf(\"-\");");
      out.println("var dt=a.substring(0,b);");
      out.println("var m=a.substring(b+1,c);");
      out.println("var ye=a.substring(c+1,a.length);");
      out.println("if(m==01 || m==1 || m==\"jan\" || m==\"january\") var f=\"jan\";");
      out.println("else if(m==02 || m==2 || m==\"feb\" || m==\"february\") var f=\"feb\";");
      out.println("else if(m==03 || m==3 || m==\"mar\" || m==\"march\") var f=\"mar\";");
      out.println("else if(m==04 || m==4 || m==\"apr\" || m==\"april\") var f=\"apr\";");
      out.println("else if(m==05 || m==5 || m==\"may\") var f=\"may\";");
      out.println("else if(m==06 || m==6 || m==\"jun\" || m==\"june\") var f=\"jun\";");
      out.println("else if(m==07 || m==7 || m==\"jul\" || m==\"july\") var f=\"jul\";");
      out.println("else if(m==08 || m==8 || m==\"aug\" || m==\"august\") var f=\"aug\";");
      out.println("else if(m==09 || m==9 || m==\"sep\" || m==\"september\") var f=\"sep\";");
      out.println("else if(m==10 || m==\"oct\" || m==\"october\") var f=\"oct\";");
      out.println("else if(m==11 || m==\"nov\" || m==\"november\") var f=\"nov\";");
      out.println("else if(m==12 || m==\"dec\" || m==\"december\") var f=\"dec\";");
      out.println("var dd=dt+\"-\"+f+\"-\"+ye;");
      out.println("document.forms[0].cdt.value=dd;");
      out.println("}");

      out.println("function bnk()");
      out.println("{");
      out.println("var a=document.forms[0].bank.value;");   
      out.println("if(a.length == 0)");
      out.println("{");
      out.println("alert(\"Null field\");");
      out.println("document.forms[0].bank.focus();");
      out.println("return;");
      out.println("}");
      out.println("else");
      out.println("{");
      out.println("for(i=0;i<a.length;i++)");
      out.println("{");
      out.println("var b=a.substring(i,i+1);");
      out.println("if(!(b >='a' && b <= 'z' || b >='A' && b <= 'Z'))");
      out.println("{");
      out.println("alert(\"Enter alphabets only\");");
      out.println("document.forms[0].bank.focus();");
      out.println("document.forms[0].bank.value=\"\";");
      out.println("return;");
      out.println("}");
      out.println("}");
      out.println("}");
      out.println("document.forms[0].bank.value=a;");
      out.println("}");


      out.println("</script>");
      out.println("<center><table>");
      out.println("<form method=post action=\"http://localhost:8080/servlet/cheque1\">");
      out.println("<input type=hidden name=rno value="+rno+">");
      out.println("<input type=hidden name=ino value="+ino+">");
      out.println("<input type=hidden name=amt value="+amt+">");
      out.println("<caption><font size=5><b><u>Cheque Details</u></b></font></cheque>");
      out.println("<tr><td><label>Cheque No:</label></td><td><input size=20 name=cno onblur=chk()></td>");
      out.println("<td><label>Cheque Date:</label></td><td><input size=20 name=cdt ></td><tr>");
      out.println("<tr><td><label>Bank Name:</label></td><td><input size=20 name=bank onfocus=\"check();\"></td></tr>");
      out.println("<table><tr><td align=center><input type=submit value=\" Insert \" onfocus=bnk()></td></tr></table>");
   }
}

