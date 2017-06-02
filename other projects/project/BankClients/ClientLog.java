

/*
 Project            :
 File Name          :

Author:
Date  :

*/



import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;




/**
* This class represents a client side
*/
public class ClientLog extends JFrame implements ActionListener, Runnable {


 // streams for communication with the server:
    static PrintStream out;         // for sending to server.
    static BufferedReader in;       // for getting data from server.
    // socket for connection with the server:
    static Socket socket = null;

   // label to show status messages:
    JLabel lblStatus = new JLabel("Connected to Server", JLabel.RIGHT);
	JTextField txtAcctNo;
	JTextField txtName;
	JPasswordField txtPasswrd;
	JButton btnLogIn;

	ClientDeposit clientDep;
	ClientMainMenu clientMain;
	ClientTransMoney clientTrans;
	ClientWithDraw clientWDraw;
	ClientViewAcc clientView;
	ClientViewLog clientVLog;
    ClientValidatePin clientVPin;
    ClientAcctOptions clientAcctOp;
    ClientPassPin clientPP;
    //ClientLoanMenu clientLnMenu;
    //ClientLoanDetails clientLnDet;
    //ClientLoanAgreement clientLnAgmt;




    StringTokenizer values ;
    StringTokenizer valLog;
    JLabel timeRunning = new JLabel("",SwingConstants.LEFT);
    JLabel lblDateRunning = new JLabel("",SwingConstants.LEFT);
   // Object[][] Data ;

     String currentTime;
     String dtString;


    // thread which cares about receiving data from server:
    Thread thServer = null;
    Thread clockThread = null;
    Thread dateThread = null;


/**
    * Default Constructor.
    */
    public ClientLog()
    {
        super ("Network Bank");

        // look & feel setup:
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName() );
        } catch (Exception e) {
            System.err.println("Couldn't use the system "
                             + "look and feel: " + e);
        }


       // processing window events:
       setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        WindowListener L= new WindowAdapter () {
            public void windowClosing(WindowEvent e) {
               //closeApplication();
            }
        };
        addWindowListener(L);

        clientDep = new ClientDeposit(this);
        clientMain = new ClientMainMenu(this);
        clientTrans = new ClientTransMoney(this);
        clientView = new ClientViewAcc(this);
        clientWDraw = new ClientWithDraw(this);
        clientVLog = new ClientViewLog(this);
        clientVPin = new ClientValidatePin(this);
        clientAcctOp = new ClientAcctOptions(this);
        clientPP = new ClientPassPin(this);
        //clientLnMenu = new ClientLoanMenu(this);
        //clientLnDet = new ClientLoanDetails(this);
       // clientLnAgmt = new ClientLoanAgreement(this);

        // prepare the layout:
        JPanel  pMain = new JPanel ();
        pMain.setLayout(new BorderLayout());

		JPanel pUpper = new JPanel();           // for labels.
        pUpper.setLayout(new GridLayout(1,2));

		JPanel pUp = new JPanel();           // for labels.
        pUp.setLayout(new GridLayout(5,1));

		JPanel  pBtn = new JPanel ();
        pBtn.setLayout(new FlowLayout());

        JPanel pLeft = new JPanel(new GridLayout(3,1, 8,8));  //
        JPanel pRight = new JPanel(new GridLayout(3,1,8,8));   //.
        JLabel lblAcctNo = new  JLabel("Account No :");
        JLabel lblName = new JLabel("Name :");
        JLabel lblPasswrd = new JLabel("Password :");

        txtAcctNo = new JTextField("",10);
		txtName =  new JTextField("",10);
		txtPasswrd = new JPasswordField("",10);
        lblAcctNo.setSize(5,4);
        lblName.setSize(5,4);
        lblPasswrd.setSize(5,4);
        txtAcctNo.setSize(5,4);
        txtName.setSize(5,4);
        txtPasswrd.setSize(5,4);


        lblDateRunning.setSize(5,4);
        timeRunning.setSize(5,4);
        lblDateRunning.updateUI();
        timeRunning.updateUI();


        txtAcctNo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtPasswrd.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pLeft.add(lblAcctNo);
        pLeft.add(lblName);
        pLeft.add(lblPasswrd);

		pRight.add(txtAcctNo);
		pRight.add(txtName);
		pRight.add(txtPasswrd);

		pUpper.add(pLeft);
		pUpper.add(pRight);
        JLabel lbl1 = new JLabel("Welcome To Network Banking",SwingConstants.CENTER);
        lbl1.setFont(new Font("",Font.BOLD,12));
        lbl1.setSize(5,4);

        pUp.add(lbl1,JLabel.CENTER);
        pUp.add(new JLabel(" ",JLabel.CENTER));
        pUp.add(lblDateRunning);
        pUp.add(timeRunning);
        pUp.add(new JLabel(" ",JLabel.CENTER));

        JLabel lblSpaces1 = new JLabel("         ");
        JLabel lblSpaces2 = new JLabel("         ");
        JLabel lblSpaces3 = new JLabel("         ");
		pMain.add(pUp,BorderLayout.NORTH);
		pMain.add(pUpper,BorderLayout.CENTER);
		pMain.add(lblSpaces1,BorderLayout.EAST);
		pMain.add(lblSpaces2,BorderLayout.WEST);
		pMain.add(lblSpaces3,BorderLayout.SOUTH);


        btnLogIn = new JButton("Login");
		pBtn.add(btnLogIn);

		pMain.add(pBtn,BorderLayout.SOUTH);
		btnLogIn.addActionListener(this);


        // show the window:
        setContentPane (pMain);
        setSize(330,250);
        setBounds(220,175,330,250);
        setResizable(false);
        setVisible(true);


        // start the thread that cares about receiving data from server:
        thServer = new Thread(this);
        clockThread = new Thread(this);
        dateThread = new Thread(this);
        thServer.start();
        clockThread.start();
        dateThread.start();
        sendToServer("Hello_Server");

    }



      /**
    * Main function, from where GameClient starts.
    */
    public static void main(String args[]) {

        // validate parameters:
        if (args.length != 2) {
            System.err.println("Usage: java ClientLog <host> <port>");
            System.exit(1);
        }

        // trying to connect to the server:
        try {
            String serverAddress = args[0];
            int serverPort = Integer.parseInt(args[1]);
            socket = new Socket(serverAddress, serverPort);
        } catch (Exception e) {
            System.err.println("Wrong arguments -> " + e);
            System.exit(1);
        }


        System.out.println(socket);

        // open in and out streams for talking with the server:
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
        }
        catch (IOException e) {
            System.err.println("Open streams -> " + e);
            System.exit(1);
        }


         // print the address of player - for verification:
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host - probably localhost with no IP!");
            // no exit, since can work on "localhost" without internet.
        }
        System.out.println("Clients local address: " + localHost);

        // create GameClient and show its window:
        new ClientLog();
    }



