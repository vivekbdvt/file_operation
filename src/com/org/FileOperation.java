package com.org;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperation {

	public static void main(String[] args) {
		FileInputStream fstream = null;
		
		List<String> lineStrings = new ArrayList<String>();
		System.out.println("Commands : ");
		System.out.println("list");
		System.out.println("del n : del <line number> ");
		System.out.println("ins n : ins <line number>");
		System.out.println("save");
		System.out.println("quit");
		try {
			fstream = new FileInputStream("temp.txt");
			fileOperation(lineStrings, fstream);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fileOperation(List<String> lineStrings, FileInputStream fstream) throws FileNotFoundException, IOException {
		
		DataInputStream in = null;
		
		Scanner scanner = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Select the command");
		
		String command = scanner.nextLine();  // Read user input
		String cmnd = command.split(" ")[0];
		
		switch (cmnd) {
		case "list":
			lineStrings = new ArrayList<String>();
			
			String strLine;
			fstream.getChannel().position(0);
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
				lineStrings.add(strLine);
			}
			int index = 1;
			for (String string : lineStrings) {
				System.out.println(index++ +":" +string);
			}
			fileOperation(lineStrings, fstream);
			break;
		case "del":
			lineStrings.remove(Integer.parseInt(command.split(" ")[1])-1);
			fileOperation(lineStrings, fstream);
			break;
		case "ins":
			String lineString = scanner.nextLine();  // Read user input
			lineStrings.add(Integer.parseInt(command.split(" ")[1])-1, lineString);
			fileOperation(lineStrings, fstream);
			break;
		case "save":
			FileWriter fileWriter = new FileWriter("temp.txt");
			for (String str : lineStrings) {
			    fileWriter.write(str + System.lineSeparator());
			}
			fileWriter.close();
			fileOperation(lineStrings, fstream);
			break;
		case "quit":
			scanner.close();
			break;
		default:
			break;
		}
	}
}
