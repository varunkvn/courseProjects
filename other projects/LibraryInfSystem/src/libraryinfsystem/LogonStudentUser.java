package libraryinfsystem;

import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>Title: Library Information System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author James J. Wilcox
 * @version 1.0
 */

public class LogonStudentUser extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JPasswordField jPasswordField1 = new JPasswordField();
  JButton jButton1 = new JButton();

  public LogonStudentUser() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
    jLabel1.setText("STUDENT USER LOGON");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel2.setText("USERNAME");
    jLabel3.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel3.setText("PASSWORD");
    jPasswordField1.setText("");
    jPasswordField1.addActionListener(new LogonStudentUser_jPasswordField1_actionAdapter(this));
    jButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton1.setText("LOGIN");
    jButton1.addActionListener(new LogonStudentUser_jButton1_actionAdapter(this));
    jTextField1.addActionListener(new LogonStudentUser_jTextField1_actionAdapter(this));
    this.getContentPane().setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
    this.getContentPane().add(jLabel1,    new XYConstraints(7, 10, 291, -1));
    this.getContentPane().add(jLabel2, new XYConstraints(14, 56, -1, -1));
    this.getContentPane().add(jLabel3, new XYConstraints(10, 111, -1, -1));
    this.getContentPane().add(jTextField1,  new XYConstraints(107, 55, 205, -1));
    this.getContentPane().add(jPasswordField1, new XYConstraints(106, 110, 205, -1));
    this.getContentPane().add(jButton1,  new XYConstraints(105, 161, 253, -1));
  }

  void jButton1_actionPerformed(ActionEvent e) {
  Search4User   newWin=new Search4User();
  newWin.setSize(400, 300);
  newWin.setVisible(true);
  System.out.println(jTextField1.getText().trim());
  System.out.println(jPasswordField1.getText().trim());
  }

  void jTextField1_actionPerformed(ActionEvent e) {

  }

  void jPasswordField1_actionPerformed(ActionEvent e) {

  }
}

class LogonStudentUser_jButton1_actionAdapter implements java.awt.event.ActionListener {
  LogonStudentUser adaptee;

  LogonStudentUser_jButton1_actionAdapter(LogonStudentUser adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class LogonStudentUser_jTextField1_actionAdapter implements java.awt.event.ActionListener {
  LogonStudentUser adaptee;

  LogonStudentUser_jTextField1_actionAdapter(LogonStudentUser adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField1_actionPerformed(e);
  }
}

class LogonStudentUser_jPasswordField1_actionAdapter implements java.awt.event.ActionListener {
  LogonStudentUser adaptee;

  LogonStudentUser_jPasswordField1_actionAdapter(LogonStudentUser adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jPasswordField1_actionPerformed(e);
  }
}