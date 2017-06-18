package de.hsMannheim.ss17.tpe.martinDavid.uebung5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.Dialogs;

public class CountWords {
	
	public static void main(String params[]) throws IOException {
		File fileToCount = Dialogs.chooseFile();
		
		CountWords countWords = new CountWords();
		
		long startTime = System.currentTimeMillis();
		
		Map<String, Integer> map = countWords.countWords(fileToCount);
		
		long endTime = System.currentTimeMillis();
		
		//print results
		List<Map.Entry<String, Integer>> list = new LinkedList<>( map.entrySet() );
		for(int i = 0; i < 100 && i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("Time needed: " + (endTime - startTime));
	}
	
	private static final String[] symbolsToRemove = {"!","\"", "$", "%", "&", "/", "\\(", "\\)", "=", 
			"\\?", ",", "\\.", ";", ":", "\\\'", "´", "`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
	
	/**
	 * counts all words and sorts them by value
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Map<String, Integer> countWords(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while((line = in.readLine()) != null) {
			stringBuilder.append(line);
		}
		
		in.close();
		
		System.out.println("String builded");
		
		return countWords(stringBuilder.toString());	
	}
	
	/**
	 * counts all words and sorts them by value
	 * @param originalString 
	 * @return map with the word as key and the count of the word in the string as value
	 */
	public Map<String, Integer> countWords(String originalString) {
		String string = originalString.toLowerCase();
		string = removeSymbols(string);
		List<String> splittedSpace = Arrays.asList(string.split(" "));
		removeSpaces(splittedSpace);
		
		System.out.println("Removed all symobls and spaces");
		
		Map<String, Integer> words = new HashMap<String, Integer>();
		String word;
		int count;
		for(int i = 0; i < splittedSpace.size(); i++) {
			word = splittedSpace.get(i);
			
			if(!words.containsKey(word)) {
				count = Collections.frequency(splittedSpace, word);
				words.put(word, count);
			}
			
			if(i % 1000 == 0) {
				System.out.println(((float) i / (float) splittedSpace.size()) * 100 + " Prozent (" + i + " von " + splittedSpace.size() + ")");
			}
		}
		
		System.out.println("Words found - now sort");
		
		return sortMapByValue(words);	
	}
	
	/**
	 * removes all symbols (which are defined in symbolsToRemove)
	 * @param original
	 * @return string without symbols
	 */
	private String removeSymbols(String original) {
		for(String symbol : symbolsToRemove) {
			original = original.replaceAll(symbol, "");
		}
		
		return original;
	}
	
	/**
	 * Removes all spaces at the end or beginning of all words inthe list
	 * @param list
	 */
	private void removeSpaces(List<String> list) {
		for(int i = 0; i < list.size(); i++) {
			list.set(0, list.get(0).trim());
		}
	}
	
	private Map<String, Integer> sortMapByValue(Map<String, Integer> toSort) {
		
		List<Map.Entry<String, Integer>> list =
		        new LinkedList<>( toSort.entrySet() );
		    Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
		    {

		    	//only the entrys should be compared to each other
		    	@Override
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return o1.getValue().compareTo( o2.getValue() );
				}
		        
		    });

		    Collections.reverse(list);
		    
		    Map<String, Integer> result = new LinkedHashMap<>();
		    for (Map.Entry<String, Integer> entry : list)
		    {
		        result.put( entry.getKey(), entry.getValue() );
		    }
		    return result;
	}
}
