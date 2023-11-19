package org.example;

import java.util.Random;
import java.util.function.Function;

public class SortedMthdCheck {
    private SortedMthd sortedMthd;
    private Integer[] arrayForSpeedCheck;

    public SortedMthdCheck() {
        this.sortedMthd = new SortedMthd();
        this.arrayForSpeedCheck = generateRandomArray();
    }

    private Integer[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        Integer[] arr = new Integer[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }

    public long speedCheck(Function<Integer[], Integer[]> sortingMethod) {
        Integer[] copyArray = arrayForSpeedCheck.clone(); // Создаем копию массива
        long start = System.currentTimeMillis();
        sortingMethod.apply(copyArray);
        return System.currentTimeMillis() - start;
    }

    public String findFastestSortingMethod() {
        Function<Integer[], Integer[]> bubbleSort = SortedMthd::sortBubble;
        Function<Integer[], Integer[]> selectionSort = SortedMthd::sortSelection;
        Function<Integer[], Integer[]> insertionSort = SortedMthd::sortInsertion;

        long timeBubbleSort = speedCheck(bubbleSort);
        long timeSelectionSort = speedCheck(selectionSort);
        long timeInsertionSort = speedCheck(insertionSort);

        if (timeBubbleSort <= timeSelectionSort && timeBubbleSort <= timeInsertionSort) {
            return "Bubble Sort";
        } else if (timeSelectionSort <= timeBubbleSort && timeSelectionSort <= timeInsertionSort) {
            return "Selection Sort";
        } else {
            return "Insertion Sort";
        }
    }

    private void shuffleArray(Integer[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(i + array.length - i);
            int tmp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = tmp;
        }
    }
}
