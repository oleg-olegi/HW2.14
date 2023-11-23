package org.example;

import org.example.exceptions.InvalidIndexException;
import org.example.exceptions.ItemNOtFoundException;
import org.example.exceptions.NullItemException;

import java.util.Objects;

public class CustomListImpl_Integers implements CustomListInterface<Integer> {

    private int capacity;
    private int currentIndex = 0;
    private Integer[] integersList;

    public CustomListImpl_Integers(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity<0");
        }
        this.capacity = initialCapacity;
        this.integersList = new Integer[capacity];
    }

    public Integer[] getIntegersList() {
        return this.integersList;
    }

    public int size() {
        return integersList.length;
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        if (currentIndex >= integersList.length) {
            makeArrayGreatAgain();
        }
        integersList[currentIndex++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        if (currentIndex >= capacity) {
            makeArrayGreatAgain();
        }
        if (index == currentIndex) {
            integersList[index] = item;
            return item;
        }
        System.arraycopy(integersList, index, integersList, index + 1, currentIndex - index);
        integersList[index] = item;
        currentIndex++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        integersList[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index != -1) {
            System.arraycopy(integersList, index + 1, integersList, index, size() - (index + 1));
            currentIndex--;
        } else {
            throw new ItemNOtFoundException("Item not found");
        }
        return item;
    }


    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item;
        if (integersList[index] != null) {
            item = integersList[index];
            System.arraycopy(integersList, index + 1, integersList, index, size() - (index + 1));
            integersList[size() - 1] = null;  // Очищаем последний элемент после сдвига
            currentIndex--;
        } else {
            throw new ItemNOtFoundException("Not found");
        }
        return item;
    }

    @Override
    public int indexOf(Integer item) {
        validateItem(item);
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(integersList[i], item)) {//метод для обхода NullPointerException
                return i;
            }
        }
        return -1;
    }


    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = size() - 1; i >= 0; i--) {
            if (Objects.equals(item, integersList[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        if (index > currentIndex - 1) {
            throw new ItemNOtFoundException("Not found");
        }
        return integersList[index];
    }

    @Override
    public boolean equals(Integer[] otherList) {
        if (size() != otherList.length) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(integersList[i], otherList[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int number() { //вместо size
        return currentIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == 0;
    }

    @Override
    public void clear() {
        for (Integer e : integersList) {
            e = null;
        }
        currentIndex = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] newArray = new Integer[currentIndex];
        int counter = 0; //счетчик существующих объектов, если объеты добавлены в массив не по  порядку
        for (int i = 0; i < size(); i++) {
            if (integersList[i] != null) {
                newArray[counter] = integersList[i];
                counter++;
            }
        }
        return newArray;
    }

    @Override
    public boolean contains(Integer item) {
        validateItem(item);
        return binarySearch(getIntegersList(), item);
    }

    private void insertionSort(Integer[] array) {
        SortedMthd.sortInsertion(array);
    }

    private boolean binarySearch(Integer[] array, Integer element) {
        insertionSort(array);
        int min = 0;
        int max = array.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            Integer midElement = array[mid]; // Получаем текущий элемент
            if (midElement != null && midElement.equals(element)) {
                // Если midElement не null и равен element, нашли элемент
                return true;
            }
            if (element == null || (midElement != null && midElement < element)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return false; // Не нашли элемент
    }

    private void makeArrayGreatAgain() {
        int newCapacity = capacity * (3 / 2) + 1;
        Integer[] grownArray = new Integer[newCapacity];
        System.arraycopy(integersList, 0, grownArray, 0, capacity);
        integersList = grownArray;
        capacity = newCapacity;
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException("Item == null");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new InvalidIndexException("Invalid Index");
        }
    }

    public static void main(String[] args) {
        CustomListImpl_Integers customList = new CustomListImpl_Integers(3);
        customList.add(2);
        System.out.println(customList.capacity);
    }
}

