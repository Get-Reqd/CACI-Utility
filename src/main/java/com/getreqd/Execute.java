package com.getreqd;

import java.lang.Process;
import java.lang.ProcessBuilder;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.io.File;

public class Execute {
	public Execute() {};

	public static void display(String[] cmd) {
		System.out.print("Command: ");
		for(String s : cmd) System.out.print(s + " ");
		System.out.print('\n');
	}

	public static void command(String[] arg) {
		display(arg);
		try {
			ProcessBuilder pb = new ProcessBuilder(arg);
			Process p = pb.start();
			p.waitFor();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void redirectedCommand(String[] arg, File out) {
		display(arg);
		try {
			ProcessBuilder pb = new ProcessBuilder(arg);
			pb.redirectOutput(Redirect.appendTo(out));
			Process p = pb.start();
			p.waitFor();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}