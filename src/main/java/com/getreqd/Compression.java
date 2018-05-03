package com.getreqd;
import java.io.IOException;

//Compression/Decompression class
//Can be run along all Operating Systems with this input
public class Compression {

	/**
	 * No output directory chosen, therefore placed into the working directory.
	 * @param file - File that is being compressed
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 
	 */
	public static void compress(String file) throws IOException, InterruptedException {
		System.out.println("Zipping File: " + file + "\n to location: Working Directory");
		String[] command = { "zip","-j",file};
		Process terminal = new ProcessBuilder(command).start();
		terminal.waitFor();
		System.out.println("Completed Compression!");
	}

	/**
	 * Output directory is chosen, therefore file will be compressed to specified directory. **DO NOT USE ON ORIGINAL FILE**
	 * Will delete the non-zipped file afterwards
	 * 
	 * @param zipName - Name of the newly compressed file, including path before it
	 * @param file - File to be compressed
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	public static void compress(String zipName, String file) throws IOException, InterruptedException {
		String operatingSystem = System.getProperty("os.name");
		//May want to just keep it regular zip..
		//Example: If Mac User compresses the file as a tar.gz and sends it. 
		//				A Windows user can retrieve the file and not be able to uncompress it.

		System.out.println("Zipping File: " + file + "\n to location: "+ zipName);
		String[] command = { "zip", "-j",zipName, file};
		Process terminal = new ProcessBuilder(command).start();
		terminal.waitFor();
		System.out.println("Completed Compression!");

		//Removing the old uncompressed files
		String[] commandRemove = {"rm",file};
		terminal = new ProcessBuilder(commandRemove).start();
		terminal.waitFor();
		System.out.println("Removed old file. Replaced with compressed file.");



		/**
		switch(operatingSystem){
			case "Mac OS X" :
				//tar -zcvf archive_name.tar.gz folder_to_compress
				System.out.println("Compressing File: "+ file + " to location: "+ outputLocation);
				String[] command1 = { "tar","-zcvf", outputLocation, file};
				Process terminal1 = new ProcessBuilder(command1).start();
				terminal1.waitFor();
			case "Linux":
				//tar -zcvf archive_name.tar.gz folder_to_compress
				System.out.println("Compressing File: "+ file + " to location: "+ outputLocation);
				String[] command2 = { "tar","-zcvf", outputLocation, file};
				Process terminal2 = new ProcessBuilder(command2).start();
				terminal2.waitFor();
			case "Windows":	
				//zip -r archive_name.zip folder_to_compress
				System.out.println("Zipping File: " + file + " to location: "+ outputLocation);
				String[] command3 = { "zip","-r", outputLocation, file};
				Process terminal3 = new ProcessBuilder(command3).start();
				terminal3.waitFor();
		}
		 **/

	}
	/**
	 * Output directory chosen, therefore file will be decompressed into specified directory.
	 * 
	 * @param file
	 * @param destination
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	
	public static void decompress(String file, String destination) throws IOException, InterruptedException {
		//unzip archive_name.zip
		System.out.println("Unzipping File: " + file);
		//Using -qq to remove output given from terminal/cmd
		//Using -d to specify a destination folder
		String[] command = { "unzip","-qq", file,"-d", destination};
		Process terminal = new ProcessBuilder(command).start();
		
		//terminal.waitFor();

		/*//Removing the compressed files
		String[] commandRemove = {"rm",file};
		terminal = new ProcessBuilder(commandRemove).start();
		terminal.waitFor();
		System.out.println("Removed old file. Replaced with compressed file.");*/


	}

	/**
	 * 
	 * @param file
	 * @throws InterruptedException
	 * @throws IOException
	 * 
	 * Output directory not chosen, therefore file will be decompressed into specified directory.
	 */
	public static void decompress(String file) throws InterruptedException, IOException {
		System.out.println("Unzipping File: " + file);
		//Using -j to remove the archived directory from being replicated
		String[] command = { "unzip","-j", file};
		Process terminal = new ProcessBuilder(command).start();
		terminal.waitFor();
		System.out.println("Completed Decompression!");
	}
}
