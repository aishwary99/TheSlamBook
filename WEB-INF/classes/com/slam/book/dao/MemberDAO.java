package com.slam.book.dao;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import com.slam.book.util.*;
import com.google.gson.*;
public class MemberDAO implements MemberDAOInterface
{
private PreparedStatement preparedStatement;
private Connection connection;
private Statement statement;
private ResultSet resultSet;
public MemberDAO()
{
this.preparedStatement=null;
this.statement=null;
this.connection=null;
this.resultSet=null;
}
public int getSlamsCount(int code) throws DAOException
{
int slamCount=0;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
preparedStatement=connection.prepareStatement("select count(*) as cnt from book where friend_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
resultSet.next();
slamCount=resultSet.getInt("cnt");
}catch(Exception e)
{
}
return slamCount;
}
public int getSlamsWrittenCount(int code) throws DAOException
{
int slamWrittenCount=0;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
preparedStatement=connection.prepareStatement("select count(*) as cnt from book where member_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
resultSet.next();
slamWrittenCount=resultSet.getInt("cnt");
}catch(Exception e)
{
}
return slamWrittenCount;
}
public JsonArray getArrayOfSlamFriends(int code) throws DAOException
{
JsonArray jsonArray=null;
try
{
jsonArray=new JsonArray();
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
preparedStatement=connection.prepareStatement("select * from book where friend_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
JsonObject jsonObject;
while(resultSet.next())
{
int friendCode=resultSet.getInt("member_code");
ResultSet resultSet1;
PreparedStatement preparedStatement1;
preparedStatement1=connection.prepareStatement("select * from user where code=?");
preparedStatement1.setInt(1,friendCode);
resultSet1=preparedStatement1.executeQuery();
resultSet1.next();
jsonObject=new JsonObject();
jsonObject.addProperty("code",resultSet1.getInt("code"));
jsonObject.addProperty("name",resultSet1.getString("name"));
jsonObject.addProperty("regId",resultSet1.getInt("regId"));
jsonObject.addProperty("email",resultSet1.getString("email"));
String line="";
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(resultSet1.getString("name")))
{
image=line.substring(line.indexOf(',')+1);
jsonObject.addProperty("imagePath","profile-pic/"+image);
}
}
jsonArray.add(jsonObject);
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
System.out.println(exception);
}
return jsonArray;
}
public String getSlamData(int code,int friendCode) throws DAOException
{
String jsonString="";
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
preparedStatement=connection.prepareStatement("select * from user where code=?");
preparedStatement.setInt(1,friendCode);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
preparedStatement=connection.prepareStatement("select * from book where member_code=? and friend_code=?");
preparedStatement.setInt(1,friendCode);
preparedStatement.setInt(2,code);
resultSet=preparedStatement.executeQuery();
resultSet.next();
jsonString=resultSet.getString("data");
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
System.out.println(exception);
}
return jsonString;
}
public void addSlam(int memberCode,int friendCode,String jsonString) throws DAOException
{
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where code=?");
preparedStatement.setInt(1,memberCode);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
preparedStatement=connection.prepareStatement("select * from user where code=?");
preparedStatement.setInt(1,friendCode);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
preparedStatement=connection.prepareStatement("select * from book where member_code=? and friend_code=?");
preparedStatement.setInt(1,memberCode);
preparedStatement.setInt(2,friendCode);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("You have already written slam for the selected friend....");
}
preparedStatement=connection.prepareStatement("insert into book (member_code,friend_code,data) values (?,?,?)");
preparedStatement.setInt(1,memberCode);
preparedStatement.setInt(2,friendCode);
preparedStatement.setString(3,jsonString);
preparedStatement.executeUpdate();
preparedStatement.close();
resultSet.close();
connection.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public int getFriendsCount(int code) throws DAOException
{
int friendsCount=0;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select count(*) as friendsCount from friend where member_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
resultSet.next();
friendsCount=resultSet.getInt("friendsCount");
if(friendsCount==0)
{
preparedStatement=connection.prepareStatement("select count(*) as friendsCount from friend where friend_code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
resultSet.next();
friendsCount=resultSet.getInt("friendsCount");
}
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException e)
{
throw new DAOException(e.getMessage());
}
catch(Exception e)
{
}
return friendsCount;
}
public Member getMember(int regId) throws DAOException
{
Member member=null;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where regId=?");
preparedStatement.setInt(1,regId);
resultSet=preparedStatement.executeQuery();
resultSet.next();
member=new Member();
member.setEmail(resultSet.getString("email"));
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException e)
{
throw new DAOException(e.getMessage());
}
catch(Exception e)
{
}
return member;
}
public Member add(Member member) throws DAOException
{
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where email=?");
preparedStatement.setString(1,member.getEmail());
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Member already exists");
}
preparedStatement=connection.prepareStatement("insert into user(name,email,regId,e_password,k_password,gender) values (?,?,?,?,?,?)");
preparedStatement.setString(1,member.getName().trim());
preparedStatement.setString(2,member.getEmail().trim());
preparedStatement.setInt(3,member.getRegistrationId());
preparedStatement.setString(4,member.getEncryptedPassword());
preparedStatement.setString(5,member.getPasswordKey());
preparedStatement.setString(6,member.getGender());
preparedStatement.executeUpdate();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from user where email=?");
preparedStatement.setString(1,member.getEmail());
resultSet=preparedStatement.executeQuery();
resultSet.next();
int code=resultSet.getInt(1);
member.setCode(code);
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException e)
{
throw new DAOException(e.getMessage());
}
catch(Exception e)
{
}
return member;
}
public void update(Member member) throws DAOException
{
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where regId=?");
preparedStatement.setInt(1,member.getRegistrationId());
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Member identification failed");
}
String passwordKey=resultSet.getString("k_password");
String encryptedPassword=resultSet.getString("e_password");
String decryptedPassword=encryption.decrypt(encryptedPassword,passwordKey);
if(!decryptedPassword.equals(member.getCurrentPassword()))
{
throw new DAOException("Wrong current password");
}
encryptedPassword=encryption.encrypt(member.getPassword(),passwordKey);
preparedStatement=connection.prepareStatement("update user set name=?,email=?,e_password=? where regId=? ");
preparedStatement.setString(1,member.getName().trim());
preparedStatement.setString(2,member.getEmail().trim());
preparedStatement.setString(3,encryptedPassword);
preparedStatement.setInt(4,member.getRegistrationId());
preparedStatement.executeUpdate();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException e)
{
throw new DAOException(e.getMessage());
}
catch(Exception e)
{
}
}

