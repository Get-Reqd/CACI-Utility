package com.getreqd;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class Log {

	public Log() {};

	//	Creates text log file from a string
	public static void createLog( String fileName ) {

		try {
	    	File file = new File( fileName );
	        file.createNewFile( );
	    	FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
	    	BufferedWriter bw = new BufferedWriter( fw );
	    	bw.close();
	    	fw.close();
	    	System.out.println( "Created " + fileName + " successfully..." ); //For testing 
	 	}
	 	catch( IOException e ) {
	 	  System.out.println("Error: " + e);
	 	  e.printStackTrace();
	 	}

	}
	
	//	Reads string from the log file
	public static String readLog( String fileName ) {

		String line = null;
		try {
			FileReader fr = new FileReader( fileName );
			BufferedReader br = new BufferedReader( fr );
			line = br.readLine();
			br.close();
			fr.close();
			System.out.println( "READ: " + line );
		}
		catch( FileNotFoundException ex ) {
			System.out.println( "Unable to open file '" + fileName + "'" );
			ex.printStackTrace();                
		}
		catch( IOException ex ) {
		    System.out.println( "Error reading file '" + fileName + "'" );                  
			ex.printStackTrace();
		}

		return line;

	}

	//	Write a string to an existing log file
	//	The parameters are the string to be written
	//  Parameter String file is the absolute path to the file
	public static void writeToLog(	String s, String file ) {

		try {
			File f = new File( file );
			FileWriter fw = new FileWriter( f.getAbsoluteFile() );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write( s );
			bw.close();
			fw.close();
			System.out.println( "Writing " + s +  " to " + file + " was successful " );
		}
		catch ( IOException e ) {
			System.out.println( "Error: " + e );
			e.printStackTrace();
		}

	}
}
