package org.students;
public class Addressbean {
String street;
String city;
String state;

public Addressbean()
{
 System.out.println("...this is zero parameter constructor...");
}
public void setStreet(String street)
{
 this.street=street;
}
public String getStreet()
{
 return street;
}
public void setCity(String city)
{
 this.city=city;
}
public String getCity()
{
 return city;
}
public void setState(String state)
{
 this.state=state;
}
public String getState()
{
 return state;
}
}