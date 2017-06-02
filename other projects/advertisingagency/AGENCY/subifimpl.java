package submits;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.sql.*;
public class  subifimpl extends UnicastRemoteObject
implements subif
 {
String str,str1,str2,str3;
Connection con;
Statement stmt,stmt1,stmt2,stmt3;
ResultSet rs,rs1,rs2,rs3; 
double retval;
double balval;
 public  subifimpl()throws RemoteException
{
  super();
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:aruna","scott","tiger");
}catch(Exception e){}
   }
  public double paid_amount(int appno)throws RemoteException
{

try
{
stmt=con.createStatement();
rs=stmt.executeQuery("select charges from netcharges where typeid=("+"select typeid from subtypes1  where applno="+appno+")");
rs.next();
retval=rs.getDouble(1);
stmt.close();
}catch(Exception e){}
balval=retval-10000;
  return balval;  
   }
public String paid_receipt()throws RemoteException
{
try
{
stmt1=con.createStatement();
rs1=stmt1.executeQuery("select 'R'||lpad(to_char(nvl(to_number(max(substr(paidrecpt,3))),0)+1),3,'0') from submit where paidrecpt like 'R%'");
rs1.next();
str=rs1.getString(1);
stmt1.close();
}catch(Exception e){}
return str;
}
public String project_id()throws RemoteException
{
try
{
stmt2=con.createStatement();
rs2=stmt2.executeQuery("select 'P'||lpad(to_char(nvl(to_number(max(substr(project_id,3))),0)+1),3,'0') from submit where project_id like 'P%'");
rs2.next();
str1=rs2.getString(1);
stmt2.close();
}catch(Exception e){}
return str1;

}
public String submit_id()throws RemoteException
{
try
{
stmt3=con.createStatement();
rs3=stmt3.executeQuery("select 'S'||lpad(to_char(nvl(to_number(max(substr(submissionid,3))),0)+1),3,'0') from submit where submissionid like 'S%'");
rs3.next();
str2=rs3.getString(1);
stmt3.close();
}catch(Exception e){}
return str2;

}

  public static void main(String args[]){
   
    String name="kalyan";
    try{
     subifimpl instance=new  subifimpl();
     Naming.rebind(name,instance);
     System.out.println("server is ready");
      }

    catch(Exception e){
          System.out.println(e.toString());
      }
     }
    }
