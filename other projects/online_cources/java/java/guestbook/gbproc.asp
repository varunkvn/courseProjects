<% Option Explicit %>
<html>
<head>
<title>Guest Book Response</title>
</head>
<body topmargin="20" leftmargin="20" background="../images/GENERIC.gif">

<%=Request.Form("FirstName")%><br>
<%=Request.Form("LastName")%><br>
Gender: <%=Request.Form("Gender")%><br>
Age: <%=Request.Form("Age")%><br>
Email: <%=Request.Form("Email")%><p>
<p>
<%
Dim filesys, peoplefile
Set filesys = CreateObject("Scripting.FileSystemObject")
Set peoplefile = filesys.OpenTextFile("gbpeople.txt", 8, true)
peoplefile.WriteLine Request.Form("FirstName")
peoplefile.WriteLine Request.Form("LastName")
peoplefile.WriteLine Request.Form("Gender")
peoplefile.WriteLine Request.Form("Age")
peoplefile.WriteLine Request.Form("Email")
peoplefile.Close
%>

<h3>This information has been saved.</h3>

<a href="gblist.asp">See others who signed in.</a>
</body>
</html>
