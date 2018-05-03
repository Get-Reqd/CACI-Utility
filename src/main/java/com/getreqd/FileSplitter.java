package com.getreqd;

import com.getreqd.FileSplitter;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

public class FileSplitter {

	/**
	 * This method will split any file into chunks of your desired size and output
	 * each of the file chunks (.caci extension) to your desired directory.
	 * 
	 * @param inputFile File to be split
	 * @param outputPath Desired path for the file chunks
	 * @param size Desired size of the file chunks
	 */
	public static void split(File inputFile, String outputPath, long size) {
		
		// Create output path from input path (e.g. ~/input/example.iso to ~/output/example.iso)
		String outputFilePath = Paths.get(outputPath, inputFile.getName()).toString();
		
		System.out.println("[NOTICE] File Split Initiated");
		System.out.println("[LOG] Input File......................" + inputFile.getName());
		System.out.println("[LOG] Input File Size................." + inputFile.length());
		System.out.println("[LOG] Expected Partition Size........." + (int) Math.floor((inputFile.length()*size)/10));
		System.out.println("[LOG] Expected Number of Partitions..." + (int) Math.ceil(((double) inputFile.length()/(int) Math.floor((inputFile.length()*size)/10))));
		
		try {
			
			// Calculate the percentage based partition size
			int partitionSize = (int) Math.floor((inputFile.length()*size)/10);
			
			System.out.println("[LOG] Partition Size.................." + partitionSize);

			// Read in the input file
			InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
			int inputFileData = inputStream.read();
			
			// Create count integer to uniquely identify the .caci files.
			int count = 1;
			
			// Loop through every byte in the object
			while (inputFileData != -1) {
				
				// Reset the 
				long splitSize = 0;
				
				// Modify the output path (defined above) to have a .caci extension and a unique number
				File outputFile = new File(outputFilePath + ".caci" + count++);
				
				// Create the output stream object using the newly modified output path.
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
				
				// Write "split" files of the specified size until there is no data left to write.
				while (inputFileData != -1 && splitSize < partitionSize) {
					outputStream.write(inputFileData);
					splitSize++;
					inputFileData = inputStream.read();
				}

				outputStream.close();
				
				System.out.println("[LOG] File Created...................." + outputFile.getName());
				System.out.println("[LOG] File Size......................." + outputFile.length());
				
			}
			
			System.out.println("[LOG] Total Partitions Created........" + (count-1));
			System.out.println("[NOTICE] File Split Complete");
			
			inputStream.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}

	/**
	 * Given an input file and an output path, this method will join all of the split files
	 * located in the selected input file's directory and output the resulting file to
	 * the given output path.
	 * 
	 * @param inputFile One of any of the .caci split files
	 * @param outputPath Desired output for the joined file
	 */
	public static void join(File inputFile, String outputPath) {

		try {
			
			System.out.println("[NOTICE] File Join Initiated");
			
			// Create outputFile and get output file name. If output file does not already exist, create an empty file.
			String outputFileName = inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.'));
			File outputFile = new File(Paths.get(outputPath, outputFileName).toString());
			outputFile.createNewFile();

			// Create an output stream to output the merged file to the desired output path.
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
			
			// Create count integer to uniquely identify the .caci files.
			int count = 1;
			
			// Create loop to iterate through each .caci file
			while (true) {
				
				// Create expected path for a .caci file
				File splitFile = new File(inputFile.getAbsolutePath().substring(0, inputFile.getAbsolutePath().lastIndexOf('.')) + ".caci" + count++);
				
				// Detect if the expected path exists
				if (splitFile.exists()) {
					
					System.out.println("[LOG] Input File......................" + splitFile.getName());
					System.out.println("[LOG] Input File Size................." + splitFile.length());
					
					// Read in the .caci file
					InputStream inputStream = new BufferedInputStream(new FileInputStream(splitFile));
					int data = inputStream.read();
					
					// Loop through every byte in the .caci file
					while (data != -1) {

						// Merge data into the output file
						outputStream.write(data);
						
						// Load next bit of information
						data = inputStream.read();
						
					}
					
					inputStream.close();
					
				} else {
					
					break;
					
				}
				
			}
			
			outputStream.close();
			
			System.out.println("[LOG] Merged File Size................" + outputFile.length());
			System.out.println("[LOG] Total Partitions Found.........." + (count-2));
			System.out.println("[NOTICE] File Join Completed");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}