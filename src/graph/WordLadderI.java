package graph;

import java.util.ArrayList;

//https://www.interviewbit.com/problems/word-ladder-i/
public class WordLadderI {
	public static void main(String [] args) {
		ArrayList<String> dict = new ArrayList<String>();
		dict.add("hot"); dict.add("dot"); dict.add("dog"); dict.add("lot"); dict.add("log");
		
		System.out.println(ladderLength("hit", "cog" , dict)); // expected : 5
		
		/*Dictionary = {POON, PLEE, SAME, POIE, PLEA, PLIE, POIN}
        start = TOON
        target = PLEA*/
		
		ArrayList<String> dict2 = new ArrayList<String>();
		dict2.add("POON"); dict2.add("PLEE"); dict2.add("SAME"); dict2.add("POIE"); dict2.add("PLEA");
		dict2.add("PLIE"); dict2.add("POIN");
		
		System.out.println(ladderLength("TOON", "PLEA" , dict2)); // expected : 7
	}
	
	// Code ran in one go ... 
	public static int ladderLength(String start, String end, ArrayList<String> dictV) {
		if(start.length() != end.length()) {
			return 0;
		}
		
		int minTransFormations = Integer.MAX_VALUE;
		boolean [] visited = new boolean[dictV.size()];
		
		ArrayList<String> lst = getAdjecentWords(start, dictV, visited);
		
		for(int i=0; i < lst.size(); i++)  {
			String str = lst.get(i);
			visited[getIndex(str, dictV)] = true;
			
			if(isCharDiffOne(str, end)) {
				return 3;
			} else {
				int returnValue = ladderLength2(str, end, dictV, visited, 3);
				if(returnValue !=0 && returnValue < minTransFormations) {
					minTransFormations = returnValue;
				}
			}
		}
		
		return minTransFormations == Integer.MAX_VALUE ? 0 : minTransFormations;
    }

	private static int ladderLength2(String start, String end, ArrayList<String> dictV,  boolean[] visited,
			int noOftranformations) {
		int minTransFormations = Integer.MAX_VALUE;
		ArrayList<String> lst = getAdjecentWords(start, dictV, visited);
		
		for(int i=0; i < lst.size(); i++)  {
			String str = lst.get(i);
			visited[getIndex(str, dictV)] = true;
			if(isCharDiffOne(str, end)) {
				return noOftranformations +1;
			} else {
				int returnValue = ladderLength2(str, end, dictV, visited, noOftranformations +1);
				if(returnValue !=0 && returnValue < minTransFormations) {
					minTransFormations = returnValue;
				}
			}
			
			visited[getIndex(str, dictV)] = false;
		}
		
		return minTransFormations == Integer.MAX_VALUE ? 0 : minTransFormations;
	}

	private static int getIndex(String str, ArrayList<String> dictV) {
		for(int i=0; i< dictV.size(); i++) {
			if(str.equals(dictV.get(i))) {
				return i;
			}
		}
	
		return -1;
	}

	private static boolean isCharDiffOne(String str1, String str2) {
		int diff = 0;
		
		for(int i=0; i< str1.length(); i++) {
			if(!(str1.charAt(i) == str2.charAt(i))) {
				diff++;
			}
		}
		
		return diff == 1 ? true : false;
	}

	private static ArrayList<String> getAdjecentWords(String start, ArrayList<String> dictV, boolean[] visited) {
		ArrayList<String> output = new ArrayList<String>();
		
		for(int i=0; i< dictV.size(); i++) {
			if(!visited[i] && isCharDiffOne(start, dictV.get(i))) {
				output.add(dictV.get(i));
			}
		}
		
		return output;
	}
}
