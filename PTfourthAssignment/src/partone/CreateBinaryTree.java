
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partone;

/**
 *
 * @author gligo_000
 */
public class CreateBinaryTree {

    static BinaryTreeNode t;
    static BinaryTreeNode p;
    static int i = 0;
    static BinaryTreeNode s[];
    static int top = -1;

    static int lefts[];
    static int rights[];
    static int first;

    //Modified Robson Traversal
    static BinaryTreeNode topRobson;
    static BinaryTreeNode stackRobson;
    static BinaryTreeNode predp = new BinaryTreeNode();
    static BinaryTreeNode avail = new BinaryTreeNode();
    static boolean checkIfDone = false;

    /**
     * Initializes Binary Tree.
     *
     * @param firstInput checks if first input is 1 and creates root node.
     * @return pointer to the root of created binary tree.
     */
    public static BinaryTreeNode initialize(int firstInput) {
        BinaryTreeNode node = null;
        if (firstInput == 1) {
            node = new BinaryTreeNode();
        }
        return node;
    }

    /**
     * Creates left and right node of Node currently traversed.
     *
     * @param node BinaryTreeNode currently traversed.
     */
    static void visit(BinaryTreeNode node) {
        if (lefts[i] == 1) {
            node.setLeftTree(new BinaryTreeNode());
        }
        if (rights[i] == 1) {
            node.setRightTree(new BinaryTreeNode());
        }
        i++;
        p.setInfo(i);
        System.out.println(i + " , " + lefts[i - 1] + " , " + rights[i - 1]);

    }

    /**
     * Creates Binary Tree.
     *
     * @return pointer to the root of the created binary tree.
     */
    static BinaryTreeNode create() {
        t = initialize(first);
        p = t;

        while (p != null || top != -1) {
            if (p != null) {
                visit(p);
                s[++top] = p; //push(p, s)
                if (p.getLeftTree() != null) {
                    p = p.getLeftTree();
                } else {
                    p = p.getRightTree();
                }
            } else {
                BinaryTreeNode q = null;
                BinaryTreeNode rtprt = null;
                do {
                    q = s[top--];

                    if (top != -1) {
                        rtprt = s[top].getRightTree();
                    } else {
                        rtprt = null;
                    }
                } while (top != -1 && q.equals(rtprt));
                p = rtprt;
            }
        }
        return t;
    }

    /**
     * Preorder traverse for Binary Trees implementation.
     *
     * Write and test a program "create", that comes from adapting the preorder
     * traversal program that uses a stack as we discussed in class. Its input
     * will be 0 for a null tree and 1 followed by the preorder sequence of
     * subtree information we used in class. Create should return a pointer to
     * the root of the tree it has created in memory using a linked
     * representation.
     *
     * This means each node of the tree in memory will have three members -
     * info, lt, and rt. Lt points to the left successor node and rt points to
     * the right successor node. It should assign i to the info member of the
     * ith node. Have your program echo the input.
     */
    static void preorder() {
        p = t;

        while (p != null || top != -1) {
            if (p != null) {
                visitPrint(p);
                s[++top] = p; //push(p, s)
                if (p.getLeftTree() != null) {
                    p = p.getLeftTree();
                } else {
                    p = p.getRightTree();
                }
            } else {
                BinaryTreeNode q = null;
                BinaryTreeNode rtprt = null;
                do {
                    q = s[top--];

                    if (top != -1) {
                        rtprt = s[top].getRightTree();
                    }
                } while (s != null && q.equals(rtprt));
                p = rtprt;
            }
        }
    }

    /**
     * Prints out the information about node that is currently traversed.
     *
     * @param p Node that is currently traversed.
     */
    private static void visitPrint(BinaryTreeNode p) {
        if (p.getLeftTree() != null && p.getRightTree() != null) {
            System.out.println(p.getInfo() + " " + p.getLeftTree().getInfo()
                    + " " + p.getRightTree().getInfo());
        } else if (p.getLeftTree() != null && p.getRightTree() == null) {
            System.out.println(p.getInfo() + " " + p.getLeftTree().getInfo()
                    + " x");
        } else if (p.getLeftTree() == null && p.getRightTree() != null) {
            System.out.println(
                    p.getInfo() + " x " + p.getRightTree().getInfo());
        } else {
            System.out.println(p.getInfo() + " x x");
        }
    }