public Member authenticate(String email,String password) throws DAOException
{
Member member=null;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where email=?");
preparedStatement.setString(1,email);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Email/Password");
}
String encryptedPassword=resultSet.getString("e_password");
String passwordKey=resultSet.getString("k_password");
String decryptedPassword=encryption.decrypt(encryptedPassword,passwordKey);
if(!decryptedPassword.equals(password))
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Email/Password");
}
member=new Member();
member.setCode(resultSet.getInt("code"));
member.setName(resultSet.getString("name"));
member.setEmail(email);
member.setGender(resultSet.getString("gender"));
member.setRegistrationId(resultSet.getInt("regId"));
member.setPassword(decryptedPassword);
member.setEncryptedPassword(resultSet.getString("e_password"));
member.setPasswordKey(resultSet.getString("k_password"));
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException e)
{
throw new DAOException(e.getMessage());
}
catch(Exception e)
{
}
return member;
}
public int getFriendRequestArrivedCount(int code) throws DAOException
{
int requestCount=0;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from friend_request where sent_to=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.last())
{
requestCount=resultSet.getRow();
}
else
{
requestCount=0;
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
throw new DAOException(dao.getMessage());
}
catch(Exception e)
{
e.printStackTrace();
}
return requestCount;
}
public List<Member> getListOfFriendRequestArrived(int code) throws DAOException
{
Member member=null;
List<Member> requestsArrived=null;
try
{
requestsArrived=new LinkedList<Member>();
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from friend_request where sent_to=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
int fromCode=resultSet.getInt("sent_by");
PreparedStatement preparedStatement1=connection.prepareStatement("select * from user where code=?");
preparedStatement1.setInt(1,fromCode);
ResultSet resultSet1=preparedStatement1.executeQuery();
resultSet1.next();
String name=resultSet1.getString("name");
member=new Member();
member.setCode(resultSet1.getInt("code"));
member.setName(name);
member.setEmail(resultSet1.getString("email"));
member.setRegistrationId(resultSet1.getInt("regId"));
String line="";
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(name))
{
image=line.substring(line.indexOf(',')+1);
member.setImagePath("profile-pic/"+image);
}
}
requestsArrived.add(member);
br.close();
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
throw new DAOException(dao.getMessage());
}
catch(Exception e)
{
e.printStackTrace();
}
return requestsArrived;
}
public JsonArray getArrayOfFriendRequestArrived(int code) throws DAOException
{
JsonObject jsonObject=null;
JsonArray jsonArray=null;
try
{
jsonArray=new JsonArray();
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from friend_request where sent_to=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
int fromCode=resultSet.getInt("sent_by");
PreparedStatement preparedStatement1=connection.prepareStatement("select * from user where code=?");
preparedStatement1.setInt(1,fromCode);
ResultSet resultSet1=preparedStatement1.executeQuery();
resultSet1.next();
String name=resultSet1.getString("name");
jsonObject=new JsonObject();
jsonObject.addProperty("code",resultSet1.getInt("code"));
jsonObject.addProperty("name",name);
jsonObject.addProperty("email",resultSet1.getString("email"));
jsonObject.addProperty("regId",resultSet1.getInt("regId"));
String line="";
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(name))
{
image=line.substring(line.indexOf(',')+1);
jsonObject.addProperty("imagePath","profile-pic/"+image);
}
}
jsonArray.add(jsonObject);
br.close();
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
throw new DAOException(dao.getMessage());
}
catch(Exception e)
{
e.printStackTrace();
}
return jsonArray;
}
public JsonArray getArrayOfFriends(int code) throws DAOException
{
JsonObject jsonObject=null;
JsonArray jsonArray=null;
try
{
jsonArray=new JsonArray();
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
preparedStatement=connection.prepareStatement("select * from friend where member_code=? or friend_code=?");
preparedStatement.setInt(1,code);
preparedStatement.setInt(2,code);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
int friendCode=resultSet.getInt("friend_code");
int memberCode=resultSet.getInt("member_code");
if(friendCode==code)
{
friendCode=memberCode;
}
PreparedStatement p1;
ResultSet r1;
p1=connection.prepareStatement("select * from user where code=?");
p1.setInt(1,friendCode);
r1=p1.executeQuery();
r1.next();
jsonObject=new JsonObject();
jsonObject.addProperty("code",r1.getInt("code"));
jsonObject.addProperty("name",r1.getString("name"));
jsonObject.addProperty("email",r1.getString("email"));
jsonObject.addProperty("regId",r1.getInt("regId"));
String line="";
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(r1.getString("name")))
{
image=line.substring(line.indexOf(',')+1);
jsonObject.addProperty("imagePath","profile-pic/"+image);
}
}
jsonArray.add(jsonObject);
br.close();
}//end of loop
}catch(Exception exception)
{
System.out.println(exception);
}
return jsonArray;
}

