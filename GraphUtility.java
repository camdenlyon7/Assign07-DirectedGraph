package assign07;

import java.util.List;
import java.utio.ArrayList;
/**
 * GraphUtility
 * 
 * @author Camden Lyon & Ibrahim Alasady
 * @version 2026-10-29
 * @param <EType>
 */
public class GraphUtility {
  public static <Type> List<Type> (
  List<Type> sources,
  List<Type> destinations,
  Type srcData
  Type dstData) {

// check lists have equal sizes
if (sources.size() != destinations.size()) {
  throw new IllegalArugmentException();

}

// create graph
Graph<Type> graph = new Graph<>();

// Add edges to the graph
for (int i = 0; i < sources.size(); i++) {
  Type source = sources.get(i);
  Type destination = destinations.get(i);

  //add edge here

  }

  // Find the shortest path using BFS

  List<Type> path = graph.breadthFirstSearch(srcData,dstData);

  return path;
}


public static <Type> List<Type> (
  List<Type> sources,
  List<Type> destination,
  List<Double> weights,
  Type srcData,
  Type dstData) {

  // check all lists for equal sizes
  if (sources.size() != destinations.size() || sources.size() != weights.size()) {
    throw new IllegalArgumentException();
  }

  //created weighted graph and add edges 

  // call dijkstra's algorithm

  return null;
}
