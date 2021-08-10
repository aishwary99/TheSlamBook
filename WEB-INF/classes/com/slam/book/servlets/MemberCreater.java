package com.slam.book.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import com.slam.book.dao.*;
import com.slam.book.util.*;
import com.google.gson.*;
import org.json.simple.*;
 
public class MemberCreater extends HttpServlet
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
String name=jsonObject.get("name").getAsString();
String email=jsonObject.get("email").getAsString();
String password=jsonObject.get("password").getAsString().trim();
String gender=jsonObject.get("gender").getAsString();
if(gender.equals("male"))
{
gender="M";
}
else
{
gender="F";
}
Random random=new Random();
int registrationId=random.nextInt(500000);
String passwordKey=UUID.randomUUID().toString().trim();
String encryptedPassword=encryption.encrypt(password,passwordKey);
Member member=new Member();
member.setName(name);
member.setEmail(email);
member.setEncryptedPassword(encryptedPassword);
member.setPasswordKey(passwordKey);
member.setGender(gender);
member.setRegistrationId(registrationId);
MemberDAO memberDAO=new MemberDAO();
try
{
member=memberDAO.add(member);
}catch(DAOException daoException)
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",daoException.getMessage());
PrintWriter pw=response.getWriter();
pw.print(responseJson);
return;
}
//Writing to member.txt
File file=new File("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt");
if(!file.exists())
{
file.createNewFile();
}
String imagePath=null;
BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
bw.write(name+","+imagePath);
bw.write("\r\n");
bw.close();
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