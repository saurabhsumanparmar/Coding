package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSort {
	
	public static void main(String args[]) 
    { 
        Graph g = new Graph(6); 
        g.addEdge(5, 2); 
        g.addEdge(5, 0); 
        g.addEdge(4, 0); 
        g.addEdge(4, 1); 
        g.addEdge(2, 3); 
        g.addEdge(3, 1); 
   
        ArrayList<Integer> output= topologicalSort(g); 
        for(int i=0; i<output.size(); i++) {
        	System.out.print(output.get(i) + " ");
        }
    }
	
	public static ArrayList<Integer> topologicalSort(Graph g) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		int v = g.V;
		LinkedList<Integer> [] adj = g.adj;
		
		int [] incomingEdges = new int[v];
		
		for(int i=0; i<v; i++) {
			LinkedList<Integer> lst = adj[i];
			
			Iterator<Integer> itr = lst.iterator();
			
			while(itr.hasNext()) {
				incomingEdges[itr.next()]++;
			}
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=0; i < v; i++) {
			if(incomingEdges[i] ==0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int vertex = q.poll();
			output.add(vertex);
			
			LinkedList<Integer> lst = adj[vertex];
			
			Iterator<Integer> itr = lst.iterator();
			
			while(itr.hasNext()) {
				int adjVertex = itr.next();
				incomingEdges[adjVertex]--;
				
				if(incomingEdges[adjVertex] ==0) {
					q.add(adjVertex);
				}
			}
		}
		
		return output;
	}
}
