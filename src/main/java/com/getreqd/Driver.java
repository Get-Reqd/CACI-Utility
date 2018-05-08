package com.getreqd;

import org.apache.commons.io.FilenameUtils;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class Driver extends JFrame {

	// Serialization default variable
	private static final long serialVersionUID = 1L;

	// OS Detection Variable
	private static String operatingSystem = System.getProperty("os.name");

	public static void main(String[] args) {

		// Initialize the user interface
		initUI();

	}

	public final static void initUI() {

		// Define window
		JFrame window = new JFrame("CACI ISO Utility");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);

		// Define main panel area
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(5, 5));

		// Create the input file panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("Input"));
		JTextField inputFileField = new JTextField("", 25);
		JLabel inputFileLabel = new JLabel("Filename: ");
		JButton inputBrowseButton = new JButton("Browse...");

		// Create the output directory panel
		JPanel outputPanel = new JPanel();
		outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
		JTextField outputDirectoryField = new JTextField("", 25);
		JLabel outputDirectoryLabel = new JLabel("Directory: ");
		JButton outputBrowseButton = new JButton("Browse...");

		// Create the button panel
		JPanel buttonPanel = new JPanel();
		JButton buttonCreate = new JButton("Create");
		JButton buttonRename = new JButton("Split");
		JButton buttonBuild = new JButton("Build");
		JButton buttonSettings = new JButton("Settings");
		JButton buttonExit = new JButton("Exit");

		// Create slider to make variable splitting sizes
		JSlider partitionSize = new JSlider(1, 5);

		// Remove the border if it's not worth it and the space is better used for ticks
		partitionSize.setBorder(BorderFactory.createTitledBorder("Partition size (1% to 10%)"));
		partitionSize.setMajorTickSpacing(1);
		partitionSize.setPaintTicks(true);
		partitionSize.setPaintLabels(true);

		// Input browse button action listener
		inputBrowseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Create a regular file chooser
				JFileChooser fileChooser = new JFileChooser();

				// Show the file chooser
				int returnValue = fileChooser.showDialog(null, "Select");

				// Check to make sure the file selection is successful
				if (returnValue == JFileChooser.APPROVE_OPTION) {

					// Update the text box in the user interface
					inputFileField.setText(fileChooser.getSelectedFile().toString());

				}
			}
		});

		// Output browse button action listener
		outputBrowseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Create a regular file chooser
				JFileChooser fileChooser = new JFileChooser();

				// Set the file chooser to directory selection only
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				// Show the file chooser
				int returnValue = fileChooser.showDialog(null, "Select");
				if (returnValue == JFileChooser.APPROVE_OPTION) {

					// Update the text box in the user interface
					outputDirectoryField.setText(fileChooser.getSelectedFile().toString());

				}
			}
		});

		// Exit button action listener
		buttonExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Exit with no errors
				System.exit(0);

			}

		});

		// Create button action listener
		buttonCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (validateInput(window, inputFileField, outputDirectoryField)) {

					// Create variables for method call
					File inputFile = new File(inputFileField.getText());
					String outputPath = outputDirectoryField.getText();

					// Call create class
					Create.dumpMedia(operatingSystem, inputFile, outputPath); // Pass inputFile as File and outputPath as String

					// Clear UI text fields on complete
					clearTextFields(inputFileField, outputDirectoryField);

				}

			}

		});

		// Build button action listener
		buttonBuild.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (validateInput(window, inputFileField, outputDirectoryField)) {

					// Create variables for method call
					File inputFile = new File(inputFileField.getText());
					String outputPath = outputDirectoryField.getText();

					// Call assemble class
					Assemble as = new Assemble();
					try {
						as.buildISO(inputFile, outputPath);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // Pass inputFile as File and outputPath as String

					// Clear UI text fields on complete
					clearTextFields(inputFileField, outputDirectoryField);

				}

			}

		});

		// Split button action listener
		buttonRename.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (validateInput(window, inputFileField, outputDirectoryField)) {

					// Create variables for method call
					File inputFile = new File(inputFileField.getText());
					String outputPath = outputDirectoryField.getText();

					//get the size of the desired partion size from the slider
					int size = partitionSize.getValue();

					// Call split class
					Split.splitISO(inputFile, outputPath, Integer.toString(size)); // Pass inputFile as File and outputPath as String

					// Clear UI text fields on complete
					clearTextFields(inputFileField, outputDirectoryField);

				}

			}

		});

		// Add inputPanel components to inputPanel
		inputPanel.add(inputFileLabel);
		inputPanel.add(inputFileField);
		inputPanel.add(inputBrowseButton);

		// Add outputPanel components to outputPanel
		outputPanel.add(outputDirectoryLabel);
		outputPanel.add(outputDirectoryField);
		outputPanel.add(outputBrowseButton);

		// Add buttonPanel components to buttonPanel
		buttonPanel.add(buttonCreate);
		buttonPanel.add(partitionSize);
		buttonPanel.add(buttonRename);
		buttonPanel.add(buttonBuild);
		buttonPanel.add(buttonSettings);
		buttonPanel.add(buttonExit);

		// Create a panel to hold I/O panels and add I/O panels
		JPanel filePanel = new JPanel();
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
		filePanel.add(inputPanel, BorderLayout.NORTH);
		filePanel.add(outputPanel, BorderLayout.SOUTH);

		// Add sub-panels to the main panel
		mainPanel.add(filePanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel);

		// Prepare window
		window.setContentPane(mainPanel);
		window.pack();
		window.setLocationByPlatform(true);
		window.setVisible(true);

	}

	public static boolean validateInput(JFrame window, JTextField inputFileField, JTextField outputDirectoryField) {

		// Gather string from JTextField
		String input = inputFileField.getText();
		String output = outputDirectoryField.getText();

		// Immediately return false if input or output are empty
		if (input.equals("") || output.equals("")) {

			showError(window, "Either input or output has no value.");
			return false;

		}

		// Create temporary I/O file objects
		File inputFile = new File(input);
		File outputDirectory = new File(output);

		// Check to make sure the given input file exists.
		if (!inputFile.exists()) {

			// On error, write to console.
			showError(window, "Unable to identify input file: " + "\n'" + input + "'");
			return false;

		}

		// Check to make sure the output directory exists.
		if (!outputDirectory.isDirectory()) {

			// On error, write to console.
			showError(window, "Unable to identify output directory: " + "\n'" + output + "'");
			return false;

		}

		// If no problems are found, return true.
		return true;

	}

	public static void clearTextFields(JTextField inputFileField, JTextField outputDirectoryField) {

		inputFileField.setText("");
		outputDirectoryField.setText("");

	}

	public static void showError(JFrame window, String message) {

		System.err.println(message);
		JOptionPane.showMessageDialog(window, message);

	}

}
