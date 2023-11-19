package org.example;

public class Main {
    public static void main(String[] args) {
        SortedMthdCheck sortedMthdCheck = new SortedMthdCheck();
        String fastestMethodForSort = sortedMthdCheck.findFastestSortingMethod();
        System.out.println("fastestMethodForSort = " + fastestMethodForSort);
    }
}