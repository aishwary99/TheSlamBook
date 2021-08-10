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
 
public class SearchMember extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
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
String name=jsonObject.get("name").getAsString();
HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean)session.getAttribute("memberBean");
int code=memberBean.getCode();
MemberDAO memberDAO=new MemberDAO();
List<Member> searchResults=null;
boolean friendStatus=false;
try
{
searchResults=memberDAO.searchMember(name);
for(Member member:searchResults)
{
friendStatus=memberDAO.getFriendStatus(code,member.getCode());
member.setFriendStatus(friendStatus);
}
}catch(DAOException daoException)
{
//Divert to "No results found" page...
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
PrintWriter pw=response.getWriter();
pw.print(responseJson);
}
session.setAttribute("searchResults",searchResults);
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
PrintWriter pw=response.getWriter();
pw.print(responseJson);
}catch(Exception e)
{
System.out.println(e);
}
}
}