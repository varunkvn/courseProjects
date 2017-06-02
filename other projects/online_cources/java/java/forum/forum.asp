<!--#INCLUDE File="../db/adovbs.inc" -->
<!-- #INCLUDE File="Sessions.inc" -->
<%
	RESPONSE.BUFFER = TRUE
	PageTitle = "forum.gif"
	smode = request.querystring("smode")
	set conntemp = Server.CreateObject("ADODB.Connection")
	DSNtemp="DRIVER={Microsoft Access Driver (*.mdb)}; "
	DSNtemp=dsntemp & "DBQ=" & server.mappath("../db/forum.mdb")
	conntemp.Open DSNtemp
	
	set rstemp = server.createobject("ADODB.Recordset")
	sqltemp = "select * from tblForums"
	rstemp.open sqltemp, conntemp
	
	ForumID = rstemp("ForumID")
	ForumQuestion = rstemp("ForumQuestion")
	
	set rstCount = server.createobject("ADODB.Recordset")
	sqlCount = "select * from tblMessages where ForumID=" & ForumID
	rstCount.open sqlCount, conntemp,1,3
	
	rstCount.movelast
	NumOfMsg = rstCount.RecordCount

%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Forums</title>
<link rel="stylesheet" type="text/css" href="../css/mm.css">
<base target="_self">
</head>

<body topmargin="10" leftmargin="20" background="../images/GENERIC.gif">

  <table border="0" width="540" cellspacing="5" cellpadding="5">
    <tr>
      <td width="100%">
        <div align="center">
          <center>
        <table border="0" width="100%" cellspacing="5" cellpadding="5">
          <tr>
            <td width="69%" valign="top" height="315">
            
            <table border="0" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td width="100%"><!-- #include file="forum_header.inc"-->
            </td>
              </tr>
              <tr>
                <td width="100%">
                <%select case smode%>
                <%case ""%>
            	<!-- #include file="main.inc" -->
            	<%case 1%>
            	<!-- #include file="postmsg.inc" -->
            	<%case 2%>
            	<!-- #include file="showlist.inc" -->
            <%end select%>
            </td>
              </tr>
            </table>
            
            </td>
          </tr>
        </table>
          </center>
        </div>
      </td>
    </tr>
  </table>

</body>

</html>
<%
	rstemp.close
	set rstemp=nothing
	conntemp.close
	set conntemp = nothing
%>
