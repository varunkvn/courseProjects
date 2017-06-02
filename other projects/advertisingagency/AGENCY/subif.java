package submits;
import java.rmi.*;
public interface subif extends Remote
 {
 double paid_amount(int appno) throws RemoteException; 
 String paid_receipt()throws RemoteException;
 String project_id()throws RemoteException;
 String submit_id()throws RemoteException;
 }
