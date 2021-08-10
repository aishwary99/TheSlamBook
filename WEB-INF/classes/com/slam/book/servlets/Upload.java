package com.slam.book.servlets;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.slam.book.beans.*;
import com.google.gson.*;

public class Upload extends HttpServlet 
{
    String image_in_file;     
    private static final long serialVersionUID = 1L; // uid for servlet
    private final String UPLOAD_DIRECTORY = "C:\\tomcat9\\webapps\\slamBook\\profile-pic\\";       // static path to the folder
    String name = null;   // to store file name
    String fileType = null;     // to store file type
    String fileTypeandURL = null;  //to store file type and the base64 string
    String extension = null;    // to store extension of image
    String datetime = null;    //  to store date and time in miliseconds
    String image = null;      // to store actual encoded base64 string

    protected void doPost(HttpServletRequest request,HttpServletResponse response) 
{
 try
{
response.setContentType("application/json");
InputStream inputStream=request.getInputStream();
InputStreamReader isr=new InputStreamReader(inputStream);
StringBuilder sb=new StringBuilder();
int x;
while(true)
{
x=isr.read();
if(x==-1) break;
sb.append((char)x);
}
image_in_file=sb.toString();
int i = image_in_file.indexOf(":");
if (i > 0) {
fileTypeandURL = image_in_file.substring(i + 1);
}
int j = fileTypeandURL.indexOf(";");
if (j > 0) {
fileType = fileTypeandURL.substring(0, j);
}
int k = image_in_file.indexOf(",");
if (k > 0) {
image = image_in_file.substring(k + 1);
}

        //checks file type and stores into extension
        if (fileType.equalsIgnoreCase("image/jpeg")) {
            extension = "jpg";
        }
        if (fileType.equalsIgnoreCase("image/png")) {
            extension = "png";
        }
        if (fileType.equalsIgnoreCase("image/gif")) {
            extension = "gif";
        }
HttpSession session=request.getSession();
byte[] imageByteArray = decodeImage(image);
MemberBean member=(MemberBean)session.getAttribute("memberBean");
String name=member.getName();
name=name.substring(0,name.indexOf(' '));
member.setImagePath("profile-pic/" + name + "." + extension);
FileOutputStream imageOutFile = new FileOutputStream("C:\\tomcat9\\webapps\\slamBook\\profile-pic\\" + name + "." + extension);
imageOutFile.write(imageByteArray);
imageOutFile.close();

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
image=nameFromFile.substring(0,nameFromFile.indexOf(' '))+"."+extension;
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
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
PrintWriter pw=response.getWriter();
pw.print(responseJson);        
}catch(Exception e)
{
System.out.println(e);
}
}

    public static byte[] decodeImage(String imageDataString) 
    {
        return Base64.decodeBase64(imageDataString);
    }
}
