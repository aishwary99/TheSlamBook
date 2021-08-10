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
import com.slam.book.util.*;
import com.slam.book.dao.*;

public class UploadSlam extends HttpServlet 
{
    String image_in_file;     
    private static final long serialVersionUID = 1L; // uid for servlet
    private final String UPLOAD_DIRECTORY = "C:\\tomcat9\\webapps\\slamBook\\WEB-INF\\slamPictures\\";       // static path to the folder
    String name = null;   // to store file name
    String fileType = null;     // to store file type
    String fileTypeandURL = null;  //to store file type and the base64 string
    String extension = null;    // to store extension of image
    String datetime = null;    //  to store date and time in miliseconds
    String image = null;      // to store actual encoded base64 string

protected void doPost(HttpServletRequest request,HttpServletResponse response) 
{
PrintWriter pw=null;
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
String jsonString=sb.toString();
Gson gson=new Gson();
JsonElement jsonElement=gson.fromJson(jsonString,JsonElement.class);
JsonObject jsonObject=jsonElement.getAsJsonObject();
int memberCode=jsonObject.get("memberCode").getAsInt();
int friendCode=jsonObject.get("friendCode").getAsInt();
image_in_file=jsonObject.get("img").getAsString().trim();

//making another json in which 'img' will be excluded....
JsonObject newJson=new JsonObject();
newJson.addProperty("name",jsonObject.get("name").getAsString());
newJson.addProperty("nickName",jsonObject.get("nickName").getAsString());
newJson.addProperty("dob",jsonObject.get("dob").getAsString());
JsonArray required,optional;
JsonObject answers=jsonObject.get("answers").getAsJsonObject();
required=answers.get("required").getAsJsonArray();
optional=answers.get("optional").getAsJsonArray();
newJson.add("required",required);
newJson.add("optional",optional);
MemberDAO memberDAO;
try
{
memberDAO=new MemberDAO();
memberDAO.addSlam(memberCode,friendCode,newJson.toString());
}catch(DAOException dao)
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","false");
responseJson.addProperty("exception",dao.getMessage());
pw=response.getWriter();
pw.print(responseJson);   
return;
}
if(image_in_file.trim().equals("") || image_in_file.trim().equals("\n"))
{
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
pw=response.getWriter();
pw.print(responseJson);   
}
else
{
int i = image_in_file.indexOf(":");
if (i > 0) 
{
fileTypeandURL = image_in_file.substring(i + 1);
}
int j = fileTypeandURL.indexOf(";");
if (j > 0) 
{
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
String name=member.getCode()+"-"+friendCode;
FileOutputStream imageOutFile = new FileOutputStream("C:\\tomcat9\\webapps\\slamBook\\WEB-INF\\slamPictures\\" + name + "." + extension);
imageOutFile.write(imageByteArray);
imageOutFile.close();
CompressImage compressImage;
if(extension.toUpperCase().equals("JPG"))
{
compressImage=new CompressImage();
compressImage.compress(name+"."+extension);
}
JsonObject responseJson=new JsonObject();
responseJson.addProperty("success","true");
pw=response.getWriter();
pw.print(responseJson);       
}  
}catch(Exception e)
{
e.printStackTrace();
}
}

    public static byte[] decodeImage(String imageDataString) 
    {
        return Base64.decodeBase64(imageDataString);
    }
}
