<%
	dim errors, errormsg(4)
	errors = 0
	for i = 0 to 3
		errormsg(i) = ""
	next
	
	sParentID = request("post_id")
	sThreadID = request("thread_id")
	
	'response.write sParentID & " - " & sThreadID
	
	function IsVoid (s)
      		if IsEmpty(s) or IsNull(s) or s = "" then
         		IsVoid = true
      		else
         		IsVoid = false
      		end if
   	end function
	
	mode = request.form("Submit")

	if mode = "Submit" then
	
	txtUsername = request.form("username")
	txt_email = request.form("email")
	txtSubject = request.form("subject")
	txtMessage = request.form("message")
	
	if txtUsername = "" then
		errors = errors + 1
		errormsg(0) = "Erreur/Error"
	end if
	
	dim atCnt
   	chkEmail = 0
					
	if txt_email = "" then
		chkEmail = 1
					
   		' chk length
   	elseif len(txt_email) < 5  then 
      		' a@b.c should be the shortest an
   		' address could be
		chkEmail = 1
			
		' chk format
		' has at least one "@"
	elseif instr(txt_email,"@") = 0 then
		chkEmail = 1
 
		' has at least one "."
	elseif instr(txt_email,".") = 0 then
		chkEmail = 1

		' has no more than 3 chars after last "."
	elseif len(txt_email) - instrrev(txt_email,".") > 3 then
		chkEmail = 1
 
		' has no "_" after the "@"
	elseif instr(txt_email,"_") <> 0 and _
		instrrev(txt_email,"_") > instrrev(txt_email,"@")  then
		chkEmail = 1

	else
		' has only one "@"
		atCnt = 0
		for i = 1 to len(txt_email)
			if  mid(txt_email,i,1) = "@" then
				atCnt = atCnt + 1
			end if
		next
 
		if atCnt > 1 then
			chkEmail = 1
		end if

		' chk each char for validity
 		for i = 1 to len(txt_email)
        		if  not isnumeric(mid(txt_email,i,1)) and _
				(lcase(mid(txt_email,i,1)) < "a" or _
				lcase(mid(txt_email,i,1)) > "z") and _
				mid(txt_email,i,1) <> "_" and _
				mid(txt_email,i,1) <> "." and _
				mid(txt_email,i,1) <> "@" and _
				mid(txt_email,i,1) <> "-" then
            			chkEmail = 1
        		end if
      		next
  	end if
  					
  	if chkEmail = 1 then
  		errors = errors + 1
		errormsg(1) = "Erreur/Error"
	end if
	
	if txtSubject="" then
		errors = errors + 1
		errormsg(2) = "Erreur/Error"
	end if
	
	if txtMessage="" then
		errors = errors + 1
		errormsg(3) = "Erreur/Error"
	end if
	
	if errors = 0 then
		Application.Lock
		if not IsVoid (sThreadID) then  ' i.e. this is a reply
      			nThreadID = Clng (sThreadID)
   		else                            ' i.e. this is a new thread
      			sParentID = "0"
      			set rsThread = Server.CreateObject ("ADODB.RECORDSET")
      			sqlThreads = "select * from Threads"
      			rsThread.Open sqlThreads, conntemp, adopenstatic, adLockPessimistic, adCMDText
     			if not rsThread.EOF then
         			sThreadID = rsThread("thread_id")
        			nThreadID = CLng (sThreadID) + 1
         			rsThread("thread_id") = nThreadID
         			rsThread.Update
      			else
         			response.Write "An error has occurred!<br>"
         			rsThread.Close
         			set rsThread = Nothing
         			Application.Unlock
         			Response.End
      			end if
      			rsThread.Close
      			Set rsThread = Nothing
   		end if
	set rstAddMsg = server.createobject("ADODB.Recordset")
	sqlAddMsg = "select * from tblMessages"
	rstAddMsg.open sqlAddMsg, conntemp,adopenstatic, adLockPessimistic, adCMDText
	
	rstAddMsg.AddNew
	rstAddMsg("Thread_ID") = nThreadID
	rstAddMsg("ParentMessageID") = CLng(sParentID)
	rstAddMsg("Username") = txtUsername
	rstAddMsg("Email") = txt_email
	rstAddMsg("DateOfPost") = Now()
	rstAddMsg("Subject") = txtSubject
	rstAddMsg("Message") = txtMessage
	rstAddMsg("ForumID") = ForumID
	rstAddMsg.Update
	rstAddMsg.close
	set rstAddMsg = nothing
	
	Application.UnLock
	
	response.redirect "forum.asp"
	
	end if
	
	end if

%>
<table border="0" width="100%" cellpadding="5">
  <tr>
    <td width="100%">
      <table border="0" width="100%" bgcolor="#FFD9E6">
        <tr bgcolor="#990033">
          <td width="100%">
            <table border="0" width="100%" cellpadding="4" bgcolor="#FFFFFF">
              <tr>
                <td width="100%" bgcolor="#FFFFFF">
                  <form method="POST" action="forum.asp?smode=1">
                  <input type="hidden" name="thread_id" value="<%=sThreadID%>">
   		<input type="hidden" name="post_id" value="<%=sParentID%>">
                    <table border="0" width="100%" cellspacing="1" cellpadding="2">
                      <tr>
                        <td width="31%" align="right"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Nickname:</b></font></td>
                        <td width="69%"><input type="text" name="username" size="20" maxlength="50" value="<%=request.form("username")%>" style="border-style: solid; border-width: 1">
                          <font face="Arial,helvetica,verdana,geneva" color="#FF0000" size="1"><%if not errormsg(0)="" then%><%=errormsg(0)%><%end if%></font></td>
                      </tr>
                      <tr>
                        <td width="31%" align="right"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Email:</b></font></td>
                        <td width="69%"><input type="text" name="email" size="20" maxlength="50" value="<%=request.form("email")%>" style="border-style: solid; border-width: 1">
                          <font face="Arial,helvetica,verdana,geneva" color="#FF0000" size="1"><%if not errormsg(1)="" then%><%=errormsg(1)%><%end if%></font></td>
                      </tr>
                      <tr>
                        <td width="31%" align="right"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Subject:</b></font></td>
                        <td width="69%"><input type="text" name="subject" size="20" maxlength="50" value="<%=request.form("subject")%>" style="border-style: solid; border-width: 1">
                          <font face="Arial,helvetica,verdana,geneva" color="#FF0000" size="1"><%if not errormsg(2)="" then%><%=errormsg(2)%><%end if%></font></td>
                      </tr>
                      <tr>
                        <td width="31%" valign="top" align="right"><font face="Arial,helvetica,verdana,geneva" size="2"><b>Message:</b></font></td>
                        <td width="69%" valign="top"><textarea rows="8" name="message" cols="40" wrap="virtual"><%=request.form("message")%></textarea>
                          <font face="Arial,helvetica,verdana,geneva" color="#FF0000" size="1"><%if not errormsg(3)="" then%><%=errormsg(3)%><%end if%></font></td>
                      </tr>
                      <tr>
                        <td width="100%" colspan="2" align="right">
                          <p align="center"><b><input type="submit" value="Submit" name="Submit"></b></td>
                      </tr>
                    </table>
                  </form>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>