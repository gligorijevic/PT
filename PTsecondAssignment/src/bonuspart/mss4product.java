/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonuspart;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Djordje Gligorijevic
 */
public class mss4product {

    public static int mss4product(int[] a, int p1, int p2) {
        int maxProduct = 1;
        int positiveProduct = 1;
        int negativeProduct = 0;
        int hack = 0;
        int i = p1;

        while (i < p2) {
            if (a[i] > 0) {
                hack = 1;
                positiveProduct *= a[i];
                negativeProduct *= a[i];
            } else if (a[i] < 0) {
                hack = 1;
                int val = negativeProduct;
                negativeProduct = -positiveProduct * a[i];
                positiveProduct = -val * a[i];
            } else {
                positiveProduct = 1;
                negativeProduct = 0;
            }
            
            if (positiveProduct < 1) {
                positiveProduct = 1;
            }
            
            if (positiveProduct > maxProduct) {
                maxProduct = positiveProduct;
            }
            System.out.println("positive"+positiveProduct+", negative "+negativeProduct+" "+a[i]);
            i++;
        }
        if (hack == 0) {
            return 0;
        } else {
            return maxProduct;
        }
    }

    public static void main(String[] args) throws IOException {
        int n = 10;
        int i = 0;
        int x = 0;
        Integer first = 0;
        Integer last = 0;
        int maximum = 0;
        int a[];
//        a = new int[]{1, -1, 2, -2, 3, -3, 4, -4, 5, -5};
//        a = new int[]{-2,-2,-3};
        a = new int[]{5, 0, -3, 2, 1, -5, 0, 7};
        maximum = mss4product(a, 0, a.length);

        PrintWriter out = null;
        String newFilename = "src/bonuspart/results/result6.txt";
        out = new PrintWriter(new BufferedWriter(new FileWriter(newFilename, true)));

        System.out.println("input sequence is: 1");
        out.print("input sequence is: ");
        for (int j = 0; j < a.length; j++) {
            System.out.print(a[j] + " ");
            out.print(a[j] + " ");
        }
        out.print("\n");
        System.out.println("\n");
        System.out.println("Maximum product of all integers in any subsequence "
                + "of consecutive integers is: " + maximum + "\n");
        out.println("Maximum product of all integers in any subsequence "
                + "of consecutive integers is: " + maximum + "\n");
        out.flush();
        out.close();
    }

}
