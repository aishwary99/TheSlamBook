package com.slam.book.util;
import java.io.*;
import java.nio.file.*;
import java.awt.image.*;
import javax.imageio.*;
import com.sun.org.apache.xerces.internal.impl.dv.util.*;
public class GetImageContents 
{
public static void main(String gg[])
{
String imageString="";
BufferedImage image=null;
ByteArrayOutputStream baos=null;
byte[] imageContents=null;
int memberCode=32;
int friendCode=33;
try
{
String imagePath="C://tomcat9//webapps//slamBook//WEB-INF//slamPictures//compressedImages//"+memberCode+"-"+friendCode+".jpg";
baos=new ByteArrayOutputStream();
image = ImageIO.read(new File(imagePath));
ImageIO.write(image,"jpg",baos);
baos.flush();
imageString=Base64.encode(baos.toByteArray());
baos.close();
}catch(Exception exception)
{
System.out.println(exception);
}
System.out.println(imageString);
}


public String[] getImageContents(int memberCode,int friendCode)
{
String imageString="";
BufferedImage image=null;
ByteArrayOutputStream baos=null;
byte[] imageContents=null;
String format="";
File file=null;
String i[]=new String[2];
try
{
String imagePath="C://tomcat9//webapps//slamBook//WEB-INF//slamPictures//compressedImages//";
baos=new ByteArrayOutputStream();
file=new File(imagePath+""+memberCode+"-"+friendCode+".jpg");
if(file.exists())
{
image = ImageIO.read(file);
ImageIO.write(image,"jpg",baos);
format="j";
}
else
{
file=new File("C://tomcat9//webapps//slamBook//WEB-INF//slamPictures//"+memberCode+"-"+friendCode+".png");
image = ImageIO.read(file);
ImageIO.write(image,"jpg",baos);
format="p";
}
baos.flush();
imageString=Base64.encode(baos.toByteArray());
baos.close();
}catch(Exception exception)
{
System.out.println(exception);
}
i[0]=format;
i[1]=imageString;
return i;
}
}