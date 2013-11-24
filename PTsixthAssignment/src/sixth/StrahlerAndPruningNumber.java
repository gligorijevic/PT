/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sixth;

/**
 *
 * @author djordje
 */
public class StrahlerAndPruningNumber {

    static int[] lefts;
    static int[] rights;
    public static int n;
    static double numberOfTrees;
    static int insert;
    static int[] strahler;
    static int[] pruning;

    private static void print(int n) {
        int p;
        int q;
        int rtptr = -1;
        int a = 0;
        int[] outputArray = new int[2 * n];
        for (int i = 0; i < outputArray.length; i++) {
            outputArray[i] = 555;
        }
        int[] preorderStack = new int[2 * n];
        int preorderStackTop = -1;
        System.out.println("Tree number: " + numberOfTrees);
        System.out.println("Lefts: ");
        for (int j = 1; j <= n; j++) {
            System.out.print(" " + lefts[j]);
        }
        System.out.println("\nRights:");
        for (int j = 1; j <= n; j++) {
            System.out.print(" " + rights[j]);
        }
        if (n <= 5) {
            p = 1;
            while (p != 0 || preorderStackTop != -1) {
                if (p != 0) {
                    if (a == 2 * n) {
                        break;
                    }
                    outputArray[a++] = 1;
                    preorderStack[++preorderStackTop] = p;
                    if (lefts[p] != 0) {
                        p = lefts[p];
                    } else {
                        outputArray[a++] = 0;
                        p = rights[p];
                    }
                } else {
                    if (a == 2 * n) {
                        break;
                    }
                    outputArray[a++] = 0;
                    do {
                        q = preorderStack[preorderStackTop];
                        preorderStackTop--;
                        if (preorderStackTop != -1) {
                            rtptr = rights[preorderStack[preorderStackTop]];
                        } else {
                            rtptr = 0;
                        }
                    } while (preorderStackTop != -1 && q == rtptr);
                    p = rtptr;
                }
            }
            System.out.println("\nOutput array:");
            for (int k = 0; k < 2 * n; k++) {
                System.out.print(" " + outputArray[k]);
            }
        }
    }

    public static int pruning(int root) {

        int result = 0, next = 0, index = 0;
        if (lefts[root] == 0 && rights[root] == 0) {
            return 1;
        } else if (rights[root] != 0) {
            next = root;
            while (rights[next] != 0) {
                if (lefts[next] != 0) {
                    pruning[index] = pruning(lefts[next]);
                    index++;
                } else {
                    pruning[index] = 1;
                    index++;
                }
                next = rights[next];
            }
            //find the largest pruning number
            int count = 0;
            for (int a = 0; a < index; a++) {
                if (pruning[a] > result) {
                    result = pruning[a];
                    count = 0;
                }

                if (pruning[a] == result) {
                    count++;
                }
            }
            if (count >= 2) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        n = 4;
        lefts = new int[n + 1];
        rights = new int[n + 1];
        strahler = new int[n + 1];
        pruning = new int[n + 1];

        for (int i = 1; i <= n - 1; i++) {
            lefts[i] = 0;
            rights[i] = i + 1;
        }
//        lefts[1] = 2;
//        rights[1] = 4;
//        rights[2] = 3;
//
//        rights[n] = 0;
//        lefts[n] = 0;
        insert = 1;
        numberOfTrees = 0;
        print(n);

        System.out.println("\nCalculating Strahler number");
        int strahler_done = 0;
        for (int a = 1; a <= n; a++) {
            if (lefts[a] == 0 && rights[a] == 0) {
                strahler[a] = 1;
                strahler_done++;
            }
        }

        while (strahler_done < n) {
            for (int a = n; a > 0; a--) {
                if (strahler[a] == 0) {
                    if (strahler[lefts[a]] != 0 && strahler[rights[a]] != 0) {
                        if (strahler[lefts[a]] != strahler[rights[a]]) {
                            strahler[a] = Math.max(strahler[lefts[a]], strahler[rights[a]]);
                        } else {
                            strahler[a] = strahler[lefts[a]] + 1;
                        }
                        strahler_done++;
                    } else if ((strahler[lefts[a]] == 0 && strahler[rights[a]] != 0) || (strahler[lefts[a]] != 0 && strahler[rights[a]] == 0)) {
                        strahler[a] = Math.max(strahler[lefts[a]], strahler[rights[a]]);
                        strahler_done++;
                    }
                }
            }
        }
        System.out.println(" The strahler no for the enterned tree is : " + strahler[1]);

        System.out.println("\nCalculating Pruning number");
        int next = 1, leftPruning = 0, rightPruning = 0;
        do {
            leftPruning = pruning(next);
            if (rightPruning > leftPruning) {
                leftPruning = rightPruning;
            }
            next = rights[next];
        } while (next != 0);
        System.out.println("The Pruning no of the tree  is: " + leftPruning);

    }

}
