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

    public static BinaryTreeNode initialize(int firstInput) {
        BinaryTreeNode node = null;
        if (firstInput == 1) {
            node = new BinaryTreeNode();
        }
        return node;
    }

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

    private static void visitPrint(BinaryTreeNode p) {
        if (p.getLeftTree() != null && p.getRightTree() != null) {
            System.out.println(p.getInfo() + " " + p.getLeftTree().getInfo() + " " + p.getRightTree().getInfo());
        } else if (p.getLeftTree() != null && p.getRightTree() == null) {
            System.out.println(p.getInfo() + " " + p.getLeftTree().getInfo() + " x");
        } else if (p.getLeftTree() == null && p.getRightTree() != null) {
            System.out.println(p.getInfo() + " x " + p.getRightTree().getInfo());
        } else {
            System.out.println(p.getInfo() + " x x");
        }
    }

    public static void modifiedRobsonTraversal() {
        if (t != null) {
            predp = t;
            p = t;
            topRobson = null;
            stackRobson = null;

            while (p.getLeftTree() != null || p.getRightTree() != null) {
                if (p.getLeftTree() != null) {
                    visitRobson(p, stackRobson, topRobson);
                    BinaryTreeNode n = p.getLeftTree();
                    p.setLeftTree(predp);
                    predp = p;
                    p = n;
                } else if (p.getRightTree() != null) {
                    visitRobson(p, stackRobson, topRobson);
                    BinaryTreeNode n = p.getLeftTree();
                    n = p.getRightTree();
                    p.setRightTree(predp);
                    predp = p;
                    p = n;
                }
                visitRobson(p, stackRobson, topRobson);
                avail = p;
                do {
                    if (p == t) {
                    }

                } while (true);

            }

        }

    }

    public static void main(String[] args) {
        first = 1;
        lefts = new int[]{1, 1, 0, 0, 0};
        rights = new int[]{1, 0, 0, 1, 0};

        //initialization
        s = new BinaryTreeNode[lefts.length];
        t = create();

        top = -1;
        preorder();

    }

    private static void visitRobson(BinaryTreeNode p, BinaryTreeNode stackRobson, BinaryTreeNode topRobson) {
        System.out.println("**NODE VISITED: ");
        if (p.getLeftTree() != null && p.getRightTree() != null) {
            System.out.println(p.getInfo() + " " + p.getLeftTree().getInfo() + " " + p.getRightTree().getInfo());
        } else if (p.getLeftTree() != null && p.getRightTree() == null) {
            System.out.println(p.getInfo() + " " + p.getLeftTree().getInfo() + " x");
        } else if (p.getLeftTree() == null && p.getRightTree() != null) {
            System.out.println(p.getInfo() + " x " + p.getRightTree().getInfo());
        } else {
            System.out.println(p.getInfo() + " x x");
        }

        System.out.println("***TOP: ");
        if (topRobson != null) {
            System.out.println("Info: " + topRobson.getInfo() + " ,left: " + topRobson.getLeftTree().getInfo() + " ,right: " + topRobson.getRightTree().getInfo());

        } else {
            System.out.println("NULL");
        }

        BinaryTreeNode temp = stackRobson;
        System.out.println("***STACK POINTERS");
        if (stackRobson == null) {
            System.out.println("NULL");
        } else {
            while (temp != null) {
                if (temp.getLeftTree() != null && temp.getRightTree() != null) {
                    System.out.println(temp.getInfo() + " " + temp.getLeftTree().getInfo() + " " + temp.getRightTree().getInfo());
                } else if (temp.getLeftTree() != null && temp.getRightTree() == null) {
                    System.out.println(temp.getInfo() + " " + temp.getLeftTree().getInfo() + " x");
                } else if (temp.getLeftTree() == null && temp.getRightTree() != null) {
                    System.out.println(temp.getInfo() + " x " + temp.getRightTree().getInfo());
                } else {
                    System.out.println(stackRobson.getInfo() + " x x");
                }
                temp = temp.getLeftTree();
            }
        }

        temp = stackRobson;
        System.out.println("***ELEMENTS ON STACK");
        while (temp != null) {
            if (temp.getRightTree() != null) {
                BinaryTreeNode stackElement = temp.getRightTree();
                if (stackElement.getLeftTree() != null && stackElement.getRightTree() != null) {
                    System.out.println(stackElement.getInfo() + " " + stackElement.getLeftTree().getInfo() + " " + stackElement.getRightTree().getInfo());
                } else if (stackElement.getLeftTree() != null && stackElement.getRightTree() == null) {
                    System.out.println(stackElement.getInfo() + " " + stackElement.getLeftTree().getInfo() + " x");
                } else if (stackElement.getLeftTree() == null && stackElement.getRightTree() != null) {
                    System.out.println(stackElement.getInfo() + " x " + stackElement.getRightTree().getInfo());
                } else {
                    System.out.println(stackElement.getInfo() + " x x");
                }
            }
            temp = temp.getLeftTree();
        }
    }

}
