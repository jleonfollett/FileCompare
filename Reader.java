package john;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Reader {
	private BufferedReader br;
	private FileReader fr;
	private ArrayList<String> currentFile = new ArrayList<String>();
	private ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
	

	public Reader() throws IOException, ParseException{
		
		  File dir = new File("C:\\FileComp");
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	fr = new FileReader(child);
		    	br = new BufferedReader(fr);
		       	ReadLines(br);
		    }
		  } else {
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }
		boolean equal = CheckLines(lists.get(0), lists.get(1));
		if(equal){
			System.out.println("The files are the same! :)");
		} else {
			if(lists.get(0).size() == lists.get(1).size()){
				System.out.println("The files have different characters");
			} else {
				System.out.println("The files have different lines");
			}
		}
	}
	
	private void ReadLines(BufferedReader file1) throws IOException{
		// Reads lines from files and add them to Line Lists
		currentFile = new ArrayList<String>();
		String line;
		while((line = file1.readLine()) != null){
			currentFile.add(line);
		}
		for(String lines: currentFile){
			System.out.println(lines);
		}
		lists.add(currentFile);
		
		
	}
	
	private boolean CheckLines(ArrayList<String> list1, ArrayList<String> list2){
		for(int x = 0; x < list1.size(); x++)
		if(list1.size() == list2.size()){
			if(list1.get(x).equals(list2.get(x))){
				System.out.println("Lines are equal");
			} else {
				System.out.println("Lines are not equal");
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
}
