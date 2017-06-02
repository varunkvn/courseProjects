
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Online Examination</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<body>
<div id="wrap">
	
	<div id="header">
		<div id="innerheader">
			<div id="title">
				<h1>ONLINE EXAM</h1>
				<h2>A nEw WaY To LeArN</h2>
			</div>
		</div>
	</div>
<div id="content">
		<div id="page">
		<div class="innerpage">




<%@ page import="java.util.Date" %>
<%
 String vemail=request.getParameter("email");
 String vsname=request.getParameter("name");
 String vmn=request.getParameter("mnumber");
 System.out.println(session.getId());
 String s="00:00";
 
 session.setAttribute("email",vemail);
 session.setAttribute("sname",vsname);
 session.setAttribute("mnumber",vmn);
 session.setAttribute("timestart",s);
 Date d=new Date();
%>
<html><head><title>First question </title></head>
<body>
Time <%=d.getHours() %>:<%=d.getMinutes() %><br>
Time Start <%=session.getAttribute("timestart")%><br>
<form  method="post" action="<%=response.encodeURL("ftwo.jsp")%>">
<pre>
   1. Oracle is a
   <input type="radio" name="fone" value="a">Operating system
   <input type="radio" name="fone" value="b">language
   <input type="radio" name="fone" value="c">GUI
   <input type="radio" name="fone" value="d">Kernal package
</pre>
  <input type="submit" value="next">
</form>


				
				</div>
		</div>
		<div id="sidebar">
			<ul>
				<li><a href="" class="selected">EXAM</a></li>
				
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	
	<div id="footer">
		<p>&copy; 2009. varun site.com. Design: <a href="http://www.orkut.com">varun</a> | <a href="http://www.facebook.com">varun again</a></p>
	</div>
</div>

</body>
</html>


	





