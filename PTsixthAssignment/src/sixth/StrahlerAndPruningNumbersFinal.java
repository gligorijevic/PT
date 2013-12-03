/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sixth;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author gligo_000
 */
public class StrahlerAndPruningNumbersFinal {

    static PrintWriter out;
    static int n;
    static int[] lefts;
    static int[] rights;
    static double numberOfTrees;
    static int insert;
    static int[] strahler;
    static int[] pruning;
    static int errorCount = 0;

    static void generator() throws IOException {
        int i;
        int ind;
        int ins;
        int numOfElements;
        int rightmost;
        int parent;
        ind = 1;
        parent = 0;
        numOfElements = 0;
        rightmost = 0;
        insert = 0;
        while (rights[ind] != 0 || lefts[ind] != 0) {
            while (rights[ind] != 0) {
                numOfElements = 0;
                parent = ind;
                rightmost = rights[ind];
                ind = rightmost;
            }
            if (lefts[ind] != 0) {
                numOfElements++;
                ind = lefts[ind];
            }
        }
        if (parent == 0) {
            insert = 0;
        } else if (lefts[parent] == 0) {
            insert = parent;
        } else {
            ind = lefts[parent];
            while (rights[ind] != 0) {
                ind = rights[ind];
            }
            insert = ind;
        }
        if (rights[insert] == 0) {
            rights[insert] = rightmost;
        } else {
            lefts[insert] = rightmost;
        }
        rights[rightmost] = 0;
        lefts[rightmost] = 0;
        rights[parent] = 0;
        rightmost++;
        ins = 1;
        while (rights[ins] != 0) {
            ins = rights[ins];
        }
        for (i = 1; i <= numOfElements; i++) {
            rights[ins] = rightmost;
            lefts[rightmost] = 0;
            rights[rightmost] = 0;
            ins = rightmost;
            rightmost++;
        }
        if (insert != 0) {
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
            int next = 1;
            int leftPruning = 0;
            int rightPruning = 0;
            do {
                leftPruning = pruning(next);
                if (rightPruning > leftPruning) {
                    leftPruning = rightPruning;
                }
                next = rights[next];
            } while (next != 0);
            int pruningNumber = getPruningNumber();
            System.out.println("The Pruning no of the tree  is: " + pruningNumber);
            if (strahler[1] != pruningNumber) {
                System.out.println("################################################################ error ################################################################");
                errorCount++;
            }
            out.write(String.valueOf(strahler[1]) + ", " + String.valueOf(pruningNumber) + "\n");
            out.flush();
            clear();
        }
    }

    public static void clear() {
        for (int i = 0; i <= n; i++) {
            strahler[i] = 0;
            pruning[i] = 0;
        }
    }

