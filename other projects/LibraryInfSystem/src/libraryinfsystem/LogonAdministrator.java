package libraryinfsystem;

import javax.swing.*;
import java.awt.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 * <p>Title: Library Information System</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author James J. Wilcox
 * @version 1.0
 */

public class LogonAdministrator extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JPasswordField jPasswordField1 = new JPasswordField();
  JButton jButton1 = new JButton();

  public LogonAdministrator() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
    jLabel1.setMaximumSize(new Dimension(97, 15));
    jLabel1.setText("ADMINISTRATOR LOGON");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel2.setText("USERNAME");
    jLabel3.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel3.setOpaque(false);
    jLabel3.setText("PASSWORD");
    jPasswordField1.setText("");
    jPasswordField1.addActionListener(new LogonAdministrator_jPasswordField1_actionAdapter(this));
    jTextField1.setText("");
    jTextField1.addActionListener(new LogonAdministrator_jTextField1_actionAdapter(this));
    jButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton1.setRolloverEnabled(false);
    jButton1.setSelected(false);
    jButton1.setText("LOGIN");
    jButton1.addActionListener(new LogonAdministrator_jButton1_actionAdapter(this));
    this.getContentPane().setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
    this.getContentPane().add(jLabel1,  new XYConstraints(5, 7, -1, -1));
    this.getContentPane().add(jLabel2, new XYConstraints(12, 47, -1, -1));
    this.getContentPane().add(jLabel3, new XYConstraints(12, 105, -1, -1));
    this.getContentPane().add(jTextField1, new XYConstraints(96, 45, 187, -1));
    this.getContentPane().add(jPasswordField1, new XYConstraints(96, 103, 186, -1));
    this.getContentPane().add(jButton1,  new XYConstraints(95, 146, 272, -1));
  }

  void jButton1_actionPerformed(ActionEvent e) {
  RECORDS newWin=new RECORDS();
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

class LogonAdministrator_jButton1_actionAdapter implements java.awt.event.ActionListener {
  LogonAdministrator adaptee;

  LogonAdministrator_jButton1_actionAdapter(LogonAdministrator adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class LogonAdministrator_jTextField1_actionAdapter implements java.awt.event.ActionListener {
  LogonAdministrator adaptee;

  LogonAdministrator_jTextField1_actionAdapter(LogonAdministrator adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField1_actionPerformed(e);
  }
}

class LogonAdministrator_jPasswordField1_actionAdapter implements java.awt.event.ActionListener {
  LogonAdministrator adaptee;

  LogonAdministrator_jPasswordField1_actionAdapter(LogonAdministrator adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jPasswordField1_actionPerformed(e);
  }
}