public JsonArray getArrayOfMembers(int code) throws DAOException
{
JsonObject jsonObject=null;
JsonArray jsonArray=null;
try
{
jsonArray=new JsonArray();
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user order by name");
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
if(code==resultSet.getInt("code")) 
{
continue;
}
int friendCode=resultSet.getInt("code");
PreparedStatement p2;
p2=connection.prepareStatement("select * from friend where member_code=? and friend_code=?");
p2.setInt(1,code);
p2.setInt(2,friendCode);
ResultSet r2;
r2=p2.executeQuery();
boolean b2,b3;
b2=r2.next();
p2=connection.prepareStatement("select * from friend where member_code=? and friend_code=?");
p2.setInt(1,friendCode);
p2.setInt(2,code);
r2=p2.executeQuery();
b3=r2.next();

int token=0;
p2=connection.prepareStatement("select * from friend_request where sent_by=? and sent_to=?");
p2.setInt(1,friendCode);
p2.setInt(2,code);
//Case where token=1;
r2=p2.executeQuery();
if(r2.next())
{
token=1;
}
p2=connection.prepareStatement("select * from friend_request where sent_by=? and sent_to=?");
p2.setInt(1,code);
p2.setInt(2,friendCode);
//Case where token=2;
r2=p2.executeQuery();
if(r2.next())
{
token=2;
}
jsonObject=new JsonObject();
jsonObject.addProperty("code",resultSet.getInt("code"));
jsonObject.addProperty("name",resultSet.getString("name"));
jsonObject.addProperty("email",resultSet.getString("email"));
jsonObject.addProperty("regId",resultSet.getInt("regId"));
jsonObject.addProperty("token",token);
String line="";
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(resultSet.getString("name")))
{
image=line.substring(line.indexOf(',')+1);
jsonObject.addProperty("imagePath","profile-pic/"+image);
}
}
boolean x=b2 || b3;
if(!x) jsonArray.add(jsonObject);
br.close();
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
throw new DAOException(dao.getMessage());
}
catch(Exception e)
{
e.printStackTrace();
}
return jsonArray;
}
public List<Member> getListOfMembers(int regId) throws DAOException
{
List<Member> members=new LinkedList<>();
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user order by name");
resultSet=preparedStatement.executeQuery();
Member member;
int memberCode=0;
while(resultSet.next())
{
String name=resultSet.getString("name");
int memberRegId=resultSet.getInt("regId");
if(memberRegId==regId) 
{
memberCode=resultSet.getInt("code");
}
}
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
String name=resultSet.getString("name");
int memberRegId=resultSet.getInt("regId");
if(memberRegId==regId) 
{
continue;
}
PreparedStatement p2=connection.prepareStatement("select * from friend where member_code=? and friend_code=?");
p2.setInt(1,memberCode);
p2.setInt(2,resultSet.getInt("code"));
ResultSet r2=p2.executeQuery();
boolean b1=r2.next();
PreparedStatement p3=connection.prepareStatement("select * from friend where friend_code=? and member_code=?");
p3.setInt(1,memberCode);
p3.setInt(2,resultSet.getInt("code"));
ResultSet r3=p3.executeQuery();
boolean b3=r3.next();

member=new Member();
member.setCode(resultSet.getInt("code"));
member.setName(name);
member.setEmail(resultSet.getString("email"));
member.setRegistrationId(resultSet.getInt("regId"));
String line="";
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(member.getName()))
{
image=line.substring(line.indexOf(',')+1);
member.setImagePath("profile-pic/"+image);
}
}
boolean x=b1 || b3;
if(!x)members.add(member);
br.close();
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
throw new DAOException(dao.getMessage());
}
catch(Exception e)
{
e.printStackTrace();
}
return members;
}
public List<Member> getListOfFriendRequestSent(int code) throws DAOException
{
List<Member> requestsSent=new LinkedList<>();
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from friend_request where sent_by=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
Member member;
while(resultSet.next())
{
int toCode=resultSet.getInt("sent_to");
PreparedStatement preparedStatement1=connection.prepareStatement("select * from user where code=?");
preparedStatement1.setInt(1,toCode);
ResultSet resultSet1=preparedStatement1.executeQuery();
resultSet1.next();
member=new Member();
member.setCode(resultSet1.getInt("code"));
member.setName(resultSet1.getString("name"));
member.setEmail(resultSet1.getString("email"));
member.setRegistrationId(resultSet1.getInt("regId"));
String line="";
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(member.getName()))
{
image=line.substring(line.indexOf(',')+1);
member.setImagePath("profile-pic/"+image);
}
}
requestsSent.add(member);
br.close();
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
throw new DAOException(dao.getMessage());
}
catch(Exception e)
{
e.printStackTrace();
}
return requestsSent;
}
public List<Member> getListOfFriends(int code) throws DAOException
{
List<Member> friends=new LinkedList<Member>();
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where code=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
preparedStatement=connection.prepareStatement("select * from friend where member_code=? or friend_code=?");
preparedStatement.setInt(1,code);
preparedStatement.setInt(2,code);
resultSet=preparedStatement.executeQuery();
ResultSet resultSet1;
PreparedStatement preparedStatement1;
while(resultSet.next())
{
int friendCode=resultSet.getInt("friend_code");
int memberCode=resultSet.getInt("member_code");
if(friendCode==code)
{
friendCode=memberCode;
}
preparedStatement1=connection.prepareStatement("select * from user where code=?");
preparedStatement1.setInt(1,friendCode);
resultSet1=preparedStatement1.executeQuery();
resultSet1.next();
Member member=new Member();
member.setCode(resultSet1.getInt("code"));
member.setName(resultSet1.getString("name"));
member.setEmail(resultSet1.getString("email"));
String line="";
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(member.getName()))
{
image=line.substring(line.indexOf(',')+1);
member.setImagePath("profile-pic/"+image);
}
}
friends.add(member);
br.close();
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
}
return friends;
}
public boolean getFriendStatus(int code,int friendCode) throws DAOException
{
boolean status=false;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from friend where (member_code=? and friend_code=?) or (member_code=? and friend_code=?)");
preparedStatement.setInt(1,code);
preparedStatement.setInt(2,friendCode);
preparedStatement.setInt(3,friendCode);
preparedStatement.setInt(4,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
status=true;
}
else
{
status=false;
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
throw new DAOException(dao.getMessage());
}
catch(Exception e)
{
e.printStackTrace();
}
return status;
}
public List<Member> searchMember(String name) throws DAOException
{
List<Member> searchResults=new LinkedList<>();
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where name like ? ");
preparedStatement.setString(1,name+"%");
resultSet=preparedStatement.executeQuery();
Member member;
while(resultSet.next())
{
member=new Member();
member.setCode(resultSet.getInt("code"));
member.setName(resultSet.getString("name"));
member.setEmail(resultSet.getString("email"));
member.setRegistrationId(resultSet.getInt("regId"));
String line="";
String nameFromFile="";
String image="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(member.getName()))
{
image=line.substring(line.indexOf(',')+1);
member.setImagePath("profile-pic/"+image);
}
}
searchResults.add(member);
br.close();
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
throw new DAOException(dao.getMessage());
}
catch(Exception e)
{
e.printStackTrace();
}
return searchResults;
}
public void addFriendRequest(Member fromMember,Member toMember) throws DAOException
{
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select code from user where name=?");
preparedStatement.setString(1,toMember.getName());
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Member Name");
}
int toCode=toMember.getCode();
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from friend_request where (sent_by=? and sent_to=?) or (sent_by=? and sent_to=?)");
preparedStatement.setInt(1,fromMember.getCode());
preparedStatement.setInt(2,toCode);
preparedStatement.setInt(3,toCode);
preparedStatement.setInt(4,fromMember.getCode());
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return;
}
preparedStatement=connection.prepareStatement("insert into friend_request (sent_by,sent_to) values (?,?)");
preparedStatement.setInt(1,fromMember.getCode());
preparedStatement.setInt(2,toCode);
preparedStatement.executeUpdate();
preparedStatement.close();
}catch(SQLException sql)
{
sql.printStackTrace();
}
catch(DAOException dao)
{
}
catch(Exception e)
{
System.out.println(e);
}
}

