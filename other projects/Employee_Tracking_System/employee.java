

/*
 *Project:employee.java
 *Date:April 04,2007.
 *Purpose:To All Java Developers
 **/
 
 package frm.employee;
 
 
 import javax.swing.*;
 import java.awt.*;
 import javax.swing.border.*;
 import java.awt.event.*;
 import java.sql.*;
 
  
  public class employee extends JFrame implements ActionListener
  {
  	 JTextField txtID,txtFirstName,txtLastName,txtAddress;
  	 JButton cmdAdd,cmdSave,cmdSearch,cmdDelete,cmdEdit,cmdClose;
  	 JComboBox cboGender;
  	 
  	 String url="jdbc:odbc:employee";
  	 String driver="sun.jdbc.odbc.JdbcOdbcDriver";
  	 Connection connection;
  	 Statement statement;
  	 ResultSet rs;
  	 
  	 JLabel ImageLabel;
     String imagename = "me_new.jpg";
     ImageIcon ii = new ImageIcon(imagename);
   
  	 
  	   public employee()
  	   {
  	    	 setTitle("Employee Tracking System");
  	    	 setBounds(140,150,520,300);
  	    	 setVisible(true);
  	    	 init();
  	   }
  	    
  	    void init()
  	    {
  	    	 Container c=this.getContentPane();
  	    	 this.setLayout(null);
  	         
  	         JLabel mainTitle=new JLabel("Employee Tracking System");
  	         c.add(mainTitle).setBounds(150,3,260,24);
  	         mainTitle.setFont(new Font("Verdana",Font.BOLD,14));
  	         
  	         ImageLabel=new JLabel(" ", ii ,JLabel.CENTER);//---default image
      		 c.add(ImageLabel).setBounds(340,30,150,130);
        	 ImageLabel.setFont(new Font("Verdana",Font.PLAIN,13));
       		 ImageLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED) );
      
  	         
  	         JLabel t1=new JLabel("EmpID");
  	         c.add(t1).setBounds(10,50,100,24);   
  	          
  	         JLabel t2=new JLabel("FirstName");
  	         c.add(t2).setBounds(10,80,100,24);   
  	          
  	         JLabel t3=new JLabel("LastName");
  	         c.add(t3).setBounds(10,110,100,24);   
  	         
  	         JLabel t4=new JLabel("Address");
  	         c.add(t4).setBounds(10,140,100,24);   
  	         
  	          
  	         txtID=new JTextField(10);
  	         c.add(txtID).setBounds(100,50,140,24);
  	         txtID.addActionListener(this);
  	            	            
  	         txtFirstName=new JTextField(10);
  	         c.add(txtFirstName).setBounds(100,80,170,24);
  	            	            
  	         txtLastName=new JTextField(10);
  	         c.add(txtLastName).setBounds(100,110,170,24);
  	            	            
             txtAddress=new JTextField(10);
  	         c.add(txtAddress).setBounds(100,140,240,24);
  	     
         
             cmdAdd=new JButton("Add");
             c.add(cmdAdd).setBounds(10,240,80,24);
             cmdAdd.addActionListener(this);
             
             cmdSave=new JButton("Save");
             c.add(cmdSave).setBounds(90,240,80,24);
             cmdSave.addActionListener(this);
             
             cmdSearch=new JButton("Search");
             c.add(cmdSearch).setBounds(170,240,80,24);
             cmdSearch.addActionListener(this);
             
             cmdDelete=new JButton("Delete");
             c.add(cmdDelete).setBounds(250,240,80,24);
             cmdDelete.addActionListener(this);
             
             cmdClose=new JButton("Edit");
             c.add(cmdClose).setBounds(330,240,80,24);
             cmdClose.addActionListener(this);
             
     
             cmdEdit=new JButton("Close");
             c.add(cmdEdit).setBounds(400,240,80,24);
             cmdEdit.addActionListener(this);
                    	            
  	    }
        //---Save  	    
  	    public void onSave()
  	    { 
  	       try
  	            {
  	          	 Class.forName(driver);
  	          	 connection=DriverManager.getConnection(url);
  	          	 statement=connection.createStatement();
  	          	 String query="INSERT INTO employee(EmployeeID,FirstName,LastName,Address)VALUES('"+txtID.getText()+"','"+txtFirstName.getText()+"','"+txtLastName.getText()+"','"+txtAddress.getText()+"')";
  	          	 statement.executeUpdate(query);
  	          	 }
  	          	 catch(ClassNotFoundException c)
  	          	 {
  	          	 	 System.err.println(c);
  	          	 }
  	          	 catch(SQLException sql)
  	          	 {
  	          	 	 System.err.println(sql);
  	          	 }
  	   }
  	    //--Search
  	    
  	    public void onSearch()
  	    { 
  	       try
  	            {
  	          	 Class.forName(driver);
  	          	 connection=DriverManager.getConnection(url);
  	          	 statement=connection.createStatement();
  	          	 String query="SELECT EmployeeID,FirstName,LastName,Address,Photo FROM employee WHERE EmployeeID='"+txtID.getText()+"'";
  	          	 rs=statement.executeQuery(query);
  	          	 
  	          	 rs.next();
  	          	 
  	          	 txtID.setText(rs.getString(1));
  	          	 txtFirstName.setText(rs.getString(2));
  	          	 txtLastName.setText(rs.getString(3));
  	          	 txtAddress.setText(rs.getString(4));
  	          	 
  	           	 String image=rs.getString(5);
  	          	 //ImageLabel.setText(image);
  	          	 
  	          	 ImageLabel.setIcon(new ImageIcon(image));      
    
  	          	 }
  	          	 catch(ClassNotFoundException c)
  	          	 {
  	          	 	 System.err.println(c);
  	          	 }
  	          	 catch(SQLException sql)
  	          	 {
  	          	 	 //System.err.println(sql);
  	          	 JOptionPane.showMessageDialog(null,"Record Not Found","No Record found",JOptionPane.WARNING_MESSAGE);
  	          	 }
  	   }
  	    
  	    
  	    
  	    public void actionPerformed(ActionEvent e)
  	    {
  	    	 Object source=e.getSource();
  	              if(source==cmdSave)
  	              {
  	              	onSave();
  	              }
  	              if(source==txtID)
  	              {
  	              	onSearch();
  	              }  	  
  	    	 
  	    }
  	    
  }//End of Class
 
  