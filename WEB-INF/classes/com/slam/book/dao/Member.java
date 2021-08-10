package com.slam.book.dao;
import java.io.*;
public class Member implements Serializable
{
private int code;
private String name;
private String email;
private int registrationId;
private String currentPassword;
private String password;
private String gender;
private String encryptedPassword;
private String passwordKey;
private String imagePath;
private boolean friendStatus;
private int friendCode;
public Member()
{
this.code=0;
this.name="";
this.email="";
this.registrationId=0;
this.currentPassword="";
this.password="";
this.gender="";
this.passwordKey="";
this.encryptedPassword="";
this.imagePath="";
this.friendStatus=false;
this.friendCode=0;
}
public void setCurrentPassword(String currentPassword)
{
this.currentPassword=currentPassword;
}
public String getCurrentPassword()
{
return this.currentPassword;
}
public void setFriendCode(int friendCode)
{
this.friendCode=friendCode;
}
public int getFriendCode()
{
return this.friendCode;
}
public void setFriendStatus(boolean friendStatus)
{
this.friendStatus=friendStatus;
}
public boolean getFriendStatus()
{
return this.friendStatus;
}
public void setImagePath(String imagePath)
{
this.imagePath=imagePath;
}
public String getImagePath()
{
return this.imagePath;
}
public void setEncryptedPassword(String encryptedPassword)
{
this.encryptedPassword=encryptedPassword;
}
public String getEncryptedPassword()
{
return this.encryptedPassword;
}
public void setPasswordKey(String passwordKey)
{
this.passwordKey=passwordKey;
}
public String getPasswordKey()
{
return this.passwordKey;
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
}