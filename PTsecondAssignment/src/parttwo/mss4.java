/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parttwo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Djordje Gligorijevic
 */
public class mss4 {

    /**
     *
     * This method returns maximum subsequence sum for all subsequences that
     * start and end before n (starting from 0) and positions of the first and
     * the last element of the subsequence which has maximum subsequence sum for
     * all subsequences that start and end before i + 1 are known
     *
     * @param a input array representing a sequence from which we look for
     * maximum subsequence sum
     * @param p1 first element of the part of the sequence we are looking
     * @param p2 last element of the part of the sequence we are looking
     * @param position Object containing values of first and last position of
     * the subsequence which has maximum subsequence sum for all subsequences
     * that start and end before i + 1
     * @return Maximum subsequence sum for all subsequences that start and end
     * before n
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

        PrintWriter out = null;
        String newFilename = "src/parttwo/results/result4.txt";
        out = new PrintWriter(new BufferedWriter(new FileWriter(newFilename, true)));

        System.out.println("input sequence is: ");
        out.print("input sequence is: ");
        for (int j = 0; j < n; j++) {
            System.out.print(a[j] + " ");
            out.print(a[j] + " ");
        }
        out.print("\n");
        System.out.println("\n");
        System.out.println("Maximum sum of all integers in any subsequence "
                + "of consecutive integers is: " + maximum + "\n");
        out.println("Maximum sum of all integers in any subsequence "
                + "of consecutive integers is: " + maximum + "\n");
        System.out.println("Positions of the first and last element in"
                + " subsequence with the max sum are respectively: "
                + position.getFirst() + " " + position.getLast());
        out.println("Positions of the first and last element in"
                + " subsequence with the max sum are respectively: "
                + position.getFirst() + " " + position.getLast());
        out.flush();
        out.close();
    }

}