/**
    * Makes current thread to pause.
    *
    * @param time miliseconds to sleep.
    */
    private void pause(int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }



    public void sendToServer(String msg) {
        out.println(msg);
        // Flush the stream and check its error state:
        if (out.checkError())
            System.err.println("Cannot send -> " + msg);
        //else
            //System.out.println("SENT to SERVER: " + msg);
    }


    public void serverTalks(String msg) {

        if (thServer == null) {  // system over?
            System.out.println("CLIENT (ignored ): " + msg);
            return; // no need to process.
        }

        //System.out.println("SERVER: " + msg);

        // process the message:

        if (msg.equals("Welcome_Client")) {
          System.out.println("All connected");
         }
        else if(msg.equals("LOGSUCCESS")){
        setVisible(false);
         clientMain.setVisible(true);



         }

        else if(msg.equals("TERMINATED"))
         {

            clientMain.setVisible(false);
            clientDep.setVisible(false);
            clientWDraw.setVisible(false);
            clientView.setVisible(false);
            clientTrans.setVisible(false);

            sendToServer("FORCED_LOGGED_OUT." + txtAcctNo.getText());
            setVisible(true);
            setClear();
            JOptionPane.showMessageDialog(this,
    "This port has been TERMINATED...\n",
    "TERMINATION",
    JOptionPane.ERROR_MESSAGE
    );
            closeApplication();

          }

         else if(msg.equals("LOGFAIL")){
          JOptionPane.showMessageDialog(this,
    "Log In Failed",
    "Client Log In",
    JOptionPane.ERROR_MESSAGE
    );
    setClear();


         }
        else if(msg.equals("INCORRECT_PIN"))
          {
           JOptionPane.showMessageDialog(clientVPin,
    "PIN INCORRECT !!",
    "Client Validate PIN",
    JOptionPane.ERROR_MESSAGE
    );



          }
        else if(msg.equals("TRANSACTION_FAILED_ACCOUNT_NOT_VALID"))
         {
          JOptionPane.showMessageDialog(clientTrans,
    "Transfer failed ..Not a valid Account no..\n Contact Administrator for details.",
    "Client Trans Money",
    JOptionPane.ERROR_MESSAGE
    );
     clientTrans.txtAcctTo.setText("");


         }
        else if(msg.equals("PIN_CHANGE_SUCCESS"))
         {
          JOptionPane.showMessageDialog(clientPP,
    "PIN Changed",
    "Client Account Options>>Change PIN",
    JOptionPane.INFORMATION_MESSAGE
    );
         clientPP.setVisible(false);
         clientMain.setVisible(true);


         }
        else if(msg.equals("PIN_CHANGE_FAILED_INCORRECT_OLDPIN"))
         {
          JOptionPane.showMessageDialog(clientPP,
    "PIN Change failed ..\nIncorrect Old PIN..",
    "Client Account Options>>Change PIN",
    JOptionPane.ERROR_MESSAGE
    );



         }
        else if(msg.equals("PIN_CHANGE_FAILED_INCORRECT_NEWPIN"))
         {
          JOptionPane.showMessageDialog(clientPP,
    "PIN Change failed ..\nIncorrect New PIN..\n PIN should be a 4 digit number..",
    "Client Account Options>>Change PIN",
    JOptionPane.ERROR_MESSAGE
    );



         }
        else if(msg.equals("PASSWORD_CHANGE_SUCCESS"))
         {
          JOptionPane.showMessageDialog(clientPP,
    "Password Changed",
    "Client Account Options>>Change Password",
    JOptionPane.INFORMATION_MESSAGE
    );
         clientPP.setVisible(false);
         clientMain.setVisible(true);


         }
        else if(msg.equals("PASSWORD_CHANGE_FAILED_INCORRECT_OLDPASSWORD"))
         {
          JOptionPane.showMessageDialog(clientPP,
    "Password Change failed ..\nIncorrect Old Password..",
    "Client Account Options>>Change Password",
    JOptionPane.ERROR_MESSAGE
    );



         }
        else if(msg.equals("PASSWORD_CHANGE_FAILED_INCORRECT_NEWPASSWORD"))
         {
          JOptionPane.showMessageDialog(clientPP,
    "Password Change failed ..\nIncorrect New Password..\n New Password and Reconfirmed New password should be the same..",
    "Client Account Options>>Change Password",
    JOptionPane.ERROR_MESSAGE
    );



         }

        else if(msg.equals("FAILED_MIN_BAL_WTH"))
         {
           JOptionPane.showMessageDialog(clientWDraw,
    "Withdraw failed.\nMinimum balance to be kept at Rs 500\\-",
    "Client Withdraw Money",
    JOptionPane.ERROR_MESSAGE  );


         }
         else if(msg.equals("FAILED_MIN_BAL_TRANS"))
         {
           JOptionPane.showMessageDialog(clientTrans,
    "Transfer failed.\nMinimum balance to be kept at Rs 500\\-",
    "Client Transfer Money",
    JOptionPane.ERROR_MESSAGE  );


         }
        else if(msg.equals("ACCT_OPTIONS_ALLOWED"))
         {
          clientVPin.setVisible(false);
          clientAcctOp.setVisible(true);


         }
       /*  else if(msg.equals("AVAIL_LOANS_ALLOWED"))
         {
          clientVPin.setVisible(false);
          clientLnMenu.setVisible(true);


         } */
        else if(msg.equals("TRANSACTION_SUCCESS"))
         {
           JOptionPane.showMessageDialog(clientTrans,
    "Transfer Success",
    "Client Transfer Money",
    JOptionPane.INFORMATION_MESSAGE    );
         clientTrans.setVisible(false);
         clientMain.setVisible(true);


         }
         else if(msg.equals("TRANSACTION_FAILURE"))
         {
           JOptionPane.showMessageDialog(clientTrans,
    "Transfer Failure",
    "Client Transfer Money",
    JOptionPane.ERROR_MESSAGE    );


         }
        else if(msg.equals("DEPOSIT_NOT_ALLOWED"))
         {
            JOptionPane.showMessageDialog(clientMain,
    "Deposit only through Teller",
    "Client Deposit Money",
    JOptionPane.INFORMATION_MESSAGE  );


         }
       else if(msg.equals("DEPOSIT_SUCCESS"))
        {
         JOptionPane.showMessageDialog(clientDep,
    "Deposit Success",
    "Client Deposit Money:Teller",
    JOptionPane.INFORMATION_MESSAGE  );
    clientDep.setVisible(false);
    clientMain.setVisible(true);


        }
       else if(msg.equals("DEPOSIT_FAILED"))
        {
          JOptionPane.showMessageDialog(clientDep,
    "Deposit Failed",
    "Client Deposit Money:Teller",
    JOptionPane.ERROR_MESSAGE  );


        }
       else if(msg.equals("TRANS_NOT_ALLOWED"))
        {
         JOptionPane.showMessageDialog(clientMain,
    "Transfer of money not allowed.\nYour current balance(minimum balance) is only Rs 500\\-",
    "Client Transfer Money",
    JOptionPane.INFORMATION_MESSAGE  );


        }
        else if(msg.equals("WITHDRAW_NOT_ALLOWED"))
         {
            JOptionPane.showMessageDialog(clientMain,
    "Withdrawal of money not allowed.\nYour current balance(minimum balance) is only Rs 500\\-",
    "Client Withdraw Money",
    JOptionPane.INFORMATION_MESSAGE  );




         }
        else if(msg.equals("WITHDRAW_SUCCESS"))
         {
          JOptionPane.showMessageDialog(clientWDraw,
    "Withdraw Success...Wait for processing",
    "Client Withdraw Money",
    JOptionPane.INFORMATION_MESSAGE  );
          clientWDraw.setVisible(false);
          clientMain.setVisible(true);



         }
        else if(msg.equals("WITHDRAW_FAILURE"))
         {
          JOptionPane.showMessageDialog(clientWDraw,
    "Withdraw Failure...\nMinimum balance to be kept at Rs 500\\-",
    "Client Withdraw Money",
    JOptionPane.ERROR_MESSAGE  );
    clientWDraw.txtAmt.setText("");

         }
        else if(msg.equals("LOGGED_IN_DUPLICATE"))
         {
            JOptionPane.showMessageDialog(this,
    "User already Logged In",
    "Client Entry Level",
    JOptionPane.ERROR_MESSAGE  );
    setClear();


         }
       else if(msg.equals("ACCOUNT_NOT_VALID"))
        {
          JOptionPane.showMessageDialog(this,
    "This is not a valid Account.\nContact Administrator for further details.",
    "Client Entry Level",
    JOptionPane.ERROR_MESSAGE  );
        setClear();

        }
       else if(msg.equals("LOGGED_OUT_SUCCESS"))
        {
         setClear();
         setVisible(true);

        }
       else if(msg.equals("ACCOUNT_DOES_NOT_EXIST"))
        {
          JOptionPane.showMessageDialog(this,
    "Account does not exist.\nContact Administrator for further details.",
    "Client Entry Level",
    JOptionPane.ERROR_MESSAGE  );

         setClear();
        }


        else
         {
           String message = msg ;
           values = new StringTokenizer(message,".");
           String cmd = values.nextToken();
           if(cmd.equals("VIEWACC"))
            {
             while(values.hasMoreTokens())
              {
               clientView.lblAcctNo.setText("   " + values.nextToken());
               clientView.lblName.setText("   " + values.nextToken());
               clientView.lblBal.setText("    Rs " + values.nextToken() + "\\-");
              }
              clientMain.setVisible(false);
              clientView.setVisible(true);



            }
           else if(cmd.equals("ERROR"))
            {
             String errInfo = values.nextToken();
              if(errInfo.equals("LOGIN"))
               {
                 JOptionPane.showMessageDialog(this,
    "This is not a valid Account.\n Account No should be digits..",
    "Client Entry Level",
    JOptionPane.ERROR_MESSAGE  );
                setClear();
               }
              else if(errInfo.equals("TRANSACTION"))
               {
                 JOptionPane.showMessageDialog(clientTrans,
    "This is not a valid Account.\n Account No and Money should be entered as  digits..",
    "Client Transfer Money Level",
    JOptionPane.ERROR_MESSAGE  );
               }
              else if(errInfo.equals("WITHDRAWAL"))
               {
                 JOptionPane.showMessageDialog(clientWDraw,
    "This is not a valid Entry...\n  Money should be entered as  digits..",
    "Client Withdraw Money Level",
    JOptionPane.ERROR_MESSAGE  );
               }
              else if(errInfo.equals("DEPOSIT"))
               {
                 JOptionPane.showMessageDialog(clientDep,
    "This is not a valid Entry...\n Money should be entered as  digits..",
    "Client Deposit Money Level",
    JOptionPane.ERROR_MESSAGE  );
               }

            }
           /*else if(cmd.equals("GET_LOAN_DET"))
            {
              String AcctNo = values.nextToken();
              String Bal = values.nextToken();
              String lnStatus = values.nextToken();
              clientLnDet.setActionCmd(Bal,AcctNo,lnStatus);
              clientLnMenu.setVisible(false);
              clientLnDet.Initialize();
              clientLnDet.setVisible(true);


            }*/
           else if(cmd.equals("ACCOUNT_LOGS_TO_CLIENT"))
            {
             String AcctNo = values.nextToken();
             String max = values.nextToken();

              clientVLog.count = 0;
              String log ;

              clientVLog.setActionCmd(max,AcctNo);

              while(values.hasMoreTokens())
               {
                 try
                 {
                 log = values.nextToken();
                 valLog = new StringTokenizer(log,"$");
                 clientVLog.Data[clientVLog.count][0] = valLog.nextToken();
                 clientVLog.Data[clientVLog.count][1] = valLog.nextToken();
                 clientVLog.Data[clientVLog.count][2] = valLog.nextToken();
                 clientVLog.Data[clientVLog.count][3] = valLog.nextToken();


                 clientVLog.count =  clientVLog.count + 1;
                 }

                 catch(java.util.NoSuchElementException nle)
                  {
                   System.out.println("Error :" + nle);

                   }


            }







              clientMain.setVisible(false);
              clientVLog.Initialize();
              clientVLog.setVisible(true);




            }
           else if(cmd.equals("TRANS"))
            {
              while(values.hasMoreTokens())
              {
               clientTrans.lblAcctNoFr.setText("   " + values.nextToken());
               clientTrans.lblBal.setText("  Rs " + values.nextToken() + "\\-");

              }
              clientTrans.txtAcctTo.setText("");
              clientTrans.txtAmt.setText("");
              clientVPin.setVisible(false);
              clientTrans.setVisible(true);


            }
           else if(cmd.equals("DEPOSIT_ALLOWED"))
            {
             clientDep.lblAcctNo.setText("   " + values.nextToken());
             clientDep.lblName.setText("   " + values.nextToken());
             clientDep.lblBal.setText("   Rs " + values.nextToken() + "\\-");
             clientDep.txtAmt.setText("");
             clientMain.setVisible(false);
             clientDep.setVisible(true);
            }
           else if(cmd.equals("WITHDRAW_ALLOWED"))
           {
            clientWDraw.lblAcctNo.setText("  " + values.nextToken());
            clientWDraw.lblName.setText("  " + values.nextToken());
            clientWDraw.lblBal.setText("  Rs " + values.nextToken() + "\\-");
            clientWDraw.txtAmt.setText("");
            clientVPin.setVisible(false);
            clientWDraw.setVisible(true);


             }



            }

        }





