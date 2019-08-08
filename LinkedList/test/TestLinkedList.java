import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Single Linked List")
public class TestLinkedList {
    LinkedList list;

    @Nested
    @DisplayName("An empty list")
    class EmptyListTest {
        @BeforeEach
        public void before() {
            list = new LinkedList();
        }

        @Test
        public void shouldStringCorrectly() {
            assertEquals(
                    "empty list",
                    list.toString(),
                    "should toString() correctly"
            );
        }

        @Test
        public void shouldAddFirstNodeToHead() {
            list.add(1);

            assertEquals(
                    "HEAD(1)",
                    list.toString(),
                    "should add first head node"
            );
        }
    }

    @Nested
    @DisplayName("A non-empty list")
    class NonEmptyListTest {
        @BeforeEach
        public void before() {
            list = new LinkedList(7);
        }

        @Test
        public void shouldAddNewNodeToEnd() {
            list.add(1);

            assertEquals(
                    "HEAD(7)->(1)",
                    list.toString(),
                    "new nodes should add to end"
            );
        }

        @Test
        public void shouldAddMultipleNewNodesToEnd() {
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(128512);

            assertEquals(
                    "HEAD(7)->(1)->(2)->(3)->(128512)",
                    list.toString(),
                    "new nodes should add to end"
            );
        }
    }

    @Nested
    @DisplayName("A node")
    class NodeTest {
        @Test
        public void shouldLookNice() {
            LinkedList list = new LinkedList(1);

            assertEquals(
                    "(1)",
                    list.head.toString(),
                    "should print nicely"
            );
        }

        @Test
        public void shouldReportExistanceOfNextNode() {
            LinkedList list = new LinkedList(1);

            assertEquals(
                    false,
                    list.head.hasNext(),
                    "lonely head should report no next node"
            );

            list.add(2);

            assertEquals(
                    true,
                    list.head.hasNext(),
                    "head with next should report it"
            );
        }
    }

}
