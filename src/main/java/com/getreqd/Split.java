package com.getreqd;

import java.io.File;

public class Split {

	public static void splitISO(File inputFile, String outputPath, String size) {

		FileSplitter.split(inputFile, outputPath, Long.parseLong(size)); 
		
	}

}
