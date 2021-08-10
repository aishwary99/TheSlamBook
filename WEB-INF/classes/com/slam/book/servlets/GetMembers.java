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
public class GetMembers extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
JsonArray jsonArray=null;
PrintWriter pw=null;
try
{
response.setContentType("application/json");
pw=response.getWriter();
HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean)session.getAttribute("memberBean");
int code=memberBean.getCode();
MemberDAO memberDAO=new MemberDAO();
try
{
jsonArray=memberDAO.getArrayOfMembers(code);
}catch(DAOException dao)
{
System.out.println(dao.getMessage());
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",dao.getMessage());
pw.print(responseJson);
}
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
responseJson.addProperty("members",jsonArray.toString());
pw.print(responseJson);
}catch(Exception e)
{
System.out.println(e);
}
}
}