<%if session("username") = "" and session("password")="" then
	response.redirect "login.asp"
end if
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Enter Quiz Information</title>
<script language="Javascript">
<!--
	var chkval=""
		
	function chk_text(t, msg) {
		if (t.value != '') return true;
		alert(msg);
		t.focus();
		return false;
	}
	
	function check(frm) {
		if (chk_text(frm.filename, 'Please enter a File Name.') && 
			chk_text(frm.author, 'Please enter the Author\'\ns name.')) frm.submit();
	}
//-->
</script>
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="5">
    <tr>
      <td width="100%">
        <form method="POST" action="create_02.asp" onsubmit="check(this); return false;">
          <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><img border="0" src="../images/enterquizinformationheader.gif" width="232" height="23"></font></p>
          <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Filename: </b><input type="text" name="filename" size="30"></font></p>
          <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Author's
          Name:</b> <input type="text" name="author" size="30"></font></p>
          <p><font face="Arial,helvetica,verdana,geneva" size="2"><b>Number of
          questions in quiz: <select size="1" name="numofquestions">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10" selected>10</option>
            <option value="11">11</option>
            <option value="12">12</option>
            <option value="13">13</option>
            <option value="14">14</option>
            <option value="15">15</option>
            <option value="16">16</option>
            <option value="17">17</option>
            <option value="18">18</option>
            <option value="19">19</option>
            <option>20</option>
          </select> </b></font></p>
          <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><input type="submit" value="Submit Information" name="Submit">&nbsp;
          <input type="reset" value="Clear Information" name="Reset"></font></p>
        </form>
      </td>
    </tr>
  </table>

</body>

</html>
