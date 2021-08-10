package com.slam.book.dao;
import java.sql.*;
import java.util.*;
public class DAOConnection 
{
public static Connection getConnection() throws Exception
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/slamdb","root","admin");
return connection;
}
}