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

public class ModifyRecord extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JButton jButton1 = new JButton();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();

  public ModifyRecord() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
    jLabel1.setText("RECORD MODIFICATION");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel2.setIconTextGap(4);
    jLabel2.setText("TITLE OF BOOK");
    jLabel3.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel3.setText("AUTHOR OF BOOK");
    jLabel4.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel4.setText("ISBN NUMBER");
    jLabel5.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel5.setText("PUBLISHER");
    jButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton1.setText("MODIFY RECORD");
    jButton1.addActionListener(new ModifyRecord_jButton1_actionAdapter(this));
    jTextField1.setText("");
    jTextField1.addActionListener(new ModifyRecord_jTextField1_actionAdapter(this));
    jTextField2.setRequestFocusEnabled(true);
    jTextField2.setText("");
    jTextField2.addActionListener(new ModifyRecord_jTextField2_actionAdapter(this));
    jTextField3.setText("");
    jTextField3.addActionListener(new ModifyRecord_jTextField3_actionAdapter(this));
    jTextField4.setText("");
    jTextField4.addActionListener(new ModifyRecord_jTextField4_actionAdapter(this));
    this.getContentPane().setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
    this.getContentPane().add(jLabel1, new XYConstraints(6, 8, -1, -1));
    this.getContentPane().add(jLabel2, new XYConstraints(11, 43, -1, -1));
    this.getContentPane().add(jLabel3, new XYConstraints(6, 82, -1, -1));
    this.getContentPane().add(jTextField1, new XYConstraints(137, 42, 179, -1));
    this.getContentPane().add(jTextField2, new XYConstraints(136, 80, 180, -1));
    this.getContentPane().add(jLabel4, new XYConstraints(10, 121, -1, -1));
    this.getContentPane().add(jTextField3, new XYConstraints(136, 117, 180, -1));
    this.getContentPane().add(jLabel5, new XYConstraints(14, 161, -1, -1));
    this.getContentPane().add(jTextField4, new XYConstraints(134, 163, 184, -1));
    this.getContentPane().add(jButton1, new XYConstraints(75, 205, 235, -1));
  }

  void jButton1_actionPerformed(ActionEvent e) {
    System.out.println(jTextField1.getText().trim());
    System.out.println(jTextField2.getText().trim());
    System.out.println(jTextField3.getText().trim());
    System.out.println(jTextField4.getText().trim());
  }

  void jTextField1_actionPerformed(ActionEvent e) {

  }

  void jTextField2_actionPerformed(ActionEvent e) {

  }

  void jTextField3_actionPerformed(ActionEvent e) {

  }

  void jTextField4_actionPerformed(ActionEvent e) {

  }
}

class ModifyRecord_jButton1_actionAdapter implements java.awt.event.ActionListener {
  ModifyRecord adaptee;

  ModifyRecord_jButton1_actionAdapter(ModifyRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class ModifyRecord_jTextField1_actionAdapter implements java.awt.event.ActionListener {
  ModifyRecord adaptee;

  ModifyRecord_jTextField1_actionAdapter(ModifyRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField1_actionPerformed(e);
  }
}

class ModifyRecord_jTextField2_actionAdapter implements java.awt.event.ActionListener {
  ModifyRecord adaptee;

  ModifyRecord_jTextField2_actionAdapter(ModifyRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField2_actionPerformed(e);
  }
}

class ModifyRecord_jTextField3_actionAdapter implements java.awt.event.ActionListener {
  ModifyRecord adaptee;

  ModifyRecord_jTextField3_actionAdapter(ModifyRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField3_actionPerformed(e);
  }
}

class ModifyRecord_jTextField4_actionAdapter implements java.awt.event.ActionListener {
  ModifyRecord adaptee;

  ModifyRecord_jTextField4_actionAdapter(ModifyRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField4_actionPerformed(e);
  }
}