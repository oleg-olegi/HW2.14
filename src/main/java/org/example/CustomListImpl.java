package org.example;

import org.example.exceptions.CellIsNotEmptyException;

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
        if (item == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < size(); i++) {
            if (stringsList[i] == null) {
                stringsList[i] = item;
                countOfItems++;
                break;
            }
        }
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (stringsList[index] == null && index < size() && 0 < index) {
            stringsList[index] = item;
            countOfItems++;
            return item;
        }
        throw new CellIsNotEmptyException("Ячейка занята");
    }

    @Override
    public String set(int index, String item) {
        stringsList[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(stringsList[i], item)) {
                stringsList[i] = null;
                countOfItems--;
                return item;
            }
        }
        throw new IllegalArgumentException("Item not found");
    }

    @Override
    public String remove(int index) {
        stringsList[index] = null;
        countOfItems--;
        return stringsList[index];
    }

    @Override
    public boolean contains(String item) {
        for (String e : stringsList) {
            if (Objects.equals(e, item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(stringsList[i], item)) {//метод для обхода NullPointerException
                return i;
            }
        }
        return -1;
    }


    @Override
    public int lastIndexOf(String item) {
        for (int i = size()-1; i >= 0; i--) {
            if (Objects.equals(item, stringsList[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index > countOfItems || stringsList[index] == null) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
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
}