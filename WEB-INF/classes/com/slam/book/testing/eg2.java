import java.io.*;
import java.util.*;
public class eg2
{
public static void main(String gg[])
{
String line=null;
String name=gg[0];
String imageName=gg[1];
String nameFromFile="";
try
{
BufferedReader br=new BufferedReader(new FileReader("member.txt"));
BufferedWriter bw=new BufferedWriter(new FileWriter("temp.txt",true));
while((line=br.readLine())!=null)
{
nameFromFile=line.substring(0,line.indexOf(','));
if(nameFromFile.equals(name))
{
bw.write(name+","+imageName);
bw.write("\r\n");
continue;
}
bw.write(line);
bw.write("\r\n");
}
bw.close();
bw=new BufferedWriter(new FileWriter("member.txt"));
bw.write("");
bw.close();

br=new BufferedReader(new FileReader("temp.txt"));
bw=new BufferedWriter(new FileWriter("member.txt"));
while((line=br.readLine())!=null)
{
bw.write(line);
bw.write("\r\n");
}
bw.close();
bw=new BufferedWriter(new FileWriter("temp.txt"));
bw.write("");
bw.close();
System.out.println("Mission complete");
}catch(Exception e)
{
e.printStackTrace();
}
}
}