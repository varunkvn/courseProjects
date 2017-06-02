import java.util.*;
class dates420
{
   String months[]={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
   int mno[]={1,2,3,4,5,6,7,8,9,10,11,12};
   int m,n,p,q,y1,y2,x,z,v,t,a1,a2,b1,b2,srin;
   static int r;
   String dt1,dt2,d1,d2,d3,dd1,dd2,dd3;
   public String date5(String date1,String date2,String time1,String time2)
   {
      dt1=date1;
      dt2=date2;
      a1=dt1.indexOf("-");
      a2=dt1.lastIndexOf("-");
      d1=dt1.substring(0,a1);
      d2=dt1.substring(a1+1,a2);
      d3=dt1.substring(a2+1,dt1.length());

      b1=dt2.indexOf("-");
      b2=dt2.lastIndexOf("-");
      dd1=dt2.substring(0,b1);
      dd2=dt2.substring(b1+1,b2);
      dd3=dt2.substring(b2+1,dt2.length());

      for(int i=0;i<=months.length;i++)
      {
           if(months[i].equals(d2))
           {
                m=mno[i];
                break ;
           }
      }

      for(int j=0;j<months.length;j++)
      {
           if(months[j].equals(dd2))
           {
                n=mno[j];
                break ;
          }
      }

      p=Integer.parseInt(d1);
      q=Integer.parseInt(dd1);
      y1=Integer.parseInt(d3);
      y2=Integer.parseInt(dd3);

      if( m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12)
      r=31;
      else if(m==4 || m==6 || m==9 || m==11)
      {
          r=30;
          
      }
      
      if(p<q)
      {
            System.out.println("Srinivas "+q);
            if(m==2)
            {
              x=p+30;
              t=x-q;
              if(n==1 || n==3 || n==5 || n==7 ||n==8 || n==10 ||n==12)
              t=t+1;
            }
           else
           {
                x=p+r;
                t=x-q;
                if(m==8 && n==7 || m==1 && n==12)
                System.out.println("T:"+t);
                else if(n==2)
                t=t-3;
                else if(n==1 || n==3 || n==5 || n==7 ||n==8 || n==10 ||n==12)
                t=t+1;
                else if(m==1 || m==3 || m==5 || m==7 ||m==8 || m==10 ||m==12)
                t=t-1;
           }
      }
     else if(p==q && m==n)
      {
           /*Calendar c=Calendar.getInstance();
           int h=c.get(Calendar.HOUR_OF_DAY);*/
           srin=time2.indexOf("h");
           time2=time2.substring(0,srin);
           System.out.println("Time:"+time1);
           System.out.println("Time1:"+time2);
           int an=Integer.parseInt(time1);
           int an1=Integer.parseInt(time2);
           int a1=an1-an;
           if(a1<0)
           {
               t=a1*(-1);
           }
           else
           t=a1;
      }
      else if(p==q)
      {
           t=m-n;
          if(n==2)
          {
               if(y2%4==0 && y2%100!=0 || y2%400==0)
                    t=29;
              else
                   t=28;

          }
          else if(n==1 || n==3 || n==5 || n==7 ||n==8 || n==10 || n==12)
          t=31;
          else
          t=30;
      }

      
      else
      t=p-q;
     String rani=String.valueOf(t);
return rani;
   }
}
/*   public void displaydate()
   {
     System.out.println("T :"+t);
     System.out.println("x :"+x);
     System.out.println("z :"+z);
     System.out.println("v :"+v);
     System.out.println("R :"+r);
   }        
}

class date1
{
     public static void main(String args[])
     {
          dates420 d1=new dates420();
          d1.date5("28-aug-2001","28-aug-2001","13","16h");
          d1.displaydate();
     }
} */

