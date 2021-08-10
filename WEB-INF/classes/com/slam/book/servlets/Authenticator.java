package com.slam.book.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import com.slam.book.dao.*;
import com.slam.book.util.*;
import com.slam.book.beans.*;
import com.google.gson.*;
public class Authenticator extends HttpServlet
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
String email=jsonObject.get("email").getAsString();
String password=jsonObject.get("password").getAsString();
MemberDAO memberDAO=new MemberDAO();
Member member=null;
int friendsCount=0;
int slamsCount=0;
int slamsWrittenCount=0;
try
{
member=memberDAO.authenticate(email,password);
}catch(DAOException dao)
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",dao.getMessage());
PrintWriter pw=response.getWriter();
pw.print(responseJson);
return;
}
MemberBean memberBean=new MemberBean();
memberBean.setCode(member.getCode());
memberBean.setName(member.getName());
memberBean.setEmail(member.getEmail());
memberBean.setRegistrationId(member.getRegistrationId());
memberBean.setPassword(member.getPassword());
memberBean.setGender(member.getGender());
MemberDataStructure mds=new MemberDataStructure();
List<Member> requestsSent=null;
List<Member> friends=null;
List<Member> requestsArrived=null;
try
{
requestsSent=memberDAO.getListOfFriendRequestSent(member.getCode());
mds.setRequestsSent(requestsSent);
requestsArrived=memberDAO.getListOfFriendRequestArrived(member.getCode());
mds.setRequestsArrived(requestsArrived);
friendsCount=memberDAO.getFriendsCount(member.getCode());
mds.setFriendsCount(friendsCount);
friends=memberDAO.getListOfFriends(member.getCode());
mds.setFriends(friends);
slamsCount=memberDAO.getSlamsCount(member.getCode());
mds.setSlamsCount(slamsCount);
slamsWrittenCount=memberDAO.getSlamsWrittenCount(member.getCode());
mds.setSlamsWrittenCount(slamsWrittenCount);
}catch(DAOException dao)
{
System.out.println(dao.getMessage());
}
HttpSession session=request.getSession();
session.setAttribute("mds",mds);
String line=null;
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(member.getName()))
{
image=line.substring(line.indexOf(',')+1);
break;
}
}
if(image.equals("null"))
{
session.setAttribute("memberBean",memberBean);
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
responseJson.addProperty("imageStatus","false");
PrintWriter pw=response.getWriter();
pw.print(responseJson);
}
else
{
memberBean.setImagePath("profile-pic/"+image);
session.setAttribute("memberBean",memberBean);
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
responseJson.addProperty("imageStatus","true");
PrintWriter pw=response.getWriter();
pw.print(responseJson);
}
}catch(Exception e)
{
System.out.println(e);
}
}
}