    /**
     * Write and test a "modified" Robson Traversal program that uses the linked
     * representation of the trees. This modified version differs from the
     * Robson only in that the pointers to a node's predecessor should now be as
     * in the Linked Inversion Traversal. That is, when a node's left(right)
     * subtree is being traversed, its left(right) pointer will point to its
     * predecessor. During the traversal, when a node is visited, output for
     * each "stack' entry, its info value and the info value of its left and
     * right successors. This means, first, output the number of the node Top
     * points to, and then the number that that node's left and right pointers
     * point to. Then, output the number of the node that Stack points to,and
     * then the numbers that that node's left and right pointers point to. Do
     * this for each node on the "stack". Also, output similar information for
     * each node along the path from the predecessor of the node being visited
     * to the root. That is, for each of these nodes output its info value and
     * the numbers that the nodes left left and right pointers point to.
     */
    public static void modifiedRobsonTraversal() {
        if (t != null) {
            predp = t;
            p = t;
            topRobson = null;
            stackRobson = null;
            boolean end = false;
            while (true) {

                while (p.getLeftTree() != null || p.getRightTree() != null) {
                    if (end) {
                        if (t.equals(p)) {
                            return;
                        }
                    }
                    if (p.getLeftTree() != null) {
                        visitRobson(p, stackRobson, topRobson);
                        BinaryTreeNode n = p.getLeftTree();
                        p.setLeftTree(predp);
                        predp = p;
                        p = n;
                        end = true;
                    } else if (p.getRightTree() != null) {
                        visitRobson(p, stackRobson, topRobson);
                        BinaryTreeNode n = p.getRightTree();
                        p.setRightTree(predp);
                        predp = p;
                        p = n;
                        end = true;
                    }
                }

                visitRobson(p, stackRobson, topRobson);
                avail = p;

                if (t.equals(p)) {
                    return;
                } else {
                    while (!p.equals(t)) {
                        if (predp.getRightTree() == null) { //coming from left
                            BinaryTreeNode n = predp.getLeftTree(); // pointed
                            //at the predecessor which is now the next one to go
                            predp.setLeftTree(p); //restore the pointer 
                            //of predp to point the node you were
                            p = predp; //go one up
                            predp = n; //this is new current node, the 
                            //predp was stored in n
                            //now if p==t_root that means that you are 
                            //back to the root and it has a nll right subree,
                            //therefore you are finished
                            continue;
                        }
                        if (predp.getLeftTree() == null) { //coming from right
                            BinaryTreeNode n = predp.getRightTree(); // pointed
                            //at the predecessor which is now the next one to go
                            predp.setRightTree(p); //restore the pointer of 
                            //predp to point the node you were
                            p = predp; //go one up
                            predp = n; //this is new current node, 
                            //the predp was stored in n.
                            //now if p==t_root that means that you are back 
                            //to the root and it has a nll right subree,
                            //therefore you are finished
                            continue;
                        }
                        if (predp.equals(topRobson)) {
                            System.out.println("Right subtree finished.");
                            BinaryTreeNode n = stackRobson; //Find where to 
                            //go next, fix the links of the last avail 
                            topRobson = stackRobson.getRightTree(); //start 
                            //ascending, find next top
                            stackRobson = stackRobson.getLeftTree();
                            n.setLeftTree(null);
                            n.setRightTree(null);
                            n = predp.getRightTree();
                            predp.setRightTree(p);
                            p = predp;
                            predp = n;
                            continue;
                        } else {
                            avail.setLeftTree(stackRobson); //for the most 
                            //recent available point it's left link to the 
                            //stack avail.setRightTree(topRobson); 
                            //and it's right link to the top
                            stackRobson = avail; //now store it to the 
                            //stack so that next time you find an 
                            //available node you can store it to avail
                            topRobson = predp; //you finished the 
                            //left subtree and starting the right 
                            //subtree of predp, this is top
                            BinaryTreeNode n = predp.getRightTree();//you start
                            //the right subtree of the predp
                            BinaryTreeNode temp = predp.getLeftTree();
                            predp.setLeftTree(p);
                            predp.setRightTree(temp);
                            p = n;
                            break;
                        }
                    }
                }
            }
        } else {
            System.out.println("This is NULL tree.");
        }
    }

