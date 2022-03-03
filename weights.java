// Copyright Harry James Hocker
// Created on February 25th, 2022

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class weights {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {



        int loop = Integer.parseInt(br.readLine());
        // generate the weights to compare
        int[] weights = new int[loop];
        for (int i = 0; i < loop; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }

        // default is false, use this to keep track of weights used
        boolean[] used = new boolean[20];

        for (int j = 0; j < loop; j++) {
            // initialize values
            int left_total = weights[j];
            int right_total = 0;
            int remaining = left_total;
            ArrayList<Integer> left = new ArrayList<>();
            ArrayList<Integer> right = new ArrayList<>();

            // while the scale isn't balanced, keep balancing! easy.
            while (remaining != 0) {
                // increase either side whenever they weigh less
                if (right_total < left_total) {
                    int weight = next_weight(used, remaining);
                    right.add(weight);
                    right_total += weight;
                    remaining = Math.abs(left_total - right_total);
                }

                if (left_total < right_total) {
                    int weight = next_weight(used, remaining);
                    left.add(weight);
                    left_total += weight;
                    remaining = Math.abs(left_total - right_total);
                }
            }

            // print out the values
            System.out.print("left pan: ");
            for (Integer integer : left) {
                System.out.print(integer + " ");
            }

            System.out.println();

            System.out.print("right pan: ");
            for (Integer integer : right) {
                System.out.print(integer + " ");
            }
            System.out.println("\n");

        }

    }

    // a greedy function to find the nearest weight
    private static int next_weight(boolean[] used, int weight) {
        // this calculates the closest "base"
        double index = Math.log(weight)/Math.log(3);
        // we want the base index values below and above
        int low = (int) Math.floor(index);
        int high = (int) Math.ceil(index);

        // if the low base is used, go to the previous one
        while (low > 0 && used[low]) {
            low--;
        }
        // if the high base is used, go to the next one
        while (high < used.length - 1 && used[high]) {
            high++;
        }

        // automatically return one of the bases if either are still used
        if (used[low]) {
            used[high] = true;
            return high;
        }
        if (used[high]) {
            used[low] = true;
            return low;
        }

        // calculate the highest weight
        int high_weight = (int) Math.pow(3, high);
        int low_weight = (int) Math.pow(3, low);
        long remaining = sum_remaining(used, high-1);

        if (high_weight - weight > remaining) return low_weight;
        return high_weight;

    }

    static long sum_remaining(boolean[] used, int i) {
        // prevent OOB
        if (i < 0) return 0;
        // base case, return 1
        if (i == 0) return 1;
        // if it's used, add nothing and return previous
        if (used[i]) return sum_remaining(used, i-1);
        // return the summation of previous
        return (long) (Math.pow(3, i) + sum_remaining(used, i-1));
    }

}
