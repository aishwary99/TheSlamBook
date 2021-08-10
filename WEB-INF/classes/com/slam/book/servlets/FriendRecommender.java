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
public class FriendRecommender extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
System.out.println("Friend Recommender Chali");
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
int code=jsonObject.get("code").getAsInt();
HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean)session.getAttribute("memberBean");
//int code=memberBean.getCode();
MemberDAO memberDAO=new MemberDAO();
List<Member> friends=null;
try
{
friends=memberDAO.getListOfRecommendedFriends(code);
if(friends==null)
{
System.out.println("Zero Friends hai");
session.setAttribute("recommendedFriends",friends);
return;
}
System.out.println("No. Of Friends : "+friends.size());
//System.out.println(Integer.parseInt(a));
for(Member m:friends){
System.out.println(m.getCode());
System.out.println("*****");
}
System.out.println("Friend Recommend hue");
}catch(DAOException daoException)
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
PrintWriter pw=response.getWriter();
pw.print(responseJson);
System.out.println("errors");
}
JsonObject responseJson=new JsonObject();
for(Member m:friends){
System.out.println(m.getCode());
System.out.println("*****");
}
session.setAttribute("recommendedFriends",friends);
responseJson.addProperty("success","true");
PrintWriter pw=response.getWriter();
pw.print(responseJson);
}catch(Exception e)
{
System.out.println(e);
}
}
}