package com.getreqd;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

public class Log {

	public Log() {};

	//	Creates text log file from a string
	public static File createLog( String fileName ) {

		File file = null;
		try {
			file = new File( fileName );
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

		return file;

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

	public static void makeHashes(String folderName, String partitionName) throws IOException, NoSuchAlgorithmException
	{
		//gets a list of all the files in the directory the partitions were split to.
		File[] partitions = new File(Paths.get(folderName).toString()).listFiles(); //folderName
		
		System.out.println("PartitionName: " + partitionName);

		//creates the log file in that directory with all the partitions
		File logFile = createLog(Paths.get(folderName, "logFile.txt").toString()); // "/logFile"

		//makes the writers for inserting the hashes
		FileWriter fw = new FileWriter( logFile.getAbsoluteFile() );
		BufferedWriter bw = new BufferedWriter( fw );

		//for each of the files in the folder, make the hash
		for (File partion : partitions)
		{
			System.out.println("Partitions: " + partion.toString());
			{
				//if the file is one of the files we want //hard coded DEMO
				if(partion.toString().contains(partitionName))
				{
					MessageDigest hashAlgo = MessageDigest.getInstance("MD5");
					String individualHash = Checksum.getFileChecksum(hashAlgo, partion);
					System.out.println("Hash: " + individualHash);
					bw.write(individualHash + "\n");
				}
			}
		}

		bw.close();
		fw.close();
	}

	public static String checkHashes(String fileName, String partitionName) throws IOException, NoSuchAlgorithmException
	{
		String failures = "";
		//gets a list of all the files in the directory the partitions were split to.
		File[] partitions = new File(Paths.get(fileName).toString()).listFiles(); //fileName

		
		String line = null;
		try {
			FileReader fr = new FileReader(Paths.get(fileName, "logFile.txt").toString()); // "/logFile"
			BufferedReader br = new BufferedReader( fr );
			
			for (File partition : partitions)
			{
				//if its one of the split files
				if (partition.toString().contains(partitionName))
				{
					line = br.readLine();
					//if the checksums dont match up, then add it to the error list
					if (!Checksum.getFileChecksum(MessageDigest.getInstance("MD5"), partition).equals(line))
					{
						failures += (partition.getPath() + " didn't match\n");
					}
				}
			}

			br.close();
			fr.close();
		}
		catch( FileNotFoundException ex ) {
			System.out.println( "Unable to open file '" + fileName + "'" );
			ex.printStackTrace();                
		}
		catch( IOException ex ) {
			System.out.println( "Error reading file '" + fileName + "'" );                  
			ex.printStackTrace();
		}

		return failures;
	}

}
