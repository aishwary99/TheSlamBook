package com.slam.book.dao;
import java.util.*;
import com.google.gson.*;
public interface MemberDAOInterface
{
public int getSlamsCount(int code) throws DAOException;
public int getSlamsWrittenCount(int code) throws DAOException;
public Member add(Member member) throws DAOException;
public List<Member> getListOfMembers(int regId) throws DAOException;
public JsonArray getArrayOfMembers(int regId) throws DAOException;
public JsonArray getArrayOfFriendRequestArrived(int code) throws DAOException;
public Member authenticate(String email,String password) throws DAOException;
public void update(Member member) throws DAOException;
public void addFriendRequest(Member fromMember,Member toMember) throws DAOException;
public void cancelFriendRequest(Member fromMember,Member toMember) throws DAOException;
public void acceptFriendRequest(Member requestSender,Member requestAccepter) throws DAOException;
public void rejectFriendRequest(Member fromMember,Member toMember) throws DAOException;
public void removeFriend(Member fromMember,Member toMember) throws DAOException;
public List<Member> searchMember(String name) throws DAOException;
public boolean getFriendStatus(int code,int friendCode) throws DAOException;
public List<Member> getListOfFriendRequestSent(int code) throws DAOException;
public List<Member> getListOfFriendRequestArrived(int code) throws DAOException;
public int getFriendRequestArrivedCount(int code) throws DAOException;
public List<Member> getListOfRecommendedFriends(int code) throws DAOException;
public Member getMember(int regId) throws DAOException;
public int getFriendsCount(int code) throws DAOException;
public List<Member> getListOfFriends(int code) throws DAOException;
public JsonArray getArrayOfFriends(int code) throws DAOException;
public void addSlam(int memberCode,int friendCode,String jsonString) throws DAOException;
public JsonArray getArrayOfSlamFriends(int code) throws DAOException;
public String getSlamData(int code,int friendCode) throws DAOException;
}