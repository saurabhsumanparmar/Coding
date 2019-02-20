package com.parmar;

import java.util.ArrayList;

public class StockBuyAndSell {
	
		/*Input:
		2
		7
		100 180 260 310 40 535 695
		10
		23 13 25 29 33 19 34 45 65 67

		Output:
		(0 3) (4 6)
		(1 4) (5 9)*/
	
	public static void main(String [] args) {
		StockBuyAndSell testclass = new StockBuyAndSell();
		int [] arr = {4, 5};
		//int [] arr = {5, 4, 3, 2, 1};
		//int [] arr = {23, 13, 25, 29, 33, 19, 34, 45, 65, 67};
		//int [] arr = {100, 180, 260, 310, 40, 535, 695};
		ArrayList<Integer> output = testclass.buyAndSell(arr);
		
		for(int i=0; i< output.size(); i = i+2) {
			System.out.print("("+ output.get(i) + " " + output.get(i+1) + ")" + " ");
		}
		
		if(output.isEmpty()) {
			System.out.println("No Profit");
		}
		
	}
	
	private ArrayList<Integer> buyAndSell(int [] price) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		if(price.length == 0 || price.length == 1)
			return output;
		
		int min =0;
		int max =-1;
		
		for(int i=1; i<price.length ; i++) {
			if(max !=-1 && price[max] < price[i]) {
				max =i;
			} else if(max !=-1 && price[max] > price[i]) {
				output.add(min);
				output.add(max);
				
				min = i;
				max =-1;
			} else if(max == -1 && price[i] > price[min]) {
				max = i;
			} else {
				min =i;
			}
		}
		
		if(max !=-1) {
			output.add(min);
			output.add(max);
		}
		
		return output;
	}
}
