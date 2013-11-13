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

    static BinaryTreeNode t;
    static BinaryTreeNode p;
    static BinaryTreeNode[] s;
    static int top = -1;
    static int i = 0;
    static BinaryTreeNode q;
    static BinaryTreeNode rtptr;
    static int n;

    static int noOfTrees;

    static BinaryTreeNode predp;
    int[] leftNode;
    int[] rightNode;

    //create methods
    static int[] tree_input;
    static int info = 1;
    static int topavail = -1;

    public static void preorderNormal(BinaryTreeNode t) {
        p = t;
        top = -1;
        i = 0;

        while (p != null || top != -1) {
            if (p != null) {
                visitNormal(p);

                s[++top] = p; //push(p,s)
                if (p.getLeftTree() != null) {
                    p = p.getLeftTree();
                } else {
                    p = p.getRightTree();
                }
            } else {
                do {
                    q = s[top];
                    top--;
                    if (top != -1) {//is s is not empty
                        BinaryTreeNode newTop = s[top];
                        rtptr = newTop.getRightTree();
                    } else {
                        rtptr = null;
                    }
                } while (top != -1 && q == rtptr);
                p = rtptr;
            }
        }
    }

    private static void visitNormal(BinaryTreeNode p) {
        System.out.println(p);
    }

    public static void preorderBinaryTree(BinaryTreeNode t) {
        // t = initialization();
////        Write and test a non-recursive program "create", 
//        that reads only the sequence of n 0's and n 1's
//        (i.e. the n right and n left parentheses) representing an n node 
//                binary tree as we discussed in class and creates the standard  
//                linked representation of the tree in memory with a node assigned
//                an info value i if it is the ith node visited in a preorder 
//                traversal. This program should echo the input. After the tree 
//                is created, output its parentheses representation with each left
//                parenthesis succeeded by its info value.  For example, if the 
//                input is (()())() then the output parenthesis representation 
//                should appear as (1(2)(3))(4)
        p = t;
        top = -1;
        i = 0;

        while (p != null || top != -1) {
            if (p != null) {
                visitBinTreeNode(p);

                s[++top] = p; //push(p,s)
                if (p.getLeftTree() != null) {
                    p = p.getLeftTree();
                } else {
                    System.out.print(")");
                    i++;
                    p = p.getRightTree();
                }
            } else {
                do {
                    q = s[top];
                    top--;
                    if (top != -1) {//is s is not empty
                        BinaryTreeNode newTop = s[top];
                        rtptr = newTop.getRightTree();
                    } else {
                        rtptr = null;
                    }
                } while (top != -1 && q == rtptr);

                p = rtptr;

                if (i < n) {
                    i++;
                    System.out.print(")");
                }
            }
        }
    }

    private static void visitBinTreeNode(BinaryTreeNode p) {
        System.out.print("(" + p.getInfo());
    }

    public static BinaryTreeNode create(BinaryTreeNode t) {
        BinaryTreeNode p = t;
        BinaryTreeNode predp = t;
        i = 0;
        //visitCreate
        while ((p != null || top != -1) && (i < 2 * n - 1)) {
            if (p != null) {
                visitCreate(p);

                s[++top] = p; //push(p,s)
                if (p.getLeftTree() != null) {
                    p = p.getLeftTree();
                } else {
                    p = p.getRightTree();
                }
            } else {
                do {
                    predp = s[top];
                    if (predp.getRightTree() == null && predp.getLeftTree() != null) {
                        if ((i < tree_input.length - 1)) {
                            if (tree_input[i] == 1) {
                                p = new BinaryTreeNode();
                                rtptr = p;
                                s[top].setRightTree(rtptr);
                                break;
                            } else {
                                top--;
                                i++;
                                if (top != -1) {//is s is not empty
                                    BinaryTreeNode newTop = s[top];
                                    rtptr = newTop.getRightTree();
                                } else {
                                    rtptr = null;
                                }
                            }
                        }
                    } else {
                        top--;
                        if (top != -1) {//is s is not empty
                            BinaryTreeNode newTop = s[top];
                            rtptr = newTop.getRightTree();
                        } else {
                            rtptr = null;
                        }
                    }
                } while (top != -1 && predp == rtptr);
                p = rtptr;
            }
        }
        return t;
    }

    private static void visitCreate(BinaryTreeNode p) {
        System.out.println("*******************NODE " + info + "**********");
        i++;
        p.setInfo(info);

        if (tree_input[i] == 1) {
            p.setLeftTree(new BinaryTreeNode());

        } else if ((i < tree_input.length - 1) && tree_input[i + 1] == 1) {
            p.setRightTree(new BinaryTreeNode());
            i++;
        } else {
            i = i + 2;
        }
        System.out.println(p);
        info++;
    }

    public static void main(String[] args) {
//        int[] inputarray = {1, 1, 1, 1, 1, 0, 0, 0, 0, 0};
//        n = 5;
//        int[] inputarray={1, 1, 0, 1, 0, 0, 1, 0}; n=4;
//        int[] inputarray={1,0,1,0,1,0,1,0,1,0}; n=5;
        int[] inputarray = {1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0};
        n = 13;

        t = new BinaryTreeNode();
        tree_input = inputarray;
        s = new BinaryTreeNode[n];
        create(t);

        System.out.println("***************************");
        System.out.println("Input:");
        for (int i = 0; i < inputarray.length; i++) {
            int j = inputarray[i];
            if (j == 1) {
                System.out.print("( ");
            } else {
                System.out.print(")");
            }
        }
        System.out.println("\nTree representation:");
        s = new BinaryTreeNode[n];
        preorderNormal(t);
        System.out.println("Input is:");
        for (int i = 0; i < inputarray.length; i++) {
            int j = inputarray[i];
            if (j == 1) {
                System.out.print("( ");
            } else {
                System.out.print(")");
            }
        }
        System.out.println("\nParenthesis representation:");
        s = new BinaryTreeNode[n];
        preorderBinaryTree(t);
    }
}