    /**
     *
     * Prints out the information about node currently traversed.
     *
     * @param p BinaryTreeNode currently traversed
     * @param stackRobson current Stack node
     * @param topRobson current topRobson node
     */
    private static void visitRobson(BinaryTreeNode p,
            BinaryTreeNode stackRobson, BinaryTreeNode topRobson) {
        System.out.println("~~~NODE VISITED: ");
        System.out.println(p);
        System.out.println("***TOP: ");
        if (topRobson != null) {
            System.out.println(topRobson);
        } else {
            System.out.println("NULL");
        }

        System.out.println("***PREVIOUS: ");
        BinaryTreeNode temp1 = predp;
        if (predp != null) {
            System.out.println(predp);
            while (!temp1.equals(t)) {
                if (temp1.getRightTree() != null && temp1.getLeftTree() == null) {
                    temp1 = temp1.getRightTree();
                } else if (temp1.getRightTree() == null && temp1.getLeftTree() != null) {
                    temp1 = temp1.getLeftTree();
                } else {
                    if (temp1.equals(topRobson)) {
                        temp1 = temp1.getRightTree();
                    } else {
                        if (stackRobson != null) {
                            if (temp1.equals(stackRobson.getRightTree())) {
                                temp1 = temp1.getRightTree();
                            } else if (!temp1.equals(t) && !temp1.equals(stackRobson.getRightTree())) {
                                temp1 = temp1.getLeftTree().getRightTree();
                            }
                        }else{
                            temp1 = temp1.getLeftTree();
                        }
                    }
                }
                System.out.println(temp1);

//                System.out.println(temp1);
//                if (temp1.getInfo() > temp1.getLeftTree().getInfo()) {
//                    temp1 = temp1.getRightTree();
//                } else if (temp1.getInfo() < temp1.getLeftTree().getInfo()) {
//                    temp1 = temp1.getLeftTree();
//                } else {
//                    break;
//                }
//                if (temp1 != null && temp1.getLeftTree() != null) {
//                    temp1 = temp1.getLeftTree();
//                    temp1 = temp1.getRightTree();
//                }
            }

        } else {
            System.out.println("NULL");
        }

        BinaryTreeNode temp = stackRobson;
        System.out.println("***STACK POINTERS");
        if (stackRobson == null) {
            System.out.println("NULL");
        } else {
            while (temp != null) {
                System.out.println(temp);
                temp = temp.getLeftTree();
            }
        }

        temp = stackRobson;
        System.out.println("***ELEMENTS ON STACK");
        while (temp != null) {
            if (temp.getRightTree() != null) {
                BinaryTreeNode stackElement = temp.getRightTree();
                System.out.println(stackElement);
            }
            temp = temp.getLeftTree();
        }
    }

    public static void main(String[] args) {
        first = 1;
//        lefts = new int[]{1, 1, 0, 0, 0};
//        rights = new int[]{1, 0, 0, 1, 0};

        lefts = new int[]{1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0};
        rights = new int[]{1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0};
//        lefts = new int[]{0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0};
//        rights = new int[]{1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0};

//        lefts = new int[]{0};
//        rights = new int[]{0};
        //initialization
        s = new BinaryTreeNode[lefts.length];
        t = create();

        //traversal
        top = -1;
        modifiedRobsonTraversal();
        System.out.println("###########################"
                + "########################################");
        System.out.println("###########Preorder traversal "
                + "after Modified Robson################");
        System.out.println("################################"
                + "###################################");
        preorder();

    }

}
