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

public class Search4User extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();

  public Search4User() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
    jLabel1.setText("USER SEARCH");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel2.setAlignmentY((float) 0.5);
    jLabel2.setIconTextGap(4);
    jLabel2.setText("PLEASE CHOOSE ONE: ");
    jButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton1.setText("TITLE");
    jButton1.addActionListener(new Search4User_jButton1_actionAdapter(this));
    jButton2.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton2.setText("AUTHOR");
    jButton2.addActionListener(new Search4User_jButton2_actionAdapter(this));
    jButton3.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton3.setText("ISBN");
    jButton3.addActionListener(new Search4User_jButton3_actionAdapter(this));
    this.getContentPane().setBackground(UIManager.getColor("activeCaption"));
    this.getContentPane().add(jLabel1,  new XYConstraints(7, 11, -1, -1));
    this.getContentPane().add(jLabel2,  new XYConstraints(14, 52, -1, -1));
    this.getContentPane().add(jButton1, new XYConstraints(36, 86, 273, -1));
    this.getContentPane().add(jButton2, new XYConstraints(37, 126, 271, -1));
    this.getContentPane().add(jButton3, new XYConstraints(37, 165, 270, -1));
  }

  void jButton1_actionPerformed(ActionEvent e) {
    SearchTitle newWin=new SearchTitle();
  newWin.setSize(400, 300);
  newWin.setVisible(true);

  }

  void jButton2_actionPerformed(ActionEvent e) {
    AuthorSearch newWin=new AuthorSearch();
   newWin.setSize(400, 300);
   newWin.setVisible(true);

  }

  void jButton3_actionPerformed(ActionEvent e) {
    SearchISBN newWin=new SearchISBN();
  newWin.setSize(400, 300);
  newWin.setVisible(true);

  }
}

class Search4User_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Search4User adaptee;

  Search4User_jButton1_actionAdapter(Search4User adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class Search4User_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Search4User adaptee;

  Search4User_jButton2_actionAdapter(Search4User adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class Search4User_jButton3_actionAdapter implements java.awt.event.ActionListener {
  Search4User adaptee;

  Search4User_jButton3_actionAdapter(Search4User adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}