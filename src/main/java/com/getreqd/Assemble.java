package com.getreqd;

import java.io.File;

public class Assemble {

	public static void buildISO(File inputFile, String outputPath) {
		
		FileSplitter.join(inputFile, outputPath);
		
		// Meaningless comment for testing purposes. You can delete this.

	}

}
