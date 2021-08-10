package com.slam.book.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import com.slam.book.dao.*;
import com.slam.book.beans.*;
import com.google.gson.*;
public class GetArrayOfFriends extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
try
{
response.setContentType("application/json");
HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean) session.getAttribute("memberBean");
MemberDAOInterface memberDAO;
Member member;
JsonArray jsonArray=null;
pw=response.getWriter();
try
{
memberDAO=new MemberDAO();
jsonArray=memberDAO.getArrayOfFriends(memberBean.getCode());
System.out.println("Json Array: "+jsonArray);
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
responseJson.addProperty("listOfFriends",jsonArray.toString());
pw.print(responseJson);
}catch(DAOException daoException)
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",daoException.getMessage());
pw.print(responseJson);
return;
}
}catch(Exception e)
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",e.getMessage());
pw.print(responseJson);
return;
}
}
}