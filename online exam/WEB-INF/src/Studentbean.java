package org.students;
public class Studentbean {
int age;
String username;
org.students.Addressbean address;
public Studentbean()
{
 System.out.println("...this is zero parameter constructor...");
}
public void setAge(int age)
{
 this.age=age;
}
public int getAge()
{
 return age;
}
public void setUsername(String username)
{
 this.username=username;
}
public String getUsername()
{
 return username;
}
public void setAddress(org.students.Addressbean address)
{
 this.address=address;
}
public org.students.Addressbean getAddress()
{
 return address;
}
}