    public static int pruning(int root) {
        int result = 0;
        int next = 0;
        int index = 0;
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

    public static int getPruningNumber() {
        int index = 1;
        int l = 0;
        if (lefts[index] != 0) {
            l = getPruningNumberRecursively(lefts[index]);
//            index = lefts[index];
            //System.out.println("l="+l);
        }
        int r = 0;
        boolean allRightSubTreeHaveSamePruningNo = true;
        while (rights[index] != 0) {
            index = rights[index];
            int k = getPruningNumberRecursively(index);
            if (r == 0) {
                r = k;
            } else if (k > r) {
                r = k;
                allRightSubTreeHaveSamePruningNo = false;
            } else if (k < r) {
                allRightSubTreeHaveSamePruningNo = false;
            }
            //    System.out.println("k="+k);
        }
        if (r == l) {
            return l + 1;
        }
        return r > l ? r : l;
    }

    private static int getPruningNumberRecursively(int index) {
        if (lefts[index] == 0 && rights[index] == 0) {
            return 1;
        }
        int l = 0, r = 0;
        if (lefts[index] != 0 && index != 0) {

            l = getPruningNumberRecursively(lefts[index]);
        }
//                        l = getPruningNumberRecursively(index);
        if (rights[index] != 0) {
//            index = lefts[index];
            //    System.out.println("in rec l::"+l+" for index:"+index);
            boolean allRightSubTreeHaveSamePruningNo = true;
//            index = lefts[index];
            while (index != 0 && rights[index] != 0) {
                int k = getPruningNumberRecursively(rights[index]);
                index = rights[rights[index]]; //rights[rights[index]]
                if (r == 0) {
                    r = k;
                } else if (k > r) {
                    r = k;
                    allRightSubTreeHaveSamePruningNo = false;
                }
                //         System.out.println("in rec k::"+" for index:"+index);
            }
            if (r == l) {
                return l + 1;
            }
//            return r > l ? r : l;
        }
            return r > l ? r : l;
    }

    private static void print(int n) {
        int p;
        int q;
        int rtptr = -1;
        int a = 0;
        int[] outputArray = new int[2 * n];
        for (int i = 0; i < outputArray.length; i++) {
            outputArray[i] = 555;
        }
        int[] preorderStack = new int[2 * n]; //TODO 2*n?
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

        if (n <= 10) {
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
                        q = preorderStack[preorderStackTop]; //peek
                        preorderStackTop--; //pop
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

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new FileWriter("file.csv"));
        int i;
//        int max;
//        double a;
//        char f;

        n = 10;
        strahler = new int[n + 1];
        pruning = new int[n + 1];
        lefts = new int[n + 1];
        rights = new int[n + 1];

        for (i = 1; i <= n - 1; i++) {
            lefts[i] = 0;
            rights[i] = i + 1;
        }
//        lefts = new int[]{0, 2, 3, 0, 0, 6, 0, 0};
//        rights = new int[]{0, 5, 4, 0, 0, 7, 0, 0};
//        lefts = new int[]{0,2,0,4,0};
//        rights = new int[]{0, 3,0,0,0};
//        lefts = new int[]{0,2 ,3,0,5,6,0, 0,9,0,0, 12,13,0,15,0,17,18,0,0,21,0,0,0};
//        rights = new int[]{0,11,4,0,8,7,0,0,10,0,0,0,14,0,16,0,20,19,0,0,22,0,0,0};
//        lefts = new int[]{0, 2, 3, 0, 0, 6, 0, 0, 0};
//        rights = new int[]{0, 0, 5, 4, 0, 8, 7, 0, 0};
//        lefts = new int[]{0, 2, 3, 4, 0, 0, 0, 0, 0};
//        rights = new int[]{0, 0, 0, 0, 5, 6, 7, 0, 0};
//        lefts = new int[]{0, 2, 3, 4, 5, 0, 7, 0, 0};
//        rights = new int[]{0, 0, 0, 0, 0, 6, 8, 0, 0};
//        lefts = new int[]{0, 2, 3, 0, 0, 6, 0 , 0};
//        rights = new int[]{0, 5, 4, 0, 0, 7,0,0};

//        lefts[1] = 2;
//        rights[1] = 4;
//        rights[2] = 3;
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
        int next = 1;
        int leftPruning = 0;
        int rightPruning = 0;
        do {
            leftPruning = pruning(next);
            if (rightPruning > leftPruning) {
                leftPruning = rightPruning;
            }
            next = rights[next];
        } while (next != 0);
        int pruningNumber = getPruningNumber();
        System.out.println("The Pruning no of the tree  is: " + pruningNumber);
        if (strahler[1] != pruningNumber) {
            System.out.println("################################ error ##################################");
            errorCount++;
        }
        out.write(String.valueOf(strahler[1]) + ", " + String.valueOf(pruningNumber) + "\n");
        out.flush();
        clear();

        while (insert != 0) {
            System.out.println("\n________________________________");
            generator();
            if (insert != 0) {
                numberOfTrees++;
            }
        }
        System.out.println("\n________________________________");
        System.out.println("Number of all trees: " + numberOfTrees);
        System.out.println("Error count is: " + errorCount);
        out.close();
    }

}
