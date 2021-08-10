package com.slam.book.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import com.slam.book.dao.*;
import com.slam.book.beans.*;
import com.google.gson.*;
public class Updater extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
try
{
response.setContentType("application/json");
HttpSession session=request.getSession();
MemberDataStructure mds=(MemberDataStructure) session.getAttribute("mds");
MemberBean memberBean=(MemberBean) session.getAttribute("memberBean");
List<Member> requestsSent;
List<Member> friends;
List<Member> requestsArrived;
int friendsCount=0;
int slamsCount=0;
int slamsWrittenCount=0;
MemberDAOInterface memberDAO;
Member member;
JsonArray jsonArray=null;
pw=response.getWriter();
try
{
memberDAO=new MemberDAO();
requestsSent=memberDAO.getListOfFriendRequestSent(memberBean.getCode());
friends=memberDAO.getListOfFriends(memberBean.getCode());
jsonArray=memberDAO.getArrayOfFriendRequestArrived(memberBean.getCode());
requestsArrived=memberDAO.getListOfFriendRequestArrived(memberBean.getCode());
mds.setRequestsSent(requestsSent);
mds.setFriendsCount(friends.size());
mds.setFriends(friends);
mds.setRequestsArrived(requestsArrived);
slamsCount=memberDAO.getSlamsCount(memberBean.getCode());
mds.setSlamsCount(slamsCount);
slamsWrittenCount=memberDAO.getSlamsWrittenCount(memberBean.getCode());
mds.setSlamsWrittenCount(slamsWrittenCount);
session.setAttribute("mds",mds);
member=memberDAO.getMember(memberBean.getRegistrationId());
memberBean.setEmail(member.getEmail());
session.setAttribute("memberBean",memberBean);
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
responseJson.addProperty("listOfFriendRequestArrived",jsonArray.toString());
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