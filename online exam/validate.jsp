<%@ page import="java.io.*,java.sql.*" %>
<%
try
{
String s1=null;
String s2=null;
s1=request.getParameter("name");
s2=request.getParameter("password");
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
 Connection cn=DriverManager.getConnection("jdbc:odbc:oraclecon","swathi","swathi53");
Statement st=cn.createStatement();
 ResultSet rs=st.executeQuery("select password from login where username like '"+s1+"' ");

  while(rs.next())
  {
   if(rs.getString(1).equals(s2))
   {
    out.println("SUCCESSFULLY LOGGED IN");
   }
else
out.println("ID AND PASSWORD DO NOT MATCH");
break;
  }
}catch(Exception eas)
{
out.println("SOrry sir"+eas);
}
%>  