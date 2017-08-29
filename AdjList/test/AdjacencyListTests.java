import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdjacencyListTests {

    private AdjacencyList adjacencyList;

    @Before
    public void setupTests() {
        adjacencyList = new AdjacencyList();

    }

    @Test
    public void testAddSingleConnection() {
        adjacencyList.addConnection("Atlanta", "Chattanooga");
        String[] expected = new String[50];
        expected[1] = "Chattanooga";
        expected[0] = "Atlanta";
        assertArrayEquals(
                adjacencyList.getConnections("Atlanta"),
                expected
        );
//        expected[0] = "Chattanooga";
//        expected[1] = "Atlanta";
//        assertArrayEquals(
//                adjacencyList.getConnections("Chattanooga"),
//                expected
//        );
    }
}