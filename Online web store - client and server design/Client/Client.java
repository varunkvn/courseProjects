import java.rmi.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Client implements Runnable{
	static  ArrayList books;
	private Scanner sc;
	long startTime =0;
	long stopTime =0;
    public void run() {  
    	RMI ri;

  	  	try {
  		System.out.println("\t \t \t Enter the IP address or localhost to connect .\n \t \t \t \t Eg. localhost or 162.142.12.1 ");
  		sc = new Scanner(System.in);
  		String localhost=sc.next();
  		String ping = "rmi://" + localhost + "/Server";
  		System.out.println("Here ping generated ");
  		ri = (RMI)Naming.lookup(ping);
  		System.out.println("Here ri generated "+ri);
  		books=ri.getBook();

  		
  		int choice;
  			do{
  		  	  	Scanner menu = new Scanner( System.in );
  					System.out.println("=================================================");
  					System.out.println("   Welcome to the World's smallest Bookstore    ");
  					System.out.println("=================================================");
  					System.out.println("==================Mybooks.com====================");
  					System.out.println("                                        --varun  ");
  					System.out.println("Please choose from one of the options below");
  					System.out.println("   1. Books List ");
  					System.out.println("   2. Search a Book By name ");
  					System.out.println("   3. Search by ID ");
  					System.out.println("   4. Purchase ");
  					System.out.println("   5. Exit ");
  					System.out.println("================================================");
  					System.out.println("Enter Your choice");
  					choice = menu.nextInt();

      switch (choice) {
      case 1:
    	 startTime = System.currentTimeMillis();   
      		System.out.println(books);
      	
      	stopTime = System.currentTimeMillis();
		long fetchTime = stopTime-startTime;
		System.out.println("                          .....Retrieving the data took  "+ fetchTime +" Milli seconds ");
		
        break;
    	case 2:
      		System.out.println("Please Enter a keyword to search. (Note : My search function is case sensitive)");
      		String topic=menu.next();
      		startTime = System.currentTimeMillis();
      		ArrayList sbooks=ri.getBookByName(topic);
      		stopTime = System.currentTimeMillis();
    		long searchtime = stopTime-startTime;
      		if(sbooks.size()>0)
      		{
      			for(java.lang.Object i:sbooks)
      		{	    
      				System.out.println(i.toString());
      	
      		}
      			System.out.println("                          .....Searching for the product took "+ searchtime +" Milli seconds ");
      		}
      		else
      			System.out.println("No Books Found with that topic");
      		break;
        case 3:
        	
          	System.out.println("Enter BookNo to find a book by ID");
         	int itemno=menu.nextInt();
           	ArrayList books=ri.getBookData(itemno);
           	if(books.size()>0){
           	for(java.lang.Object i:books)
          	{	    
          		System.out.println(i.toString().replaceAll(" \t", " \t"));
          	
          	}
           	}
           	else
           		System.out.println("Book not Found");
        	  
            break;
        case 4:
        	System.out.println("Enter Item No to Order Book");
    	 	
         	int itmno=menu.nextInt();
         	System.out.println("Enter No of Copies of Book");
        	int noofcopies=menu.nextInt();
        	long searchtime1 = stopTime-startTime;
  	       double bill=ri.bill(itmno,noofcopies);
  	     long searchtime2 = stopTime-startTime;
  	     long searchtime3=searchtime2-searchtime1;
  	       if(bill>0)
  	       {
  	    	   System.out.println("total bill for ur books order is "+bill);
  	    	 System.out.println(ri.reportGoodOrds(noofcopies));
  	    	 System.out.println(ri.reportFailOrds());
  	    	 System.out.println("Total time taken to order a book is"+searchtime3+"milli seconds");
  	    	   
  	       }
  	       else
  	    	   System.out.println("Book not found");
  	    	  
  	        break;

  	    	
  	        
 
      	
      default:
    	  System.out.println("You have selected to Quit !!!");
  		break;
      }
     }while(choice!=5);
 	
  	  } catch (MalformedURLException me) 
  	     { 
            System.out.println(); 
            System.out.println( "MalformedURLException"); 
            System.out.println(me); 
     } 
    catch (RemoteException re)
     { 
            System.out.println(); 
            System.out.println( "RemoteException"); 
            System.out.println(re); 
     } 
    catch (NotBoundException ne)
     { 
            System.out.println(); 
            System.out.println( "NotBoundException"); 
            System.out.println(ne); 
     } 
    catch (Exception e)
     { 
            System.out.println(); 
            System.out.println( "Exception"); 
            System.out.println("Unable to connect . Please Try Later"); 
     } 
    }	  
}