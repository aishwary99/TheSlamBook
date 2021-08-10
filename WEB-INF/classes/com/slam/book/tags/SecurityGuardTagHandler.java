package com.slam.book.tags;
import com.slam.book.dao.*;
import com.slam.book.beans.*;
import java.util.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
public class SecurityGuardTagHandler extends TagSupport
{
private boolean status=false;
public int doStartTag()
{
try
{
HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
HttpServletResponse response=(HttpServletResponse) pageContext.getResponse();
HttpSession session=request.getSession();
MemberBean memberBean=(MemberBean) session.getAttribute("memberBean");
MemberDataStructure mds=(MemberDataStructure) session.getAttribute("mds");
if(mds==null|| memberBean==null || memberBean.getName()=="" || memberBean.getCode()==0 || memberBean.getRegistrationId()==0)
{
RequestDispatcher rd=request.getRequestDispatcher("404.jsp");
rd.forward(request,response);
}
}catch(Exception exception)
{
System.out.println(exception);
return SKIP_BODY;
}
return EVAL_BODY_INCLUDE;
}
public int doEndTag()
{
return EVAL_PAGE;
}
}