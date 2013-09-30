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
public class ModifiedGarwickAlgorithm {

    /**
     * Global variables
     */
    static int N;
    static int ro;
    static int tableBase;
    static int tableMax;
    static int[] stack = new int[10];
    static int[] table;
    static int[] top;
    static int[] base;
    static int[] oldTop;
    static int[] newBase;
    static int[] increase;
    static double[] alloc;
    static int[] delta;

    public static void initialize() {
    }

    public static void garwick() throws Exception {

        int totalSpaceUsed = 0;
        int totalIncrease = 0;
        for (int i = 0; i < N; i++) {
            if (top[i] > oldTop[i]) {
                increase[i] = top[i] - oldTop[i];
            } else {
                increase[i] = 0;
            }
            totalSpaceUsed = top[i] - base[i];
            totalIncrease += increase[i];
        }
        int REM = (tableMax - tableBase) - totalSpaceUsed;

        if (REM < 0) {
            throw new Exception("REM is less than zero");
        } else {

            for (int i = 0; i < N; i++) {
                alloc[i] = REM * (0.1 * (1 / N) + (increase[i] / totalIncrease) * ro * 0.9 + (stack[i] / totalSpaceUsed) * (1 - ro) * 0.9);
            }
            double check = 0;
            for (double val : alloc) {
                check += val;
            }
            if (REM != check) {
                throw new Exception("Alloc has been calculated wrong.");
            } else {

                double allocSum = 0;
                for (int i = 0; i < N; i++) {
                    delta[i] = (int) Math.floor(alloc[i - 1]);
                    double currentFraction = alloc[i] - delta[i];
                    delta[i] += Math.floor(currentFraction + allocSum);
                    allocSum += currentFraction;
                    if (i == 0) {
                        newBase[i] = base[i];
                    } else {
                        newBase[i] = newBase[i - 1] + stack[i - 1] + delta[i - 1];
                        base[i] = newBase[i];
                    }

                }

            }

        }

    }

    public static void moving() {
//        newBase[N+1] = base[N+1];

        int i = 2;
        while (i <= N) {
            if (newBase[i] < base[i]) {
                int delta = base[i] - newBase[i];
                for (int j = base[i] + 1; j < top[i]; j++) {
                    table[j - delta] = table[j];
                }
                base[i] = newBase[i];
                top[i] = top[i] + delta;
                i++;
            } else {
                
                
                
            }
        }
    }

}
