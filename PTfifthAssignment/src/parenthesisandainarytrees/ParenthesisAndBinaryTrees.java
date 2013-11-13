package parenthesisandainarytrees;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Djordje Gligorijevic
 */
public class ParenthesisAndBinaryTrees {

    static int n;
    static int[] lefts = new int[50];
    static int[] rights = new int[50];
    static double numberOfTrees;
    static int insert;

    static void generator() {
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
        }
    }

    public static void main(String[] args) {
        int i;
        int max;
        double a;
        char f;

        n = 4;
//        for (i = 1; i <= n - 1; i++) {
//            lefts[i] = 0;
//            rights[i] = i + 1;
//        }

        lefts[1] = 2;
        rights[1] = 4;
        rights[2] = 3;

        rights[n] = 0;
        lefts[n] = 0;
        insert = 1;
        numberOfTrees = 0;
        print(n);
        while (insert != 0) {

            System.out.println("\n________________________________");
            generator();
            if (insert != 0) {
                numberOfTrees++;
            }
        }

        System.out.println("\n________________________________");
        System.out.println("Number of all trees: " + numberOfTrees);
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

        if (n <= 5) {
            p = 1;
            while (p != 0 || preorderStackTop != -1) {
                if (p != 0) {
                    //visit p
                    if (a == 2 * n) {
                        break;
                    }
                    outputArray[a++] = 1;
                    //push p into the stack
                    preorderStack[++preorderStackTop] = p; //push
                    //and go to the next
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
//        else {
//            ++noOfTree;
//        }

    }

}
