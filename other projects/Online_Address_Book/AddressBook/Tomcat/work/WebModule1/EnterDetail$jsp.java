package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class EnterDetail$jsp extends HttpJspBase {


    static {
    }
    public EnterDetail$jsp( ) {
    }

    private static boolean _jspx_inited = false;

    public final void _jspx_init() throws org.apache.jasper.runtime.JspException {
    }

    public void _jspService(HttpServletRequest request, HttpServletResponse  response)
        throws java.io.IOException, ServletException {

        JspFactory _jspxFactory = null;
        PageContext pageContext = null;
        HttpSession session = null;
        ServletContext application = null;
        ServletConfig config = null;
        JspWriter out = null;
        Object page = this;
        String  _value = null;
        try {

            if (_jspx_inited == false) {
                synchronized (this) {
                    if (_jspx_inited == false) {
                        _jspx_init();
                        _jspx_inited = true;
                    }
                }
            }
            _jspxFactory = JspFactory.getDefaultFactory();
            response.setContentType("text/html;ISO-8859-1");
            pageContext = _jspxFactory.getPageContext(this, request, response,
            			"", true, 8192, true);

            application = pageContext.getServletContext();
            config = pageContext.getServletConfig();
            session = pageContext.getSession();
            out = pageContext.getOut();

            // HTML // begin [file="/EnterDetail.jsp";from=(0,0);to=(33,2)]
                out.write("<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns=\"http://www.w3.org/TR/REC-html40\">\r\n<head>\r\n<title>My Address Book---Add Details</title>\r\n\r\n<script>\r\n\r\nfunction form_validator()\r\n{\r\n\r\n  \tif(theForm.contname.value == \"\") {\r\n\t\t alert(\"Please Enter Name !\");\r\n\t\t theForm.userName.focus();\r\n\t\t return(false);\r\n\t}\r\n\r\n\tif(theForm.contphone.value == \"\") {\r\n\t\t alert(\"Please Enter Phone No !\");\r\n\t\t theForm.studentNo.focus();\r\n\t\t return(false);\r\n\t}\r\n\r\n\tif(theForm.contaddress.value == \"\") {\r\n\t\t alert(\"Please Enter Address !\");\r\n\t\t theForm.userID.focus();\r\n\t\t return(false);\r\n\t}\r\n\r\n}\r\n</script>\r\n</head>\r\n<BODY bgcolor=\"#ffffff\" bgproperties=\"fixed\" background=\"images/pusar.GIF\">\r\n\r\n<BLOCKQUOTE dir=ltr style=\"MARGIN-RIGHT: 0px\">\r\n  ");

            // end
            // begin [file="/EnterDetail.jsp";from=(33,4);to=(43,2)]
                
                
                
                  	String name=(String)session.getAttribute("username");
                
                              if(name==null){
                          response.sendRedirect("http://localhost:8080/WebModule1/SessionExpire.jsp");
                        }
                
                        //out.println("This is a value YA ALLAH SHOW HO JAY AB Enter Detail"+name);
                  
            // end
            // HTML // begin [file="/EnterDetail.jsp";from=(43,4);to=(60,44)]
                out.write("\r\n\r\n<P align=center></P>\r\n\r\n<P align=center></P>\r\n\r\n<P align=center></P>\r\n\r\n<P align=center><strong><font face=\"Verdana\" color=\"#800000\">\r\n\r\n\r\n\r\n\r\n\r\n<form onsubmit=\"form_validator()\" name=\"theForm\" action=\"http://localhost:8080/WebModule1/addserver\">\r\n</font></strong></P>\r\n\r\n<input type=\"hidden\" name=\"username\" value=\"");

            // end
            // begin [file="/EnterDetail.jsp";from=(60,47);to=(60,51)]
                out.print(name);
            // end
            // HTML // begin [file="/EnterDetail.jsp";from=(60,53);to=(153,0)]
                out.write("\">\r\n\r\n<p>&nbsp;</p>\r\n<table cellSpacing=\"0\" cellPadding=\"0\" width=\"770\" align=\"center\" border=\"0\">\r\n  <tr>\r\n    <td>\r\n    <table cellSpacing=\"0\" cellPadding=\"2\" width=\"100%\" bgColor=\"#99cccc\" border=\"0\">\r\n      <tr>\r\n        <td vAlign=\"top\">&nbsp;<table cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" bgColor=\"#ffffff\" border=\"0\">\r\n          <tr>\r\n            <td noWrap width=\"1%\" height=\"1%\">\r\n            <img height=\"30\" src=\"blue_top.gif\" width=\"583\"></td>\r\n          </tr>\r\n        </table>\r\n        <table height=\"100%\" cellSpacing=\"0\" cellPadding=\"6\" width=\"100%\" bgColor=\"#ffffff\" border=\"0\">\r\n          <tr>\r\n            <td><font face=\"Arial, Helvetica, sans-serif\"><strong>Enter Contect\r\n            Info In My Address Book</strong></font><br>\r\n            <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">Enter\r\n            the name of the person so that the record will manage and you will\r\n            be able to view the details of the person.</font>\r\n            <input type=\"hidden\" value=\"0\" name=\"flag\">\r\n              In case of any error in marks contact Administrator.<table cellSpacing=\"2\" cellPadding=\"3\" width=\"80%\" align=\"center\" border=\"0\" height=\"235\">\r\n                <tr bgColor=\"#cccccc\">\r\n                  <td noWrap colSpan=\"2\" height=\"16\">\r\n                  <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">\r\n                  Enter Contect Details</font></td>\r\n                </tr>\r\n                <tr bgColor=\"#eeeeee\">\r\n                  <td noWrap align=\"right\" width=\"50%\" height=\"23\">\r\n                  <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">\r\n                  Name :</font>\r\n                          <input name=\"contname\" size=\"22\" maxLength=15>\r\n                          <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">&nbsp;\r\n                  </font></td>\r\n                  <td vAlign=\"top\" bgColor=\"#eeeeee\" height=\"32\">\r\n                  <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">\r\n                  Enter the Name of the person for which your are going to enter\r\n                  the details.</font></td>\r\n                </tr>\r\n                <tr bgColor=\"#eeeeee\">\r\n                  <td noWrap align=\"right\" height=\"32\">\r\n                  <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">\r\n                  Phone No:</font>\r\n                          <input name=\"contphone\" maxLength=11 size=\"22\">\r\n                          <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">&nbsp;\r\n                  </font></td>\r\n                  <td vAlign=\"top\" bgColor=\"#eeeeee\" height=\"32\">\r\n                  Enter Phone of The Person</td>\r\n                </tr>\r\n                <tr bgColor=\"#eeeeee\">\r\n                  <td noWrap align=\"right\" height=\"33\">\r\n                  <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">\r\n                  Address:</font>\r\n                          <input type=text name=\"contaddress\" maxLength=30 size=\"22\">\r\n                          <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">&nbsp;\r\n                  </font></td>\r\n                  <td vAlign=\"top\" bgColor=\"#eeeeee\" height=\"33\">\r\n                  Enter Address of The Person.</td>\r\n                </tr>\r\n                <tr bgColor=\"#eeeeee\">\r\n                  <td colSpan=\"2\" height=\"16\"><strong>\r\n                  <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">\r\n                  NOTE - </font></strong>\r\n                  <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">\r\n                  <b>Administrator</b> </font><strong>\r\n                  <font face=\"Verdana, arial, helvetica, sans-serif\" size=\"-1\">\r\n                  are able to change&nbsp; information at any time.</font></strong></td>\r\n                </tr>\r\n                <tr bgColor=\"#cccccc\">\r\n                  <td align=\"right\" colSpan=\"2\" height=\"27\">\r\n     <input type=\"submit\" value=\"  Save  \" \" style=\"background-color:#EFEFEF\">&nbsp;\r\n     <input type=\"reset\" value=\"  Cancel  \" \" style=\"background-color:#EFEFEF\"></td>\r\n                </tr>\r\n              </table>\r\n            </td>\r\n          </tr>\r\n        </table>\r\n        <table height=\"100%\" cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" bgColor=\"#ffffff\" border=\"0\">\r\n          <tr>\r\n            <td>&nbsp;</td>\r\n          </tr>\r\n        </table>\r\n        </td>\r\n      </tr>\r\n    </table>\r\n    </td>\r\n  </tr>\r\n</table>\r\n</form>\r\n\r\n</BODY>\r\n</HTML>\r\n");

            // end

        } catch (Throwable t) {
            if (out != null && out.getBufferSize() != 0)
                out.clearBuffer();
            if (pageContext != null) pageContext.handlePageException(t);
        } finally {
            if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
        }
    }
}
