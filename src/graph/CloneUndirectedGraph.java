package graph;

import java.util.HashMap;

public class CloneUndirectedGraph {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
        HashMap<Integer, UndirectedGraphNode> nodeMap = new HashMap<Integer, UndirectedGraphNode>();
        
        UndirectedGraphNode graphNode = new UndirectedGraphNode(node.label);
        visited.put(node.label, true);
        nodeMap.put(node.label, graphNode);
        for(int i=0; i<node.neighbors.size(); i++) {
        	UndirectedGraphNode newNode;
        	
        	// Only For self Loop
        	if(nodeMap.get(node.neighbors.get(i).label) != null) {
        		newNode = nodeMap.get(node.neighbors.get(i).label);
        	} else {
        		newNode = new UndirectedGraphNode(node.neighbors.get(i).label);
        		nodeMap.put(node.neighbors.get(i).label, newNode);
        	}
        	
        	graphNode.neighbors.add(newNode);
        	
        	if(!visited.get(node.neighbors.get(i).label)) {
        		cloneGraph(node.neighbors.get(i), visited, nodeMap);
        	}
        }
        
        return graphNode;
    }

	private void cloneGraph(UndirectedGraphNode node, HashMap<Integer, Boolean> visited,
			HashMap<Integer, UndirectedGraphNode> nodeMap) {
			
		UndirectedGraphNode graphNode = nodeMap.get(node.label);
		visited.put(node.label, true);
		
    	for(int i=0; i<node.neighbors.size(); i++) {
        	UndirectedGraphNode newNode;
        	
        	if(nodeMap.get(node.neighbors.get(i).label) != null) {
        		newNode = nodeMap.get(node.neighbors.get(i).label);
        	} else {
        		newNode = new UndirectedGraphNode(node.neighbors.get(i).label);
        		nodeMap.put(node.neighbors.get(i).label, newNode);
        	}
        	
        	graphNode.neighbors.add(newNode);
        	
        	if(!visited.get(node.neighbors.get(i).label)) {
        		cloneGraph(node.neighbors.get(i), visited, nodeMap);
        	}
        }
	}
}
