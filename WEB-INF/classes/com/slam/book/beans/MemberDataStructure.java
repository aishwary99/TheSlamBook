package com.slam.book.beans;
import java.util.*;
import com.slam.book.dao.*;
public class MemberDataStructure
{
private List<Member> requestsSent;
private List<Member> requestsArrived;
private List<Member> friends;
private int friendRequestArrivedCount;
private int friendsCount;
private int slamsCount;
private int slamsWrittenCount;
public MemberDataStructure()
{
this.requestsSent=null;
this.requestsArrived=null;
this.friends=null;
this.friendRequestArrivedCount=0;
this.friendsCount=0;
this.slamsCount=0;
this.slamsWrittenCount=0;
}
public void setSlamsCount(int slamsCount)
{
this.slamsCount=slamsCount;
}
public int getSlamsCount()
{
return this.slamsCount;
}
public void setSlamsWrittenCount(int slamsWrittenCount)
{
this.slamsWrittenCount=slamsWrittenCount;
}
public int getSlamsWrittenCount()
{
return this.slamsWrittenCount;
}
public void setFriends(List<Member> friends)
{
this.friends=friends;
}
public List<Member> getFriends()
{
return this.friends;
}
public void setFriendsCount(int friendsCount)
{
this.friendsCount=friendsCount;
}
public int getFriendsCount()
{
return this.friendsCount;
}
public void setFriendRequestArrivedCount(int friendRequestArrivedCount)
{
this.friendRequestArrivedCount=friendRequestArrivedCount;
}
public int getFriendRequestArrivedCount()
{
return this.friendRequestArrivedCount;
}
public void setRequestsArrived(List<Member> requestsArrived)
{
this.requestsArrived=requestsArrived;
}
public List<Member> getRequestsArrived()
{
return this.requestsArrived;
}
public void setRequestsSent(List<Member> requestsSent)
{
this.requestsSent=requestsSent;
}
public List<Member> getRequestsSent()
{
return this.requestsSent;
}
}