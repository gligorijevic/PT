/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parttwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Djordje Gligorijevic
 */
public class mss4 {

    /**
     *
     * @param a
     * @param p1
     * @param p2
     * @param position
     * @return
     */
    public static int mss4(int[] a, int p1, int p2, Position position) {
        int maxSum = 0;
        int sum = 0;
        int first = p1;
        int last = 0;
        int i = p1;

        while (i < p2) {
            sum = sum + a[i];
            last = i;
            if (sum > maxSum) {
                maxSum = sum;
                position.setFirst(first);
                position.setLast(last);
            } else if (sum < 0) {
                sum = 0;
                first = i + 1;
            }
            i++;
        }
        return maxSum;
    }

    public static void main(String[] args) throws IOException {
        int n = 10;
        int i = 0;
        int x = 0;
        Integer first = 0;
        Integer last = 0;
        int maximum = 0;
        int a[] = new int[n];
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Enter values for the sequence");
        for (int j = 0; j < n; j++) {
            x = Integer.parseInt(reader.readLine());
            a[j] = x;
        }

        Position position = new Position();
        maximum = mss4(a, 0, n, position);

        System.out.println("input sequence is: ");
        for (int j = 0; j < n; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("\n");
        System.out.println("Maximum sum of all integers in any subsequence "
                + "of consecutive integers is: " + maximum + "\n");

        System.out.println("Positions of the first and last element in"
                + " subsequence with the mas sum are respectively: "
                + position.getFirst() + " " + position.getLast());
    }

}
