<html>

<body bgcolor="#FFFFFF" onload="document.form1.Say.focus()">

<%
If trim(Request.Form("say")) <> "" Then
   Application.Lock
   Application("conversation") = "<b>" & Session("name") & ": </b>" & _
      Request.Form("say") & "<br>" & Application("conversation")
   Application.Unlock
End If
%>

<form action="say.asp" method="POST" name="form1">
    <input type="submit" name="Submit" value="Say:">
    <input type="text" size="80" name="Say">
</form>
</body>
</html>
