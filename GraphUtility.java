package assign07;

import java.util.List;
import java.util.ArrayList;
/**
 * GraphUtility
 * 
 * @author Camden Lyon & Ibrahim Alasady
 * @version 2026-10-29
 * @param <EType>
 */
public class GraphUtility {
  public static <Type> List<Type> shortestpath(
  List<Type> sources,
  List<Type> destinations,
  Type srcData,
  Type dstData) {

// check lists have equal sizes
if (sources.size() != destinations.size()) {
  throw new IllegalArgumentException();

}

// create graph
DirectedGraph<Type> graph = new DirectedGraph<>();

  // Add edges to the graph
  for (int i = 0; i < sources.size(); i++) {
    Type source = sources.get(i);
    Type destination = destinations.get(i);

    graph.addEdge(source, destination);

  }

  // Find the shortest path using BFS

  List<Type> path = graph.breadthFirstSearch(srcData,dstData);

  return path;
}


public static <Type> List<Type> shortestWeightedpath (
  List<Type> sources,
  List<Type> destination,
  List<Double> weights,
  Type srcData,
  Type dstData) {

  // check all lists for equal sizes
  if (sources.size() != destinations.size()
          || sources.size() != weights.size()) {
    throw new IllegalArgumentException();
  }

  //created weighted graph and add edges 
  DirectedGraph<Type> graph = new DirectedGraph<>();

  for (int i = 0; i < sources.size(); i++) {
    Type source = sources.get(i);
    Type destination = destinations.get(i);

    graph.addEdge(source, destination);
  }
  // call dijkstra's algorithm

  return graph.topoSort();
  }
}
