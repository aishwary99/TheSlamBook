package com.slam.book.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import com.slam.book.dao.*;
import com.slam.book.beans.*;
import com.google.gson.*;
public class GetSlamFriends extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
JsonArray jsonArray=null;
try
{
pw=response.getWriter();
response.setContentType("application/json");
HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean)session.getAttribute("memberBean");
int code=memberBean.getCode();
MemberDAO memberDAO;
try
{
memberDAO=new MemberDAO();
jsonArray=memberDAO.getArrayOfSlamFriends(code);
}catch(DAOException dao)
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",dao.getMessage());
pw.print(responseJson);
}
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
responseJson.addProperty("friends",jsonArray.toString());
pw.print(responseJson);
}catch(Exception e)
{
}
}
}