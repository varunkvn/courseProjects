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

public class RECORDS extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();

  public RECORDS() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
    jLabel1.setText("PLEASE CHOOSE ONE");
    this.getContentPane().setLayout(xYLayout1);
    jButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton1.setOpaque(true);
    jButton1.setText("CREATE RECORDS");
    jButton1.addActionListener(new RECORDS_jButton1_actionAdapter(this));
    jButton2.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton2.setPreferredSize(new Dimension(125, 25));
    jButton2.setFocusPainted(true);
    jButton2.setText("MODIFY RECORDS");
    jButton2.addActionListener(new RECORDS_jButton2_actionAdapter(this));
    jButton3.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton3.setOpaque(true);
    jButton3.setText("DELETE RECORDS");
    jButton3.addActionListener(new RECORDS_jButton3_actionAdapter(this));
    jButton4.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton4.setRolloverEnabled(false);
    jButton4.setText("SEARCH RECORDS");
    jButton4.addActionListener(new RECORDS_jButton4_actionAdapter(this));
    this.getContentPane().setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
    this.getContentPane().add(jLabel1,  new XYConstraints(25, 18, 300, -1));
    this.getContentPane().add(jButton4,   new XYConstraints(12, 225, 310, -1));
    this.getContentPane().add(jButton3, new XYConstraints(11, 175, 311, -1));
    this.getContentPane().add(jButton2, new XYConstraints(11, 124, 313, -1));
    this.getContentPane().add(jButton1, new XYConstraints(13, 73, 312, -1));
  }

  void jButton2_actionPerformed(ActionEvent e) {
  ModifyRecord newWin=new ModifyRecord();
  newWin.setSize(400, 300);
  newWin.setVisible(true);

  }

  void jButton1_actionPerformed(ActionEvent e) {
    CreateRecord newWin=new CreateRecord();
     newWin.setSize(400, 300);
     newWin.setVisible(true);


  }

  void jButton3_actionPerformed(ActionEvent e) {
  DeleteRecord newWin=new DeleteRecord();
  newWin.setSize(400, 300);
  newWin.setVisible(true);

  }

  void jButton4_actionPerformed(ActionEvent e) {
  SearchRecord newWin=new SearchRecord();
  newWin.setSize(400, 300);
  newWin.setVisible(true);

  }
}

class RECORDS_jButton2_actionAdapter implements java.awt.event.ActionListener {
  RECORDS adaptee;

  RECORDS_jButton2_actionAdapter(RECORDS adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class RECORDS_jButton1_actionAdapter implements java.awt.event.ActionListener {
  RECORDS adaptee;

  RECORDS_jButton1_actionAdapter(RECORDS adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class RECORDS_jButton3_actionAdapter implements java.awt.event.ActionListener {
  RECORDS adaptee;

  RECORDS_jButton3_actionAdapter(RECORDS adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class RECORDS_jButton4_actionAdapter implements java.awt.event.ActionListener {
  RECORDS adaptee;

  RECORDS_jButton4_actionAdapter(RECORDS adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}