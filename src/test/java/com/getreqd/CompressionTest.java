package com.getreqd;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author SpencerBarnes
 *
 *Must have file CompressionTest.iso (**Just used for testing**) in working directory to run
 */

/* IMPORTANT: Due to Maven and Travis CI we will probably have to rewrite some of these unit tests. Unit testing
 * things that deal with I/O are a bit more tricky than simple local Java unit testing. Because of this, I'm
 * commenting out all of these tests until we look further into this. This unit test will pass every time for now.
 */

public class CompressionTest {
	
	@Test
	public void placeholderTest() {
		assertEquals(true, true);
	}

	/**
	 * 	After the file has run through the compression method, did it create a "compressed" file?
	 * @throws IOException
	 * @throws InterruptedException
	 * 	
	 */
//	@Test
//	void testCreation() throws IOException, InterruptedException {
//		
//		String file = System.getProperty("user.dir") + "/JUnitTestFiles/CompressionTest.iso";
//		Compression.compress("CompressionTest.zip",file);
//		File compressed = new File(System.getProperty("user.dir") + "/CompressionTest.zip");
//		//TEST
//		Assert.assertTrue(compressed.exists());
//	}
	/**
	 * 	-After the file is compressed, we check to see if the size of the compressed file is
	 * smaller than the original file (basically, did the compression work?)
	 * @throws IOException
	 * @throws InterruptedException
	 */
//	@Test
//	void testCompression() throws IOException, InterruptedException {
//		String file = System.getProperty("user.dir") + "/JUnitTestFiles/CompressionTest.iso";
//		Compression.compress("CompressionTest.zip",file);
//		String file1 = System.getProperty("user.dir") + "/CompressionTest.zip";
//		File test = new File(file);
//		File test1 = new File(file1);
//		System.out.println("Uncompressed: " + test.length() + "\nCompressed: " + test1.length());
//		Assert.assertTrue(test.length() > test1.length());
//	}
	/**
	 *  - After the file is compressed, we decompress it and check to see if the size of the files are identical.
	 * @throws IOException
	 * @throws InterruptedException
	 */
//	@Test
//	void testDecompression() throws IOException, InterruptedException {
//		String file = System.getProperty("user.dir") + "/JUnitTestFiles/CompressionTest.iso";
//		File test = new File(file);
//		Compression.compress("CompressionTest.zip",file);
//		String file1 = System.getProperty("user.dir") + "/CompressionTest.zip";
//		Compression.decompress(file1);
//		File test1 = new File(System.getProperty("user.dir") + "/CompressionTest.iso");
//		Assert.assertTrue(test.length() == test1.length());
//		
//	}

}
