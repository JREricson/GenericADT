package linkedlist;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Random ;

import static org.junit.jupiter.api.Assertions.*;

public class doublyLinkedListTest {
    //setup
    Random rand = new Random();


    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("testing getFirst")
    void getFirst() {
        doublyLinkedList<String> testList = new doublyLinkedList<>();
        assertNull(testList.getFirst());

        Node<String> first = testList.append("hat");
        Assertions.assertEquals(first, testList.getFirst());
        Node<String> second =testList.append("bat");
        Assertions.assertEquals(first, testList.getFirst());
        testList.remove(first);
        assertEquals(second, testList.getLast());
        testList.remove(second);
        assertNull(testList.getLast());


    }

    @Test
    void getLast() {
        //testing with strings
        doublyLinkedList<String> testList = new doublyLinkedList<>();
        assertNull(testList.getFirst());

        Node<String> first = testList.append("hat");
        assertEquals(first, testList.getLast());
        Node<String> second =  testList.append("bat");
       assertEquals(second, testList.getLast());
        testList.remove(second);
       assertEquals(first, testList.getLast());
        testList.remove(first);
        assertEquals(null, testList.getLast());


    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1,2, 400, 7000, 13_001, 1_850_001})
     void size(int num) {
        doublyLinkedList<Integer> testList = new doublyLinkedList<>();
        for (int i = 0; i < num; i++) {
            testList.append(rand.nextInt());
        }
        assertEquals(num, testList.size());
    }





    @Test
    void isEmpty() {
        doublyLinkedList<String> testList = new doublyLinkedList<>();
        assertNull(testList.getFirst());
        assertEquals(0,testList.size());
        assertNull(testList.getLast());

    }

    @Test
    void clear() {
        doublyLinkedList<Integer> testList = new doublyLinkedList<>();
        assertTrue(testList.isEmpty());
        //adding random numbers
        for (int i = 0; i < 20 ; i++) {
            testList.append(rand.nextInt(100));
        }
        //making sure list is not empty
        assertFalse(testList.isEmpty());
        testList.clear();
        //checking list is empty after clear
        assertTrue(testList.isEmpty());
        assertEquals(0,testList.size());

    }



        @Nested
        class Insert {
            Node<String> first, second, mid, end;

            doublyLinkedList<String> testList = new doublyLinkedList<>();

            @Test
            void insertHead() {
                //when list is empty
                first = testList.insert(null, "hat");
                assertNull(testList.getLast().next);
                assertNull(testList.getFirst().prev);
                assertEquals(first, testList.getFirst());
                assertEquals(first, testList.getLast());
                //when list is not empty
                second = testList.insert(null, "bat");
                assertNull(testList.getLast().next);
                assertNull(testList.getFirst().prev);
                assertEquals(first, testList.getFirst().next);
                assertEquals(second, testList.getFirst().next.prev);
                assertEquals(second, testList.getFirst());
                assertEquals(first, testList.getLast());
                assertEquals(2, testList.size());


            }

            @Test
            void insertTail() {
                testList.clear();
                first = testList.insert(null, "hat");
                second = testList.insert(null, "bat");
                end = testList.insert(first, "cat");
                assertEquals(end, testList.getLast());
                assertEquals(first, testList.getLast().prev);
                assertEquals(end, testList.getLast().prev.next);
                assertEquals(3, testList.size());

            }

            @Test
            void insertMid() {
                testList.clear();
                first = testList.insert(null, "hat");
                second = testList.insert(null, "bat");
                mid = testList.insert(second, "cat");

                assertEquals(mid,testList.getFirst().next);
                assertEquals(second,testList.getFirst().next.prev);

                assertEquals(first,testList.getLast());
                assertEquals(mid,testList.getLast().prev);
                assertEquals(3, testList.size());
            }

        }


    @Test
    void append() {
        doublyLinkedList<String> testList = new doublyLinkedList<>();
        Node<String> first = testList.append("hat");
        assertEquals(first, testList.getFirst());
        Node<String> second =testList.append("bat");
        assertNull(testList.getFirst().prev);
        assertEquals(first,testList.getLast().prev);
        assertNull(testList.getLast().next);
        assertEquals(second,testList.getLast());
    }

    @Test
    void prepend() {
        doublyLinkedList<String> testList = new doublyLinkedList<>();
        Node<String> first = testList.prepend("hat");
        assertEquals(first, testList.getFirst());
        Node<String> second =testList.prepend("bat");
        assertNull(testList.getFirst().prev);

        assertEquals(second,testList.getLast().prev);
        assertNull(testList.getLast().next);
        assertEquals(first,testList.getLast());
        assertEquals(2, testList.size());
    }


        @Nested
        class Remove {
            Node<String> first, second, third;

            doublyLinkedList<String> testList = new doublyLinkedList<>();
            @Test
            void removeHead() {
                first = testList.append("hat");
                assertEquals(first, testList.getFirst());
                second = testList.append("bat");
                assertEquals(second, testList.getLast());
                testList.remove(first);
                assertEquals(second, testList.getLast());
                assertNull(testList.getFirst().next);
                assertNull(testList.getFirst().prev);
                assertEquals(1, testList.size());
                testList.remove(second);
                assertNull(testList.getFirst());
                assertNull(testList.getFirst());
                assertEquals(0, testList.size());
            }
            //test tail
            @Test
            void removeTail() {
                first = testList.append("hat");
                second = testList.append("bat");
                third = testList.append("cat");
                testList.remove(third);
                assertNull(testList.getLast().next);
                assertEquals(second, testList.getLast());
                assertEquals(first, testList.getLast().prev);
            }
            //test middle
            @Test
            void removeMid() {
                testList.clear();
                first = testList.append("hat");
                second = testList.append("bat");
                third = testList.append("cat");
                testList.remove(second);
                assertEquals(third, testList.getFirst().next);
                assertEquals(first, testList.getLast().prev);
                assertEquals(2, testList.size());
            }


        }


        @Nested
        class Find {
            doublyLinkedList<Integer> testList = new doublyLinkedList<>();


            //@RepeatedTest(10)
            @Test
            void TestIntegerFind() {
                for (int i = 0; i < 30; i++) {
                    int numToInsert = rand.nextInt(100);
                    if (numToInsert == 5 || numToInsert == 7) {
                        continue;
                    }
                    testList.append(numToInsert);

                }
                //searching for node in middle of list
                Node<Integer> first5 = testList.insert(testList.getFirst().next.next, 5);
                assertEquals(testList.getFirst().next.next.next, testList.find(null, 5));
                assertEquals(testList.getFirst().next.next.next, testList.find(testList.getFirst().next.next.next, 5));
                assertEquals(testList.getFirst().next.next.next, testList.find(testList.getFirst().next.next, 5));
                assertNull(testList.find(testList.getFirst().next.next.next.next, 5));
                assertNull(testList.find(null, 7));
                assertNull(testList.find(null, 102));

                //searching at head
                testList.insert(null, 5);
                assertEquals(testList.getFirst().next.next.next.next, testList.find(testList.getFirst().next, 5));
                assertEquals(testList.getFirst(), testList.find(testList.getFirst(), 5));
                assertEquals(testList.getFirst(), testList.find(null, 5));
                //searching for node at tail
                testList.insert(testList.getLast(), 5);
                assertEquals(testList.getLast(), testList.find(testList.getLast().prev, 5));
                assertEquals(testList.getLast(), testList.find(testList.getFirst().next.next.next.next.next, 5));
            }
                @RepeatedTest(10)
                void TestStringFind() {
                    doublyLinkedList<String> testList = new doublyLinkedList<>();

                    for (int i = 0; i < 30; i++) {
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < rand.nextInt(10)+5; j++) {
                            char ch = (char)(rand.nextInt(26) + 'a');
                            sb.append(ch);
                        }
                        testList.append(sb.toString());


                        }
                    Node<String> testWord = testList.insert(testList.getFirst().next.next, "test");
                    assertEquals(testList.getFirst().next.next.next, testList.find(null, "test"));
                    assertEquals(testList.getFirst().next.next.next, testList.find(testList.getFirst().next.next.next, "test"));
                    assertEquals(testList.getFirst().next.next.next, testList.find(testList.getFirst().next.next, "test"));
                    assertNull(testList.find(testList.getFirst().next.next.next.next, "test"));

                    assertNull(testList.find(null, "102"));
                    //should cause to fail!!!!
//                    assertNotNull(testList.find(testList.getFirst().next.next.next.next, "test"));
//                    assertNotNull(testList.find(null, "102"));

                    }

            @Test
            @Disabled
            void CustomClass() {}
    }





        //find double

        //find string

        //find custom class



        //make subtests for each type




    @Nested
    class toList {

        @Test
        void toIntlist(){
            ArrayList<Integer> intArr = new ArrayList<>();
            doublyLinkedList<Integer> testList = new doublyLinkedList<>();

            for (int i = 0; i < 30000; i++) {
                int randInt = rand.nextInt(300);
                intArr.add(randInt);
                testList.append(randInt);
            }
            assertEquals(intArr,testList.toList());

        }


    }
}