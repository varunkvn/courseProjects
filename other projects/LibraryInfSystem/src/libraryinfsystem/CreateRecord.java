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

public class CreateRecord extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JButton jButton1 = new JButton();

  public CreateRecord() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
    jLabel1.setMaximumSize(new Dimension(96, 15));
    jLabel1.setText("RECORD CREATION");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel2.setText("TITLE OF BOOK");
    jLabel3.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel3.setText("AUTHOR OF BOOK");
    jLabel4.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel4.setMaximumSize(new Dimension(68, 15));
    jLabel4.setText("ISBN NUMBER");
    jLabel5.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel5.setMinimumSize(new Dimension(55, 15));
    jLabel5.setText("PUBLISHER");
    jTextField4.setText("");
    jTextField4.setScrollOffset(1);
    jTextField4.addActionListener(new CreateRecord_jTextField4_actionAdapter(this));
    jTextField1.setText("");
    jTextField1.addActionListener(new CreateRecord_jTextField1_actionAdapter(this));
    jTextField2.setText("");
    jTextField2.addActionListener(new CreateRecord_jTextField2_actionAdapter(this));
    jTextField3.setText("");
    jTextField3.addActionListener(new CreateRecord_jTextField3_actionAdapter(this));
    jButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton1.setText("CREATE RECORDS");
    jButton1.addActionListener(new CreateRecord_jButton1_actionAdapter(this));
    this.getContentPane().setBackground(UIManager.getColor("ComboBox.selectionBackground"));
    this.getContentPane().add(jTextField2, new XYConstraints(121, 109, 213, -1));
    this.getContentPane().add(jTextField1, new XYConstraints(122, 67, 211, -1));
    this.getContentPane().add(jTextField3, new XYConstraints(121, 149, 210, -1));
    this.getContentPane().add(jTextField4, new XYConstraints(121, 196, 214, -1));
    this.getContentPane().add(jLabel3, new XYConstraints(6, 109, -1, -1));
    this.getContentPane().add(jLabel2, new XYConstraints(5, 70, -1, -1));
    this.getContentPane().add(jLabel4, new XYConstraints(4, 152, -1, -1));
    this.getContentPane().add(jLabel5, new XYConstraints(5, 198, -1, -1));
    this.getContentPane().add(jLabel1, new XYConstraints(22, 16, -1, -1));
    this.getContentPane().add(jButton1,  new XYConstraints(118, 238, 276, -1));
  }

  void jTextField1_actionPerformed(ActionEvent e) {

  }

  void jTextField2_actionPerformed(ActionEvent e) {

  }

  void jTextField3_actionPerformed(ActionEvent e) {

  }

  void jTextField4_actionPerformed(ActionEvent e) {

  }

  void jButton1_actionPerformed(ActionEvent e) {
    System.out.println(jTextField1.getText().trim());
    System.out.println(jTextField2.getText().trim());
    System.out.println(jTextField3.getText().trim());
    System.out.println(jTextField4.getText().trim());
  }
}

class CreateRecord_jTextField1_actionAdapter implements java.awt.event.ActionListener {
  CreateRecord adaptee;

  CreateRecord_jTextField1_actionAdapter(CreateRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField1_actionPerformed(e);
  }
}

class CreateRecord_jTextField2_actionAdapter implements java.awt.event.ActionListener {
  CreateRecord adaptee;

  CreateRecord_jTextField2_actionAdapter(CreateRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField2_actionPerformed(e);
  }
}

class CreateRecord_jTextField3_actionAdapter implements java.awt.event.ActionListener {
  CreateRecord adaptee;

  CreateRecord_jTextField3_actionAdapter(CreateRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField3_actionPerformed(e);
  }
}

class CreateRecord_jTextField4_actionAdapter implements java.awt.event.ActionListener {
  CreateRecord adaptee;

  CreateRecord_jTextField4_actionAdapter(CreateRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField4_actionPerformed(e);
  }
}

class CreateRecord_jButton1_actionAdapter implements java.awt.event.ActionListener {
  CreateRecord adaptee;

  CreateRecord_jButton1_actionAdapter(CreateRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}