package org.example;

import org.example.exceptions.InvalidIndexException;
import org.example.exceptions.ItemNOtFoundException;
import org.example.exceptions.NullItemException;

import java.util.Objects;

public class CustomListImpl_Integers implements CustomListInterface<Integer> {

    private int capacity;
    private int countOfItems = 0;
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
        integersList[countOfItems++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        if (index == countOfItems) {
            integersList[index] = item;
            return item;
        }
        System.arraycopy(integersList, index, integersList, index + 1, countOfItems - index);
        integersList[index] = item;
        countOfItems++;
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
            countOfItems--;
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
            countOfItems--;
        } else {
            throw new ItemNOtFoundException("Not found");
        }
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        validateItem(item);
        return indexOf(item) != -1;
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
        if (index > countOfItems - 1) {
            throw new ItemNOtFoundException("Not found");
        }
        return integersList[index];
    }

    @Override
    public boolean equals(Integer[] otherList) {
        if (size() != otherList.length) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(integersList[i], otherList[i])) {
                count++;
            }
        }
        return count == size();
    }

    @Override
    public int number() { //вместо size
        return countOfItems;
    }

    @Override
    public boolean isEmpty() {
        return countOfItems == 0;
    }

    @Override
    public void clear() {
        for (Integer e : integersList) {
            e = null;
        }
        countOfItems = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] newArray = new Integer[countOfItems];
        int counter = 0; //счетчик существующих объектов, если объеты добавлены в массив не по  порядку
        for (int i = 0; i < size(); i++) {
            if (integersList[i] != null) {
                newArray[counter] = integersList[i];
                counter++;
            }
        }
        return newArray;
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
}

