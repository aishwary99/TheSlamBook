package com.slam.book.dao;
import com.google.gson.*;
public class eg1
{
public static void main(String gg[])
{
JsonArray jsonArray=null;
try
{
MemberDAO memberDAO=new MemberDAO();
String jsonString=memberDAO.getSlamData(32,33);
System.out.println(jsonString);
}catch(Exception exception)
{
System.out.println(exception);
}
}
}