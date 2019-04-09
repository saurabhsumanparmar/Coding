package strings;
import java.util.ArrayList;
import java.util.Collections;


public class UniquePermutation {
	
	public static void main(String [] args) {
		 
		 ArrayList<Integer> list = new ArrayList<Integer>();
		 //[2 1 4 3 2]
		 //10, 9, 10, 9, 10
		  list.add(10);
		  list.add(9);
		  list.add(10);
		  list.add(9);
		  list.add(10);
		 
		  UniquePermutation testClass = new UniquePermutation();
		  ArrayList<ArrayList<Integer>> output = testClass.permute(list);
		  
		  for(int i=0; i<output.size(); i++) {
			  System.out.println(output.get(i));
		  }
	 }
	
	public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
		
		if(A.size() == 0) {
			return output;
		} else if(A.size() == 1) {
			output.add(A);
			return output;
		}
		
		Collections.sort(A);
		
		output.add(A);
		
		
		for(int i=A.size()-2; i>= 0; i--) {
			for(int j=i +1; j< A.size(); j++) {
				if(A.get(j) != A.get(j-1)) {
					ArrayList<Integer> replica = (ArrayList<Integer>)A.clone();
					recursivelyAppend(A, output, replica, i, j);
				}
			}
		}
		
		return output;
    }

	private void recursivelyAppend(ArrayList<Integer> a, 
			ArrayList<ArrayList<Integer>> output, ArrayList<Integer> curArray, int mainIndex, int secIndex) {
		
		if(a.get(mainIndex) == a.get(secIndex)) {
			return;
		}
		
		shift(curArray, mainIndex, secIndex);
		output.add(curArray);
		
		mainIndex++;
		if(mainIndex >= a.size()-1) {
			return;
		}
		
		for(int i=mainIndex; i< a.size(); i++) {
			for(int j=i +1; j< a.size() && j!=i; j++) {
				if(curArray.get(j) != curArray.get(j-1)) {
					ArrayList<Integer> replica = (ArrayList<Integer>)curArray.clone();
					recursivelyAppend(curArray, output, replica, i, j);
				}
			}
		}
		
		return;
	}

	private void shift(ArrayList<Integer> curArray, int mainIndex, int secIndex) {
		int temp = curArray.get(mainIndex);
		curArray.set(mainIndex, curArray.get(secIndex));
		
		while(secIndex -1 > mainIndex) {
			curArray.set(secIndex, curArray.get(secIndex -1));
			secIndex--;
		}
		
		
		curArray.set(secIndex, temp);
	}
}
