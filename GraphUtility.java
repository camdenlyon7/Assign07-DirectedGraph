package assign07;

import java.util.List;

/**
 * GraphUtility
 * 
 * @author Camden Lyon & Ibrahim Alasady
 * @version 2026-10-29
 * @param <EType>
 */
public class GraphUtility {
  public static <Type> List<Type> shortestPath(
  List<Type> sources,
  List<Type> destinations,
  Type srcData,
  Type dstData) {

if (sources.size() != destinations.size()) {
  throw new IllegalArgumentException();

}

Graph<Type> graph = new Graph<>();

  for (int i = 0; i < sources.size(); i++) {
    Type source = sources.get(i);
    Type destination = destinations.get(i);

    graph.addEdge(source, destination);

  }
  List<Type> path = graph.breadthFirstSearch(srcData,dstData);
  return path;
}


public static <Type> List<Type> shortestWeightedPath (
  List<Type> sources,
  List<Type> destinations,
  List<Double> weights,
  Type srcData,
  Type dstData) {

  // check all lists for equal sizes
  if (sources.size() != destinations.size()
          || sources.size() != weights.size()) {
    throw new IllegalArgumentException();
  }

  //created weighted graph and add edges 
  Graph<Type> graph = new Graph<>();

  for (int i = 0; i < sources.size(); i++) {
    Type source = sources.get(i);
    Type destination = destinations.get(i);
    double weight = weights.get(i);
    
    graph.addEdge(source, destination, weight);
  }
  // call dijkstra's algorithm
  return graph.dijkstrasAlgorithm(srcData, dstData);
  }

public static <Type> List<Type> sort (
  List<Type> sources,
  List<Type> destinations) {

  // check all lists for equal sizes
  if (sources.size() != destinations.size()) {
    throw new IllegalArgumentException();
  }

  Graph<Type> graph = new Graph<>();

  for (int i = 0; i < sources.size(); i++) {
    Type source = sources.get(i);
    Type destination = destinations.get(i);
    
    graph.addEdge(source, destination);
  }

  return graph.topoSort();
  }
}
