<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">
<head>
<title>My Address Book---Add Details</title>

<script>

function form_validator()
{

  	if(theForm.contname.value == "") {
		 alert("Please Enter Name !");
		 theForm.userName.focus();
		 return(false);
	}

	if(theForm.contphone.value == "") {
		 alert("Please Enter Phone No !");
		 theForm.studentNo.focus();
		 return(false);
	}

	if(theForm.contaddress.value == "") {
		 alert("Please Enter Address !");
		 theForm.userID.focus();
		 return(false);
	}

}
</script>
</head>
<BODY bgcolor="#ffffff" bgproperties="fixed" background="images/pusar.GIF">

<BLOCKQUOTE dir=ltr style="MARGIN-RIGHT: 0px">
  <%


  	String name=(String)session.getAttribute("username");

              if(name==null){
          response.sendRedirect("http://localhost:8080/WebModule1/SessionExpire.jsp");
        }

        //out.println("This is a value YA ALLAH SHOW HO JAY AB Enter Detail"+name);
  %>

<P align=center></P>

<P align=center></P>

<P align=center></P>

<P align=center><strong><font face="Verdana" color="#800000">





<form onsubmit="form_validator()" name="theForm" action="http://localhost:8080/WebModule1/addserver">
</font></strong></P>

<input type="hidden" name="username" value="<%=name%>">

<p>&nbsp;</p>
<table cellSpacing="0" cellPadding="0" width="770" align="center" border="0">
  <tr>
    <td>
    <table cellSpacing="0" cellPadding="2" width="100%" bgColor="#99cccc" border="0">
      <tr>
        <td vAlign="top">&nbsp;<table cellSpacing="0" cellPadding="0" width="100%" bgColor="#ffffff" border="0">
          <tr>
            <td noWrap width="1%" height="1%">
            <img height="30" src="blue_top.gif" width="583"></td>
          </tr>
        </table>
        <table height="100%" cellSpacing="0" cellPadding="6" width="100%" bgColor="#ffffff" border="0">
          <tr>
            <td><font face="Arial, Helvetica, sans-serif"><strong>Enter Contect
            Info In My Address Book</strong></font><br>
            <font face="Verdana, arial, helvetica, sans-serif" size="-1">Enter
            the name of the person so that the record will manage and you will
            be able to view the details of the person.</font>
            <input type="hidden" value="0" name="flag">
              In case of any error in marks contact Administrator.<table cellSpacing="2" cellPadding="3" width="80%" align="center" border="0" height="235">
                <tr bgColor="#cccccc">
                  <td noWrap colSpan="2" height="16">
                  <font face="Verdana, arial, helvetica, sans-serif" size="-1">
                  Enter Contect Details</font></td>
                </tr>
                <tr bgColor="#eeeeee">
                  <td noWrap align="right" width="50%" height="23">
                  <font face="Verdana, arial, helvetica, sans-serif" size="-1">
                  Name :</font>
                          <input name="contname" size="22" maxLength=15>
                          <font face="Verdana, arial, helvetica, sans-serif" size="-1">&nbsp;
                  </font></td>
                  <td vAlign="top" bgColor="#eeeeee" height="32">
                  <font face="Verdana, arial, helvetica, sans-serif" size="-1">
                  Enter the Name of the person for which your are going to enter
                  the details.</font></td>
                </tr>
                <tr bgColor="#eeeeee">
                  <td noWrap align="right" height="32">
                  <font face="Verdana, arial, helvetica, sans-serif" size="-1">
                  Phone No:</font>
                          <input name="contphone" maxLength=11 size="22">
                          <font face="Verdana, arial, helvetica, sans-serif" size="-1">&nbsp;
                  </font></td>
                  <td vAlign="top" bgColor="#eeeeee" height="32">
                  Enter Phone of The Person</td>
                </tr>
                <tr bgColor="#eeeeee">
                  <td noWrap align="right" height="33">
                  <font face="Verdana, arial, helvetica, sans-serif" size="-1">
                  Address:</font>
                          <input type=text name="contaddress" maxLength=30 size="22">
                          <font face="Verdana, arial, helvetica, sans-serif" size="-1">&nbsp;
                  </font></td>
                  <td vAlign="top" bgColor="#eeeeee" height="33">
                  Enter Address of The Person.</td>
                </tr>
                <tr bgColor="#eeeeee">
                  <td colSpan="2" height="16"><strong>
                  <font face="Verdana, arial, helvetica, sans-serif" size="-1">
                  NOTE - </font></strong>
                  <font face="Verdana, arial, helvetica, sans-serif" size="-1">
                  <b>Administrator</b> </font><strong>
                  <font face="Verdana, arial, helvetica, sans-serif" size="-1">
                  are able to change&nbsp; information at any time.</font></strong></td>
                </tr>
                <tr bgColor="#cccccc">
                  <td align="right" colSpan="2" height="27">
     <input type="submit" value="  Save  " " style="background-color:#EFEFEF">&nbsp;
     <input type="reset" value="  Cancel  " " style="background-color:#EFEFEF"></td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table height="100%" cellSpacing="0" cellPadding="0" width="100%" bgColor="#ffffff" border="0">
          <tr>
            <td>&nbsp;</td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</form>

</BODY>
</HTML>
