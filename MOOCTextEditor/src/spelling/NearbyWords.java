/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<String>();
		   insertions(s, retList, wordsOnly);
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);		   
		   return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */

public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		
		for(int index = 0; index < s.length() + 1; index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				String[] strs = s.split("");
				LinkedList<String> sList = new LinkedList<String>(Arrays.asList(strs));
				sList.add(index, Character.toString((char)charCode));
				
				String newWord = listToString(sList);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(newWord) && (!wordsOnly||dict.isWord(newWord)) && !s.equals(newWord)) {
					currentList.add(newWord);
				}
			}
		}
	}
	
	private String listToString(List<String> list) {
		String ret = "";
		for(String c: list) {
			ret += c;
	
		}
		return ret;
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			
			String[] strs = s.split("");
			LinkedList<String> sList = new LinkedList<String>(Arrays.asList(strs));
			sList.remove(index);
			String newWord = listToString(sList);
			if(!currentList.contains(newWord) && 
					(!wordsOnly||dict.isWord(newWord)) &&
					!s.equals(newWord)) {
				currentList.add(newWord);
			}
			
		}
	}


	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// initial variables
		List<String> queue = new LinkedList<String>();     // String to explore
		HashSet<String> visited = new HashSet<String>();   // to avoid exploring the same  
														   // string multiple times
		List<String> retList = new LinkedList<String>();   // words to return
		 
		
		// insert first node
		queue.add(word);
					
		// TODO: Implement the remainder of this method, see assignment for algorithm
		while (!queue.isEmpty() && retList.size() < numSuggestions) {
			String curr = queue.remove(0);
			visited.add(curr);
			List<String> neighbours = distanceOne(curr, true);
			for(String neighbour : neighbours){
				if(!visited.contains(neighbour)){
					visited.add(neighbour);
					queue.add(neighbour);
					if(dict.isWord(neighbour)){
						retList.add(neighbour);
						if(retList.size() > numSuggestions){
							break;
						}
					}
				}

			}
		}		
		return retList;
	}	

   public static void main(String[] args) {
	   String word = "i";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "E:\\Java Coursera Course 4\\Main Project\\MOOCTextEditor\\data\\dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away word Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");

	   word = "tailo";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
   }

}
