package com.slam.book.beans;
import java.io.*;
public class MemberBean implements Serializable
{
private int code;
private String name;
private String email;
private int registrationId;
private String password;
private String gender;
private String imagePath;
private int friendRequestCount;
public MemberBean()
{
this.code=0;
this.name="";
this.email="";
this.registrationId=0;
this.password="";
this.gender="";
this.imagePath="";
this.friendRequestCount=0;
}
public void setFriendRequestCount(int friendRequestCount)
{
this.friendRequestCount=friendRequestCount;
}
public int getFriendRequestCount()
{
return this.friendRequestCount;
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setEmail(String email)
{
this.email=email;
}
public String getEmail()
{
return this.email;
}
public void setPassword(String password)
{
this.password=password;
}
public String getPassword()
{
return this.password;
}
public void setRegistrationId(int registrationId)
{
this.registrationId=registrationId;
}
public int getRegistrationId()
{
return this.registrationId;
}
public void setGender(String gender)
{
this.gender=gender;
}
public String getGender()
{
return this.gender;
}
public void setImagePath(String imagePath)
{
this.imagePath=imagePath;
}
public String getImagePath()
{
return this.imagePath;
}
}