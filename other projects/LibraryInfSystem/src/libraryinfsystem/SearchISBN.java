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

public class SearchISBN extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton jButton1 = new JButton();
  JTextField jTextField1 = new JTextField();

  public SearchISBN() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
    jLabel1.setText("ISBN SEARCH");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel2.setIconTextGap(4);
    jLabel2.setText("ISBN");
    jButton1.setFont(new java.awt.Font("Dialog", 1, 11));
    jButton1.setSelected(false);
    jButton1.setText("SEARCH");
    jButton1.addActionListener(new SearchISBN_jButton1_actionAdapter(this));
    jTextField1.setText("");
    jTextField1.addActionListener(new SearchISBN_jTextField1_actionAdapter(this));
    this.getContentPane().setBackground(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
    this.getContentPane().add(jTextField1,      new XYConstraints(49, 103, 323, -1));
    this.getContentPane().add(jLabel1,  new XYConstraints(10, 37, -1, -1));
    this.getContentPane().add(jLabel2, new XYConstraints(16, 105, -1, -1));
    this.getContentPane().add(jButton1,   new XYConstraints(77, 160, 252, -1));
  }

  void jButton1_actionPerformed(ActionEvent e) {
    System.out.println(jTextField1.getText().trim());
  }

  void jTextField1_actionPerformed(ActionEvent e) {

  }
}

class SearchISBN_jButton1_actionAdapter implements java.awt.event.ActionListener {
  SearchISBN adaptee;

  SearchISBN_jButton1_actionAdapter(SearchISBN adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class SearchISBN_jTextField1_actionAdapter implements java.awt.event.ActionListener {
  SearchISBN adaptee;

  SearchISBN_jTextField1_actionAdapter(SearchISBN adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField1_actionPerformed(e);
  }
}