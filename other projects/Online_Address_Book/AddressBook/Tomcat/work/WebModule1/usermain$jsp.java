package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;


public class usermain$jsp extends HttpJspBase {


    static {
    }
    public usermain$jsp( ) {
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

            // HTML // begin [file="/usermain.jsp";from=(0,0);to=(31,1)]
                out.write("<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns=\"http://www.w3.org/TR/REC-html40\">\r\n<head>\r\n<title>My Address Book---Main Page</title>\r\n<link rel=\"File-List\" href=\"usermain_files/filelist.xml\">\r\n\r\n<!--[if !mso]>\r\n<style>\r\nv\\:*         { behavior: url(#default#VML) }\r\no\\:*         { behavior: url(#default#VML) }\r\n.shape       { behavior: url(#default#VML) }\r\n</style>\r\n<![endif]--><!--[if gte mso 9]>\r\n<xml><o:shapedefaults v:ext=\"edit\" spidmax=\"1027\"/>\r\n</xml><![endif]-->\r\n</head>\r\n<BODY bgcolor=\"#ffffff\" bgproperties=\"fixed\" background=\"images/pusar.GIF\">\r\n<BLOCKQUOTE dir=ltr style=\"MARGIN-RIGHT: 0px\">\r\n\r\n<P align=center>&nbsp;</P>\r\n\r\n<P align=center></P>\r\n\r\n<P align=center></P>\r\n\r\n<P align=center><strong><font face=\"Verdana\" color=\"#800000\">\r\n\r\n\r\n\r\n\r\n\r\n<form>\r\n ");

            // end
            // begin [file="/usermain.jsp";from=(31,3);to=(37,0)]
                
                  String name=(String)session.getAttribute("username");
                
                              if(name==null){
                          response.sendRedirect("http://localhost:8080/WebModule1/SessionExpire.jsp");
                        }
            // end
            // HTML // begin [file="/usermain.jsp";from=(37,2);to=(183,0)]
                out.write("\r\n\r\n</font></strong></P>\r\n\r\n\r\n\r\n<p>&nbsp;</p>\r\n<table cellSpacing=\"0\" cellPadding=\"0\" width=\"770\" align=\"center\" border=\"0\">\r\n  <tr>\r\n    <td>\r\n    <table cellSpacing=\"0\" cellPadding=\"2\" width=\"100%\" bgColor=\"#99cccc\" border=\"0\">\r\n      <tr>\r\n        <td vAlign=\"top\">&nbsp;<table cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" bgColor=\"#ffffff\" border=\"0\">\r\n          <tr>\r\n            <td noWrap width=\"1%\" height=\"1%\">\r\n            <img height=\"30\" src=\"blue_top.gif\" width=\"583\"></td>\r\n          </tr>\r\n        </table>\r\n        <table height=\"100%\" cellSpacing=\"0\" cellPadding=\"6\" width=\"100%\" bgColor=\"#ffffff\" border=\"0\">\r\n          <tr>\r\n            <td>\r\n            <p align=\"center\"><b><font color=\"#99CCCC\" size=\"5\">Welcome To Main\r\n            Page of </font></b><font size=\"5\" color=\"#99CCCC\"><b>My Address Book</b></font></p>\r\n            <p align=\"center\">&nbsp;</p>\r\n            <b>\r\n            <font face=\"VERDANA\" color=\"blue\" size=\"1\">\r\n            <font color=\"blue\" face=\"verdana\" size=\"1\">\r\n            <table cellSpacing=\"0\" cellPadding=\"0\" width=\"142\" border=\"0\" height=\"1\">\r\n              <tr>\r\n                <td height=\"34\" width=\"161\">\r\n                <img border=\"0\" src=\"new.GIF\" width=\"154\" height=\"34\"></td>\r\n              </tr>\r\n              <tr>\r\n                <td width=\"161\" background=\"menu_02.gif\" height=\"18\">\r\n                <p align=\"center\"><font color=\"#99CCCC\" face=\"ms sans serif\">&nbsp;</font></font></font><font color=\"blue\"><font color=\"#314f6b\"><a style=\"text-decoration: none\" href=\"usermain.jsp\"><font color=\"#99CCCC\" face=\"Verdana\">HOME</font></a></font></td>\r\n              </tr>\r\n              </font>\r\n            <font face=\"VERDANA\" color=\"blue\" size=\"1\">\r\n            <font color=\"blue\" face=\"verdana\" size=\"1\">\r\n              <tr>\r\n                <td width=\"161\" background=\"menu_02.gif\" height=\"19\">&nbsp;</td>\r\n              </tr>\r\n              </font><font face=\"VERDANA\" size=\"1\" color=\"#99CCCC\">\r\n              <tr>\r\n                <td width=\"161\" background=\"menu_02.gif\" height=\"1\"></font>\r\n            </font></font>\r\n            <font face=\"VERDANA\" color=\"blue\">\r\n                <p align=\"center\">\r\n                <font face=\"MS Sans Serif\" color=\"#99CCCC\">&nbsp;&nbsp;&nbsp;</font></font><font face=\"VERDANA\" color=\"#99CCCC\"></font></font><font face=\"VERDANA\" color=\"blue\"><font face=\"VERDANA\" color=\"#99CCCC\"><a href=\"EnterDetail.jsp\" style=\"text-decoration: none\"><font color=\"#99CCCC\">ADD\r\n                CONTACT</font></a></font></p>\r\n                </font>\r\n            <font face=\"VERDANA\" color=\"blue\" size=\"1\">\r\n                <font face=\"VERDANA\" color=\"#99CCCC\">\r\n                <p>\r\n            <b>\r\n            <!--[if gte vml 1]><v:shapetype id=\"_x0000_t75\"\r\n coordsize=\"21600,21600\" o:spt=\"75\" o:preferrelative=\"t\" path=\"m@4@5l@4@11@9@11@9@5xe\"\r\n filled=\"f\" stroked=\"f\">\r\n <v:stroke joinstyle=\"miter\"/>\r\n <v:formulas>\r\n  <v:f eqn=\"if lineDrawn pixelLineWidth 0\"/>\r\n  <v:f eqn=\"sum @0 1 0\"/>\r\n  <v:f eqn=\"sum 0 0 @1\"/>\r\n  <v:f eqn=\"prod @2 1 2\"/>\r\n  <v:f eqn=\"prod @3 21600 pixelWidth\"/>\r\n  <v:f eqn=\"prod @3 21600 pixelHeight\"/>\r\n  <v:f eqn=\"sum @0 0 1\"/>\r\n  <v:f eqn=\"prod @6 1 2\"/>\r\n  <v:f eqn=\"prod @7 21600 pixelWidth\"/>\r\n  <v:f eqn=\"sum @8 21600 0\"/>\r\n  <v:f eqn=\"prod @7 21600 pixelHeight\"/>\r\n  <v:f eqn=\"sum @10 21600 0\"/>\r\n </v:formulas>\r\n <v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/>\r\n <o:lock v:ext=\"edit\" aspectratio=\"t\"/>\r\n</v:shapetype><v:shape id=\"_x0000_s1031\" type=\"#_x0000_t75\" style='position:absolute;\r\n margin-left:135.75pt;margin-top:-99pt;width:381.75pt;height:243pt;z-index:1;\r\n mso-position-horizontal-relative:text;mso-position-vertical:absolute;\r\n mso-position-vertical-relative:text' wrapcoords=\"-171 0 -171 21479 21600 21479 21600 0 -171 0\">\r\n <v:imagedata src=\"usermain_files/image001.jpg\" o:title=\"pbog\" gain=\"19661f\"\r\n  blacklevel=\"22938f\"/>\r\n</v:shape><![endif]--><![if !vml]><span style='mso-ignore:vglayout;position:\r\nabsolute;z-index:1;left:181px;top:-132px;width:509px;height:324px'><img\r\nwidth=509 height=324 src=\"usermain_files/image002.jpg\" v:shapes=\"_x0000_s1031\"></span><![endif]></b><font face=\"VERDANA\" color=\"blue\"></td>\r\n              </tr>\r\n            <font face=\"VERDANA\" color=\"blue\" size=\"1\">\r\n              <font face=\"VERDANA\" size=\"1\" color=\"#99CCCC\">\r\n              <tr>\r\n            <font color=\"blue\" face=\"verdana\">\r\n                <td width=\"161\" background=\"menu_02.gif\" height=\"18\">\r\n                <p align=\"center\"><font color=\"#99CCCC\" face=\"ms sans serif\">&nbsp;</font></font></font></font></font></font><font color=\"blue\"><a style=\"text-decoration: none\" href=\"SearchDetail.jsp\"><font color=\"#99CCCC\" face=\"Verdana\">SEARCH\r\n                CONTACT</font></a></td>\r\n              </tr>\r\n              </font>\r\n            <font face=\"VERDANA\" color=\"blue\" size=\"1\">\r\n              <tr>\r\n            <font color=\"blue\" face=\"verdana\" size=\"1\">\r\n                <td width=\"161\" background=\"menu_02.gif\" height=\"19\">&nbsp;</td>\r\n              </font>\r\n              </tr>\r\n              </font>\r\n            <font face=\"VERDANA\" color=\"blue\">\r\n              <tr>\r\n            <font color=\"blue\" face=\"verdana\">\r\n                <td width=\"161\" background=\"menu_02.gif\" height=\"18\">\r\n                <p align=\"center\"><font color=\"#99CCCC\" face=\"ms sans serif\">&nbsp;</font></font></font><font color=\"#99CCCC\"><a style=\"text-decoration: none\" href=\"Default.jsp\"><font color=\"#99CCCC\" face=\"Verdana\">SIGN\r\n                OUT</font></a></td>\r\n              </tr>\r\n            </font><font face=\"VERDANA\" size=\"1\" color=\"#99CCCC\">\r\n              <tr>\r\n            <font color=\"blue\" face=\"verdana\" size=\"1\">\r\n                <td width=\"161\" background=\"menu_02.gif\" height=\"19\">&nbsp;</td>\r\n              </font>\r\n              </tr>\r\n              <tr>\r\n                <td height=\"33\" width=\"161\">\r\n            <b>\r\n                <font face=\"VERDANA\" color=\"blue\" size=\"1\">\r\n                <img border=\"0\" src=\"newnew.GIF\" width=\"156\" height=\"33\"></font></b><p>&nbsp;</p>\r\n                <p>&nbsp;</p>\r\n                <p>&nbsp;</p>\r\n                <p></td>\r\n              </tr>\r\n            </table>\r\n            </font></b>\r\n            <p align=\"center\">&nbsp;</td>\r\n          </tr>\r\n        </table>\r\n        <table height=\"100%\" cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" bgColor=\"#ffffff\" border=\"0\">\r\n          <tr>\r\n            <td>&nbsp;</td>\r\n          </tr>\r\n        </table>\r\n        </td>\r\n      </tr>\r\n    </table>\r\n    </td>\r\n  </tr>\r\n</table>\r\n<p>&nbsp;</p>\r\n<p>&nbsp;</p>\r\n<p>&nbsp;</p>\r\n</form>\r\n\r\n</BODY>\r\n</HTML>\r\n");

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
