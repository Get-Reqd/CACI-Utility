package com.getreqd;

import java.io.File;
import java.io.IOException;

public class Assemble {

	public static void buildISO(File inputFile, String outputPath) throws IOException, InterruptedException {
		
		FileSplitter.join(inputFile, outputPath);
		
		// Meaningless comment for testing purposes. You can delete this.

	}

}
