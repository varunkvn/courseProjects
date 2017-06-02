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

public class SearchRecord extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();

  public SearchRecord() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
    jLabel1.setText("RECORD SEARCH");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 3, 11));
    jLabel2.setText("PLEASE CHOOSE ONE: ");
    jButton1.setText("TITLE OF BOOK");
    jButton1.addActionListener(new SearchRecord_jButton1_actionAdapter(this));
    jButton2.setPreferredSize(new Dimension(127, 25));
    jButton2.setText("AUTHOR OF BOOK");
    jButton2.addActionListener(new SearchRecord_jButton2_actionAdapter(this));
    jButton3.setText("ISBN NUMBER");
    jButton3.addActionListener(new SearchRecord_jButton3_actionAdapter(this));
    jButton4.setRequestFocusEnabled(true);
    jButton4.setText("PUBLISHER");
    jButton4.addActionListener(new SearchRecord_jButton4_actionAdapter(this));
    this.getContentPane().setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
    this.getContentPane().add(jButton1,  new XYConstraints(17, 83, 353, -1));
    this.getContentPane().add(jButton2,  new XYConstraints(19, 118, 350, -1));
    this.getContentPane().add(jButton3,  new XYConstraints(18, 152, 349, -1));
    this.getContentPane().add(jButton4,  new XYConstraints(18, 189, 351, -1));
    this.getContentPane().add(jLabel1, new XYConstraints(7, 9, -1, -1));
    this.getContentPane().add(jLabel2, new XYConstraints(7, 40, -1, -1));
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

  void jButton4_actionPerformed(ActionEvent e) {
  SearchPublisher newWin=new SearchPublisher();
  newWin.setSize(400, 300);
  newWin.setVisible(true);

  }
}

class SearchRecord_jButton1_actionAdapter implements java.awt.event.ActionListener {
  SearchRecord adaptee;

  SearchRecord_jButton1_actionAdapter(SearchRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class SearchRecord_jButton2_actionAdapter implements java.awt.event.ActionListener {
  SearchRecord adaptee;

  SearchRecord_jButton2_actionAdapter(SearchRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class SearchRecord_jButton3_actionAdapter implements java.awt.event.ActionListener {
  SearchRecord adaptee;

  SearchRecord_jButton3_actionAdapter(SearchRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class SearchRecord_jButton4_actionAdapter implements java.awt.event.ActionListener {
  SearchRecord adaptee;

  SearchRecord_jButton4_actionAdapter(SearchRecord adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}