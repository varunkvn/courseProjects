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
 String tque=request.getParameter("fthree");
 //System.out.println(session.getId());
 session.setAttribute("thirdanswer",tque);
  Date d=new Date();
 %>
<html><head><title>four question </title></head>
<body>
Time <%=d.getHours() %>:<%=d.getMinutes() %><br>
Time start <%=session.getAttribute("timestart")%><br>
<form  method="post" action="<%=response.encodeURL("ffive.jsp")%>">

<pre>
   4. when we want to look the data from the table use
   <input type="radio" name="ffour" value="a">create
   <input type="radio" name="ffour" value="b">select
   <input type="radio" name="ffour" value="c">retrieve
   <input type="radio" name="ffour" value="d">run
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


	


				