import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
public class ProcessAndCheck {
	private int LENGTH = 370098;
	private int LIKENESS_LONG = 55;
	private int LIKENESS_SHORT = 45;
	public Queue<String[]> suggestions(LinkedList<String> userText, String[] englishDictionary){
		Queue<String[]> checkWord = new LinkedList<String[]>();
		//See if the word exists in the english dictionary while the file given has more words
		while(userText.size()>0) {
			int sentinel = 0;
			String currentWord = userText.remove();
			System.out.println(currentWord);
			//Look and see if word matches a word in the english dictionary
			for(int j = 0;j<englishDictionary.length;j++) {
				if(!currentWord.equals(englishDictionary[j]))
					sentinel++;
			}
			//Jump to method if you it does not match any of the words in the english dictionary, or it is a one letter word,
			//for some reason the words list has single letters, detect if it is single letter and it is not a or i then proceed as normal
			System.out.println(sentinel);
			if(sentinel == LENGTH 
					|| (currentWord.length() == 1 && (!currentWord.equals("a") && !currentWord.equals("i")))) {
				checkWord.add(suggestionsLogic(currentWord, englishDictionary));
			}
		}
		//Return final queue
		return checkWord;
	}
	//TODO words suggested logic flawed, need a way to get a ratio on how many letters are alike
	private String[] suggestionsLogic(String word, String[] englishDictionary) {
		String[] betterWords = new String[3];
		int sentinel = 0;
		if(word.length()>=5) {
			for(int i = 0;i<englishDictionary.length;i++) {
				//If the first letter matches, the words has at least 55% similarity, and we're still within the bounds of the array, add to array
				if(englishDictionary[i].charAt(0) == word.charAt(0) 
						&& sentinel < 3
						&& englishDictionary[i].length()-word.length() >= -2 
						&& englishDictionary[i].length()-word.length() <= 2
						&& compareWordLikeness(englishDictionary[i], word) >= LIKENESS_LONG) {
					//TODO remove print for debug purposes
					System.out.println(englishDictionary[i]);
					System.out.println("SUGGESTION");
					betterWords[sentinel] = englishDictionary[i];
					sentinel++;
				}
			}
		}
		//Check for smaller words, smaller ratio of 45% likeness
		else if(word.length()>1) {
			for(int i = 0;i<englishDictionary.length;i++) {
				if(englishDictionary[i].charAt(0) == word.charAt(0) 
				&& sentinel < 2
				&& (englishDictionary[i].length()-word.length() >= -1 
				&& englishDictionary[i].length()-word.length() <= 1)
				&& compareWordLikeness(englishDictionary[i], word) >= LIKENESS_SHORT) {
					System.out.println(englishDictionary[i]);
					System.out.println("SUGGESTION");
					betterWords[sentinel] = englishDictionary[i];
					sentinel++;
				}
			}
		}
		//Suggest only 2 words for 1 letter words
		else {
			if(!word.equals("a") || !word.equals("i")) {
				String[] oneLetter = new String[2];
				oneLetter[0] = "a";
				oneLetter[1] = "i";
				System.out.println("SUGGESTION FOR ONE LETTER WORKS");
				return oneLetter;
			}
		}
		//TODO this might need to be more effecient
		return betterWords;
	}
	//What percentage of the word is the same?
	private double compareWordLikeness(String dictionaryWord, String word) {
		double likeChars = 0;
		String smallerWord = null;
		//Determine which word is the smallest so you dont run into exception
		if(dictionaryWord.length() > word.length()) 
			smallerWord = word;
		else
			smallerWord = dictionaryWord;
		//Calculate percentage of word that is the same
		for(int i=0;i<smallerWord.length();i++) {
			if(dictionaryWord.charAt(i) == word.charAt(i))
				likeChars++;
		}
		double fraction = likeChars/((double) word.length());
		return fraction*100;
	}
}
