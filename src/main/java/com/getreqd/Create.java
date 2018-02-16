package com.getreqd;

import java.io.IOException;
//import java.lang.ProcessHandle;
import java.lang.Process;
import java.lang.ProcessBuilder.Redirect;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import javax.swing.filechooser.FileSystemView;

public class Create {

	/*
	 * 
	 * ISO Creation Method 1. Get the operating string definition 1. Match
	 * signatures in definition 2. Execute corresponding operating system dump
	 * method 3. Gather information on system devices (eg. disk, cd, dvd drives) 3.
	 * Run progress process after beginning media drive dump
	 * 
	 */

	public static void dumpMedia(String osType, File inputFile, String outputPath) {
		System.out.println("Ripping the image from a media drive on " + osType);

		FileSystemView fsv = FileSystemView.getFileSystemView();
		File root = new File("disk");

		/* Default Parameters */
		String hardDisk = fsv.getSystemDisplayName(root);
		System.out.println("Hard Disk: " + hardDisk);
		String device = null;
		String directory = ".";
		String name = "noname.iso";

		/* Media Drive Selector */

		/* This is the User Selected Media Drive */
		File d = inputFile;
		System.out.println("Media Drive: " + d.getName());

		/* Output File Directory Selector */

		/* This is the User Selected Output Directory */
		String path = outputPath;

		/* Update the Media Drive being Read Parameter */
		device = d.getName();

		/* User MUST select Media Drive */
		if (device != null) {
			switch (osType) {

			case "Mac OS X":
				dumpOSX(hardDisk, device, path, name);
				break;

			case "Linux":

				dumpLinux(hardDisk, device, path, name);
				break;

			case "Windows 10":

				dumpWindows(hardDisk, device, path, name);
				break;

			}
		} else {
			System.out.println("Error: Media Drive was not detected...!");
		}

	}

	/*
	 * 
	 * sudo dd if=/dev/< media drive > of=< directory >/< image name >
	 * 
	 */
	public static void dumpOSX(String hardDisk, String device, String directory, String name) {

		System.out.println("Executing: OSX RAW dumping script...");

		String in = "if=/dev/" + device;
		String out = "of=/volumes/" + hardDisk + directory + "/" + name;
		String[] command = { "dd", in, out };

		System.out.println("in: " + in);
		System.out.println("out: " + out);

		printCommands(command);

		try {

			ProcessBuilder pb = new ProcessBuilder(command);
			Process dump = pb.start();
			// long processID = dump.pid();
			// System.out.println( "Dump PID: " + processID );

			/*
			 * ProcessBuilder progress = new ProcessBuilder("kill", "-SIGINFO",
			 * Long.toString( processID ) ); progress.redirectOutput(Redirect.INHERIT);
			 * progress.redirectError(Redirect.INHERIT); Process p = progress.start();
			 */

			dump.waitFor();

			System.out.println("ISO created successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void dumpLinux(String hardDisk, String device, String directory, String name) {

		System.out.println("Executing: Linux RAW dumping script...");

		String in = "if=/dev/" + device;
		String out = "of=/volumes/" + hardDisk + directory + "/" + name;
		String[] command = { "dd", in, out };

		System.out.println("in: " + in);
		System.out.println("out: " + out);
		printCommands(command);

		try {
			Process Terminal = new ProcessBuilder(command).start();
			Terminal.waitFor();
			System.out.println("ISO created successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * Wbadmin start backup [-backupTarget:{<BackupTargetLocation> |
	 * <TargetNetworkShare>}] [-include:<ItemsToInclude>]
	 * [-nonRecurseInclude:<ItemsToInclude>] [-exclude:<ItemsToExclude>]
	 * [-nonRecurseExclude:<ItemsToExclude>] [-allCritical] [-systemState]
	 * [-noVerify] [-user:<UserName>] [-password:<Password>] [-noInheritAcl]
	 * [-vssFull | -vssCopy] [-quiet]
	 * 
	 * Example: WBadmin start backup -backupTarget:E: -include:C: -allcritical
	 * -quiet
	 * 
	 */

	public static void dumpWindows(String hardDisk, String device, String directory, String name) {
		System.out.println("Executing: Windows RAW dumping script...");
		String[] command = {};

	}

	private static void printCommands(String[] command) {
		System.out.print("Command: ");
		for (String s : command) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

	private static void printArgs(String argument[]) {
		for (int i = 0; i < argument.length; i++) {
			System.out.println("arg[" + i + "]: " + argument[i]);

		}
	}

	public static void main(String args[]) {
		if (args.length < 5 || args.length > 5) {
			System.out.println(
					"Usage: < OSX: -o, Linux: -l, Windows: -w > < Hard Disk > < Device > < Directory > < Name >");
		} else if (args.length == 5) {

			String os = args[0];
			String hd = args[1];
			String device = args[2];
			String directory = args[3];
			String name = args[4];

			printArgs(args);

			switch (os) {

			case "-o":

				dumpOSX(hd, device, directory, name);
				break;

			case "-l":

				dumpLinux(hd, device, directory, name);
				break;

			case "-w":

				dumpWindows(hd, device, directory, name);
				break;

			}
		}
	}
}