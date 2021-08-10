package com.slam.book.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import com.slam.book.dao.*;
import com.slam.book.util.*;
import com.google.gson.*;
import org.json.simple.*;
import com.slam.book.beans.*;
 
public class ProfileUpdater extends HttpServlet
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
String email=jsonObject.get("email").getAsString();
String currentPassword=jsonObject.get("currentPassword").getAsString().trim();
String newPassword=jsonObject.get("newPassword").getAsString().trim();
HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean)session.getAttribute("memberBean");
int regId=memberBean.getRegistrationId();
Member member=new Member();
member.setName(name);
member.setEmail(email);
member.setCurrentPassword(currentPassword);
member.setPassword(newPassword);
member.setRegistrationId(regId);
MemberDAO memberDAO=new MemberDAO();
try
{
memberDAO.update(member);
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
System.out.println(e);
}
}
}