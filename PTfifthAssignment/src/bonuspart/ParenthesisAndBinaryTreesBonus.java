/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonuspart;

import node.BinaryTreeNode;

/**
 *
 * @author gligo_000
 */
public class ParenthesisAndBinaryTreesBonus {

    static int N;

    static BinaryTreeNode create(int root, int[] leftArray, int[] rightArray) {
        BinaryTreeNode leftTree = null;
        BinaryTreeNode rightTree = null;
        BinaryTreeNode rootNode = null;
        BinaryTreeNode rtptr = null;
        BinaryTreeNode t = null;
        BinaryTreeNode p = null;
        BinaryTreeNode q = null;
        int counter = 1;
        int cinLeft = 0;
        int cinRight = 0;
        BinaryTreeNode[] nodeStack = new BinaryTreeNode[leftArray.length + rightArray.length];
        int stackIterator = -1;
        int arrayIndex = 0;
        int currentIndex = 0;

        if (root == 0) {
            t = null;
        } else {
            rootNode = new BinaryTreeNode();
            t = rootNode;
        }
        p = t;
        while (p != null || stackIterator >= 0) {
            if (p != null) {
                //Now visit(p) is modify info memeber, create subtrees
                p.setInfo(counter++);
                currentIndex = arrayIndex++;

                cinLeft = leftArray[currentIndex];
                cinRight = rightArray[currentIndex];
                if (cinLeft == 0) {
                    p.setLeftTree(null);
                } else {
                    leftTree = new BinaryTreeNode();
                    p.setLeftTree(leftTree);
                }
                if (cinRight == 0) {
                    p.setRightTree(null);
                } else {
                    rightTree = new BinaryTreeNode();
                    p.setRightTree(rightTree);
                }
                //And push p to the stack
                nodeStack[++stackIterator] = p;
                //And mode to the next node
                if (p.getRightTree() != null) {
                    p = p.getLeftTree();
                } else {
                    p = p.getRightTree();
                }
            } else {
                do {
                    q = nodeStack[stackIterator];
                    stackIterator--;
                    if (stackIterator > -1) {
                        rtptr = nodeStack[stackIterator].getRightTree();
                    } else {
                        rtptr = null;
                    }

                } while (stackIterator >= 0 && q.equals(rtptr));
                p = rtptr;
            }
        }
        return t;
    }

    static void preorderTraversal(BinaryTreeNode root) {
        BinaryTreeNode p = null;
        BinaryTreeNode pr;
        BinaryTreeNode pl;
        BinaryTreeNode leftTreeNode;
        BinaryTreeNode righTreeNode;
        BinaryTreeNode rootTreeNode;
        BinaryTreeNode rtptr;
        BinaryTreeNode q;
        BinaryTreeNode[] nodeStack = new BinaryTreeNode[2 * N];
        int stackIterator = -1;
        if (root == null) {
            System.out.println("This is a NULL binary tree.");
        } else {
            p = root;
            while (p != null || stackIterator >= 0) {
                if (p != null) {
                    //now visit(p) is print the info and info of left and right node
                    System.out.println(p);

//                pl = p.getLeftTree()==null ? null : p.getLeftTree();
//                pr = p.getRightTree()==null ? null : p.getRightTree();
                    nodeStack[++stackIterator] = p;
                    //And move to the next node
                    if (p.getLeftTree() != null) {
                        p = p.getRightTree();
                    } else {
                        p = p.getRightTree();
                    }
                } else {
                    do {
                        q = nodeStack[stackIterator];
                        stackIterator--;
                        if (stackIterator >= 0) {
                            rtptr = nodeStack[stackIterator].getRightTree();
                        } else {
                            rtptr = null;
                        }

                    } while (stackIterator >= 0 && q.equals(rtptr));
                    p = rtptr;
                }
            }
        }
    }

    public static void main(String[] args) {
        N = 4;
        int rootOfTree = 0;
        BinaryTreeNode rootNode = null;
        int[] preorderStack = new int[2 * N];
        int preorderStackIterator = -1;

        int[] L = new int[N];
        int[] R = new int[N];
        for (int i = 0; i < N; i++) {
            L[i] = 0;
            R[i] = 0;
        }
//        int[] inputArray = new int[2*N];
////        int k =0;
//        for (int k = 0; k < 2*N; k++) {
//            inputArray[k]=0;
//        }
        //int[] inputArray = new int[2*N]
//         (1(2)(3))(4)
        int[] inputArray = {1,1,0,1,0,0,1,0};
        int leftCount = -1;
        int rightCount = 0;
        int nodeNumber = 1;
        char arrayToFillNext = 'L';
        if (inputArray[0] == 0) {
            System.out.println("This is an empty tree.");
            rootOfTree = 0;
        } else if (inputArray[0] == 1) {
            rootOfTree = 1;
            for (int k = 1; k <= 2 * N; k++) {
                if (inputArray[k] == 1) {
                    if (arrayToFillNext == 'L') {
                        L[++leftCount] = (++nodeNumber <= N ? nodeNumber : 0);
                        preorderStack[++preorderStackIterator] = leftCount;
                    } else {
                        if (preorderStackIterator >= 0) {
                            rightCount = preorderStack[preorderStackIterator];
                            preorderStackIterator--;
                            R[rightCount] = (++nodeNumber <= N ? nodeNumber : 0);
                            arrayToFillNext = 'L';
                        } else if (preorderStackIterator == -1) {
                            break;
                        }
                    }
                } else if (inputArray[k] == 0) {
                    if (arrayToFillNext == 'L') {
                        L[++leftCount] = 0;
                        if (inputArray[k + 1] == 0) {
                            R[leftCount] = 0;
                            arrayToFillNext = 'R';
                            k = k + 1;
                        } else if (inputArray[k + 1] != 0) {
                            R[leftCount] = (++nodeNumber <= N ? nodeNumber : 0);
                            arrayToFillNext = 'L';
                            k = k + 1;
                        }
                    } else if (arrayToFillNext == 'R') {
                        if (preorderStackIterator >= 0) {
                            rightCount = preorderStack[preorderStackIterator];
                            preorderStackIterator--;
                            R[rightCount] = 0;
                        } else if (preorderStackIterator == -1) {
                            break;
                        }
                    }
                }
            }
            System.out.println("The corresponding arrays are: ");
            System.out.println("L: ");
            for (int k = 0; k < N - 1; k++) {
                System.out.print(" " + L[k]);
            }
            System.out.println("\nR:");
            for (int k = 0; k < N - 1; k++) {
                System.out.print(" " + R[k]);
            }
            System.out.println("\n");
            //now create linked representation
            rootNode = create(rootOfTree, L, R);
            //and traverse it
            preorderTraversal(rootNode);
        }
    }
}
