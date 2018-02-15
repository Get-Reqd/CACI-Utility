package com.getreqd;

import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

public class DeviceLabel {
	private DeviceLabel() {};

	public static void main(String[] args) {
		get();
	}

	/* Creates a txt file with output of diskutil list */
	public static void get() {
		String[] cmd = {"diskutil", "list"};
		File info = new File("DeviceInfo.txt");
		Execute e = new Execute();
		e.redirectedCommand(cmd, info);
		findDisk(info);
		String[] clean = {"rm",info.getName()};
		e.command(clean);
	}

	/* Returns the device ID from the list of system devices 
	which match the input string "name" */
	public static String findDisk(File file) {
		String ID = null;
		String line;
		String patternString1 = "(disk)(.+?)";

        Pattern pattern = Pattern.compile(patternString1);
		
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	/*
				Matcher matcher = pattern.matcher(line);
				if(matcher.find())
					System.out.println("Found: " + matcher.group(1) + matcher.group(2));
				*/

				if(line.contains("dev")) {
					line.substring(line.indexOf('/')+1, line.lastIndexOf('('));//needed .trim
					System.out.println("Found: " + line + '\n');
				}
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                file.getName() + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + file.getName() + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		return ID;
	}


}
