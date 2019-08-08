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
                    "::null",
                    list.toString(),
                    "should toString() correctly"
            );
        }

        @Test
        public void shouldAddFirstNodeToHead() {
            list.add(1);

            assertEquals(
                    "::1->null",
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
                    "::7->1->null",
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
                    "Num: 1",
                    list.head.toString(),
                    "empty node should print as null"
            );
        }
    }

}
