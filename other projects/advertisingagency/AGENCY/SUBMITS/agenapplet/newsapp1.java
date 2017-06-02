import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/*<applet code=newsapp1 width=300 height=400>
<param name=applno value="1002">
<param name=subid value="halfqtr">
 <param name="img" value="dolphins.gif">
<param name=trialno value="1">
</applet>*/
public class newsapp1 extends Applet MouseMotionListener,ActionListener
{
URL url,url1;
Statement stmt;
Connection con;
ResultSet rs;
String msg,applno,subid,trialno,location,location1,res,res1;
int i,trial_no,appno;
TextArea ta;
TextField tf1,tf2,tf3,tf4,tf5;
Label l1,l2,l3,l4;
Panel p1,p2,p3;
Button b1,b2;
Image img;
StringTokenizer stk;


public void init()
{
setLayout(new BorderLayout());
 Font bold=new Font("Dialog",Font.BOLD,18);
ta=new TextArea();
ta.setFont(bold);
l1=new Label("Appno:");
l1.setFont(bold);
l2=new Label("subtypeid");
l4=new Label("trialno");
l1.setFont(bold);
l2.setFont(bold);
l4.setFont(bold);
tf1=new TextField(10);
tf2=new TextField(10);
tf4=new TextField(1);
tf5=new TextField(7);
b1=new Button("Yes");
b1.setFont(bold);
b2=new Button("No");
b2.setFont(bold);
p1=new Panel();
p2=new Panel();
p1.add(l1);
p1.add(tf1);
p1.add(l2);
p1.add(tf2);
p1.add(l4);
p1.add(tf4);
add("North",p1);
p2.add(b1);
p2.add(b2);
add("South",p2);
p3=new Panel();
p3.add(ta);
add("Center",p3);
applno=getParameter("applno");
subid=getParameter("subid");
trialno=getParameter("trialno");
img=getImage(getDocumentBase(),getParameter("img"));
setBackground(Color.cyan);
addMouseMotionListener(this);
b2.addActionListener(this);
b1.addActionListener(this);
}//voidinit
public void start()
{
tf1.setText(applno);
tf2.setText(subid);
System.out.println("hello");
try
{
System.out.println("hello1");

String query=URLEncoder.encode("applno")+"="+URLEncoder.encode(tf1.getText().trim());
String 			location1="http://localhost:8080/servlet/projserv1?";
String query1=query;System.out.println(query1);		
	url1=new URL(location1+query1);
                              System.out.println(url1);
			URLConnection con=url1.openConnection();
			DataInputStream dis=new DataInputStream(con.getInputStream());			
                          while((res1=dis.readLine())!=null)
			{
                  	                                     msg=res1;
                                
                                           ta.append(msg);
                                      }}catch(Exception e){}
trial_no=Integer.parseInt(trialno);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b1)
{
try
{
System.out.println("hello");
String query=URLEncoder.encode("applno")+"="+URLEncoder.encode(tf1.getText().trim());
String query1=URLEncoder.encode("trialno")+"="+URLEncoder.encode(tf4.getText().trim());
			location="http://localhost:8080/servlet/projserv?";
String query2=query+"&"+query1;
System.out.println(query2);
			url=new URL(location+query2);
System.out.println(url);
			URLConnection con=url.openConnection();
			DataInputStream dis=new DataInputStream(con.getInputStream());			
System.out.println(res);while((res=dis.readLine())!=null)
			{
                  
			    if(res.equals("bill"))
                                         {
                                       AppletContext ac=getAppletContext();
                                      URL url1=getCodeBase();
                                     ac.showDocument(new URL(url1+"billapp?applno="+applno+"&subid="+subid+"&trialno="+trialno),"_self");
}
                                          //tf5.setText(res);
                                      }//while
}catch(Exception e){}
}//if

if(ae.getSource()==b2)
{
try
{
if(trial_no==1)
{
AppletContext ac=getAppletContext();
URL url=getCodeBase();
ac.showDocument(new URL(url+"trials1?applno="+applno+"&subid="+subid+"&trialno="+trialno),"_self");
}
else if(trial_no==2)
{
AppletContext ac=getAppletContext();
URL url=getCodeBase();
ac.showDocument(new URL(url+"trials1?applno="+applno+"&subid="+subid+"&trialno="+trialno),"_self");
}
else if(trial_no==3)
{
AppletContext ac=getAppletContext();
URL url=getCodeBase();
ac.showDocument(new URL(url+"trials1?applno="+applno+"&subid="+subid+"&trialno="+trialno),"_self");
}
else
{
AppletContext ac=getAppletContext();
URL url=getCodeBase();
ac.showDocument(new URL(url+"trials1?applno="+applno+"&subid="+subid+"&trialno="+trialno),"_self");
}
}catch(Exception e){}
}
}
public void mouseMoved(MouseEvent me)
{
showStatus(String.valueOf(me.getX())+" "+String.valueOf(me.getY()));
}
public void mouseDragged(MouseEvent me)
{
}
public void paint(Graphics g)
{
}
}
