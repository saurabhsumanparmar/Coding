package graph;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://practice.geeksforgeeks.org/problems/implementing-floyd-warshall/0

// Need to run more test cases ..
public class FloydWarshallForWeightedDirected {
	public static void main(String [] args) {
		int [][] distance = {{0, 1, 43},
							 {1, 0, 6},
							 {-1, -1, 0}};
		
		FloydWarshallForWeightedDirected testClazz = new FloydWarshallForWeightedDirected();
		WeightedDirectedGraph graph = new WeightedDirectedGraph(3);
		
		for(int i=0; i < distance.length; i++) {
			for(int j=0; j< distance[0].length; j++) {
				graph.addEdge(i, j, distance[i][j]);
			}
		}
		
		int [][] output = testClazz.shotestDistancePair(graph);
		for(int i=0; i < output.length; i++) {
			for(int j=0; j< output[0].length; j++) {
				System.out.print(output[i][j] + " ");
			}
			
			System.out.println();
		}
	}
	
	class Edge {
		int start;
		int end;
		int weight;
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
	
	class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge e1, Edge e2) {
			if(e1.weight < e2.weight) {
				return -1;
			} else {
				return 1;
			}
		}
	}
	
	int [][] shotestDistancePair(WeightedDirectedGraph graph) {
		int [][] output = new int[graph.V][graph.V];
		
		for(int i=0; i < graph.V; i++) {
			PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>(new EdgeComparator());
			boolean [] visited = new boolean[graph.V];
			int noVisited = 0;
			
			visited[i] = true;
			noVisited =1;
			output[i][i] = 0;
			
			for(int j=0; j<graph.V; j++) {
				if(i==j) 
					continue;
				
				pQueue.add(new Edge(i, j, graph.adj[i][j]));
			}
			
			while(noVisited < graph.V) {
				Edge edge = pQueue.poll();
				
				output[i][edge.end] = edge.weight;
				noVisited++;
				visited[edge.end] = true;
				
				for(int k=0; k<graph.V; k++) {
					if(!visited[k] && graph.adj[edge.end][k] > 0) {
						pQueue.add(new Edge(edge.end, k, output[i][edge.end] + graph.adj[edge.end][k]));
					}
				}
			}
		}
		
		return output;
	}
}
