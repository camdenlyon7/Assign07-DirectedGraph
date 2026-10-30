
package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class DirectedGraphTest {

    @Test
    public void testAddVertex() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        assertTrue(graph.addVertex("A"));
        assertTrue(graph.addVertex("B"));

        // Adding a duplicate should return false
        assertFalse(graph.addVertex("A"));
    }

    @Test
    public void testAddEdgeAutomaticallyAddsVertices() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addEdge("A", "B");

        // Both vertices should now exist
        assertEquals(List.of("A", "B"),
                graph.breadthFirstSearch("A", "B"));
    }

    @Test
    public void testBreadthFirstSearch() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        List<String> path = graph.breadthFirstSearch("A", "D");

        assertEquals("A", path.get(0));
        assertEquals("D", path.get(path.size() - 1));
        assertEquals(3, path.size());
    }

    @Test
    public void testBreadthFirstSearchSourceEqualsDestination() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addVertex("A");

        assertEquals(List.of("A"),
                graph.breadthFirstSearch("A", "A"));
    }

    @Test
    public void testBreadthFirstSearchNoPath() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addEdge("A", "B");
        graph.addVertex("C");

        assertThrows(IllegalArgumentException.class,
                () -> graph.breadthFirstSearch("A", "C"));
    }

    @Test
    public void testDijkstrasAlgorithm() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addEdge("A", "B", 8);
        graph.addEdge("A", "C", 2);
        graph.addEdge("C", "B", 1);
        graph.addEdge("B", "D", 2);
        graph.addEdge("C", "D", 10);

        List<String> path = graph.dijkstrasAlgorithm("A", "D");

        assertEquals(List.of("A", "C", "B", "D"), path);
    }

    @Test
    public void testDijkstrasAlgorithmNoPath() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addEdge("A", "B");
        graph.addVertex("C");

        assertThrows(IllegalArgumentException.class,
                () -> graph.dijkstrasAlgorithm("A", "C"));
    }

    @Test
    public void testTopologicalSort() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        List<String> sorted = graph.topoSort();

        assertEquals(4, sorted.size());

        // Verify that every vertex appears exactly once
        assertEquals(4, sorted.stream().distinct().count());

        // Verify the required ordering
        assertTrue(sorted.indexOf("A") < sorted.indexOf("B"));
        assertTrue(sorted.indexOf("A") < sorted.indexOf("C"));
        assertTrue(sorted.indexOf("B") < sorted.indexOf("D"));
        assertTrue(sorted.indexOf("C") < sorted.indexOf("D"));
    }

    @Test
    public void testTopologicalSortCycle() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");

        assertThrows(IllegalArgumentException.class,
                () -> graph.topoSort());
    }

    @Test
    public void testMissingVertex() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        graph.addVertex("A");

        assertThrows(IllegalArgumentException.class,
                () -> graph.breadthFirstSearch("A", "B"));
    }

    @Test
    public void testAddNullVertex() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        assertThrows(IllegalArgumentException.class,
                () -> graph.addVertex(null));
    }

    @Test
    public void testAddNullEdge() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdge("A", null));
    }
}
