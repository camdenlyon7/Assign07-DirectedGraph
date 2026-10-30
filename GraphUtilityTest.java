
package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphUtilityTest {

    @Test
    public void testShortestPath() {
        List<String> sources = List.of("A", "A", "B", "C");
        List<String> destinations = List.of("B", "C", "D", "D");

        List<String> path =
                GraphUtility.shortestPath(
                        sources, destinations, "A", "D");

        assertEquals("A", path.get(0));
        assertEquals("D", path.get(path.size() - 1));
        assertEquals(3, path.size());
    }

    @Test
    public void testShortestWeightedPath() {
        List<String> sources = List.of("A", "A", "B", "C");
        List<String> destinations = List.of("B", "C", "D", "D");
        List<Double> weights = List.of(10.0, 2.0, 2.0, 10.0);

        List<String> path =
                GraphUtility.shortestWeightedPath(
                        sources, destinations, weights, "A", "D");

        assertEquals(List.of("A", "C", "B", "D"), path);
    }

    @Test
    public void testSort() {
        List<String> sources = List.of("A", "A", "B");
        List<String> destinations = List.of("B", "C", "C");

        List<String> sorted =
                GraphUtility.sort(sources, destinations);

        assertEquals(3, sorted.size());

        assertTrue(sorted.indexOf("A") < sorted.indexOf("B"));
        assertTrue(sorted.indexOf("A") < sorted.indexOf("C"));
        assertTrue(sorted.indexOf("B") < sorted.indexOf("C"));
    }

    @Test
    public void testShortestPathDifferentListSizes() {
        List<String> sources = List.of("A", "B");
        List<String> destinations = List.of("B");

        assertThrows(IllegalArgumentException.class,
                () -> GraphUtility.shortestPath(
                        sources, destinations, "A", "B"));
    }

    @Test
    public void testWeightedPathDifferentListSizes() {
        List<String> sources = List.of("A", "B");
        List<String> destinations = List.of("B", "C");
        List<Double> weights = List.of(1.0);

        assertThrows(IllegalArgumentException.class,
                () -> GraphUtility.shortestWeightedPath(
                        sources, destinations, weights, "A", "C"));
    }

    @Test
    public void testSortDifferentListSizes() {
        List<String> sources = List.of("A", "B");
        List<String> destinations = List.of("B");

        assertThrows(IllegalArgumentException.class,
                () -> GraphUtility.sort(sources, destinations));
    }

    @Test
    public void testEmptyGraphSort() {
        List<String> sources = List.of();
        List<String> destinations = List.of();

        assertTrue(GraphUtility.sort(sources, destinations).isEmpty());
    }

    @Test
    public void testShortestPathNoPath() {
        List<String> sources = List.of("A");
        List<String> destinations = List.of("B");

        assertThrows(IllegalArgumentException.class,
                () -> GraphUtility.shortestPath(
                        sources, destinations, "A", "C"));
    }
}
