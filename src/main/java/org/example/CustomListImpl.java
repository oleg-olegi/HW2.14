package org.example;

import org.example.exceptions.InvalidIndexException;
import org.example.exceptions.ItemNOtFoundException;
import org.example.exceptions.NullItemException;

import java.util.Objects;

public class CustomListImpl implements CustomListInterface {
    private int capacity;
    private int countOfItems = 0;
    private String[] stringsList;

    public CustomListImpl(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity<0");
        }
        this.capacity = initialCapacity;
        this.stringsList = new String[capacity];
    }

    public String[] getStringsList() {
        return this.stringsList;
    }

    public int size() {
        return stringsList.length;
    }

    @Override
    public String add(String item) {
        validateItem(item);
        stringsList[countOfItems++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateIndex(index);
        validateItem(item);
        if (index == countOfItems) {
            stringsList[index] = item;
            return item;
        }
        System.arraycopy(stringsList, index, stringsList, index + 1, countOfItems - index);
        stringsList[index] = item;
        countOfItems++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        stringsList[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        if (index != -1) {
            System.arraycopy(stringsList, index + 1, stringsList, index, size() - (index + 1));
            countOfItems--;
        } else {
            throw new ItemNOtFoundException("Item not found");
        }
        return item;
    }


    @Override
    public String remove(int index) {
        validateIndex(index);
        String item;
        if (stringsList[index] != null) {
            item = stringsList[index];
            System.arraycopy(stringsList, index + 1, stringsList, index, size() - (index + 1));
            stringsList[size() - 1] = null;  // Очищаем последний элемент после сдвига
            countOfItems--;
        } else {
            throw new ItemNOtFoundException("Not found");
        }
        return item;
    }

    @Override
    public boolean contains(String item) {
        validateItem(item);
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        validateItem(item);
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(stringsList[i], item)) {//метод для обхода NullPointerException
                return i;
            }
        }
        return -1;
    }


    @Override
    public int lastIndexOf(String item) {
        validateItem(item);
        for (int i = size() - 1; i >= 0; i--) {
            if (Objects.equals(item, stringsList[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        if (index > countOfItems-1) {
            throw new ItemNOtFoundException("Not found");
        }
        return stringsList[index];
    }

    @Override
    public boolean equals(String[] otherList) {
        if (size() != otherList.length) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(stringsList[i], otherList[i])) {
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
        for (String e : stringsList) {
            e = null;
        }
        countOfItems = 0;
    }

    @Override
    public String[] toArray() {
        String[] newArray = new String[countOfItems];
        int counter = 0; //счетчик существующих объектов, если объеты добавлены в массив не по  порядку
        for (int i = 0; i < size(); i++) {
            if (stringsList[i] != null) {
                newArray[counter] = stringsList[i];
                counter++;
            }
        }
        return newArray;
    }

    private void validateItem(String item) {
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