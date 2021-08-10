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
public class AcceptFriendRequest extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.setContentType("application/json");
InputStream inputStream=request.getInputStream();
InputStreamReader isr=new InputStreamReader(inputStream);
StringBuilder sb=new StringBuilder();
int x;
while(true)
{
x=isr.read();
if(x==-1) break;
sb.append((char)x);
}
String jsonString=sb.toString();
Gson gson=new Gson();
JsonElement jsonElement=gson.fromJson(jsonString,JsonElement.class);
JsonObject jsonObject=jsonElement.getAsJsonObject();
int requestSenderCode=jsonObject.get("requestSenderCode").getAsInt();
System.out.println("Request sender code-> "+requestSenderCode);

HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean)session.getAttribute("memberBean");
int requestAccepterCode=memberBean.getCode();

//FriendRequests List Updation->
MemberDataStructure mds=(MemberDataStructure)session.getAttribute("mds");
List<Member> requestsList=mds.getRequestsArrived();
for(Member removeMember:requestsList)
{
if(removeMember.getCode()==requestSenderCode)
{
requestsList.remove(removeMember);
break;
}
}
Member requestSender=new Member();
requestSender.setCode(requestSenderCode);

Member requestAccepter=new Member();
requestAccepter.setCode(requestAccepterCode);

MemberDAO memberDAO=new MemberDAO();
try
{
memberDAO.acceptFriendRequest(requestSender,requestAccepter);
}catch(DAOException daoException)
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",daoException.getMessage());
PrintWriter pw=response.getWriter();
pw.print(responseJson);
return;
}
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
PrintWriter pw=response.getWriter();
pw.print(responseJson);
}catch(Exception e)
{
e.printStackTrace();
}
}
}