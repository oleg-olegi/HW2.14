package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CustomListImpl list = new CustomListImpl(10);
        list.add("bvjkg" );
        list.add("Andrey Krukau");
        list.add(3, "валера");
        list.set(3, "petya");
       // list.remove("oleg");
        System.out.println(" = " + list.get(1));
        System.out.println("list = " + Arrays.toString(list.getStringsList()));
    }
}