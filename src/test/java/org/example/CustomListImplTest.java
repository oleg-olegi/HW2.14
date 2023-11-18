package org.example;

import org.example.exceptions.InvalidIndexException;
import org.example.exceptions.ItemNOtFoundException;
import org.example.exceptions.NullItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;


import java.util.stream.Stream;

public class CustomListImplTest {
    private CustomListImpl customListImpl;

    @BeforeEach
    public void setUp() {
        customListImpl = new CustomListImpl(5);
    }

    static Stream<Arguments> argumentsForTests() {
        return Stream.of(Arguments.of("cat"),
                Arguments.of("dog"),
                Arguments.of("rat"));
    }

    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void addTest(String expectedString) {
        String actual = customListImpl.add(expectedString);//actual
        assertNotNull(expectedString);
        assertEquals(expectedString, actual);
        assertTrue(customListImpl.contains(expectedString));//проверка, что добавленный элемент находится в списке
    }

    @Test
    void notNullAddTest() {
        assertThrows(NullItemException.class, () -> customListImpl.add(null));
    }

    @Test
    void addAtIndexTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");

        assertEquals("rat", customList.add(2, "rat"));
        assertThrows(InvalidIndexException.class, () -> customList.add(5, "bat"));
        assertThrows(InvalidIndexException.class, () -> customList.add(-1, "bull"));
        assertThrows(NullItemException.class, () -> customList.add(2, null));

    }

    @Test
    void setTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");

        assertEquals("bat", customList.set(1, "bat"));
        assertEquals("bat", customList.get(1));
        assertThrows(InvalidIndexException.class, () -> customList.set(5, "rat"));
    }

    @Test
    void removeByItemTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");

        assertEquals("cat", customList.remove("cat"));
        assertEquals("dog", customList.get(1));
        assertThrows(ItemNOtFoundException.class, () -> customList.remove("rat"));
    }

    @Test
    void removeByIndexTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");
        String removedItem = customList.remove(1);
        assertEquals("dog", removedItem);
        assertFalse(customList.contains(removedItem));
        assertThrows(ItemNOtFoundException.class, () -> customList.remove(1));
    }

    @Test
    void containsTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");

        assertTrue(customList.contains("cat"));
        assertFalse(customList.contains("rat"));
    }

    @Test
    void indexOfTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");

        assertEquals(0, customList.indexOf("cat"));
        assertEquals(1, customList.indexOf("dog"));
        assertEquals(-1, customList.indexOf("rat"));
    }

    @Test
    void lastIndexOfTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");
        customList.add("cat");

        assertEquals(2, customList.lastIndexOf("cat"));
        assertEquals(1, customList.lastIndexOf("dog"));
        assertEquals(-1, customList.lastIndexOf("rat"));
    }

    @Test
    void getTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");

        assertEquals("cat", customList.get(0));
        assertEquals("dog", customList.get(1));
        assertThrows(ItemNOtFoundException.class, () -> customList.get(2));
    }

    @Test
    void equalsTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");

        String[] otherList1 = {"cat", "dog", null, null, null};
        String[] otherList2 = {"cat", "bat"};

        assertTrue(customList.equals(otherList1));
        assertFalse(customList.equals(otherList2));
    }

    @Test
    void numberTest() {
        CustomListImpl customList = new CustomListImpl(5);
        customList.add("cat");
        customList.add("dog");

        assertEquals(2, customList.number());
    }

    @Test
    void isEmptyTest() {
        CustomListImpl customList = new CustomListImpl(5);
        assertTrue(customList.isEmpty());

        customList.add("cat");
        assertFalse(customList.isEmpty());
    }

    @Test
    void clearTest() {

        customListImpl.add("cat");
        customListImpl.add("dog");

        customListImpl.clear();

        assertTrue(customListImpl.isEmpty());
    }

    @Test
    void toArrayTest() {
        customListImpl.add("cat");
        customListImpl.add("dog");
        String[] expectedArray = {"cat", "dog"};
        assertArrayEquals(expectedArray, customListImpl.toArray());
    }
}

