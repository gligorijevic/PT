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

        while (i <= p2) {
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

    /**
     *
     * @param a
     * @param p1
     * @param p2
     * @param position
     */
    public static void mss5(int[] a, int p1, int p2, Position position) {
        int maxp, maxprod, prodnl, prodpl, prodnr, prodpr, prod, m, i, l, r, al, ar, bl, br;
        int pl, pr, nl, nr;
        if (p1 == p2) {
            if (a[p1] > 0) {
                maxprod = a[p1];
                position.setFirst(p1);
                position.setLast(p1);
            } else {
                maxprod = 0;
                position.setFirst(0);
                position.setLast(0);
            }
        } else {
            m = (int) Math.floor((p1 + p2) / 2);
//            l = Math.max(a,p1, m, al ,ar);
        }

    }

    public static void mss4product(int[] a, int par1, int par2) {
        int maxprod = 0;
        int maxprod_final = 0;
        int neg_prod = 1;
        int prod = 1;
        int k = par1;
        int k_rev = 0;
        int maxprod_rev = 0;
        int neg_prod_rev = 1;
        int prod_rev = 1;

        while (k <= par2) {
            if (a[k] > 0) {
                prod = prod * a[k];
                if (prod > maxprod) {
                    maxprod = prod;
                }
            } else if (a[k] == 0) {
                prod = 1;
                neg_prod = 1;
            } else if (a[k] < 0) {
                prod = prod * a[k];
                neg_prod = neg_prod * prod;
                prod = neg_prod;
                if (prod > maxprod) {
                    maxprod = prod;
                    neg_prod = 1;
                } else {
                    prod = 1;
                }
            }
            k_rev = par2 - k + par1;
            if (a[k_rev] > 0) {
                prod_rev = prod_rev * a[k];
                if (prod_rev > maxprod_rev) {
                    maxprod_rev = prod_rev;
                }
            } else if (a[k_rev] == 0) {
                prod_rev = 1;
                neg_prod_rev = 1;
            } else if (a[k_rev] < 0) {
                prod_rev = prod_rev * a[k_rev];
                neg_prod_rev = neg_prod_rev * prod_rev;
                prod_rev = neg_prod_rev;
                if (prod_rev > maxprod_rev) {
                    maxprod_rev = prod_rev;
                    neg_prod_rev = 1;
                } else {
                    prod_rev = 1;
                }

            }
            k++;
        }
        maxprod_final = Math.max(maxprod_rev, maxprod);
        System.out.println("For the numbers you have entered: ");
        for (int i = 0; i < par2; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n");
        System.out.println("The maximum product is maxprod = " + maxprod_final);
    }

    public static void main(String[] args) throws IOException {
        int n = 10;
        int i = 0;
        int x = 0;
        Integer first = 0;
        Integer last = 0;
        int maximum = 0;
        int a[] = new int[1000];
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Enter values for the sequence");
        for (int j = 0; j < n; j++) {
            x = Integer.parseInt(reader.readLine());
            a[j] = x;
        }
        
        mss4product(a, 0, 0);
        
//        Position position = new Position();
//        maximum = mss4(a, 0, n, position);
//        
//        System.out.println("input sequence is: ");
//        for (int j = 0; j < n; j++) {
//            System.out.print(a[j] + " ");
//        }
//        System.out.println("\n");
//        System.out.println("Maximum sum of all integers in any subsequence "
//                + "of consecutive integers is: " + maximum + "\n");
//
//        System.out.println("Positions of the first and last element in"
//                + " subsequence with the mas sum are respectively: "
//                + position.getFirst() + " " + position.getLast());
    }

}
