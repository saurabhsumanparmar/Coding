package arrays;

import java.util.ArrayList;
import java.util.HashMap;

public class PossibleWordsFromPhoneDigits {
	public static void main(String [] args) {
		
		HashMap<Integer, ArrayList<String>> map =  new  HashMap<Integer, ArrayList<String>>();
		ArrayList<String> lst = new ArrayList<String>();
		
		map.put(0, lst);
		map.put(1, lst);
		
		lst.add("A"); lst.add("B");lst.add("C");
		map.put(2, lst);
		
		lst = new ArrayList<String>();
		lst.add("D"); lst.add("E");lst.add("F");
		map.put(3, lst);
		
		lst = new ArrayList<String>();
		lst.add("G"); lst.add("H");lst.add("I");
		map.put(4, lst);
		
		lst = new ArrayList<String>();
		lst.add("J"); lst.add("K");lst.add("L");
		map.put(5, lst);
		
		lst = new ArrayList<String>();
		lst.add("M"); lst.add("N");lst.add("O");
		map.put(6, lst);
		
		lst = new ArrayList<String>();
		lst.add("P"); lst.add("Q");lst.add("R");lst.add("S");
		map.put(7, lst);
		
		lst = new ArrayList<String>();
		lst.add("T"); lst.add("U");lst.add("V");
		map.put(8, lst);
		
		lst = new ArrayList<String>();
		lst.add("W"); lst.add("X");lst.add("Y");lst.add("Z");
		map.put(9, lst);
		
		ArrayList<Integer> lst2 = new ArrayList<Integer>();
		lst2.add(2); lst2.add(3); lst2.add(4);
		
		recursivelyAppend(lst2, map, 0, new String[lst2.size()]);
	}
	
	public static void recursivelyAppend(ArrayList<Integer> list, HashMap<Integer, ArrayList<String>> map, int i, String [] collect) {
		if(i == list.size()) {
			int k =0;
			
			while(k < collect.length) {
				System.out.print(collect[k]);
				k++;
			}
			
			System.out.print(" ");
			return;
		}
		
		ArrayList<String> chars = map.get(list.get(i));
		
		for(int j =0; j < chars.size(); j++) {
			collect[i] = chars.get(j);
			
			recursivelyAppend(list, map, i+1, collect);
		}
		
		if(chars.size() ==0) {
			collect[i] = "";
			recursivelyAppend(list, map, i+1, collect);
		}
	}
}
