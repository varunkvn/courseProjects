<% Option Explicit %>
<html>
<head>
<title>Guest Book List</title>
</head>
<body topmargin="20" leftmargin="20" background="../images/GENERIC.gif">


<%
Dim filesys, peoplefile, fname, lname, gender, age, email
Set filesys = CreateObject("Scripting.FileSystemObject")
Set peoplefile = filesys.OpenTextFile("gbpeople.txt", 1)
do while not peoplefile.AtEndOfStream
  fname = peoplefile.ReadLine
  lname = peoplefile.ReadLine
  gender = peoplefile.ReadLine
  age = peoplefile.ReadLine
  email = peoplefile.ReadLine

  Response.Write fname & "<br>"
  Response.Write lname & "<br>"
  Response.Write gender & "<br>"
  Response.Write age & "<br>"
  Response.Write email & "<p>"
loop
peoplefile.Close
%>


</body>
</html>
