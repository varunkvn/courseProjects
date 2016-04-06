import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	 private static void startRegistry(int portnumber)
			    throws RemoteException{
			    try {
			      Registry registry = 
			        LocateRegistry.getRegistry(portnumber);
			      registry.list( );  
			    }
			    catch (RemoteException e) { 
			      Registry registry = 
			        LocateRegistry.createRegistry(portnumber);
			    }
			  } 
	public static void main(String[] args)
			{
		
			String portNum, registryURL;

		   try{     
		      int portnumber = 1099;
		      startRegistry(portnumber);
		      Book im = new Book();
		      registryURL = "rmi://localhost:" + portnumber + "/Server";
		      Naming.rebind(registryURL, (Remote) im);
		      System.out.println("\t \t \t Serer is Started. \n  Please run ClientMain.java to start the client in another terminal");
		      Server s = new Server();
		    }
		    catch (Exception re) {
		      System.out.println(
		        "Exception in Server.main: " + re);
		    } 
		  } 
}