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

public class SearchTitle extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton1 = new JButton();

  public SearchTitle() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
    jLabel1.setText("TITLE SEARCH");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel2.setText("TITLE OF BOOK");
    jTextField1.addActionListener(new SearchTitle_jTextField1_actionAdapter(this));
    jButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton1.setRequestFocusEnabled(true);
    jButton1.setToolTipText("");
    jButton1.setText("SEARCH ");
    jButton1.addActionListener(new SearchTitle_jButton1_actionAdapter(this));
    jTextField1.setPreferredSize(new Dimension(6, 21));
    jTextField1.setText("");
    this.getContentPane().setBackground(UIManager.getColor("Tree.selectionBackground"));
    this.getContentPane().add(jButton1,   new XYConstraints(108, 141, 263, -1));
    this.getContentPane().add(jLabel2, new XYConstraints(7, 80, -1, -1));
    this.getContentPane().add(jTextField1, new XYConstraints(108, 79, 165, -1));
    this.getContentPane().add(jLabel1, new XYConstraints(6, 32, -1, -1));
  }

  void jTextField1_actionPerformed(ActionEvent e) {

  }

  void jButton1_actionPerformed(ActionEvent e) {
    System.out.println(jTextField1.getText().trim());
  }
}

class SearchTitle_jTextField1_actionAdapter implements java.awt.event.ActionListener {
  SearchTitle adaptee;

  SearchTitle_jTextField1_actionAdapter(SearchTitle adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField1_actionPerformed(e);
  }
}

class SearchTitle_jButton1_actionAdapter implements java.awt.event.ActionListener {
  SearchTitle adaptee;

  SearchTitle_jButton1_actionAdapter(SearchTitle adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}