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
public class BinaryTreeNode {
    private int info;
    private BinaryTreeNode leftTree;
    private BinaryTreeNode rightTree;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(int info, BinaryTreeNode leftTree, BinaryTreeNode rightTree) {
        this.info = info;
        this.leftTree = leftTree;
        this.rightTree = rightTree;
    }

    /**
     * @return the info
     */
    public int getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(int info) {
        this.info = info;
    }

    /**
     * @return the leftTree
     */
    public BinaryTreeNode getLeftTree() {
        return leftTree;
    }

    /**
     * @param leftTree the leftTree to set
     */
    public void setLeftTree(BinaryTreeNode leftTree) {
        this.leftTree = leftTree;
    }

    /**
     * @return the rightTree
     */
    public BinaryTreeNode getRightTree() {
        return rightTree;
    }

    /**
     * @param rightTree the rightTree to set
     */
    public void setRightTree(BinaryTreeNode rightTree) {
        this.rightTree = rightTree;
    }
    
    
}
