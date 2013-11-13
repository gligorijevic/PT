/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parenthesisandainarytrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Djordje z
 * 
 */
public class ParenthesisAndBinaryTreesOld {

    static char giveNextTree = 'y';
    static char manual = 'n';
    static int n = 0;
    static int i = 0;
    static int j = 0;
    static int[] pointerL;
    static int[] pointerR;
    static int stackCounter = 1;
    static char lastPositionArray = 'N';
    static int lastPositionIndex = 0;
    static int[] availablePositionStack;
    static int availablePositionStackIterator = -1;
    static int[] nextOneToMoveStack;
    static int nextOneToMoveStackIterator = -1;
    static int noOfTree = 0;
    static int temp = 0;
    static int tempIndex = 0;
    static int lastOneChosen = 0;
    static int pending = 0;
    static int lastTreeFound = 0;

    static void print(int noOfNodes, int[] arrayLeft, int[] arrayRight) {
        int p = 0;
        int q = 0;
        int rtptr = 0;
        int a = 0;
        int[] outputArray = new int[2 * n];
        int[] preorderStack = new int[2 * n]; //TODO 2*n?
        int preorderStackIterator = -1;

        if (n <= 5) {
            System.out.println("\nTree number: " + (++noOfTree));
            System.out.println("Left: ");
            for (int k = 0; k < n; k++) {
                System.out.print(" " + arrayLeft[k]);
            }
            System.out.println("");
            System.out.println("Right: ");
            for (int k = 0; k < n; k++) {
                System.out.print(" " + arrayRight[k]);
            }
            //Preorder
//            for (int k = 0; k < 2 * n; k++) {
//                outputArray[k] = 0;
//            }
            p = 1;
            while (p != -1 || preorderStackIterator >= 0) {
                if (p != -1) {
                    //visit p
                    outputArray[a++] = 1;
                    //push p into the stack
                    preorderStack[++preorderStackIterator] = p-1; //push
                    //and go to the next
                    if (arrayLeft[p-1] != 0) {
                        p = arrayLeft[p-1];
                    } else {
                        outputArray[a++] = 0;
                        p = arrayLeft[p-1];
                    }

                } else {
                    outputArray[a++] = 0;
                    do {
                        q = preorderStack[preorderStackIterator]; //peek
                        preorderStackIterator--; //pop
                        if (preorderStackIterator >= 0) {
                            rtptr = arrayRight[preorderStack[preorderStackIterator]-1];
                        } else {
                            rtptr = -1;
                        }
                    } while (preorderStackIterator >= 0 && q == rtptr);
                    p = rtptr;
                }
            }
            System.out.println("\nOutput array:");
            for (int k = 0; k < 2 * n; k++) {
                System.out.print(" " + outputArray[k]);
            }
        } else {
            ++noOfTree;
        }

    }

