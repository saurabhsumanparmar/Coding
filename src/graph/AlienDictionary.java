package graph;

import java.util.HashMap;


// Need to run this
public class AlienDictionary {
	public char[] findOrderOfCharacters(String [] words) {
		char [] output = {};
		
		int countOfChars = 0;
		HashMap<Character, Integer> charIndex = new HashMap<Character, Integer> ();
		HashMap<Integer, Integer> edge = new HashMap<Integer, Integer>();
		
		for(int i=0; i< words.length;) {
			int j =i;
			while(words[j].charAt(0) == words[i].charAt(0) && j < words.length) {
				j++;
			}
			
			if(i != j-1) {
				int k =i;
				int maxWordLength = words[i].length();
				int charLocation = 0;
				while(charLocation < maxWordLength) {
					k = k+1;
					while( k < j) {
						if(words[k].length() > maxWordLength) {
							maxWordLength = words[k].length();
						}
						
						// To handle variable size words ..refactor this !!!!
						if(words[k-1].length() <= charLocation  && words[k].length() > charLocation) {
							k++;
							continue;
						} else if(words[k-1].length() > charLocation  && words[k].length() <= charLocation) {
							k++;
							continue;
						} else if(words[k-1].length() <= charLocation  && words[k].length() <= charLocation) {
							k++;
							continue;
						}
						
						if(words[k-1].charAt(charLocation) == words[k].charAt(charLocation)) {
							k++;
						} else {
							char char1 = words[k-1].charAt(charLocation);
							char char2 = words[k].charAt(charLocation);
							
							if(charIndex.get(char1) == null) {
								charIndex.put(char1, countOfChars);
								countOfChars++;
							}
							
							if(charIndex.get(char2) == null) {
								charIndex.put(char2, countOfChars);
								countOfChars++;
							}
							
							edge.put(charIndex.get(char1), charIndex.get(char2));
							
							k++;
						}
					}
					charLocation++;
				}
			} else {
				j++;
				if(j < words.length) {
					char char1 = words[i].charAt(0);
					char char2 = words[j].charAt(0);
					
					if(charIndex.get(char1) == null) {
						charIndex.put(char1, countOfChars);
						countOfChars++;
					}
					
					if(charIndex.get(char2) == null) {
						charIndex.put(char2, countOfChars);
						countOfChars++;
					}
					
					edge.put(charIndex.get(char1), charIndex.get(char2));
				}
				
				i=j;
			}	
		}
		
		return output;
	}
}
