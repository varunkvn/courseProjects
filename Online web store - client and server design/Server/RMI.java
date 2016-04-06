import java.io.IOException;
import java.rmi.*;
import java.util.ArrayList;
public interface RMI extends Remote
{
  
  public ArrayList<?> getBook()throws IOException, ClassNotFoundException,RemoteException;
  public ArrayList<?> getBookByName(String topic)throws IOException, ClassNotFoundException,RemoteException;
  public ArrayList<?> getBookData(int itemno)throws IOException, ClassNotFoundException,RemoteException;
  public double bill(int itmno,int noofcopies)throws IOException, ClassNotFoundException,RemoteException;
  public String reportGoodOrds(int noofcopies)throws IOException, ClassNotFoundException,RemoteException;
  public String reportFailOrds()throws IOException, ClassNotFoundException,RemoteException;
  
  
}