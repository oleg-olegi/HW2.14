package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomListImpl list = new CustomListImpl(10);
//        list.add("bvjkg");
//        list.add("Andrey Krukau");
//        list.add(3, "валера");
//        list.set(3, "petya");
//        list.remove("bvjkg");
//        list.remove("Andrey Krukau");
//        list.remove("petya");
//        System.out.println(" = " + list.get(1));
//        System.out.println(list.isEmpty());
//        String[] newArr = list.toArray();
//        ArrayList<String> liiiistt = new ArrayList<>();
//        liiiistt.add("bvjkg");
//        liiiistt.add("Andrey Krukau");
//        liiiistt.add("petya");
//        System.out.println(list.equals(liiiistt.toArray()));
//        System.out.println(Arrays.toString(newArr));
        list.add(0, "dddd");
        list.add(1, "qqq");
        list.remove(1);
        System.out.println("list = " + Arrays.toString(list.getStringsList()));
    }
}