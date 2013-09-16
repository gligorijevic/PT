/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parttwo;

/**
 *
 * @author Djordje Gligorijevic
 */
public class Position {

    private int first;
    private int last;

    public Position() {
    }

    public Position(int first, int last) {
        this.first = first;
        this.last = last;
    }

    /**
     * @return the first
     */
    public int getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(int first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public int getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(int last) {
        this.last = last;
    }
}
