package assign07;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A directed graph containing vertices and edges which can be topologically sorted, or have paths
 * found with Dijkstras algorithm or a Breadth First Algorithm
 * @author Camden Lyon & Ibrahim Alasady
 * @version 2026-10-29
 * @param <Type>
 */
public class DirectedGraph <Type> {
	private HashMap<Type, Vertex<Type>> vertices;
	
	/**
	 * constructor
	 */
	public DirectedGraph(){
		this.vertices  = new HashMap<>();
	}
	
	/**
	 * Adds a vertex to vertices
	 * @param data
	 * @return
	 */
	public boolean addVertex(Type data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (vertices.containsKey(data)){
        	return false;
        }
        vertices.put(data, new Vertex<Type>(data));
        return true;
	}
	
	/**
	 * 
	 * @param fromData
	 * @param toData
	 * @param weight
	 */
	public void addEdge(Type fromData, Type toData, double weight) {
        if (fromData == null || toData == null) {
            throw new IllegalArgumentException("Edge data cannot be null");
        }
        addVertex(fromData);
        addVertex(toData);
        
        Vertex<Type> source = vertices.get(fromData);
        Vertex<Type> destination = vertices.get(toData);
        source.addEdge(destination, weight);
        
	}
	
	/**
	 * private helper to reset all vertices to base state of infinite distance, not visited, inDegree 0, and previous is null
	 */
	private void resetAllVertices() {
        for (Vertex<Type> v : vertices.values()) {
            v.reset();
        }
    }
	
	/**
	 * private helper to get a vertex from the map if it exists
	 * @param data
	 * @return the vertex if it exists
	 * @throws IllegalArgumentException if the vertex is not found in vertices
	 */
	private Vertex<Type> getVertex(Type data) {
        Vertex<Type> vertex = vertices.get(data);
        if (vertex == null) {
            throw new IllegalArgumentException("Vertex not found in graph");
        }
        return vertex;
    }
	
	
	/**
	 * adds edge with default weight of 1
	 * @param fromData
	 * @param toData
	 */
	public void addEdge(Type fromData, Type toData) {
        addEdge(fromData, toData, 1.0);
    }
    
	/**
	 * private helper method to record the path we took to reach the destination vertex
	 * @param destination, the vertex being looked for
	 * @return a list of the path to the destination
	 */
	private List<Type> constructPath(Vertex<Type> destination) {
        LinkedList<Type> path = new LinkedList<>();
        Vertex<Type> current = destination;
        while (current != null) {
            path.addFirst(current.getData());
            current = current.getPrevious();
        }
        return path;
    }
	
	/**
	 * Runs a BFS to find the shortest path from the source to the destination
	 * @param source, the vertex to start at
	 * @param destination, the vertex to look for
	 * @return a list of the keys that lead through the graph to the destination 
	 * @throws IllegalArgumentException if no path is found
	 */
	public List<Type> breadthFirstSearch(Type source, Type destination){
		resetAllVertices();
		Vertex<Type> start = getVertex(source);
		Vertex<Type> end = getVertex(destination);
		Queue<Vertex<Type>> queue = new ArrayDeque<>();
        start.setVisited(true);
        queue.add(start);
        boolean found = false;
        while (!queue.isEmpty()) {
        	Vertex<Type> current = queue.poll();
            if (current == end) {
            	found = true;
            	break;
            }
            for (Edge<Type> edge: current.getNeighbors()) {
            	Vertex<Type> neighbor = edge.getDestination();
            	if (!neighbor.isVisited()) {
            		neighbor.setVisited(true);
            		neighbor.setPrevious(current);
            		queue.add(neighbor);
            	}
            }
        }
        if (!found) {
        	throw new IllegalArgumentException("No path exists");
        }
        return constructPath(end);

	}

	/**
	 * Uses Dijkstras Algorithm to find the l;east expensive path in a weighted DAG
	 * @param source
	 * @param destination
	 * @return a list of the keys that lead through the graph to the destination 
	 * @throws IllegalArgumentException if no path is found
	 */
	public List<Type> dijkstrasAlgorithm(Type source, Type destination){
		resetAllVertices();
		Vertex<Type> start = getVertex(source);
		Vertex<Type> end = getVertex(destination);
		List<Vertex<Type>> unvisited = new ArrayList<>(vertices.values());
		start.setDistance(0.0);
        while (!unvisited.isEmpty()) {
        	Collections.sort(unvisited, Collections.reverseOrder());
        	Vertex<Type> current = unvisited.remove(unvisited.size() - 1);
        	current.setVisited(true);
        	if (current.getDistance() == Double.POSITIVE_INFINITY) {
                break;
            }
        	if (current == end) {
                break;
            }
        	for (Edge<Type> edge : current.getNeighbors()) {
                Vertex<Type> neighbor = edge.getDestination();
                if (!neighbor.isVisited()) {
                    double altDist = current.getDistance() + edge.getWeight();
                    if (altDist < neighbor.getDistance()) {
                        neighbor.setDistance(altDist);
                        neighbor.setPrevious(current);
                    }
                }
            }
        }
        if (end.getDistance() == Double.POSITIVE_INFINITY) {
        	throw new IllegalArgumentException("No path exists");
        }
        return constructPath(end);
        
	}

	/**
	 * Runs a topological sort on the graph
	 * @return, a list of the topographically sorted order
	 * @throws IllegalArgumentException if graph is cyclic
	 */
	public List<Type> topoSort(){
		resetAllVertices();
		
		for (Vertex<Type> v : vertices.values()) {
			for (Edge<Type> edge : v.getNeighbors()) {
                Vertex<Type> dest = edge.getDestination();
                dest.setInDegree(dest.getInDegree() + 1);
            }
		}
		Queue<Vertex<Type>> readyQueue = new LinkedList<>();
		for (Vertex<Type> v : vertices.values()) {
	        if (v.getInDegree() == 0) {
	            readyQueue.offer(v);
	        }
	    }
		List<Type> sortedList = new ArrayList<>();
		while (!readyQueue.isEmpty()) {
	        Vertex<Type> current = readyQueue.poll();
	        sortedList.add(current.getData());
	        for (Edge<Type> edge : current.getNeighbors()) {
	            Vertex<Type> neighbor = edge.getDestination();
	            neighbor.setInDegree(neighbor.getInDegree() - 1);
	            
	            if (neighbor.getInDegree() == 0) {
	                readyQueue.offer(neighbor);
	            }
	        }
	    }
		if (sortedList.size() != vertices.size()) {
            throw new IllegalArgumentException("Topological sort fails for cyclic graphs");
        }
        return sortedList;
	}
	
}
