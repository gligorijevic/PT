package ptfirstassignment;

/**
 *
 * @author Djordje Gligorijevic
 */
public class TsList {

    private int value;
    private TsList next;

    public TsList() {
    }

    public TsList(int value, TsList next) {
        this.value = value;
        this.next = next;
    }

    /**
     *
     * Adds new parameter to the end of the list. Since this method traverses
     * through entire list to add element at the end, execution time of this
     * method is not constant (depends on number of elements).
     *
     *
     * @param tl new parameter to add in list
     */
    public void addLast(TsList tl) {
        TsList element = this;
        while (element != null) {
            if (element.getNext() == null) {
                element.setNext(tl);
                return;
            }
            element = element.getNext();
        }
    }

    /**
     * Adds new parameter the list at the second position. Execution time of
     * this method is constant.
     *
     * @param tl new parameter to add in list
     */
    public void addSecond(TsList tl) {
        tl.setNext(this.getNext());
        this.setNext(tl);
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the next
     */
    public TsList getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(TsList next) {
        this.next = next;
    }

}
