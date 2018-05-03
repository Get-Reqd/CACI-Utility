package com.getreqd;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileSplitterTest {
	
	@Rule
	public TemporaryFolder tempDirectory = new TemporaryFolder();

	@Test
	public void testSplit() throws InterruptedException {
		
		try {
			
			// Create test data and store information on the test data
			Random random = new Random();
			int randomInputSize = random.nextInt(10000)+1;
			String inputString = "";
			for(int i = 0; i < randomInputSize; i++) inputString += "#";
			byte[] inputData = inputString.getBytes();
			int inputDataSize = inputData.length;
			int randomPartitionSize = random.nextInt(5)+1;
			int expectedPartitions = (int) Math.ceil(((double) inputDataSize/(int) Math.floor((inputDataSize*randomPartitionSize)/10)));
			
			// Create temporary input file and output directory
			File splitOutput = tempDirectory.newFolder("splitOutput");
			File joinOutput = tempDirectory.newFolder("joinOutput");
			File inputFile = tempDirectory.newFile();
			
			// Add data to temporary input file
			OutputStream outputStream = Files.newOutputStream(inputFile.toPath());
			outputStream.write(inputData);
			outputStream.close();
			
			// Split the file
			FileSplitter.split(inputFile, splitOutput.getAbsolutePath(), randomPartitionSize);
			
			// Create variables for testing purposes
			int totalPartitionSize = 0;
			int totalPartitions = 0;
			File firstPartition = splitOutput.listFiles()[0];
			
			for(int i = 0; i < splitOutput.listFiles().length; i++) {
				
				// Collect the total size of all the partitions
				totalPartitionSize += splitOutput.listFiles()[i].length();
				
				// Collect the number of partitions that exist
				totalPartitions = i + 1;
				
			}
			
			// Test to make sure the partitions split accurately
			assertEquals(totalPartitionSize, inputDataSize);
			assertEquals(totalPartitions, expectedPartitions);
			
			// Join the partitions
			FileSplitter.join(firstPartition, joinOutput.getAbsolutePath());
			
			// Test to make sure that the merged output file is the same as the input file
			File joinOutputFile = joinOutput.listFiles()[0];
			
			// Size
			assertEquals(joinOutputFile.length(), inputFile.length());
			
			// Content
			String outputContent = "";
			Scanner scanner = new Scanner(joinOutputFile);
			while(scanner.hasNext()) {
				outputContent += scanner.next();
			}
			scanner.close();
			assertEquals(outputContent, inputString);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
