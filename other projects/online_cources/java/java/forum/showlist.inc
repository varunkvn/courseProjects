<!-- #INCLUDE File="gentools.asp" -->
<!-- #INCLUDE File="threads.asp" -->
<!--#include file="appearance.asp"-->
<%
	dim Cur2_Page
	dim Page2_Count
	dim row2_Count
	
	sThreadID = request.querystring("thread_id")
	sPostID = request.querystring("post_id")

	if Request("Page") = "" then 
		Cur2_Page = 1
	else
		Cur2_Page = CInt(Request("Page"))
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
                  	threadlimit = 25
   			set rstemp2 = server.createobject("ADODB.Recordset")
   			sSQL = "select thread_id from threads"
   			rstemp2.open sSQL, conntemp
  			if rstemp2.EOF then
      				Response.Write "An error has occurred.  No threadcount available!<br>"
      				rstemp2.Close
      				set rstemp2 = Nothing
      				Response.End
   			end if
   			maxthread = CLng(rstemp2("Thread_ID"))
   			minthread = maxthread - threadlimit
   			rstemp2.Close
   			set rstemp2 = Nothing
                  	
                  	set rstemp2=server.createobject("ADODB.Recordset")
			rstemp2.CursorLocation = adUseClient
			rstemp2.PageSize = 15
			sqltemp2 = "select * from tblMessages where tblMessages.Thread_ID <= " & maxthread & " and tblMessages.Thread_ID > " & minthread & " and Thread_ID=" & sThreadID & " and ForumID=" & ForumID & " order by tblMessages.Thread_ID desc, tblMessages.MessageID asc"
			rstemp2.open sqltemp2, conntemp, adopenstatic, adLockPessimistic, adCMDText
	
			Page2_Count = rstemp2.PageCount
			ReCount2 = rstemp2.Recordcount
	
			if 1 > Cur2_Page then Cur_Page = 1
			if Cur2_Page > Page2_Count then Cur2_Page = Page2_Count
	
			rstemp2.AbsolutePage = cINT(Cur2_Page)
			row2_Count =0
			%>
                  <tr>
                      <td width="100%">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="100%" bgcolor="#FFE6EE"><img border="0" src="../images/pixel.gif" width="1" height="1"></td>
                          </tr>
                        </table>
                      </td>
                  </tr>
                  <tr>
                      <td width="100%">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="50%"><font face="Arial,helvetica,verdana,geneva" size="2">Page <%=Cur2_Page%> 
                              of <%=Page2_Count%></font></td>
                            <td width="17%">
                              <p align="center"></td>
                            <td width="9%">
                              <p align="center"><b><font face="Arial,helvetica,verdana,geneva" size="2">&lt;&lt;</font></b></td>
                            <td width="8%">
                              <p align="center"><b><font face="Arial,helvetica,verdana,geneva" size="2">&gt;&gt;</font></b></td>
                            <td width="16%">
                              <p align="center"><font face="Arial,helvetica,verdana,geneva" size="2"><%=Cur2_Page%></font></td>
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
                    		do until rstemp2.eof and row2_Count < rstemp2.PageSize
        				row2_Count = row2_Count + 1
        				k = k + 1
        				if (k mod 2) = 0 then
        					bgcolor = "#FFF7E6"
        				else
        					bgcolor = "#FFFBF2"
        				end if
        				
        				ParentID = rstemp2("ParentMessageID")
					MessageID = rstemp2("MessageID")
					Thread_ID = rstemp2("Thread_ID")
					Username = rstemp2("Username")
					Email = rstemp2("Email")
					DateOfPost = rstemp2("DateOfPost")
					Subject = rstemp2("Subject")
					Message = rstemp2("Message")
					
					
			%>
			<%
                            AddPost (Clng(MessageID)), Clng(Thread_ID), CLng(ParentID),Subject, Username, FormatDateTime (DateOfPost, 0)
               		%>
               		<%rstemp2.movenext
                    loop%>
                          <tr>
                            <td width="91%">

                            <%ShowPage()%>
                            </td>
                            
                          <tr>
                      <td width="100%"><img border="0" src="../images/pixel.gif" width="1" height="3"></td>
                    </tr>
                        </table>
                      </td>
                    </tr>
                    
                    <tr>
                      <td width="100%">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="100%" background="images/msg_line.gif"><img border="0" src="../images/pixel.gif" width="1" height="6"></td>
                          </tr>
                          <tr>
                            <td width="100%">
                              <table border="0" width="100%" cellpadding="4">
                              <%
                              		set rstMsg = Server.createobject("ADODB.Recordset")
                              		sqlMsg = "select * from tblMessages where Thread_ID=" & sThreadID & " and MessageID=" & sPostID & " and ForumID=" & ForumID
                              		rstMsg.open sqlMsg, conntemp
                              		
                              		msg_user = rstMsg("Username")
                              		msg_date = rstMsg("DateOfPost")
                              		msg_subject = rstMsg("Subject")
                              		msg_message = rstMsg("Message")
                              %>
                                <tr>
                                  <td width="100%"><font face="Arial,helvetica,verdana,geneva" size=2pt><%=msg_user%> - <%=msg_date%></font></td>
                                </tr>
                                <tr>
                                  <td width="100%"><b><font face="Arial,helvetica,verdana,geneva" size=2pt><%=msg_subject%></font></b></td>
                                </tr>
                                <tr>
                                  <td width="100%"><font face="Arial,helvetica,verdana,geneva" size=2pt><%=msg_message%></font></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                          <tr>
                            <td width="100%" background="images/msg_line.gif"><img border="0" src="../images/pixel.gif" width="1" height="6"></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    
                    
                    
                    <tr>
                      <td width="100%">&nbsp;</td>
                    </tr>
                    
                    
                    
                    <tr>
                      <td width="100%">
                        <p align="center"><a href="forum.asp?smode=1" target="_self"><img border="0" src="../images/post.gif"></a>
                        <a href="forum.asp?smode=1&amp;thread_id=<%=sThreadID%>&amp;post_id=<%=sPostID%>" target="_self">
                        <img border="0" src="../images/reply.gif">
                        </a>
                        </p>
                      </td>
                    </tr>
                    
                    <tr>
                      <td width="100%">
                        <p align="right"><a href="forum.asp" target="_self" class=ng><font size="2"><b>Back
                        to Forum</b></font></a></td>
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