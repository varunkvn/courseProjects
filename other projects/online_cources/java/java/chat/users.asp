<% Option Explicit %>
<html>
<head>
<meta http-equiv="refresh" content="10">
</head>
<body bgcolor="#FFFFFF">
<%
Dim Userlist, Datelist, i, num, users, refresh

' Retrieve application arrays
users = Application("users")
refresh = Application("refresh")

' Find current user and update his refresh time
For i = 1 To 50
   If users(i) = Session("name") Then
      refresh(i) = Time
      Exit For
   End If
Next

' Has ANYONE been gone more than 20 seconds?
For i = 1 To 50
   If users(i) <> "" Then
      If DateDiff("s",refresh(i), Time) > 20 Then
         Application("conversation") = "<b><i>" & users(i) & _
            " Leaves The Room</b></i><br>" & Application("conversation")
         users(i) = ""
         Application("NumUsers") = Application("NumUsers") - 1
      End If
   End If
Next
Application.Lock
Application("users") = users
Application("refresh") = refresh
Application.Unlock
%>
<h3>People In The Cafe: <%=Application("NumUsers")%></h3>
<%
For i = 1 To 50
   If users(i)<> "" Then 
      Response.Write users(i) & "<br>"
   End If
Next
%>
</body>
</html>
