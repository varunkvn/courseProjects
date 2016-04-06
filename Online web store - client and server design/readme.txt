Steps to Run the program 

- import the  project into eclipse
===============================================
Start Server: 
===============================================
-Open server folder
-right click on Server.java->Run as -> Java Application

===============================================
Start Client:
===============================================
-Open client folder
-Right click on ClientMain.java-> Run as-> Java application 
- Enter localhost or other machine's IP address 

==============================================
Setup Mysql database:
==============================================

-install mysql database
-create database bookstore

- Create schema bookstore ash shown below 

CREATE DATABASE IF NOT EXISTS bookstore;
USE bookstore;

-Create a table book

CREATE TABLE `book` (
  `bookid` varchar(10) NOT NULL,
  `bookname` varchar(150) NOT NULL,
  `noofitems` int(10) NOT NULL,
  `cost` decimal(10,0) NOT NULL,
  `topic` varchar(100) NOT NULL,
) 

- insert values to the book table 
INSERT INTO `book` (`bookid`,`bookname`,`noofitems`,`cost`,`topic`) VALUES 
 ('1','How to get a good grade in CS5523',10,'10','distributed systems'),
 ('2','How to survive the graduate school',25,'15','graduate school'),
 ('3','RPCs and RMI in distributed systems',20,'15','distributed systems'),
 ('4','Why go to the graduate school',30,'14','graduate school');

Enter your username and password in server/Database.java file as shown 
conn = DriverManager.getConnection(url+"bookstore","root","Kamala@1");
Here username is "root" and password is "Kamala@1"


Happy Shopping !! 