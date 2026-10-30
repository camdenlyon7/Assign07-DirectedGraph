
package assign07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class VertexEdgeTest {

    @Test
    public void testVertexConstructor() {
        Vertex<String> vertex = new Vertex<>("A");

        assertEquals("A", vertex.getData());
        assertEquals(Double.POSITIVE_INFINITY, vertex.getDistance());
        assertFalse(vertex.isVisited());
        assertNull(vertex.getPrevious());
        assertEquals(0, vertex.getInDegree());
    }

    @Test
    public void testVertexReset() {
        Vertex<String> vertex = new Vertex<>("A");
        Vertex<String> previous = new Vertex<>("B");

        vertex.setDistance(10.0);
        vertex.setVisited(true);
        vertex.setPrevious(previous);
        vertex.setInDegree(2);

        vertex.reset();

        assertEquals(Double.POSITIVE_INFINITY, vertex.getDistance());
        assertFalse(vertex.isVisited());
        assertNull(vertex.getPrevious());
        assertEquals(0, vertex.getInDegree());
    }

    @Test
    public void testVertexCompareTo() {
        Vertex<String> vertexA = new Vertex<>("A");
        Vertex<String> vertexB = new Vertex<>("B");

        vertexA.setDistance(5.0);
        vertexB.setDistance(10.0);

        assertTrue(vertexA.compareTo(vertexB) < 0);
        assertTrue(vertexB.compareTo(vertexA) > 0);
        assertEquals(0, vertexA.compareTo(vertexA));
    }

    @Test
    public void testVertexAddEdge() {
        Vertex<String> vertexA = new Vertex<>("A");
        Vertex<String> vertexB = new Vertex<>("B");

        vertexA.addEdge(vertexB, 5.0);

        assertEquals(1, vertexA.getNeighbors().size());
        assertEquals(vertexB,
                vertexA.getNeighbors().get(0).getDestination());
        assertEquals(5.0,
                vertexA.getNeighbors().get(0).getWeight());
    }

    @Test
    public void testEdgeConstructor() {
        Vertex<String> destination = new Vertex<>("B");

        Edge<String> edge = new Edge<>(destination, 5.0);

        assertEquals(destination, edge.getDestination());
        assertEquals(5.0, edge.getWeight());
    }

    @Test
    public void testDefaultEdgeWeight() {
        Vertex<String> destination = new Vertex<>("B");

        Edge<String> edge = new Edge<>(destination);

        assertEquals(1.0, edge.getWeight());
    }

    @Test
    public void testEdgeEquals() {
        Vertex<String> destination = new Vertex<>("B");

        Edge<String> edge1 = new Edge<>(destination, 5.0);
        Edge<String> edge2 = new Edge<>(destination, 5.0);

        assertEquals(edge1, edge2);
        assertEquals(edge1.hashCode(), edge2.hashCode());
    }

    @Test
    public void testNullEdgeDestination() {
        assertThrows(IllegalArgumentException.class,
                () -> new Edge<String>(null, 5.0));
    }
}
