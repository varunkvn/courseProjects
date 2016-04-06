public class ClientMain {  
     public static void main(String[] args) 
     {  
    	 Client request = new Client();  
    	 Thread t=new Thread(request);
    	 t.start();
     }	  
 }  