    static void generate() throws IOException {
        int nextPosition = 0;
        int next = 0;
        boolean foundIt = true;
        boolean search_for_next = true;
        i = 0;
        lastOneChosen = next;
        while (search_for_next) {
//            if (availablePositionStackIterator == -1) { // ako cemo sa ovom petljom ovo mora da leti
            while (foundIt) {
                //SEARCH FOR NEXT

                //Find next node to move
                if (pointerR[i] == 0) {
                    if (pointerL[i] != 0) {
                        //Push the stack of available positions and go left
                        availablePositionStack[++availablePositionStackIterator] = i;
                        System.out.println("Available position is: " + i);
                        next = pointerL[i];
                        lastPositionArray = 'L';
                        lastPositionIndex = i;
                        i = next;
                        foundIt = true;
                    } else if (pointerL[i] == 0) {
                        next = i;
                        i = next;
                        foundIt = false;
                        lastPositionArray = 'L';
                        lastPositionIndex = i - 1;
                    }
                } else if (pointerR[i] != 0) {
                    temp = pointerR[i];
                    tempIndex = i;
                    if (pointerR[temp] == 0 && pointerL[temp] == 0) {
                        next = temp;
                        lastPositionArray = 'R';
                        lastPositionIndex = tempIndex;
                        foundIt = false;
                        i = temp;
                    } else if (pointerR[temp] != 0 || pointerL[temp] != 0) {
                        i = temp;
                        foundIt = true;
                    }
                }
//                }
            }
            if (next == lastOneChosen) {
                j = 0;
                pending = 0;
                while (pointerR[j] != 0) {
                    if (pointerL[j] != 0) {
                        pending = j;
                    }
                    j = pointerR[j];
                }
                if (pending != 0) {
                    pointerR[pending] = 0;
                    i = 0;
                    //GOTO SEARCH NEXT ===== BREAK!?!?!?
                    break;
                } else if (pending == 0) {
                    lastTreeFound = 1;
                    return;
                }
            }
            //Once you found next one to move, move it
            while (availablePositionStackIterator >= 0) {
                //and delete it
                if (lastPositionArray == 'L') {
                    pointerL[lastPositionIndex] = 0;
                } else if (lastPositionArray == 'R') {
                    pointerR[lastPositionIndex] = 0;
                }
                //and move it
                nextPosition = availablePositionStack[availablePositionStackIterator];
                pointerR[nextPosition] = next;
                //and keep info
                lastPositionArray = 'R';
                lastPositionIndex = nextPosition;
                //and take it's tale with it
                if (next != n) {
                    for (int k = next; k < n; k++) {
                        pointerL[k] = k + 1;
                        pointerR[k] = 0;
                    }
                }
                //and print
                print(n, pointerL, pointerR);
                if (manual == 'y') {
                    System.out.println("\nDo you want to see the next one?");
                    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    String s = bufferRead.readLine();
                    if (s.equals("n")) {
                        lastTreeFound = 1;
                        return;
                    }
                }
                //and pop from the stack
                if (next != n) {
                    i = 0;
                    availablePositionStack = nextOneToMoveStack;
                    lastOneChosen = next;
                    //GOTO SEARCH FOR NEXT
                    break;
                }
                availablePositionStackIterator--;
                if (nextPosition == 1) {
                    pointerR[1] = 0;
                    i = 0;
                    lastOneChosen = next;
                    //GOTO SEARCH FOR NEXT
                    break;
                }
            }
            if (nextPosition == 1 && availablePositionStackIterator == -1) {
                //delete it
                if (lastPositionArray == 'L') {
                    pointerL[lastPositionIndex] = 0;
                } else if (lastPositionArray == 'R') {
                    pointerR[lastPositionIndex] = 0;
                }
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int currentIndex = 0;
        int inputIndex = -1;
        //DEFINITION
        n = 5;
        availablePositionStack = new int[2 * n];
        nextOneToMoveStack = new int[2 * n];
        //initialize arrays
        int[] L = new int[n];
        int[] R = new int[n];
//        for (int k = 0; k <= n - 1; k++) {
//            L[k] = 0;
//            R[k] = 0;
//        }
        pointerL = L;
        pointerR = R;
        System.out.println("\nDo you want to insert the array representaton of the tree? (y/n)");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferRead.readLine();
        if (s.equals("n")) {
            manual = 'n';
            //Create the first array
//            L[0] = R[0] = 0;
            for (int k = 0; k < n - 1; k++) {
                L[k] = k + 1;
                R[k] = 0;
            }
            L[n - 1] = R[n - 1] = 0;
            //And print
            print(n, pointerL, pointerR);
        } else if (s.equals("y")) {
            manual = 'y';
            System.out.println("Insert the arrays or -1 -1 to finish");
            String line = bufferRead.readLine();
            String[] parts = line.split(" ");
            int inLeft = Integer.parseInt(parts[0]);
            int inRight = Integer.parseInt(parts[1]);
            while (inLeft != -1 && inRight != -1) {
                currentIndex = ++inputIndex;
                L[currentIndex] = inLeft;
                R[currentIndex] = inRight;
                line = bufferRead.readLine();
                parts = line.split(" ");
                inLeft = Integer.parseInt(parts[0]);
                inRight = Integer.parseInt(parts[1]);
            }
            //And print
            System.out.println("\nYou have entered the tree: ");
            print(n, pointerL, pointerR);
            System.out.println("\nAnd the next tree in lexicographical order is: ");
        }
        while (lastTreeFound == 0) {
            generate();
        }
        if (manual == 'n') {
            System.out.println("\nTotal number of trees: " + noOfTree);
        } else {
            return;
        }

    }

}
