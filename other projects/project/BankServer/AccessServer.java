/*
 Project            :
 File Name          :

Author:
Date  :
*/

import java.io.*;
import java.net.*;
import java.util.*;

/**
*
*
*
*/
public class AccessServer extends Thread {

// sockets for client connections:
    Socket clientConnection=null;
// streams for communication:
    BufferedReader inClient=null;
    PrintStream outClient=null;
 // thread for getting data from clients:
    Thread thClient = null;
    StringTokenizer values;
    String strName = new String(" ");
    String strAcctNo = new String(" ");
    String strAction = new String(" ");
    Server server; // pointer to owner server class (used to remove system).


 /**
    * Constructor.
    *

    * @param ourServer pointer to owner Server class.
    */
    public AccessServer(Server temp) {

        server = temp;
    }



 /**
    * Connects client to this system.
    *
    * @param clientConnection socket of client to be connected.
    */
    public void connectClient(Socket clientConnection) {
        this.clientConnection = clientConnection;

        // open streams for communication:
        try {
            inClient =
                new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            outClient = new PrintStream(clientConnection.getOutputStream());
        } catch (IOException e) {
            System.err.println("connectClient -> " + e);
        }

        // thread to receive messages from client:
        thClient  = new Thread (this);
        thClient.start();





        System.out.println("Starting access...");

        // inform both sides that connection is successful:

       // sendToClient("ALL_CONNECTED");

    }


/**
    * The run method of that is used by a thread.
    */
    public void run() {

        Thread thisThread = Thread.currentThread();


        // thread to receive messages from client:
        while (thClient == thisThread) {
          String inputLine=null;
          try {
               inputLine = inClient.readLine();
			//System.out.println("hello");
          } catch (IOException ioe) {
              if (thClient != null) { // system not over yet?
                  System.err.println("inClient.readLine() -> " + ioe);

              }
          }
          if (inputLine != null)
            clientTalks(inputLine);      // trigger "event".
        }

  }//end of run


/**
    * This method is triggered by thread "thClient" when
    * there is a message from client.
    * Processes the message and makes an appropriate reaction to it.
    *
    * @param msg what client has to say.
    */
    public void clientTalks(String msg) {

        if (thClient == null) {  // system over?
            System.out.println("CLIENT (ignored ): " + msg);
            return; // no need to process.
        }

        //System.out.println("CLIENT: " + msg);

        // process the message:

        if (msg.equals("Hello_Server")) {
          sendToClient("Welcome_Client");
         }
        else if(msg.equals(" ")){



        }

        else
         {
           String message = msg ;
           values = new StringTokenizer(message,".");
           String cmd = values.nextToken();


           if(cmd.equals("LOGIN"))
            {

             String AcctNo1 = new String();
             String AcctNo2 = new String();
             String Name1= new String();
             String Name2= new String();
             String pWord1 = new String();
             String pWord2= new String();

             while(values.hasMoreTokens())
              {
                 AcctNo1 = values.nextToken();
                 Name1 = values.nextToken();
                 pWord1 = values.nextToken();
              }
             try {

                server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Name,Password,Validity FROM ClientInfo WHERE AccountNo = " + AcctNo1 );
                server.aDbase.uprs.next();
                long acctno = server.aDbase.uprs.getLong(1);
                 Name2 = server.aDbase.uprs.getString(2);
                 pWord2 = server.aDbase.uprs.getString(3);
                 boolean val = server.aDbase.uprs.getBoolean(4);

                 AcctNo2 = Long.toString(acctno);
                 server.aDbase.uprs.close();
                 server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT LogInStatus FROM ClientAccStatus WHERE AccountNo = " + AcctNo1 );
                 server.aDbase.uprs.next();
                 boolean logValidity = server.aDbase.uprs.getBoolean(1);
                if((val)&&(!logValidity))
                {
                if((AcctNo1.equals(AcctNo2))&&(pWord1.equals(pWord2))&&(Name1.equals(Name2)))
                 {
                  strName = Name1;
                  strAcctNo = AcctNo1;
                  server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET LogInStatus = True  WHERE AccountNo = " + strAcctNo);


                  server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                  server.aDbase.uprs.last();
                  long id = server.aDbase.uprs.getLong(1) + 1;
                  System.out.println(id);


                  server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo1 + ",'LOGGED IN',' SUCCESSFULLY LOGGED IN ','"  + clientConnection.getInetAddress() + "')" );
                  System.out.println("Heeloooo::: " + id);
                  strAction = "Log in Success";
                  sendToClient("LOGSUCCESS");

                 }
                else if((AcctNo1.equals(AcctNo2))&&(pWord1.equals("Admin"))&&(Name1.equals("Administrator")))

                {
                 strName = "Administrator : " + Name2 ;
                 strAcctNo = AcctNo1;
                 server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                  server.aDbase.uprs.last();
                  long id = server.aDbase.uprs.getLong(1) + 1;






                 server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo1 + ",'LOGGED IN :ADMINISTRATOR',' SUCCESSFULLY LOGGED IN ','"  + clientConnection.getInetAddress() + "')" );
                 sendToClient("LOGSUCCESS");// can decrease the scope...if needed for teller


                }
                else
                 {
                 strName = Name1;
                 strAcctNo = AcctNo1;

                   strAction = "Log in Fail";
                   server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                  server.aDbase.uprs.last();
                  long id = server.aDbase.uprs.getLong(1) + 1;

                   server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo1 + ",'LOG IN',' LOG IN FAILED ','"  + clientConnection.getInetAddress() + "')" );
                   sendToClient("LOGFAIL");



                  }
               }
               else if(!val)
                {
                strName = Name1;
                strAcctNo = AcctNo1;

                 strAction = "Account Not Valid";
                 sendToClient("ACCOUNT_NOT_VALID");
                 server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                  server.aDbase.uprs.last();
                  long id = server.aDbase.uprs.getLong(1) + 1;

                 server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo1 + ",'LOG IN',' ACCOUNT NOT VALID ','"  + clientConnection.getInetAddress() + "')" );
                }
               else if(logValidity)
                {
                 strName = Name1;
                 strAcctNo = AcctNo1;


                 strAction = "Trying to Log In as Duplicate";
                 sendToClient("LOGGED_IN_DUPLICATE");
                 server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                  server.aDbase.uprs.last();
                  long id = server.aDbase.uprs.getLong(1) + 1;

                 server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo1 + ",'LOG IN',' LOG IN DUPLICATE FAILED ','"  + clientConnection.getInetAddress() + "')" );

                 }
                        }
                        catch(java.sql.SQLException sqle)
                         {
                         System.out.println("Error " + sqle);
                         strName = Name1;
                         strAcctNo = AcctNo1;
                         strAction ="Account does not exist...given an error message";
                           sendToClient("ACCOUNT_DOES_NOT_EXIST");

                         }
                         catch(java.lang.NumberFormatException nle)
                          {
                           sendToClient("ERROR.LOGIN");
                           System.out.println("Error" +  nle);
                          }



                    }
                  /*
                  else if(cmd.equals("GET_LOAN_DETAILS"))
                   {
                    String AcctNo = values.nextToken();
                    try {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Balance,LoanStatus FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();
                        long bal = server.aDbase.uprs.getLong(2);
                        boolean lnStatus = server.aDbase.uprs.getBoolean(3);
                        server.aDbase.uprs.close();
                        strAction = "Request for  Loans ..Checking Get Loan Details...";
                        if((lnStatus)||(bal<2500))
                         sendToClient("GET_LOAN_DET." + AcctNo + "." + bal + "." + "Not Eligible");
                        else
                          sendToClient("GET_LOAN_DET." + AcctNo + "." + bal + "." + "Eligible");
                       }
                        catch(java.sql.SQLException sqle)
                      {
                       strAction = "SQL Error occured ";

                        System.out.println("Error :" + sqle);


                     }



                   }*/
                  else if(cmd.equals("VIEW_LOGS"))
                   {
                     String AcctNo = values.nextToken();
                     int i = 0;
                     try{
                        //server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT Date,Time,AccountNo,Action,Remarks,LogInIP FROM ClientLogs ORDER BY Date DESC,Time Desc WHERE AccountNo = " + AcctNo );
                       //

                       //
                       // Object[][] Data = new Object[iRow+1][6];
                          String dtLog = new String("");
                       //   server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT Date,Time,AccountNo,Action,Remarks FROM ClientLogs  WHERE AccountNo = " + AcctNo + " ORDER BY Date DESC,Time Desc");
                        //  server.aDbase.uprs.last( );
                        //  int iRow = server.aDbase.uprs.getRow( );
                          server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT Date,Time,AccountNo,Action,Remarks FROM ClientLogs  WHERE AccountNo = " + AcctNo + " ORDER BY ID DESC");

                        while ( server.aDbase.uprs.next ( ))  {
                                    //Data[i][0] = server.aDbase.uprs.getString(1) ;
                                    //Data[i][1] = server.aDbase.uprs.getString(2);
                                    //Data[i][2] = Long.toString(server.aDbase.uprs.getLong(3)) ;
                                    //Data[i][3]= server.aDbase.uprs.getString(4) ;
                                   // Data[i][4]= server.aDbase.uprs.getString(5);
                                    //Data[i][5]=  server.aDbase.uprs.getString(6);

                                      dtLog = dtLog + server.aDbase.uprs.getString(1) + "$" + server.aDbase.uprs.getString(2)  + "$" + server.aDbase.uprs.getString(4) + "$"  + server.aDbase.uprs.getString(5) + "." ;
                                      i++;
                                      }
                                      strAction = "Viewing Account Logs.. ";
                                    sendToClient("ACCOUNT_LOGS_TO_CLIENT." + AcctNo + "." + Integer.toString(i)  + "." + dtLog );





                                 System.out.println(" i = " + i);

                                 }
                                 catch(java.sql.SQLException sqle)
                                  {
                                   System.out.println("Error : "  + sqle);


                                  }







                   }
                  else if(cmd.equals("FORCED_LOGGED_OUT"))
                   {

                    try{
                     String AcctNo = values.nextToken();

                    if(AcctNo.trim().equals(""))
                     {

                     }
                    else
                    {
                    server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET LogInStatus = False  WHERE AccountNo = " + AcctNo);
                    server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                     server.aDbase.uprs.last();
                     long id = server.aDbase.uprs.getLong(1) + 1;

                    server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'LOGGED OUT',' FORCED LOGGED OUT BY ADMINISTRATOR  ','"  + clientConnection.getInetAddress() + "')" );

                     strAction = "Forced Logged Out Success";

                    }
                    }
                    catch(java.sql.SQLException sqle)
                     {
                     strAction = "Forced Logged Out Failed";

                      System.out.println("Error :" + sqle);


                     }
                     catch(java.util.NoSuchElementException nle)
                     {
                     strAction = "Terminated port..\nNo clients at present.. ";

                      System.out.println("Error :" + nle);


                     }


                   }
                  /*else if(cmd.equals("AVAIL_LOAN"))
                   {
                    String Pin = values.nextToken();
                    String AcctNo = values.nextToken();
                    try {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Pin FROM ClientInfo WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();
                        long pinAct = server.aDbase.uprs.getLong(2);
                        server.aDbase.uprs.close();
                        strAction = "Request for Avail Loans ..\nChecking PIN..";
                      if(Long.parseLong(Pin)==pinAct)
                        {
                         strAction = "PIN Validation Success..\nLoans Allowed..";
                         sendToClient("AVAIL_LOANS_ALLOWED");

                        }
                      else
                       {
                        strAction = "PIN Validation failed..\nLoans Not Allowed..";
                        server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES('" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'VALIDATE PIN','INCORRECT PIN ,REQUEST FOR LOANS FAILED','"  + clientConnection.getInetAddress() + "')" );
                        sendToClient("INCORRECT_PIN");

                       }
                       }
                       catch(java.sql.SQLException sqle)
                        {
                         System.out.println("Error :" + sqle);
                        }
                        catch(java.lang.NumberFormatException nle)
                          {
                           sendToClient("ERROR.AVAIL_LOANS");
                           System.out.println("Error" +  nle);
                          }

                   }*/



                  else if(cmd.equals("ACCT_OPTIONS"))
                   {
                    String Pin = values.nextToken();
                    String AcctNo = values.nextToken();
                    try {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Pin FROM ClientInfo WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();
                        long pinAct = server.aDbase.uprs.getLong(2);
                        server.aDbase.uprs.close();
                        strAction = "Request for Account Options ..\nChecking PIN..";
                      if(Long.parseLong(Pin)==pinAct)
                        {
                         strAction = "PIN Validation Success..\nAccount Options Allowed..";
                         sendToClient("ACCT_OPTIONS_ALLOWED");

                        }
                      else
                       {
                        strAction = "PIN Validation failed..\nAccount Options Not Allowed..";
                        server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                        server.aDbase.uprs.last();
                        long id = server.aDbase.uprs.getLong(1) + 1;

                        server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'VALIDATE PIN','INCORRECT PIN ,REQUEST FOR ACCOUNT OPTIONS FAILED','"  + clientConnection.getInetAddress() + "')" );
                        sendToClient("INCORRECT_PIN");

                       }
                       }
                       catch(java.sql.SQLException sqle)
                        {
                         System.out.println("Error :" + sqle);
                        }
                        catch(java.lang.NumberFormatException nle)
                          {
                           sendToClient("ERROR.ACCT_OPTION");
                           System.out.println("Error" +  nle);
                          }

                   }
                  else if(cmd.equals("LOGGED_OUT"))
                   {
                    String AcctNo = values.nextToken();
                    try{

                    server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET LogInStatus = False  WHERE AccountNo = " + AcctNo);
                    server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                    server.aDbase.uprs.last();
                    long id = server.aDbase.uprs.getLong(1) + 1;

                    server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'LOGGED OUT',' SUCCESSFULLY LOGGED OUT ','"  + clientConnection.getInetAddress() + "')" );

                     strAction = "Logged Out Success";
                    sendToClient("LOGGED_OUT_SUCCESS");
                    }
                    catch(java.sql.SQLException sqle)
                     {
                     strAction = "Logged Out Failed";
                      sendToClient("LOGGED_OUT_FAILED");
                      System.out.println("Error :" + sqle);


                     }
                   }
                  else if(cmd.equals("VIEWACC"))
                   {
                     String AcctNo = values.nextToken();
                    try {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Name,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();
                        String Name = server.aDbase.uprs.getString(2);
                        long bal = server.aDbase.uprs.getLong(3);
                        String balance = Long.toString(bal);

                        server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                        server.aDbase.uprs.last();
                        long id = server.aDbase.uprs.getLong(1) + 1;

                        server.aDbase.stmt2.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'VIEW ACCOUNT',' BALANCE : Rs " + balance + "\\-','" + clientConnection.getInetAddress() + "')" );
                        strAction = "Viewing  Account";
                        sendToClient("VIEWACC." + AcctNo + "." +  Name + "." + balance);
                        }
                        catch(java.sql.SQLException sqle)
                        {
                         System.out.println("Error :" + sqle);


                        }

                   }
                  else if(cmd.equals("PASSWORD_CHANGE"))
                   {

                     String AcctNo = values.nextToken();
                     String oldPw  = values.nextToken();
                     String newPw1 = values.nextToken();
                     String newPw2 = values.nextToken();
                    if(newPw1.equals(newPw2))
                     {
                      try
                       {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Password FROM ClientInfo WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();
                        String passwrd = server.aDbase.uprs.getString(2);
                        if(passwrd.equals(oldPw))
                         {
                          strAction = "Password Changed Successfully";
                          server.aDbase.stmt.executeUpdate("UPDATE ClientInfo SET Password  = '" + newPw1  + "'  WHERE AccountNo = " + AcctNo);
                          server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                          server.aDbase.uprs.last();
                          long id = server.aDbase.uprs.getLong(1) + 1;

                          server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'CHANGE PASSWORD',' SUCCESSFULLY CHANGED PASSWORD ','"  + clientConnection.getInetAddress() + "')" );
                          sendToClient("PASSWORD_CHANGE_SUCCESS");


                         }
                        else
                         {
                          strAction = "Password Change Failed..\nIncorrect old password";
                          server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                          server.aDbase.uprs.last();
                          long id = server.aDbase.uprs.getLong(1) + 1;

                          server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'CHANGE PASSWORD',' FAILED:INCORRECT OLD PASSWORD  ','"  + clientConnection.getInetAddress() + "')" );
                          sendToClient("PASSWORD_CHANGE_FAILED_INCORRECT_OLDPASSWORD");

                         }


                       }catch(java.sql.SQLException sqle)
                        {

                         System.out.println("Error :" + sqle);
                        }


                     }
                    else
                    {

                     sendToClient("PASSWORD_CHANGE_FAILED_INCORRECT_NEWPASSWORD");

                    }





                   }
                  else if(cmd.equals("PIN_CHANGE"))
                   {

                     String AcctNo = values.nextToken();
                     String oldPin  = values.nextToken();
                     String newPin1 = values.nextToken();
                     String newPin2 = values.nextToken();
                     try
                       {
                    if(Long.parseLong(newPin1)==Long.parseLong(newPin2))
                     {

                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Pin FROM ClientInfo WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();
                        long pin = server.aDbase.uprs.getLong(2);
                        if(pin == Long.parseLong(oldPin))
                         {
                          strAction = "PIN Changed Successfully";
                          server.aDbase.stmt.executeUpdate("UPDATE ClientInfo SET Pin  = " + newPin1 + "  WHERE AccountNo = " + AcctNo);
                          server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                          server.aDbase.uprs.last();
                          long id = server.aDbase.uprs.getLong(1) + 1;

                          server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'CHANGE PIN',' SUCCESSFULLY CHANGED PIN ','"  + clientConnection.getInetAddress() + "')" );
                          sendToClient("PIN_CHANGE_SUCCESS");


                         }
                        else
                         {
                          strAction = "PIN Change Failed..\nIncorrect old PIN";
                          server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                          server.aDbase.uprs.last();
                          long id = server.aDbase.uprs.getLong(1) + 1;

                          server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'CHANGE PIN',' FAILED:INCORRECT OLD PIN  ','"  + clientConnection.getInetAddress() + "')" );
                          sendToClient("PIN_CHANGE_FAILED_INCORRECT_OLDPIN");

                         }


                       }



                    else
                    {

                     sendToClient("PIN_CHANGE_FAILED_INCORRECT_NEWPIN");

                    }
                    }
                     catch(java.sql.SQLException sqle)
                        {

                         System.out.println("Error :" + sqle);
                        }
                        catch(java.lang.NumberFormatException nle)
                          {
                           sendToClient("ERROR.PIN_CHANGE");
                           System.out.println("Error" +  nle);
                          }



                   }
                  else if(cmd.equals("TRANS"))
                   {
                     String Pin = values.nextToken();
                     String AcctNo = values.nextToken();
                      try {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Pin FROM ClientInfo WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();
                        long pinAct = server.aDbase.uprs.getLong(2);
                        server.aDbase.uprs.close();
                        strAction = "Request for Transaction ..\nChecking PIN..";
                      if(Long.parseLong(Pin)==pinAct)
                        {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();

                        long bal = server.aDbase.uprs.getLong(2);

                        server.aDbase.uprs.close();
                        String balance = Long.toString(bal);

                        strAction = "PIN Validation Success..\nRequest for Transaction ..";
                        if(bal>500)
                         {
                          strAction = strAction + ": Allowed";
                          sendToClient("TRANS." + AcctNo +  "." + balance);
                         }
                        else
                         {
                           strAction = strAction + ": Not Allowed (Min Bal Rs 500\\-";
                           sendToClient("TRANS_NOT_ALLOWED");

                         }
                         }
                         else
                         {
                         server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                         server.aDbase.uprs.last();
                         long id = server.aDbase.uprs.getLong(1) + 1;

                          server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'VALIDATE PIN','INCORRECT PIN ,REQUEST FOR TRANSACTION FAILED','"  + clientConnection.getInetAddress() + "')" );
                          strAction = "Request for Transaction Failed..\nIncorrect PIN";
                          sendToClient("INCORRECT_PIN");
                         }
                        }
                        catch(java.sql.SQLException sqle)
                        {
                         System.out.println("Error :" + sqle);
                        }
                        catch(java.lang.NumberFormatException nle)
                          {
                           sendToClient("ERROR.TRANS");
                           System.out.println("Error" +  nle);
                          }


                   }
                   else if (cmd.equals("TRANSACTION"))
                    {
                      String AcctNo = values.nextToken();
                      try {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();

                        long bal1 = server.aDbase.uprs.getLong(2);
                        String clntAcctTo = values.nextToken();

                        String clntAmt = values.nextToken();
                        if(clntAcctTo.equals(AcctNo))
                         {
                          strAction = "Transaction Failed";
                          sendToClient("TRANSACTION_FAILURE");
                         }
                        else
                         {
                        long Amt = Long.parseLong(clntAmt);
                        server.aDbase.uprs.close();
                        if((bal1-Amt)>=500)
                         {
                           server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Validity FROM ClientInfo WHERE AccountNo = " + clntAcctTo);
                           server.aDbase.uprs.next();
                           if(server.aDbase.uprs.getBoolean(2))
                           {
                           server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + clntAcctTo);
                           server.aDbase.uprs.next();
                           long bal2 = server.aDbase.uprs.getLong(2);
                           bal2 = bal2 + Amt;
                           bal1 = bal1 - Amt;

                           System.out.println(bal1 + "." + bal2 + "." + AcctNo + "." + clntAcctTo);
                           server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET Balance = " + bal1 + " WHERE AccountNo = " + AcctNo);
                           server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET Balance = " + bal2 + " WHERE AccountNo = " + clntAcctTo);

                           // server.aDbase.uprs.close();
                           server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                           server.aDbase.uprs.last();
                           long id = server.aDbase.uprs.getLong(1) + 1;

                            server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'TRANSFER','TRANSFERRED Rs" + clntAmt + "\\- TO AccountNo: " + clntAcctTo + "','"  + clientConnection.getInetAddress() + "')" );
                            server.pause(2000);

                           server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                           server.aDbase.uprs.last();
                           id = server.aDbase.uprs.getLong(1) + 1;

                           server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + clntAcctTo + ",'DEPOSIT','TRANSFERRED Rs" + clntAmt + "\\- FROM AccountNo: " + AcctNo + "','"  + clientConnection.getInetAddress() + "')" );
                                                      strAction = "Transaction Success";
                           sendToClient("TRANSACTION_SUCCESS");
                            //
                          }
                         else
                         {
                            sendToClient("TRANSACTION_FAILED_ACCOUNT_NOT_VALID");
                          }
                           //  server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET Balance =
                         }
                        else
                          {
                           strAction = "Transaction Failed";
                           sendToClient("FAILED_MIN_BAL_TRANS");
                          }

                         }





                        }
                        catch(java.sql.SQLException sqle)
                        {
                         //if(sqle.equals("invalid cursor state"))
                          {
                          strAction = "Transaction Failed";
                           sendToClient("TRANSACTION_FAILURE");
                          }
                         System.out.println("Error :" + sqle);
                        }

                        catch(java.lang.NumberFormatException nle)
                          {
                           sendToClient("ERROR.TRANSACTION");
                           System.out.println("Error" +  nle);
                          }


                    }
                   else if (cmd.equals("DEP"))
                   {
                     String AcctNo = values.nextToken();
                     String Name = values.nextToken();
                     String pWord = values.nextToken();
                     if((Name.equals("Administrator"))&&(pWord.equals("Admin")))
                      {
                       try {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Balance,Name FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();
                        long bal = server.aDbase.uprs.getLong(2);
                        String balance = Long.toString(bal);
                        String clntName = server.aDbase.uprs.getString(3);
                       strName = "Administrator : " + clntName;
                       strAcctNo = AcctNo;
                       strAction = "Deposit in progress for " + clntName;
                       server.pause(2000);
                       sendToClient("DEPOSIT_ALLOWED." + AcctNo + "." + clntName + "." + balance);
                      }
                      catch(java.sql.SQLException sqle)
                       {
                        System.out.println("Error :" + sqle);

                       }
                      }
                     else
                      {
                       strAction = "Deposit not allowed";
                       sendToClient("DEPOSIT_NOT_ALLOWED");
                      }

                     }
                   else if(cmd.equals("WITHDRAW"))
                    {
                     String pin = values.nextToken();
                     String AcctNo = values.nextToken();
                     String Name = values.nextToken();
                     try {

                          server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Pin FROM ClientInfo WHERE AccountNo = " + AcctNo);
                          server.aDbase.uprs.next();
                          long pinAct = server.aDbase.uprs.getLong(2);
                          server.aDbase.uprs.close();
                          strAction = "Request for withdrawal...\n Checking PIN";
                          if(Long.parseLong(pin)==pinAct)
                          {
                          server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Balance,Name FROM ClientAccStatus WHERE AccountNo =" + AcctNo);
                          server.aDbase.uprs.next();
                          long bal = server.aDbase.uprs.getLong(2);



                          if(bal>500)
                           {
                           strAction = "PIN Validation Success..\nWithdrawal Allowed .";
                           String balance = Long.toString(bal);
                           sendToClient("WITHDRAW_ALLOWED." + AcctNo + "." + server.aDbase.uprs.getString(3) + "." + balance );
                           }
                          else
                          {
                           strAction = strAction + ": Not Allowed (Bal Rs 500\\-)";
                           sendToClient("WITHDRAW_NOT_ALLOWED");
                           }
                          }
                         else
                          {
                          server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                          server.aDbase.uprs.last();
                          long id = server.aDbase.uprs.getLong(1) + 1;

                           server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'VALIDATE PIN','INCORRECT PIN ,REQUEST FOR WITHDRAWAL FAILED','"  + clientConnection.getInetAddress() + "')" );
                          strAction = "Request for withdrawal Failed..\nIncorrect PIN";
                          sendToClient("INCORRECT_PIN");


                          }
                     } catch (java.sql.SQLException sqle) {


                        sendToClient("WITHDRAW_NOT_ALLOWED");
                        System.out.println("Error :" + sqle);



                     }


                    }
                   else if(cmd.equals("WITHDRAW_IN_PROGRESS"))
                    {
                      String AcctNo = values.nextToken();
                      String clntAmt = values.nextToken();
                      try {

                          server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
                          server.aDbase.uprs.next();
                          long bal = server.aDbase.uprs.getLong(2);
                          long Amt = Long.parseLong(clntAmt);
                          if(((bal-Amt)>=500))
                            {

                            server.aDbase.uprs.close();
                            bal = bal- Amt;
                            server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET Balance = " + bal + " WHERE AccountNo = " + AcctNo);





                            server.pause(1000);

                           server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                           server.aDbase.uprs.last();
                           long id = server.aDbase.uprs.getLong(1) + 1;

                           server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'WITHDRAW',' Rs" + Amt + "\\-  CURRENT BALANCE: Rs" + bal + "\\- ','"  + clientConnection.getInetAddress() + "')" );
                                                      strAction = "Rs " + Amt + "\\- Withdrawn....Withdraw Success ";
                           sendToClient("WITHDRAW_SUCCESS");
                            //


                           //  server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET Balance =
                         }
                        else
                          {
                           strAction = "Withdraw Failed";
                           sendToClient("FAILED_MIN_BAL_WTH");
                          }
                        }

                       catch (java.sql.SQLException  sqle) {

                           strAction = "Withdraw Failed";
                           sendToClient("WITHDRAW_FAILURE");

                      }
                      catch(java.lang.NumberFormatException nle)
                          {
                           sendToClient("ERROR.WITHDRAWAL");
                           System.out.println("Error" +  nle);
                          }


                    }

                   else if(cmd.equals("DEPOSIT_IN_PROGRESS"))
                    {
                      String AcctNo = values.nextToken();
                      String dep = values.nextToken();


                      try {
                        server.aDbase.uprs = server.aDbase.stmt.executeQuery("SELECT AccountNo,Balance FROM ClientAccStatus WHERE AccountNo = " + AcctNo);
                        server.aDbase.uprs.next();
                        long bal = server.aDbase.uprs.getLong(2);
                        long depAmt = Long.parseLong(dep);
                        bal = bal + depAmt;
                        String balance = Long.toString(bal);
                        server.aDbase.stmt.executeUpdate("UPDATE ClientAccStatus SET Balance = " + balance + " WHERE AccountNo = " + AcctNo);
                        server.pause(1000);
                        server.aDbase.uprs = server.aDbase.tmpStmt.executeQuery("SELECT ID FROM ClientLogs");
                        server.aDbase.uprs.last();
                        long id = server.aDbase.uprs.getLong(1) + 1;

                        server.aDbase.stmt.executeUpdate("INSERT INTO ClientLogs VALUES(" + id + ",'" + server.dtString + "','" + server.currentTime + "'," + AcctNo + ",'DEPOSIT',' Rs" + depAmt + "\\- CURRENT BALANCE: Rs" + balance + "\\-','" + clientConnection.getInetAddress() + "')" );                         strAction = "Deposit Success";
                        sendToClient("DEPOSIT_SUCCESS");

                        }
                       catch(java.sql.SQLException sqle)
                        {

                          System.out.println("Error :" + sqle);
                          strAction = "Deposit Failed";
                          sendToClient("DEPOSIT_FAILED");
                        }

                       catch(java.lang.NumberFormatException nle)
                          {
                           sendToClient("ERROR.DEPOSIT");
                           System.out.println("Error" +  nle);
                          }



                    }
                    }


                    }

/**
    * Sends message to client
    *
    * @param msg the message to be sent.
    */
    public void sendToClient(String msg) {
        outClient.println(msg);
        // Flush the stream and check its error state:
        if (outClient.checkError())
            System.err.println("Cannot send -> " + msg);
        //else
            //System.out.println("SENT to CLIENT: " + msg);
    }



   /**
    * Returns the information about the system.
    */
    public String getInfo() {


        return  "Details of client terminal:\n" +
                "Client Address: " + clientConnection.getInetAddress() + "\n" +
                "Client Port   : " + clientConnection.getPort() + "\n\n" +

                "Logged in User details:\n" +
                "Current User      : " +     strName                 + "\n" +
                "Current Account no: " +     strAcctNo                +"\n" +
                "Current Action    : " +     strAction            ;


          }


/**
    * Prepares this system for termination.
    * Closes all sockets, streams, and stops threads.
    */
    public void closeEverything() {
          try {
              // stop all system threads:

              thClient = null;

              // close sockets and streams:
              //  streams closed after sockets, otherwise causes problems.

              clientConnection.close();
              inClient.close();
              outClient.close();


           } catch (Exception e) {
              System.err.println("On closeEverything() -> " + e);
          }
    }

}