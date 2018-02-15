package com.getreqd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;

public class Checksum {

	public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException
	{
		//Create checksum for this file
		File file = new File("/Users/JustaViet/Desktop/Capstone/test.iso");
 
		//Use MD5 algorithm
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
 
		//Get the checksum
		String checksum = getFileChecksum(md5Digest, file);
 
		//see checksum
		System.out.println(checksum);
	}
	
	public static String getFileChecksum(MessageDigest digest, File file) throws IOException
	{
	    //Get file input stream for reading the file content
	    FileInputStream fis = new FileInputStream(file);
     
	    //Create byte array to read data in chunks
	    byte[] byteArray = new byte[1024];
	    int bytesCount = 0;
      
	    //Read file data and update in message digest
	    while ((bytesCount = fis.read(byteArray)) != -1) {
	        digest.update(byteArray, 0, bytesCount);
	    };
     
	    //close the stream; We don't need it now.
	    fis.close();
     
	    //Get the hash's bytes
	    byte[] bytes = digest.digest();
     
	    //This bytes[] has bytes in decimal format;
	    //Convert it to hexadecimal format
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
     
	    //return complete hash
	   return sb.toString();
	}
	
}

