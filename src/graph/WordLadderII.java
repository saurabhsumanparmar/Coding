package graph;


import java.util.ArrayList;

//https://www.interviewbit.com/problems/word-ladder-i/
public class WordLadderII {
	public static void main(String [] args) {
		ArrayList<String> dict = new ArrayList<String>();
		dict.add("hot"); dict.add("dot"); dict.add("dog"); dict.add("lot"); dict.add("log");
		
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>> ();
		ladderLength("hit", "cog" , dict, output);
		
		for(int i=0; i < output.size(); i++) {
			for(int j=0; j < output.get(i).size(); j++) {
				System.out.print(output.get(i).get(j) + " ");
			}
			System.out.println();
		}
		
		/* Dictionary = {POON, PLEE, SAME, POIE, PLEA, PLIE, POIN}
        start = TOON
        target = PLEA*/
		
		ArrayList<String> dict2 = new ArrayList<String>();
		dict2.add("POON"); dict2.add("PLEE"); dict2.add("SAME"); dict2.add("POIE"); dict2.add("PLEA");
		dict2.add("PLIE"); dict2.add("POIN");
		
		ArrayList<ArrayList<String>> output2 = new ArrayList<ArrayList<String>> ();
		ladderLength("TOON", "PLEA" , dict2, output2);
		
		for(int i=0; i < output2.size(); i++) {
			for(int j=0; j < output2.get(i).size(); j++) {
				System.out.print(output2.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}
	
	public static void ladderLength(String start, String end, ArrayList<String> dictV, ArrayList<ArrayList<String>> output) {
		if(start.length() != end.length()) {
			return;
		}
		
		boolean [] visited = new boolean[dictV.size()];
		
		ArrayList<String> lst = getAdjecentWords(start, dictV, visited);
		
		for(int i=0; i < lst.size(); i++)  {
			ArrayList<String> collect = new ArrayList<String>();
			int collectIndex = 0;
			
			collect.add(collectIndex, start);
			collectIndex++;
			
			String str = lst.get(i);
			visited[getIndex(str, dictV)] = true;
			
			collect.add(collectIndex, str);
			collectIndex++;
			
			if(isCharDiffOne(str, end)) {
				collect.add(collectIndex,end);
				collectIndex++;
				
				output.add(collect);
				
			} else {
				ladderLength2(str, end, dictV, visited, 3, output, collect, collectIndex);
			}
			
			visited[getIndex(str, dictV)] = false;
		}		
    }

	private static void ladderLength2(String start, String end, ArrayList<String> dictV,  boolean[] visited,
			int noOftranformations, ArrayList<ArrayList<String>> output, ArrayList<String> collect, int collectIndex) {
		ArrayList<String> lst = getAdjecentWords(start, dictV, visited);
		
		for(int i=0; i < lst.size(); i++)  {
			int ouputSize = output.size();
			int minTransforms = ouputSize > 0 ? output.get(0).size() : Integer.MAX_VALUE;
			
			if(minTransforms < noOftranformations +1) {
				return;
			}
			
			String str = lst.get(i);
			visited[getIndex(str, dictV)] = true;
			
			collect.add(collectIndex, str);
			collectIndex++;
			
			if(isCharDiffOne(str, end)) {
				if(minTransforms > noOftranformations +1) {
					removeElementsFromOutput(output);
					collect.add(collectIndex,end);
					collectIndex++;
					addElementsFromCollectToOutput(output, collectIndex, collect);
					collectIndex--;
					
					minTransforms = noOftranformations +1;
				} else if(minTransforms == noOftranformations +1) {
					collect.add(collectIndex, end);
					collectIndex++;
					addElementsFromCollectToOutput(output, collectIndex, collect);
					collectIndex--;
				}
			} else {
				ladderLength2(str, end, dictV, visited, noOftranformations+1, output, collect, collectIndex);
			}
			
			visited[getIndex(str, dictV)] = false;
			collectIndex--;
		}		
	}

	private static void addElementsFromCollectToOutput(ArrayList<ArrayList<String>> output, int collectIndex,ArrayList<String> collect) {
		ArrayList<String> lst = new ArrayList<String>();
		for(int i=0; i< collectIndex; i++) {
			lst.add(collect.get(i));
		}
		
		output.add(lst);
		
	}

	private static void removeElementsFromOutput(ArrayList<ArrayList<String>> output) {
		output = new ArrayList<ArrayList<String>> ();
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

