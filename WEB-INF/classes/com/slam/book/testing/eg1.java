import java.io.*;
import java.util.*;
public class eg1
{
public static void main(String gg[])
{
try
{
String name=gg[0];
File file=new File("c:/tomcat9/webapps/slamBook/WEB-INF/classes/com/slam/book/testing/member.txt");
if(!file.exists())
{
file.createNewFile();
}
BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
bw.write(name+",null");
bw.write("\r\n");
bw.close();
}catch(Exception e)
{
e.printStackTrace();
}
}
}