package graph;

public class WeightedDirectedGraph {
	public int V;   // No. of vertices 
    public int adj[][]; // Adjacency matrix 
  
    //Constructor 
    WeightedDirectedGraph(int v) 
    { 
        V = v; 
        adj = new int[V][V];  
    } 
  
    // Function to add an edge into the graph 
    void addEdge(int v,int w, int weight) { adj[v][w] = weight;} 
}
