<% Option Explicit %>
<% Response.Buffer = true %>
<html>
<head>
<title>Cafe Entrance</title>
</head>
<body bgcolor="#FFFFFF">

<% If trim(Request.Form("name")) = "" Then %>

<h1 align="center"><font face="Arial,helvetica,verdana,geneva" size="2">Welcome to the Cafe</font></h1>

<p align="center"><font face="Arial,helvetica,verdana,geneva" size="2">

The Cafe is a casual gathering place to meet new friends and
chat with old ones.</font><p align="center">
<font face="Arial,helvetica,verdana,geneva" size="2">
To enter the Cafe, type the name you wish to use below and
click the button.</font><p align="center">

<form action="default.asp" method="POST">
    <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2">Your Name: <input type="text" size="20" name="Name"></font></p>
    <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><input type="submit" name="Submit"
    value="Take Me To The Cafe"></font></p>
</form>

<%
Else
   Dim users, refresh
   Session("name") = Request.Form("name")

   If Application("NumUsers") = 0 Then
      ' Initialize (or re-initialize) the user list
      ' and the conversation
      ReDim temp1(50)
      ReDim temp2(50)
      Application("users") = temp1
      Application("refresh") = temp2
   End If

   ' Retrieve the application arrays
   users = Application("users")
   refresh = Application("refresh")

   ' Find an empty spot and add the new user
   Dim i
   For i = 1 To 50
      If users(i) = "" Then
          users(i) = Session("name")
          refresh(i) = time
          Application.Lock
          Application("NumUsers") = Application("NumUsers") + 1
          Application.Unlock
          Exit For
      End If
   Next
   If i = 51 Then Response.Redirect "toomany.asp"

   ' Put the arrays back in the application vars
   Application.Lock
   Application("users") = users
   Application("refresh") = refresh
   Application.Unlock

   If not IsEmpty(Application("conversation")) Then 
      Application.Lock
      Application("conversation") = "<b><i>" & Session("name") & _
         " Enters The Room</b></i><br>" & Application("conversation")
      Application.Unlock
   End If

   Response.Redirect "cafe.asp"
End If
%>
</body>
</html>