/**
    * The run method of that is used by a thread.
    */
    public void run() {

        Thread thisThread = Thread.currentThread();

        // thread which cares about receiving data from server:
        while (thServer == thisThread) {
            String msg;
            try {
            if ((msg = in.readLine()) != null)

		    serverTalks(msg);  // trigger "event".

            }
            catch (IOException ioe) {
              JOptionPane.showMessageDialog(this,
                   ioe + "\nNot regular termination!",
                   "Cannot read from server!",
                   JOptionPane.ERROR_MESSAGE);
              closeApplication(); // force to close.
            }
        }
       while (clockThread == thisThread) {
         iterateTime();
         try {
               Thread.sleep(1000);

         } catch (InterruptedException e) {
             System.err.println("clock thread -> " + e);
                                   // the VM doesn't want us to sleep anymore,
                                   // so get back to work
     }
    }
      while (dateThread == thisThread) {
         iterateDate();
         try {
               Thread.sleep(1000);

         } catch (InterruptedException e) {
             System.err.println("clock thread -> " + e);
                                   // the VM doesn't want us to sleep anymore,
                                   // so get back to work
     }
    }

    } // end of run().

    private void iterateTime() {
     // get the time and convert it to a date
     Calendar cal = Calendar.getInstance();
     java.util.Date date = cal.getTime();
     //System.out.println("Display time : " + date.toString());

          //char dts[] =   date.toString().toCharArray() ;
       // Dattee ;
      //System.out.println("Display time : " + dts);
      //Dattee.valueOf(dts,12,9);
      //System.out.println("Display time : " + Dattee);
   // format it and display it
      DateFormat dateFormatter = DateFormat.getTimeInstance();
      SimpleDateFormat dateFormatterH = new SimpleDateFormat("H:mm:ss");
      currentTime = dateFormatterH.format(date);
     timeRunning.setText("  Time : " + dateFormatter.format(date));
 }


 private void iterateDate() {


     // get the time and convert it to a date

   // format it and display it
       Calendar cal = Calendar.getInstance();
       java.util.Date date = cal.getTime();
        SimpleDateFormat dt = new SimpleDateFormat ("dd MMM yyyy '(' EE ')' ");
        //java.util.Date currTime = new java.util.Date();
        dtString = dt.format(date);
        lblDateRunning.setText("  Date : " + dtString);
      }

