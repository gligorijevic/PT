/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gligo_000
 */
public class PruningNumber {

    private int L[] = null;
    private int R[] = null;

    public PruningNumber(int L[], int R[]) {
        this.L = L;
        this.R = R;
    }

    public int getPruningNumber() {
        int index = 1;
        int l = 0;
        if (L[index - 1] != 0) {
            l = getPruningNumberRecursilvely(index);

        }
        int r = 0;
        boolean allRightSubTreeHaveSamePruningNo = true;
        while (R[index - 1] != 0) {
            index = R[index - 1];
            int k = getPruningNumberRecursilvely(index);
            if (r == 0) {
                r = k;
            } else if (k > r) {
                r = k;
                allRightSubTreeHaveSamePruningNo = false;
            } else if (k < r) {
                allRightSubTreeHaveSamePruningNo = false;
            }

        }
        if (allRightSubTreeHaveSamePruningNo && r == l) {
            return l + 1;
        }

        return r > l ? r : l;
    }

    private int getPruningNumberRecursilvely(int index) {
        if (L[index - 1] == 0 && R[index - 1] == 0) {
            return 1;
        }
        int l = 0, r = 0;
        if (L[index - 1] != 0) {
            l = getPruningNumberRecursilvely(L[index - 1]);

            boolean allRightSubTreeHaveSamePruningNo = true;
            index = L[index - 1];
            while (index != 0 && R[index - 1] != 0) {
                int k = getPruningNumberRecursilvely(R[index - 1]);
                index = R[R[index - 1] - 1];
                if (r == 0) {
                    r = k;
                } else if (k > r) {
                    r = k;
                    allRightSubTreeHaveSamePruningNo = false;
                }

            }
            if (allRightSubTreeHaveSamePruningNo && r == l) {
                return l + 1;
            }

            return r > l ? r : l;
        }
        return 1;
    }

    public static void main(String args[]) {

        int L[] = {2, 0, 4, 0};
        int R[] = {3, 0, 0, 0};

        System.out.println(new PruningNumber(L, R).getPruningNumber());

        int L1[] = {2, 3, 0, 0, 6, 0, 0};
        int R1[] = {5, 4, 0, 0, 7, 0, 0};
        System.out.println(new PruningNumber(L1, R1).getPruningNumber());

        //the case u r discussing
        int L3[] = {2, 0, 0};
        int R3[] = {0, 3, 0};

        PruningNumber pruningNumber = new PruningNumber(L3, R3);
        System.out.println(pruningNumber.getPruningNumber());

        int L4[] = {2, 3, 0, 5, 6, 0, 0, 9, 0, 0, 12, 13, 0, 15, 0, 17, 18, 0, 0, 21, 0, 0, 0};
        int R4[] = {11, 4, 0, 8, 7, 0, 0, 10, 0, 0, 0, 14, 0, 16, 0, 20, 19, 0, 0, 22, 0, 0, 0};
        pruningNumber = new PruningNumber(L4, R4);
        System.out.println(pruningNumber.getPruningNumber());

        int L5[] = {2, 3, 0, 0, 6, 0, 0, 0};
        int R5[] = {0, 5, 4, 0, 8, 7, 0, 0};
        pruningNumber = new PruningNumber(L5, R5);
        System.out.println(pruningNumber.getPruningNumber());

        int L6[] = {2, 3, 4, 0, 0, 0, 0};
        int R6[] = {0, 0, 0, 5, 6, 7, 0};
        pruningNumber = new PruningNumber(L6, R6);
        System.out.println(pruningNumber.getPruningNumber());
    }
}
