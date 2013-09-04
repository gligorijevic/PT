package ptfirstassignment;

import static ptfirstassignment.PTfirstAssignment.bag;
import static ptfirstassignment.PTfirstAssignment.bagEnd;

/**
 *
 * @author Djordje Gligorijevic
 */
public class TsList {

    int value;
    TsList next;

    public TsList() {
    }

    public TsList(int value, TsList next) {
        this.value = value;
        this.next = next;
    }

    public void setNext(TsList next) {
        this.next = next;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public TsList getNext() {
        return next;
    }

    public void addLast(TsList tl) {
        TsList element = this;
        if (element.next == null) {
            element.next = tl;
        } else {
            while (element.next != null) {
                if (element.next == null) {
                    element.next = tl;
                }
                element = element.next;
            }
        }
    }

//    public void removeElement(int e) {
//        //TODO check
//        TsList element = this;
//        while (element != null) {
//            if (element.value == e) {
//                if (element.next == null) {
//                    element.next = element.next.next;
//                } else {
//                    element.next = null;
//                }
//            }
//            element = element.next;
//        }
//    }
    boolean hasSuccessor(int lastAdded) {
        TsList element = this;
        while (element != null) {
            if (element.value == lastAdded) {
                return true;
            }
            element = element.next;
        }
        return false;
    }

    void decreasePredecessorCount(int[] predCount) {
        TsList element = this;
        while (element != null) {
            predCount[element.value - 1]--;
            if(predCount[element.value - 1] == 0){
                bag[++bagEnd] = element.value;
            }
            element = element.next;
        }
    }

}
