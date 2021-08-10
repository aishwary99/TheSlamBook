package com.slam.book.servlets;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.slam.book.beans.*;
import com.google.gson.*;
public class SkipProfileSetter extends HttpServlet 
{
public void doGet(HttpServletRequest request,HttpServletResponse response) 
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response) 
{
try
{
HttpSession session=request.getSession();
MemberBean member=(MemberBean)session.getAttribute("memberBean");
String gender=member.getGender();
if(gender.equals("M"))
{
member.setImagePath("profile-pic/maleAvatar.png");
}
else
{
member.setImagePath("profile-pic/femaleAvatar.png");
}
String line=null;
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
BufferedWriter bw=new BufferedWriter(new FileWriter("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/temp.txt",true));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(member.getName()))
{
if(gender.equals("M"))
{
image="maleAvatar.png";
}
else
{
image="femaleAvatar.png";
}
bw.write(member.getName()+","+image);
bw.write("\r\n");
bw.close();
break;
}
bw.write(line);
bw.write("\r\n");
}
bw.close();
bw=new BufferedWriter(new FileWriter("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
bw.write("");
bw.close();
br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/temp.txt"));
bw=new BufferedWriter(new FileWriter("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
bw.write(line);
bw.write("\r\n");
}
bw.close();
bw=new BufferedWriter(new FileWriter("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/temp.txt"));
bw.write("");
bw.close();
RequestDispatcher requestDispatcher=request.getRequestDispatcher("home.jsp");
requestDispatcher.forward(request,response);
}catch(Exception e)
{
}
}
}