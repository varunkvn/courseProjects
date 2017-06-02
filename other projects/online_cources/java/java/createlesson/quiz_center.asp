<%
	if (session("username") = "" and session("password")="") then
		response.redirect "login.asp"
	end if
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Quiz Center Menu</title>
<base target="_self">
</head>

<body topmargin="20" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="5">
    <tr>
      <td width="55">
      </td>
      <td width="443"><b><font face="Arial,helvetica,verdana,geneva" color="#FF0000" size="4">Your
        Quizzes</font></b>
        <ul>
          <li><font face="Arial,helvetica,verdana,geneva" size="3"><a href="create_01.asp" target="_self"><font color="#0000FF"><b>Create a Quiz</b></font></a></font></li>
          <li><font face="Arial,helvetica,verdana,geneva" size="3"><a href="view_delete.asp" target="main"><font color="#0000FF"><b>View or
            delete a Quiz</b></font></a></font></li>
          <li><font face="Arial,helvetica,verdana,geneva" size="3"><a href="edit_list.asp" target="main"><font color="#0000FF"><b>Edit a Quiz</b></font></a></font></li>
        </ul>
      </td>
    </tr>
    <tr>
      <td width="55">
      </td>
      <td width="443"><b><font face="Arial,helvetica,verdana,geneva" color="#FF0000" size="4">Student</font></b>
      </td>
    </tr>
    <tr>
      <td width="55">
      </td>
      <td width="443">
        <ul>
          <li><a href="../registration/student_list.asp" target="_self"><font face="Arial,helvetica,verdana,geneva" size="3" color="#2C4D9C"><b>View
            or delete a student</b></font></a></li>
        </ul>
      </td>
    </tr>
  </table>

</body>

</html>
