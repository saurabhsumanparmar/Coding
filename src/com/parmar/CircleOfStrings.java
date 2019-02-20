package com.parmar;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class CircleOfStrings {
	
	/* Test cases: 
	 * 1. {"for", "geek", "rig", "kaf"}
	 * 2. {"for", "geek", "gale", "egg", "kaf"}
	 * 3. 
	 * */
	
	/*mistakes : 
	 * 1. 
	 * 
	 * */
	
	/* Geeksforgeeks Input : 
	 * Input
	2
	3
	abc bcd cdf
	4
	ab bc cd da


	Output
	0
	1*/
	public static void main(String [] args) {
		/*CircleOfStrings testClass = new CircleOfStrings();
		String [] arr = {"f", "for", "geek", "gale", "rig",  "egg", "kaf"};
		System.out.println("Is circle Found : " + testClass.isCircleFound(arr));
		
		String  line = "abc bcd cdf";
		
		String [] arr2 = line.split(" ");
		int index = 0;
		while(index < arr2.length) {
			System.out.println(arr2[index]);
			index ++;
		}*/
			
		/*Scanner scanner = new Scanner(System. in); 
		int noOfTestCases = scanner.nextInt();
		
		for(int i=0; i<noOfTestCases; i++) {
			int noOfStrings = scanner.nextInt();
			String line = scanner.nextLine();
			String [] input = line.split(" ");
			if(isCircleFound(input)) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}
			
		}*/
		
		/*String [] arr = {"f", "for", "geek", "gale", "rig",  "egg", "kaf"};
		System.out.println("Is circle Found : " + isCircleFound(arr));*/
	}
	
	static class Node{
		private String value;
		private int index;
		Node next;
		
		Node(String value, int index) {
			next = null;
			this.value = value;
			this.index = index;
		}
	}
	
	//Assumption: all the strings will be in lower case
	private static boolean isCircleFound(String [] inputArr) {
		LinkedList<Node> [] adjList = new LinkedList[75];
		
		for(int i=0; i<inputArr.length; i++) {
			String value = inputArr[i];
			int beginChar = value.charAt(0);
			
			if(adjList[beginChar -65] == null) {
				adjList[beginChar -65] = new LinkedList<Node>();
			}
			
			adjList[beginChar - 65].add(new Node(inputArr[i], i));
		}
		
		boolean [] visited = new boolean[inputArr.length];
		
		// Initialise one element on the fence
		int count=1;
		visited[0] = true;
		char nextChar = inputArr[0].charAt(inputArr[0].length()-1);
		Node startNode = adjList[inputArr[0].charAt(0)-65].getFirst();
		
		return isCircleFoundUsingDFS(adjList, visited, nextChar, count, startNode, inputArr.length);
	}

	private static  boolean isCircleFoundUsingDFS(LinkedList<Node>[] adjList, boolean[] visited, 
			int nextChar, int count, Node startNode, int totalCount) {
		
		// check for circle found
		if(count==totalCount && nextChar == startNode.value.charAt(0) ) {
			return true;
		}
		
		LinkedList<Node> list = adjList[nextChar -65];
		
		if(list == null) {
			return false;
		}
		
		Iterator<Node> itr = list.iterator();
		
		while(itr.hasNext()) {
			Node node = itr.next();
			if(!visited[node.index]) {
				count++;
				visited[node.index] = true;
				char nextCharTobeFound = node.value.charAt(node.value.length()-1);
				boolean isCircleFound = isCircleFoundUsingDFS(adjList, visited, nextCharTobeFound, count, startNode, totalCount);
				
				if(!isCircleFound) {
					count--;
					visited[node.index] = false;
				} else {
					return true;
				}
			}
		}
		
		return false;
	}
}
