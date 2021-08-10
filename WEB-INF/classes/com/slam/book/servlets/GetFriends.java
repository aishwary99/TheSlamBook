package com.slam.book.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import com.slam.book.dao.*;
import com.slam.book.util.*;
import com.slam.book.beans.*;
import com.google.gson.*;
import org.json.simple.*;
public class GetFriends extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
List<Member> friends=null;
PrintWriter pw=null;
try
{
HttpSession session=request.getSession();
pw=response.getWriter();
MemberBean memberBean=(MemberBean)session.getAttribute("memberBean");
MemberDataStructure mds=(MemberDataStructure) session.getAttribute("mds");
int regId=memberBean.getRegistrationId();
MemberDAO memberDAO=new MemberDAO();
try
{
friends=memberDAO.getListOfFriends(regId);
}catch(DAOException dao)
{
System.out.println(dao.getMessage());
}
mds.setFriends(friends);
mds.setFriendsCount(friends.size());
session.setAttribute("mds",mds);
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
pw.print(responseJson);
}catch(Exception e)
{
System.out.println(e);
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",e.getMessage());
pw.print(responseJson);
}
}
}