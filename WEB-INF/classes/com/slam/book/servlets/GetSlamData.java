package com.slam.book.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import com.slam.book.dao.*;
import com.slam.book.beans.*;
import com.google.gson.*;
import com.slam.book.util.*;
public class GetSlamData extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
String imageContents="";
String imageFormat="";
try
{
pw=response.getWriter();
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
int friendCode=jsonObject.get("friendCode").getAsInt();
HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean)session.getAttribute("memberBean");
int code=memberBean.getCode();
MemberDAO memberDAO;
GetImageContents imgContents;
try
{
memberDAO=new MemberDAO();
jsonString=memberDAO.getSlamData(code,friendCode);
imgContents=new GetImageContents();
String i[]=imgContents.getImageContents(friendCode,code);
if(i[0].equals("j"))
{
imageFormat="jpg";
}
else
{
imageFormat="png";
}
imageContents=i[1];
}catch(DAOException dao)
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",dao.getMessage());
pw.print(responseJson);
}
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
responseJson.addProperty("memberCode",code);
responseJson.addProperty("friendCode",friendCode);
responseJson.addProperty("slamData",jsonString);
responseJson.addProperty("img",imageContents);
responseJson.addProperty("imgFormat",imageFormat);
pw.print(responseJson);
}catch(Exception e)
{
}
}
}