public void cancelFriendRequest(Member requestSender,Member requestAccepter) throws DAOException
{
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select code from user where name=?");
preparedStatement.setString(1,requestSender.getName());
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Member Name");
}
int requestSenderCode=requestSender.getCode();
int requestAccepterCode=requestAccepter.getCode();
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("select * from friend_request where sent_by=? and sent_to=?");
preparedStatement.setInt(1,requestSenderCode);
preparedStatement.setInt(2,requestAccepterCode);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
preparedStatement=connection.prepareStatement("delete from friend_request where sent_by=? and sent_to=?");
preparedStatement.setInt(1,requestSenderCode);
preparedStatement.setInt(2,requestAccepterCode);
preparedStatement.executeUpdate();
}
preparedStatement=connection.prepareStatement("select * from friend_request where sent_by=? and sent_to=?");
preparedStatement.setInt(1,requestAccepterCode);
preparedStatement.setInt(2,requestSenderCode);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
preparedStatement=connection.prepareStatement("delete from friend_request where sent_by=? and sent_to=?");
preparedStatement.setInt(1,requestAccepterCode);
preparedStatement.setInt(2,requestSenderCode);
preparedStatement.executeUpdate();
}
else
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request !");
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
}
catch(Exception e)
{
System.out.println(e);
}
}

