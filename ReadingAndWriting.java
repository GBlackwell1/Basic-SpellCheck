//Written by Gabriel Blackwell
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
public class ReadingAndWriting {
	private String FILE_NAME = "words_alpha.txt";
	private String DELIM = " ";
	//Special characters I am having trouble with
	private char FORWARDSLASH = '\\';
	private char PERIOD = '.';
	//Load the dictionary
	//TODO UPDATED see if work
	public String[] ReadDictionary() {
		String[] englishDictionary = new String[370098];
		try {
			int currentIndex = 0;
			Scanner fileScanner = new Scanner(new File(FILE_NAME));
			String currentString = fileScanner.nextLine();
			while(fileScanner.hasNextLine()) {
				englishDictionary[currentIndex] = currentString;
				//Add and update
				//TODO should i keep or remove, make decision when final
				//TODO only adding to first index
				System.out.println(englishDictionary[currentIndex]);
				currentIndex++;
				currentString = fileScanner.nextLine();
			}
		}
		catch(Exception e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		return englishDictionary;
	}
	//Read text file written by user
	public LinkedList<String> readTextFile(String fileName) {
		LinkedList<String> newText = new LinkedList<String>();
		try {
			//Setup
			Scanner fileScanner = new Scanner(new File(fileName));
			while(fileScanner.hasNextLine()) {
				String currentLine = fileScanner.nextLine();
				String[] currentStrings = currentLine.split(DELIM);
				for(int i = 0;i<currentStrings.length;i++) {
					//regex check for only alphabetic
					if(currentStrings[i].matches("^[a-zA-Z]*$"))
						newText.add(currentStrings[i].toLowerCase());
					//move here if there's a non alphabetical character
					else {
						//Run for-loop to find the exact non alphabetic character
						for(int j = 0;j<currentStrings[i].length();j++) {
							char currentCharacter = currentStrings[i].charAt(j);
							if(!(currentCharacter >= 'A' && currentCharacter <= 'Z')
									&& !(currentCharacter >= 'a' && currentCharacter <= 'z')) {
								//Check for problem character forward slash /
								if(currentCharacter == FORWARDSLASH) {
									String tempString = "";
									 for(int k = 0;k<currentStrings[i].length();k++) {
										 if(currentStrings[i].charAt(k) != FORWARDSLASH) 
											 tempString = tempString+Character.toString(currentStrings[i].charAt(k));
									 }
									 currentStrings[i] = tempString;
								}
								//Check for problem character period.
								else if (currentCharacter == PERIOD) {
									String tempString = "";
									 for(int k = 0;k<currentStrings[i].length();k++) {
										 if(currentStrings[i].charAt(k) != PERIOD) 
											 tempString = tempString+Character.toString(currentStrings[i].charAt(k));
									 }
									 currentStrings[i] = tempString;
								}
								else {
									//Once found split the array by the character if no problem characters
									String[] tempArray = currentStrings[i].split(Character.toString(currentCharacter));
									String tempString = "";
									//Add the strings back together which in turn removes the non-alphabetic chars
									for(int k = 0;k<tempArray.length;k++)
										tempString = tempString+tempArray[k];
									currentStrings[i] = tempString;
								}
							}
						}
						newText.add(currentStrings[i].toLowerCase());
					}
					//TODO remove later for final product, keep for now
					System.out.println(currentStrings[i].toLowerCase());
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return newText;
	}

}
