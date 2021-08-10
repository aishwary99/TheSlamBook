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
public class AddFriendRequest extends HttpServlet
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
int toCode=jsonObject.get("requestSenderCode").getAsInt();
String toName=jsonObject.get("requestSenderName").getAsString();

HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean)session.getAttribute("memberBean");
int fromCode=memberBean.getCode();
String fromName=memberBean.getName();

Member toMember=new Member();
toMember.setCode(toCode);
toMember.setName(toName);

Member fromMember=new Member();
fromMember.setCode(fromCode);
fromMember.setName(fromName);

MemberDAO memberDAO=new MemberDAO();
try
{
memberDAO.addFriendRequest(fromMember,toMember);
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