public void rejectFriendRequest(Member requestAccepter,Member requestSender) throws DAOException
{
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select code from user where name=?");
preparedStatement.setString(1,requestSender.getName());
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Member Name");
}
int requestSenderCode=requestSender.getCode();
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("select * from friend_request where sent_by=? and sent_to=?");
preparedStatement.setInt(1,requestSenderCode);
preparedStatement.setInt(2,requestAccepter.getCode());
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("delete from friend_request where sent_by=? and sent_to=?");
preparedStatement.setInt(1,requestSenderCode);
preparedStatement.setInt(2,requestAccepter.getCode());
preparedStatement.executeUpdate();
preparedStatement.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
}
catch(Exception e)
{
System.out.println(e);
}
}


public void acceptFriendRequest(Member requestSender,Member requestAccepter) throws DAOException
{
try
{
connection=DAOConnection.getConnection();
//Check if the sender is valid ->
preparedStatement=connection.prepareStatement("select code from user where code=?");
preparedStatement.setInt(1,requestSender.getCode());
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Request");
}
resultSet.close();
preparedStatement.close();

//Check if the friend request is placed before or not , before accepting it->
preparedStatement=connection.prepareStatement("select * from friend_request where sent_by=? and sent_to=?");
preparedStatement.setInt(1,requestSender.getCode());
preparedStatement.setInt(2,requestAccepter.getCode());
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Friend Request Doesn't Exists");
}
resultSet.close();
preparedStatement.close();

