/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partthree;

/**
 *
 * @author Djordje Gligorijevic
 */
public class mss3product {

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

    
    
    
}
