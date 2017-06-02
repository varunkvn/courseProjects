<%
	dim Cur_Page
	dim Page_Count
	dim row_Count

	if Request("Page") = "" then 
		Cur_Page = 1
	else
		Cur_Page = CInt(Request("Page"))
	end if
		
%>
<table border="0" width="100%" cellpadding="5">
  <tr>
    <td width="100%">
      <table border="0" width="100%" bgcolor="#FFD9E6">
        <tr>
          <td width="100%" bgcolor="#990033">
            <table border="0" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td width="100%" bgcolor="#FFFFFF">
                  <table border="0" width="100%" cellspacing="0" cellpadding="3">
                  <%
                  	set rstemp2=server.createobject("ADODB.Recordset")
			rstemp2.CursorLocation = adUseClient
			rstemp2.PageSize = 15
			sqltemp2 = "select * from tblMessages where ParentMessageID=0 and ForumID=" & ForumID & " order by MessageID"
			rstemp2.open sqltemp2, conntemp, adopenstatic, adLockPessimistic, adCMDText
	
			Page_Count = rstemp2.PageCount
			ReCount = rstemp2.Recordcount
	
			if 1 > Cur_Page then Cur_Page = 1
			if Cur_Page > Page_Count then Cur_Page = Page_Count
	
			rstemp2.AbsolutePage = cINT(Cur_Page)
			row_Count =0
			%>
                  <tr>
                      <td width="100%">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="100%" bgcolor="#FFE6EE"><img border="0" src="../images/pixel.gif"></td>
                          </tr>
                        </table>
                      </td>
                  </tr>
                  <tr>
                      <td width="100%">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="50%"><font face="Arial,helvetica,verdana,geneva" size="2">Page <%=Cur_Page%> 
                              de <%=Page_Count%></font></td>
                            <td width="17%">
                              <p align="center"></td>
                            <td width="9%">
                              <p align="center"><b><font face="Arial,helvetica,verdana,geneva" size="2">&lt;&lt;</font></b></td>
                            <td width="8%">
                              <p align="center"><b><font face="Arial,helvetica,verdana,geneva" size="2">&gt;&gt;</font></b></td>
                            <td width="16%">
                              <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><%=Cur_Page%></font></td>
                          </tr>
                        </table>
                      </td>
                  </tr>
                  <tr>
                      <td width="100%">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="100%" bgcolor="#FFE6EE"><img border="0" src="../images/pixel.gif"></td>
                          </tr>
                        </table>
                      </td>
                  </tr>
                    
                    <tr>
                      <td width="100%">
                        <table border="0" width="100%" cellspacing="0">
                          <%
                    		do while not rstemp2.eof and row_Count < rstemp2.PageSize
        				row_Count = row_Count + 1
        				k = k + 1
        				if (k mod 2) = 0 then
        					bgcolor = "#FFF7E6"
        				else
        					bgcolor = "#FFFBF2"
        				end if
        				
					MessageID = rstemp2("MessageID")
					Thread_ID = rstemp2("Thread_ID")
					Username = rstemp2("Username")
					Email = rstemp2("Email")
					DateOfPost = rstemp2("DateOfPost")
					Subject = rstemp2("Subject")
					Message = rstemp2("Message")
			%>
                          <tr>
                            <td width="91%" bgcolor="<%=bgcolor%>">
                            <a href="forum.asp?smode=2&thread_id=<%=Thread_ID%>&post_id=<%=MessageID%>" class=ng>
                            <font face="Arial,helvetica,verdana,geneva" size="2"><%=Username%> - <%=Email%> - <%=DateOfPost%> : <%=Subject%></font>
                            </a>
                            </td>
                            <td width="9%" bgcolor="<%=bgcolor%>">
                              <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><%=NumOfReply%></font></td>
                          </tr>
                          <tr>
                      <td width="100%"><img border="0" src="../images/pixel.gif" width="1" height="3"></td>
                    </tr>
                          <%rstemp2.movenext
                    loop%>
                        </table>
                      </td>
                    </tr>
                    
                    <tr>
                      <td width="100%">&nbsp;</td>
                    </tr>
                    
                    
                    
                    <tr>
                      <td width="100%"><a href="forum.asp?smode=1" target="_self"><img border="0" src="../images/post.gif"></a></td>
                    </tr>
                    
                    <tr>
                      <td width="100%"></td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td width="100%"></td>
  </tr>
</table>