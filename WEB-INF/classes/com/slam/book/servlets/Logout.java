package com.slam.book.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import com.slam.book.dao.*;
import com.slam.book.beans.*;
public class Logout extends HttpServlet
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
session.removeAttribute("memberBean");
session.removeAttribute("mds");
RequestDispatcher requestDispatcher=request.getRequestDispatcher("index.html");
requestDispatcher.forward(request,response);
}catch(Exception e)
{
System.out.println(e);
}
}
}