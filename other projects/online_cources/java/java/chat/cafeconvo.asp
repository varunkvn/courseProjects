<html>

<head>
<meta http-equiv="refresh" content="5">
</head>

<body bgcolor="#FFFFFF">

<%
Dim convo
convo = Application("conversation")
If Len(convo) > 2000 Then
   convo = Left(convo, 2000)
   Application.Lock
   Application("conversation") = convo
   Application.Unlock
End If
Response.Write convo
%>

</body>
</html>