//If friend request exists , then delete it ->
preparedStatement=connection.prepareStatement("delete from friend_request where sent_by=? and sent_to=?");
preparedStatement.setInt(1,requestSender.getCode());
preparedStatement.setInt(2,requestAccepter.getCode());
preparedStatement.executeUpdate();
preparedStatement.close();

//Check if both the sender and accepter are already friends are not->
preparedStatement=connection.prepareStatement("select * from friend where (member_code=? and friend_code=?) or (member_code=? and friend_code=?)");
preparedStatement.setInt(1,requestAccepter.getCode());
preparedStatement.setInt(2,requestSender.getCode());
preparedStatement.setInt(3,requestSender.getCode());
preparedStatement.setInt(4,requestAccepter.getCode());
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return;
}
resultSet.close();
preparedStatement.close();

//add a new record in friend table , because they are meant to be friend now->
preparedStatement=connection.prepareStatement("insert into friend (member_code,friend_code) values (?,?)");
preparedStatement.setInt(1,requestAccepter.getCode());
preparedStatement.setInt(2,requestSender.getCode());
preparedStatement.executeUpdate();
preparedStatement.close();

}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
}
catch(Exception e)
{
e.printStackTrace();
}
}


public void removeFriend(Member fromMember,Member toMember) throws DAOException
{
try
{
connection=DAOConnection.getConnection();
//Check if the person who is going to be unfriend is valid or not->
preparedStatement=connection.prepareStatement("select code from user where name=?");
preparedStatement.setString(1,toMember.getName());
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Request");
}
resultSet.close();
preparedStatement.close();

//Check if both are friends before or not ->
preparedStatement=connection.prepareStatement("select * from friend where (member_code=? and friend_code=?) or (member_code=? and friend_code=?)");
preparedStatement.setInt(1,fromMember.getCode());
preparedStatement.setInt(2,toMember.getCode());
preparedStatement.setInt(3,toMember.getCode());
preparedStatement.setInt(4,fromMember.getCode());
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid request");
}
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("delete from friend where (member_code=? and friend_code=?) or (member_code=? and friend_code=?) ");
preparedStatement.setInt(1,fromMember.getCode());
preparedStatement.setInt(2,toMember.getCode());
preparedStatement.setInt(3,toMember.getCode());
preparedStatement.setInt(4,fromMember.getCode());
preparedStatement.executeUpdate();
preparedStatement.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
}
catch(Exception e)
{
System.out.println(e);
}
}
public List<Member> getListOfRecommendedFriends(int code) throws DAOException
{
List<Member> friends=new LinkedList<>();
try
{
System.out.println("MemberDAO Chali");
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from user where code!=?");
preparedStatement.setInt(1,code);
resultSet=preparedStatement.executeQuery();
Member member;
String sessionUserString="";
PreparedStatement pp=connection.prepareStatement("select * from friend where member_code=?");
pp.setInt(1,code);
ResultSet rr=pp.executeQuery();
while(rr.next())
{
System.out.println("MemberDAO Loop1 rr");
sessionUserString=sessionUserString+","+String.valueOf(rr.getInt("friend_code"));
}
PreparedStatement p8=connection.prepareStatement("select * from friend where friend_code=?");
p8.setInt(1,code);
ResultSet r8=p8.executeQuery();
while(r8.next())
{
System.out.println("MemberDAO Loop1 rr");
sessionUserString=sessionUserString+","+String.valueOf(r8.getInt("member_code"));
}

while(resultSet.next())
{
System.out.println("MemberDAO Loop2 resultSet");
member=new Member();
member.setCode(resultSet.getInt("code"));
member.setName(resultSet.getString("name"));
member.setEmail(resultSet.getString("email"));
member.setRegistrationId(resultSet.getInt("regId"));
System.out.println("Member Code : "+member.getCode()+" Member Name : "+member.getName());
String image="";
String nameFromFile="";
String line="";
BufferedReader br=new BufferedReader(new FileReader("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/member.txt"));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(member.getName()))
{
image=line.substring(line.indexOf(',')+1);
member.setImagePath("profile-pic/"+image);
}
}
PreparedStatement p1=connection.prepareStatement("select * from friend where member_code=? and friend_code=?");
p1.setInt(1,member.getCode());
p1.setInt(2,code);
ResultSet r1=p1.executeQuery();
boolean b=r1.next();
PreparedStatement p4=connection.prepareStatement("select * from friend where member_code=? and friend_code=?");
p4.setInt(1,code);
p4.setInt(2,member.getCode());
ResultSet r4=p4.executeQuery();
boolean b1=r4.next();
PreparedStatement p2=connection.prepareStatement("select * from friend where member_code=?");
p2.setInt(1,member.getCode());
ResultSet r2=p2.executeQuery();
boolean b2=r2.next();
PreparedStatement p9=connection.prepareStatement("select * from friend where friend_code=?");
p9.setInt(1,member.getCode());
ResultSet r9=p9.executeQuery();
boolean b9=r9.next();
boolean x=b2 || b9;
System.out.println("b : "+b+" x : "+x+" b1: "+b1);
String friendCodeString="";
if(!b && x && !b1)
{
System.out.println("MemberDAO if me aaya");
PreparedStatement p3=connection.prepareStatement("select * from friend where member_code=?");
p3.setInt(1,member.getCode());
ResultSet r3=p3.executeQuery();
while(r3.next())
{
System.out.println("MemberDAO innerLoop r3");
friendCodeString=friendCodeString+","+String.valueOf(r3.getInt("friend_code"));
System.out.println("friendCodeString : "+friendCodeString);
}
PreparedStatement p6=connection.prepareStatement("select * from friend where friend_code=?");
p6.setInt(1,member.getCode());
ResultSet r6=p6.executeQuery();
while(r6.next())
{
System.out.println("MemberDAO innerLoop r3");
friendCodeString=friendCodeString+","+String.valueOf(r6.getInt("member_code"));
System.out.println("friendCodeString : "+friendCodeString);
}

}
else
{
System.out.println("else");
continue;
}
double score=CosineSimilarity.cosineSimilarity(sessionUserString,friendCodeString);
System.out.println("Score : "+score);
if(score>=0.5)
{
friends.add(member);
}
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
}
catch(DAOException dao)
{
throw new DAOException(dao.getMessage());
}
catch(Exception e)
{
e.printStackTrace();
}
return friends;
}
public static void main(String g[])
{
try
{
List<Member> s=new MemberDAO().getListOfRecommendedFriends(8);
for(Member m:s)
{
System.out.println(m.getCode());
System.out.println("********");
}
}catch(Exception ex)
{}
}


}//MemberDAO ends here