/**
    * Processes clicks on buttons
    *
    * @param e the ActionEvent object.
    */
    public void actionPerformed(ActionEvent e){
      JButton src = (JButton)e.getSource();

      if (src == btnLogIn)
       {

          String pWord = new String(txtPasswrd.getPassword());
          try{


                 if((txtAcctNo.getText().trim().equals(""))||(txtName.getText().trim().equals(""))||(pWord.trim().equals("")))
                  {

                  JOptionPane.showMessageDialog(this,
                 "Fields incomplete.\nPlease enter Name,Accountno and Password again.",
                 "Client Entry Level",
                 JOptionPane.ERROR_MESSAGE  );
                 setClear();

               }
             else
              {

                sendToServer("LOGIN." + txtAcctNo.getText().trim() + "." + txtName.getText().trim() + "." + pWord);

              }


           }
           catch(java.lang.NullPointerException  nle)
            {


              JOptionPane.showMessageDialog(this,
                 "Fields incomplete.\nPlease enter Name,Accountno and Password again.",
                 "Client Entry Level",
                 JOptionPane.ERROR_MESSAGE  );
             setClear();

             System.out.println("Error :" + nle);



            }


       }
      else if(src==clientMain.btnViewAcc)
       {

         sendToServer("VIEWACC." +  txtAcctNo.getText() );
       }
      else if(src==clientMain.btnTransfer)
       {

         clientMain.setVisible(false);
         clientVPin.setClear();
         clientVPin.setVisible(true);
         clientVPin.info = "TRANS";





       }
      /*else if(src==clientMain.btnAvLoan)
       {
         clientMain.setVisible(false);
         clientVPin.setClear();
         clientVPin.setVisible(true);
         clientVPin.info = "AVAIL_LOAN";



       }*/
      else if (src==clientVPin.logIn)
       {
       try{
            String val = new String(clientVPin.txtPin.getPassword());
            int pin = Integer.parseInt(val);
         if((pin>999)&&(pin<10000))
          {

             if(clientVPin.info.equals("TRANS"))
                {
                 sendToServer("TRANS." + pin + "." + txtAcctNo.getText().trim());
                }
             else if(clientVPin.info.equals("WITHDRAW"))
                {
                 sendToServer("WITHDRAW." + pin + "." + txtAcctNo.getText().trim() + "." + txtName.getText() );
                }
             else if(clientVPin.info.equals("ACCTOP"))
              {
               sendToServer("ACCT_OPTIONS." + pin + "." + txtAcctNo.getText().trim() );

              }
             else if(clientVPin.info.equals("AVAIL_LOAN"))
              {
                sendToServer("AVAIL_LOAN." + pin + "." + txtAcctNo.getText().trim() );

              }

          }
         else
          {
            JOptionPane.showMessageDialog(clientVPin,
                 "Personal Identification Number(PIN) is a 4 digit number.\nPlease enter PIN again..",
                 "Client Validate Pin",
                 JOptionPane.ERROR_MESSAGE  );
            clientVPin.txtPin.setText("");
            clientVPin.txtPin.setFocusable(true);

          }
          }
             catch(java.lang.NumberFormatException nle)
              {
                JOptionPane.showMessageDialog(clientVPin,
                 "Personal Identification Number(PIN) is a 4 digit number.\nPlease enter PIN again..",
                 "Client Validate Pin",
                 JOptionPane.ERROR_MESSAGE  );
                 clientVPin.txtPin.setText("");
            clientVPin.txtPin.setFocusable(true);
              }

       }
      else if(src==clientVPin.cancelLogIn)
       {
        clientVPin.setVisible(false);
        clientMain.setVisible(true);

       }
      else if(src==clientMain.btnDeposit)
       {
        String pWord = new String(txtPasswrd.getPassword());
        sendToServer("DEP." + txtAcctNo.getText() + "." + txtName.getText() + "." + pWord);

       }
      else if(src==clientMain.btnViewLogs)
       {
        sendToServer("VIEW_LOGS." + txtAcctNo.getText().trim());



       }
      else if(src==clientMain.btnExit)
       {
        clientMain.setVisible(false);
        sendToServer("LOGGED_OUT." + txtAcctNo.getText().trim() );


       }
      else if(src==clientTrans.btnTrans)
       {
        if((clientTrans.txtAcctTo.getText().trim().equals(""))||(clientTrans.txtAmt.getText().trim().equals("")))
        {
          JOptionPane.showMessageDialog(clientTrans,
                 "Fields are incomplete ..\nPlease enter Account No and Amount...",
                 "Client Transfer Money",
                 JOptionPane.ERROR_MESSAGE  );
                 clientTrans.txtAcctTo.setFocusable(true);
        }
        else
        {
        sendToServer("TRANSACTION." + txtAcctNo.getText() + "." + clientTrans.txtAcctTo.getText() + "." + clientTrans.txtAmt.getText());
        }
       }

      else if(src==clientTrans.btnCancel)
       {
        clientTrans.setVisible(false);
        clientMain.setVisible(true);
       }
      else if(src==clientDep.btnDep)
       {
        if(clientDep.txtAmt.getText().trim().equals(""))
        {
         JOptionPane.showMessageDialog(clientDep,
                 "Fields are incomplete ..\nPlease enter  Amount...",
                 "Client Deposit Money",
                 JOptionPane.ERROR_MESSAGE  );

        }
        else
        {
        sendToServer("DEPOSIT_IN_PROGRESS." + clientDep.lblAcctNo.getText().trim() + "." + clientDep.txtAmt.getText().trim());
        }
       }
      else if(src==clientMain.btnWithdraw )
       {
         clientMain.setVisible(false);
         clientVPin.setClear();
         clientVPin.setVisible(true);
         clientVPin.info = "WITHDRAW";


       }
      /*else if(src==clientLnDet.btnOK)
       {
       clientLnDet.setVisible(false);
       clientLnAgmt.Initialize();
       clientLnAgmt.setVisible(true);


       }
      else if(src==clientLnDet.btnCancel)
       {
        clientLnDet.setVisible(false);
        clientLnMenu.setVisible(true);


       }*/
      else if(src==clientWDraw.btnWthDr)
       {
        if(clientWDraw.txtAmt.getText().trim().equals(""))
         {
         JOptionPane.showMessageDialog(clientWDraw,
                 "Fields are incomplete ..\nPlease enter Amount...",
                 "Client Withdraw Money",
                 JOptionPane.ERROR_MESSAGE  );
                 clientWDraw.txtAmt.setFocusable(true);
         }
        else
        {
        sendToServer("WITHDRAW_IN_PROGRESS." + txtAcctNo.getText() + "." + clientWDraw.txtAmt.getText().trim());
        }
       }
      else if(src==clientWDraw.btnCancel)
       {
        clientWDraw.setVisible(false);
        clientMain.setVisible(true);

       }
       /*
      else if(src==clientLnMenu.btnGetLoan)
       {
        sendToServer("GET_LOAN_DETAILS." + txtAcctNo.getText().trim());

       } */
      else if(src==clientMain.btnAcctOp)
       {
          clientMain.setVisible(false);
          clientVPin.setClear();
          clientVPin.setVisible(true);
          clientVPin.info = "ACCTOP";


       }
      else if(src==clientAcctOp.btnChPass)
       {
        clientAcctOp.setVisible(false);
        clientPP.info = "PASSWORD";
        clientPP.lblhead.setText("CHANGE PASSWORD");
        clientPP.lblName.setText(txtName.getText().trim());
        clientPP.lblAcctNo.setText(txtAcctNo.getText().trim());
        clientPP.lblVal1.setText("Old Password :");
        clientPP.lblVal2.setText("New Password :");
        clientPP.lblVal3.setText("Reconfirm New Password :");
        clientPP.txtVal1.setText("");
        clientPP.txtVal2.setText("");
        clientPP.txtVal3.setText("");

        clientPP.setVisible(true);
       }
      else if(src==clientAcctOp.btnChPin)
       {
        clientAcctOp.setVisible(false);
        clientPP.info = "PIN";
        clientPP.lblhead.setText("CHANGE PIN");
        clientPP.lblName.setText(txtName.getText().trim());
        clientPP.lblAcctNo.setText(txtAcctNo.getText().trim());
        clientPP.lblVal1.setText("Old PIN :");
        clientPP.lblVal2.setText("New PIN :");
        clientPP.lblVal3.setText("Reconfirm New PIN :");
        clientPP.txtVal1.setText("");
        clientPP.txtVal2.setText("");
        clientPP.txtVal3.setText("");

        clientPP.setVisible(true);
       }
      else if(src==clientPP.btnOk)
       {
        if(clientPP.info.equals("PASSWORD"))
         {
            String val1 = new String(clientPP.txtVal1.getPassword());
            String val2 = new String(clientPP.txtVal2.getPassword());
            String val3 = new String(clientPP.txtVal3.getPassword());
          if((val1.trim().equals(""))||(val2.trim().equals(""))||(val3.trim().equals("")))
           {
              JOptionPane.showMessageDialog(clientPP,
                 "Fields are incomplete ..\nPlease enter Again...",
                 "Account Options>>Change Password",
                 JOptionPane.ERROR_MESSAGE  );
           }
          else
           {
            sendToServer("PASSWORD_CHANGE." + txtAcctNo.getText().trim() + "." + val1.trim() + "." + val2.trim() + "." + val3.trim());
           }
         }
        else if (clientPP.info.equals("PIN"))
         {
            String val1 = new String(clientPP.txtVal1.getPassword());
            String val2 = new String(clientPP.txtVal2.getPassword());
            String val3 = new String(clientPP.txtVal3.getPassword());
           if((val1.trim().equals(""))||(val2.trim().equals(""))||(val3.trim().equals("")))
           {
              JOptionPane.showMessageDialog(clientPP,
                 "Fields are incomplete ..\nPlease enter Again...",
                 "Account Options>>Change PIN",
                 JOptionPane.ERROR_MESSAGE  );
           }
          else
           {
           sendToServer("PIN_CHANGE." + txtAcctNo.getText().trim() + "." + val1.trim() + "." + val2.trim() + "." + val3.trim());

         }
        }
        }
        else if(src==clientPP.btnCancel)
         {

          clientPP.setVisible(false);
          clientMain.setVisible(true);

         }
        else if(src==clientAcctOp.btnCancel)
         {
          clientAcctOp.setVisible(false);
          clientMain.setVisible(true);

         }

       }






  /*  public void pause(int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    } */
    public void setClear()
     {
      txtAcctNo.setText("");
      txtName.setText("");
      txtPasswrd.setText("");
     }




	  /**
    *
    */
    private void closeApplication() {
        // inform the server:
        try {
        thServer    = null;  // stop the thread.
        dateThread  = null;
        clockThread = null;
              // close server streams:
              out.close();      // close stream that sends data to server.
              in.close();       // close stream that gets data from server.
              // close socket connected to server:
              if (socket != null)
                 socket.close();
        } catch (IOException e) {}

        // close everything:
        System.exit(0);
    }


 }   // end of class.










