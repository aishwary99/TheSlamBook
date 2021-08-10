package com.slam.book.util;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
public class CompressImage 
{
public void compress(String fileName) throws IOException
{
String path="C:\\tomcat9\\webapps\\slamBook\\WEB-INF\\slamPictures\\";
File input = new File(path+""+fileName);
BufferedImage image = ImageIO.read(input);
input.delete();
File compressedImageFile = new File(path+"compressedImages\\"+fileName);
OutputStream os =new FileOutputStream(compressedImageFile);

Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
ImageWriter writer = (ImageWriter) writers.next();

ImageOutputStream ios = ImageIO.createImageOutputStream(os);
writer.setOutput(ios);

ImageWriteParam param = writer.getDefaultWriteParam();
      
param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
param.setCompressionQuality(0.05f);
writer.write(null, new IIOImage(image, null, null), param);
      
os.close();
ios.close();
writer.dispose();
}
}