package org.example;

import org.example.exceptions.InvalidIndexException;
import org.example.exceptions.ItemNOtFoundException;
import org.example.exceptions.NullItemException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.MethodSource;


import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class CustomListImpl_IntegersTest {
    CustomListImpl_Integers customListImpl_integers;

    @BeforeEach
    public void setUp() {
        this.customListImpl_integers = new CustomListImpl_Integers(5);
    }

    static Stream<Integer> provideIntegers() {
        return Stream.of(90, 76, 200);
    }

    static Stream<Integer> provideNull() {
        return Stream.of(null);
    }

    @ParameterizedTest
    @MethodSource("provideIntegers")
    void addTest(Integer expectedNum) {
        Integer actual = customListImpl_integers.add(expectedNum);
        assertNotNull(expectedNum);
        assertEquals(expectedNum, actual);
        assertTrue(customListImpl_integers.contains(expectedNum));
        assertThrows(NullItemException.class, () -> customListImpl_integers.add(null));
    }

    @Test
    void addWithIndexTest() {
        customListImpl_integers.add(7);
        customListImpl_integers.add(6);
        assertEquals(6, customListImpl_integers.add(2, 6));
        assertThrows(InvalidIndexException.class, () -> customListImpl_integers.add(5, 9));
        assertThrows(InvalidIndexException.class, () -> customListImpl_integers.add(-1, 90));
        assertThrows(NullItemException.class, () -> customListImpl_integers.add(2, null));
    }

    @Test
    void setTest() {

        customListImpl_integers.add(56);
        customListImpl_integers.add(467);

        assertEquals(58, customListImpl_integers.set(1, 58));
        assertEquals(58, customListImpl_integers.get(1));
        assertThrows(InvalidIndexException.class, () -> customListImpl_integers.set(5, 657));
    }

    @Test
    void removeTest() {
        customListImpl_integers.add(1000);
        customListImpl_integers.add(346);
        customListImpl_integers.add(578);
        assertEquals(1000, customListImpl_integers.remove(0));//удаление по индексу
        assertEquals(346, customListImpl_integers.remove(Integer.valueOf(346)));//удаление по значению
        assertEquals(578, customListImpl_integers.get(0));

        assertFalse(customListImpl_integers.contains(1000));
        assertThrows(InvalidIndexException.class, () -> customListImpl_integers.remove(56));
        assertThrows(ItemNOtFoundException.class, () -> customListImpl_integers.remove(Integer.valueOf(700)));
    }

    @Test
    void containsTest() {
        customListImpl_integers.add(6);
        customListImpl_integers.add(3);

        assertTrue(customListImpl_integers.contains(6));
        assertFalse(customListImpl_integers.contains(4));
    }

    @Test
    void indexOfTest() {
        customListImpl_integers.add(5);
        customListImpl_integers.add(7);

        assertEquals(0, customListImpl_integers.indexOf(5));
        assertEquals(1, customListImpl_integers.indexOf(7));
        assertEquals(-1, customListImpl_integers.indexOf(200));
    }

    @Test
    void lastIndexOfTest() {
        customListImpl_integers.add(5);
        customListImpl_integers.add(0);
        customListImpl_integers.add(9);

        assertEquals(2, customListImpl_integers.lastIndexOf(9));
        assertEquals(1, customListImpl_integers.lastIndexOf(0));
        assertEquals(-1, customListImpl_integers.lastIndexOf(66789));
    }

    @Test
    void getTest() {
        customListImpl_integers.add(2);
        customListImpl_integers.add(3);

        assertEquals(2, customListImpl_integers.get(0));
        assertEquals(3, customListImpl_integers.get(1));
        assertThrows(ItemNOtFoundException.class, () -> customListImpl_integers.get(2));
    }

    @Test
    void equalsTest() {
        customListImpl_integers.add(467);
        customListImpl_integers.add(567);

        Integer[] otherList1 = {467, 567, null, null, null};
        Integer[] otherList2 = {467, 567};

        assertTrue(customListImpl_integers.equals(otherList1));
        assertFalse(customListImpl_integers.equals(otherList2));

    }

    @Test
    void numberTest() {
        customListImpl_integers.add(1);
        customListImpl_integers.add(2);

        assertEquals(2, customListImpl_integers.number());
    }

    @Test
    void isEmptyTest() {
        assertTrue(customListImpl_integers.isEmpty());
        customListImpl_integers.add(654);
        assertFalse(customListImpl_integers.isEmpty());
    }

    @Test
    void clearTest() {
        customListImpl_integers.add(5463);
        customListImpl_integers.add(3456346);
        customListImpl_integers.clear();
        assertTrue(customListImpl_integers.isEmpty());
    }

    @Test
    void toArrayTest() {
        customListImpl_integers.add(90);
        customListImpl_integers.add(4574);
        Integer[] expectedArray = {90, 4574};
        assertArrayEquals(expectedArray, customListImpl_integers.toArray());
    }
}
