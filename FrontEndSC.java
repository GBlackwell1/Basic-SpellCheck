//Written by Gabriel Blackwell
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class FrontEndSC {
	private static ReadingAndWriting files = new ReadingAndWriting();
	private static Scanner keyboard = new Scanner(System.in);
	public static void main(String[] args) {
		ProcessAndCheck newWords = new ProcessAndCheck();
		String[] englishDictionary = files.ReadDictionary();
		System.out.println("English dictionary loaded!");
		System.out.println("Welcome to SPELL CHECK!");
		System.out.println("This is a rudementary software that will read a text file of your choosing\n"
						+ "and it will find words not listed in the english dictionary and suggest\n"
						+ "similar words for you to choose!");
		System.out.println("Please make a text file and place it within the folder \"RudementarySpellCheck\"\n"
						+ "and then please give the software the file name of the .txt file that you have created!\n"
						+ "Enter the file name now:");
		String fileName = keyboard.nextLine();
		System.out.println("Now loading text file...");
		LinkedList<String> userText = files.readTextFile(fileName);
		System.out.println("Text file loaded succesfully!");
		System.out.println("Now seeing what words need to be checked...");
		Queue<String[]> suggestions = newWords.suggestions(userText, englishDictionary);
		//TODO find a way to tell the user what word needs to be attended to
	}

}
