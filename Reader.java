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
	private ArrayList<String> listOfFileNames = new ArrayList<String>();
	private ArrayList<ArrayList<String>> listOfFiles = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> matchedFileNames = new ArrayList<ArrayList<String>>();
	

	public Reader() throws IOException, ParseException{
		
		  File dir = new File("C:\\FileComp");
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	listOfFileNames.add(child.getName());
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
		CompareArrayListValues(listOfFiles);
		System.out.println(matchedFileNames);
		}
	
	private void CompareArrayListValues(ArrayList<ArrayList<String>> listOfFiles){
		for(int x = 0; x <= (listOfFiles.size() - 1) && listOfFiles.size() > 0; x = 0){
			ArrayList<String> matchedFiles = new ArrayList<String>();
			matchedFiles.add(listOfFileNames.get(x));
			if(x != listOfFiles.size()){
			for(int y = x + 1; y < listOfFiles.size() && listOfFiles.size() > 0; y++){ //Easier way?
				boolean equal = CheckLines(listOfFiles.get(x), listOfFiles.get(y));
				if(equal){
					matchedFiles.add(listOfFileNames.get(y));
					listOfFiles.remove(y);
					listOfFileNames.remove(y);
					y=x;
				}
			}
			}
			listOfFiles.remove(x);
			listOfFileNames.remove(x);
			matchedFileNames.add(matchedFiles);
		}
	}
	
	private void PrintOutput(boolean equal, ArrayList<String> file1Contents, ArrayList<String> file2Contents){
		if(equal){
			System.out.println("The files are the same! :)");
		} else {
			if(file1Contents.size() == file2Contents.size()){
				System.out.println("The files have different characters");
			} else {
				System.out.println("The files have different lines");
			}
		}
	}
	private void ReadLines(BufferedReader file1) throws IOException{
		// Reads lines from files and add them to Line Lists
		ArrayList<String >currentFile = new ArrayList<String>();
		String line;
		while((line = file1.readLine()) != null){
			currentFile.add(line);
		}
		for(String lines: currentFile){
			System.out.println(lines);
		}
		listOfFiles.add(currentFile);
		
		
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
