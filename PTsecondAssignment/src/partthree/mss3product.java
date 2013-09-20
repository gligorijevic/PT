/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partthree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Djordje Gligorijevic
 */
public class mss3product {

    static int hack = 0;

    /**
     * This method returns maximum subsequence product for all subsequences that
     * start and end before n (starting from 0).
     *
     * @param a input array representing a sequence from which we look for
     * maximum subsequence product
     * @param p1 first element of the part of the sequence we are looking
     * @param p2 last element of the part of the sequence we are looking
     * @return Maximum subsequence product for all subsequences that start and
     * end before n
     */
    public static int mss3product(int[] a, int p1, int p2) {
        int maxProduct;
        int maxPositiveProduct;
        int maxNegativeProduct;
        if (p1 == p2) {
            System.out.println("bla" + a[p1]);
            if (a[p1] > 0) {
                hack = 1;
                maxPositiveProduct = a[p1];
                maxProduct = maxPositiveProduct;
            } else {
                maxNegativeProduct = a[p1];
                maxProduct = 0;
            }
        } else {
            int m = (p1 + p2) / 2;
            System.out.println(m);
            int L = mss3product(a, p1, m);
            int R = mss3product(a, m + 1, p2);
            int productLt = 1;
            int productRt = 1;
            int maxPositiveProdLt = 1;
            int maxPositiveProdRt = 1;
            int maxNegativeProdLt = 1;
            int maxNegativeProdRt = 1;

            for (int i = m; i >= p1; i--) {
                productLt = productLt * a[i];
                if (productLt > 0) {
                    if (productLt > maxPositiveProdLt) {
                        maxPositiveProdLt = productLt;
                    }
                } else {
                    if (productLt < maxNegativeProdLt) {
                        maxNegativeProdLt = productLt;
                    }
                }
            }
            for (int i = m + 1; i <= p2; i++) {
                productRt = productRt * a[i];
                if (productRt > 0) {
                    if (productRt > maxPositiveProdRt) {
                        maxPositiveProdRt = productRt;
                    }
                } else {
                    if (productRt < maxNegativeProdRt) {
                        maxNegativeProdRt = productRt;
                    }
                }
            }
            maxPositiveProduct = maxPositiveProdLt * maxPositiveProdRt;
            maxNegativeProduct = maxNegativeProdLt * maxNegativeProdRt;
            int M = Math.max(maxPositiveProduct, maxNegativeProduct);
            maxProduct = Math.max(Math.max(L, R), M);
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
        int a[] = new int[n];
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(System.in));

//        System.out.println("Enter values for the sequence");
//        for (int j = 0; j < n; j++) {
//            x = Integer.parseInt(reader.readLine());
//            a[j] = x;
//        }
//        a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        PrintWriter out = null;
        String newFilename = "src/partthree/results/result1.txt";
        out = new PrintWriter(new BufferedWriter(new FileWriter(newFilename, true)));

        a = new int[]{1, -1, 2, -2, 3, -3, 4, -4, 5, -5};
//        a = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        maximum = mss3product(a, 0, a.length - 1);

        System.out.println("input sequence is: ");
        out.print("input sequence is: ");
        for (int j = 0; j < a.length; j++) {
            System.out.print(a[j] + " ");
            out.print(a[j] + " ");
        }
        out.print("\n");
        System.out.println("Maximum sum of all integers in any subsequence "
                + "of consecutive integers is: " + maximum + "\n");
        out.print("Maximum sum of all integers in any subsequence "
                + "of consecutive integers is: " + maximum + "\n");
        out.flush();
        out.close();
    }

}
