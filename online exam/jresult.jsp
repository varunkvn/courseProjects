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

<%
  System.out.println(session.getId());
  
   

  String email=(String)session.getAttribute("email");

  String sname=(String)session.getAttribute("sname");

  String mnumber=(String)session.getAttribute("mnumber");

  String one=(String)session.getAttribute("firstanswer");

  String two=(String)session.getAttribute("secondanswer");

  String three=(String)session.getAttribute("thirdanswer");

  String four=(String)session.getAttribute("fourthanswer");

  String five=request.getParameter("ffive");

  int i=0;
  if(one.equals("b"))
  i+=1;
  if(two.equals("a"))
  i+=1;
  if(three.equals("a"))
  i+=1;
  if(four.equals("b"))
  i+=1;
  if(five.equals("c"))
  i+=1;
%>

<h2>Congratulation u got <%=i%> marks out of 5 Question</h2> <br>

<br>
<pre><h3>
Dear <%=sname%>

        Thanks for writing the online written exam.<br> We will send to your mail <br>i.e "<%=email%>" <br>or we will call u 
  back after a week to your mobile<br> i.e "<%=mnumber%>" for 
  attending the interview
</h3></pre>



				
				</div>
		</div>
		<div id="sidebar">
			<ul>
				<li><a href="" class="selected">EXAM</a></li>
				<li><a href="index.html">MAIN MENU</a></li>
				
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


				