package assign07;

import java.util.ArrayList;
import java.util.List;

/**
 * A vertex containing data and a list of edges to other vertices
 * 
 * @author Camden Lyon & Ibrahim Alasady
 * @version 2026-10-29
 * @param <Type>
 */
public class Vertex<VType> implements Comparable<Vertex<VType>> {
	private VType data;
	private List<Edge<VType>> neighbors = new ArrayList<>();

	private double distance = Double.POSITIVE_INFINITY;
	private boolean visited = false;
	private Vertex<VType> previous = null;
	private int inDegree = 0;

	/**
	 * constructor
	 * 
	 * @param data, the data to be stored at this point in the graph
	 */
	public Vertex(VType data) {
		this.data = data;
	}

	/**
	 * resets vertex to base state
	 */
	public void reset() {
		this.distance = Double.POSITIVE_INFINITY;
		this.previous = null;
		this.inDegree = 0;
		this.visited = false;
	}

	@Override
	public int compareTo(Vertex<VType> other) {
		return Double.compare(this.distance, other.distance);
	}

	/**
	 * getter method for data
	 * @return VType, data
	 */
	public VType getData() {
		return this.data;
	}
	
	/**
	 * getter method for distance
	 * @return double, distance
	 */
	public double getDistance() {
		return this.distance;
	}
	
	/**
	 * getter method for neighbors
	 * @return List<Edge<VType>>, neighbors
	 */
	public List<Edge<VType>> getNeighbors() {
		return this.neighbors;
	}
	
	/**
	 * getter method for inDegree
	 * @return VType, data
	 */
	public int getInDegree() {
		return this.inDegree;
	}
	
	/**
	 * getter method for previous
	 * @return Vertex<VType>, previous
	 */
	public Vertex<VType> getPrevious(){
		return this.previous;
	}
	
	/**
	 * getter method for visited
	 * @return a boolean, visited
	 */
	public boolean isVisited(){
		return this.visited;
	}
	
	/**
	 * set visited to true or false
	 * @param a boolean
	 */
	public void setVisited(boolean bool) {
		this.visited = bool;
	}
	
	/**
	 * set distance to the input double
	 * @param a double
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	/**
	 * set inDegree to an int
	 * @param an int
	 */
	public void setInDegree(int degree) {
		this.inDegree = degree;
	}
	
	/**
	 * set inDegree to the number of edges coming into it
	 * @param a vertex
	 */
	public void setPrevious(Vertex<VType> vert) {
		this.previous = vert;
	}
	
	/**
	 * Adds the given edge to neighbors
	 * 
	 * @param edge, and Edge to another vertex
	 */
	public void addEdge(Edge<VType> edge) {
		if (edge == null) {
			throw new IllegalArgumentException("Edge cannot be null");
		}
		this.neighbors.add(edge);
	}

	/**
	 * Adds a new weighted edge
	 * 
	 * @param vertex  the vertex to connect to
	 * @param weight, the weight of the edge
	 */
	public void addEdge(Vertex<VType> vertex, double weight) {
		if (vertex == null) {
			throw new IllegalArgumentException("Vertex cannot be null");
		}
		this.neighbors.add(new Edge<VType>(vertex, weight));
	}

	/**
	 * Adds a new unweighted edge
	 * 
	 * @param vertex, the vertex to connect to
	 */
	public void addEdge(Vertex<VType> vertex) {
		if (vertex == null) {
			throw new IllegalArgumentException("Vertex cannot be null");
		}
		this.neighbors.add(new Edge<VType>(vertex));
	}

}
