package assign07;

/**
 * An edge from one vertex to another, containing a weight
 * 
 * @author Camden Lyon & Ibrahim Alasady
 * @version 2026-10-29
 * @param <EType>
 */
public class Edge<EType> {
	private double weight;
	private Vertex<EType> destination;

	/**
	 * Constructor
	 * 
	 * @param destination
	 * @param weight
	 * @throws IllegalArgumentException if one of the vertices it connects is null
	 */
	public Edge(Vertex<EType> destination, double weight) {
		if (destination == null)
			throw new IllegalArgumentException("Destination cannot be null");
		this.weight = weight;
		this.destination = destination;
	}

	/**
	 * Constructor if no weight is provided
	 * 
	 * @param source
	 * @param destination
	 * @throws IllegalArgumentException if one of the vertices it connects is null
	 */
	public Edge(Vertex<EType> destination) {
		this(destination, 1);
	}

	/**
	 * getter method for destination vertex
	 * 
	 * @return a Vertex, the destination
	 */
	public Vertex<EType> getDestination() {
		return this.destination;
	}

	/**
	 * getter method for edge weight
	 * 
	 * @return a double, the edge weight
	 */
	public double getWeight() {
		return this.weight;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Edge<?> other))
			return false;
		return this.destination == other.destination && this.weight == other.weight;
	}

}
