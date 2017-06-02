

/****
* File Name		:
* Author		:
* Professor		:
* Project		:
* Description	:
*/

/* Standard Java Packages */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;



class AdminEditAcc extends JFrame implements ActionListener
{

	/* Instance Variables */


    JPanel	mainPanel = new JPanel();
	JPanel  centerPanel = new JPanel();
    JPanel  leftPanel = new JPanel();
	JPanel  rightPanel = new JPanel();
	JPanel  bottomPanel = new JPanel();
    JPanel  upperPanel = new JPanel();
    JButton btnEdit ;
    JButton btnCancel;
	JLabel lblLeft ;
	JLabel lblRight ;



    JLabel lblInfo = new JLabel("Enter Account No  :");

    JTextField txtAcctNo;
    Server server;

    public AdminEditAcc(Server temp)//AdminMainMenu temp )//Server temp

	    {
	   	 /* Instantiate an object of this class to use as ActionListener of the GUI*/
        //adminMain = temp ;
        server = temp;
	   	Initialize();

	    }

	    /**
	    * Name	    	:		Initialize
	    * @param		:		none
	    * @return		:		void
	    * Description	:		develops the GUI
	    */
	    public void Initialize()
	    {

	           //frame = new JFrame();
	           // look & feel setup:
	           try {
	               UIManager.setLookAndFeel(
	                   UIManager.getSystemLookAndFeelClassName() );
	           } catch (Exception e) {
	               System.err.println("Couldn't use the system "
	                                + "look and feel: " + e);
	           }





	   /* The default value is: HIDE_ON_CLOSE,

	   */
	           //frame.
	    	setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

	       // processing window events:
	           WindowListener L= new WindowAdapter () {
	               public void windowClosing(WindowEvent e) {
	                  server.btnAdministrator.setEnabled(true);

	                   //closeApplication();
	               }
	           };
	          // frame.
	               addWindowListener(L);




      mainPanel = new JPanel(new BorderLayout(10,10));
      centerPanel = new JPanel(new GridLayout(3,2,8,8));
      upperPanel = new JPanel(new GridLayout(3,1,2,2));
      bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));



      txtAcctNo = new JTextField("",15);
	  btnEdit = new JButton(" Edit ");

	  btnCancel = new JButton("Cancel");
	  /*btnEdit.setSize(10,4);
	  btnCancel.setSize(10,4);

	  txtAcctNo.setSize(15,4);*/
	 // lblInfo.setSize(15,4);
	 btnEdit.addActionListener(server);
	 btnCancel.addActionListener(server);
	  lblLeft = new JLabel("             ");
	  lblRight =new JLabel("             ");
      txtAcctNo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      lblInfo.setSize(5,4);
      txtAcctNo.setSize(5,4);
      centerPanel.add(lblInfo);
	  centerPanel.add(txtAcctNo );
	  centerPanel.add(new JLabel(" "));
 	  centerPanel.add(new JLabel(" "));

       JLabel lbl1 = new JLabel("Edit Account",SwingConstants.CENTER);
        lbl1.setFont(new Font("",Font.BOLD,12));
        lbl1.setSize(5,4);

	  upperPanel.add(lbl1);
	  upperPanel.add(new JLabel(" ",SwingConstants.CENTER));


      mainPanel.add(centerPanel,BorderLayout.CENTER);
	 // mainPanel.add(lblLeft,BorderLayout.EAST);
	//  mainPanel.add(lblRight,BorderLayout.WEST);
	  mainPanel.add(lblLeft,BorderLayout.EAST);
	  mainPanel.add(lblRight,BorderLayout.WEST);
      mainPanel.add(upperPanel,BorderLayout.NORTH);
	  bottomPanel.add(btnEdit);

	  bottomPanel.add(new JLabel("   "));  bottomPanel.add(btnCancel);
	  mainPanel.add(bottomPanel,BorderLayout.SOUTH);


	  setContentPane (mainPanel);

         // it doesn't work with our JTabbedPane !!! ---> pack();
            //frame.
           setSize(350,225);
		   	  	     // frame.
            setBounds(150,80,350,225);

         // show the window:
            //frame.
            setVisible(false);


	  }


    /**
	* Name	    	:		actionPeformed
	* @param		:		ActionEvent
	* @return		:		void
	* Description	:
	*/
	public void actionPerformed  (ActionEvent e)
	{
	 	}


     public void setVVVisible()
      {
       //frame.
      setVisible(true);

      }
      public void setClear()
       {
        txtAcctNo.setText(" ");
       }

     public void setInVVVisible()
      {
       //frame.
        setVisible(false);

      }

 /*  public static void main(String[] args)
  {
        new AdminEditAcc();
}
   */


}// end of class...





