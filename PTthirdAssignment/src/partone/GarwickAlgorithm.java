/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partone;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Djordje Gligorijevic
 */
public class GarwickAlgorithm {

    /**
     * Global variables
     */
    static int N;
    static double ro;
    static int K;
    static int tableBase;
    static int tableMax;
    static int REM;
    static int[] stack;
    static int[] table;
    static int[] top;
    static int[] base;
    static int[] oldTop;
    static int[] newBase;
    static int[] increase;
    static double[] alloc;
    static int[] delta;
    static int noMovements = 0;
    static int noMovements70 = 0;
    static boolean p70 = false;
    static int totalSpaceUsed = 0;
    static int totalIncrease = 0;

    static boolean nemaDalje = false;

    public static void initialize
            (int nOFMemoryLocations, int nOfStacks, double roin) {
        ro = roin;
        tableMax = nOFMemoryLocations;
        tableBase = 0;
        totalSpaceUsed = 0;
        noMovements = 0;
        noMovements70 = 0;
        p70 = false;
        table = new int[nOFMemoryLocations];
        totalIncrease = 0;
        N = nOfStacks;
        stack = new int[N];
        oldTop = new int[N];
        top = new int[N];
        base = new int[N + 1];
        newBase = new int[N + 1];
        increase = new int[N];
        alloc = new double[N];
        delta = new int[N];
        int length = nOFMemoryLocations / nOfStacks; //100
        for (int i = 0; i < N; i++) {
            stack[i] = length;
            top[i] = oldTop[i] = base[i] = (i) * length;
        }
        base[N] = nOFMemoryLocations;
        System.out.println("Base n " + base[N]);
        REM = tableMax - tableBase - totalSpaceUsed;
    }

    /**
     *
     * @throws Exception
     */
    public static void garwick() throws Exception {
        totalSpaceUsed = 0;
        totalIncrease = 0;
        for (int i = 0; i < N; i++) {
            stack[i] = top[i] - base[i];
            if (stack[i] < 0) {
                stack[i] = 0;
            }
            System.out.println("Stack " + i + " has length of: " + stack[i]);
            System.out.println("TOP is " + top[i] + " ,and base is " + base[i]);
        }

        for (int i = 0; i < N; i++) {
            if (top[i] > oldTop[i]) {
                increase[i] = top[i] - oldTop[i];
            } else {
                increase[i] = 0;
            }
            System.out.println
                    ("Increase of stack " + i + " is: " + increase[i]);
            totalSpaceUsed += top[i] - base[i];

            totalIncrease += increase[i];
        }
        p70 = totalSpaceUsed >= (0.7 * tableMax);
        System.out.println("Total increase is : " + totalIncrease);
        System.out.println("Table max " + tableMax + " table base " + 
                tableBase + " totalSpaceUSed " + totalSpaceUsed);

        REM = (tableMax - tableBase) - totalSpaceUsed;
        System.out.println("REM is: " + REM);

        if (REM < 0) {
            nemaDalje = true;
//            throw new Exception("REM is less than zero");
        } else {
            for (int i = 0; i < N; i++) {
                double totIncreaseZero, totSpaseZero;
                if (totalIncrease == 0) {
                    totIncreaseZero = 0;
                } else {
                    totIncreaseZero = 
                            (double) increase[i] * ro * 0.9 / totalIncrease;
                }
                if (totalSpaceUsed == 0) {//16
                    totSpaseZero = 0;
                } else {
                    totSpaseZero = 
                         stack[i] * (1.0 - ro) * 0.9 / (double) totalSpaceUsed;
                }
                alloc[i] = 
                    REM * ((0.1 * (1.0 / N)) + totIncreaseZero + totSpaseZero);
            }

            double check = 0;
            for (double val : alloc) {
                check += val;
            }
            if (REM != Math.round(check)) {
                throw new Exception("Alloc has been calculated wrong.");
            } else {
                double currentAllocSum = 0;
                int deltasum = 0;
                for (int i = 0; i < N - 1; i++) {
                    delta[i] = (int) Math.floor(alloc[i]);
                    double curFractPartI = alloc[i] - delta[i];
                    delta[i] += Math.floor(currentAllocSum + curFractPartI);
                    currentAllocSum += curFractPartI;
                    System.out.println("Delta " + i + " i is " + delta[i]);
                    deltasum += delta[i];
                }
                delta[N - 1] = REM - deltasum;
                System.out.println("Delta n is: " + delta[N - 1]);
                newBase[0] = base[0];
                System.out.println("base of 0 is: " + newBase[0]);
                for (int i = 1; i < N; i++) {
                    newBase[i] = newBase[i - 1] + stack[i - 1] + delta[i - 1];
                    System.out.println("base od " + i + " is: " + newBase[i]);
                }
            }
            moving();
            top[K]++;
            System.out.println("BROJ POMERAJA " + noMovements);
            for (int i = 0; i < N; i++) {
                oldTop[i] = top[i];
                System.out.println("A top je sada " + top[i]);
            }
        }
    }

    public static void moving() {
        newBase[N] = base[N];
        System.out.println("New base od n je sada " + newBase[N]);
        top[K]--;
        System.out.println("Top od K je sada " + top[K]);
        //Set i to 2.*/
        for (int i = 1; i < N + 1; i++) {//While i < N 
            System.out.println
                    ("POCINJE POMERANJE--" + i + " nova baza je " + 
                    newBase[i] + " a stara je " + base[i]);
            if (newBase[i] < base[i]) {
                System.out.println("U levo");
                System.out.println("Nova baza je manja" + newBase[i]);
                int moveAmount = base[i] - newBase[i];
                System.out.println
                     ("Treba pomeriti bazu i=" + i + " za move " + moveAmount);
                for (int h = base[i] + 1; h <= top[i]; h++) {
                    noMovements++;
                    if (!p70) {
                        noMovements70++;
                    }
                }
                base[i] = newBase[i];
                top[i] = top[i] - moveAmount;
                System.out.println
                        ("After the movement base of" + i + " is: " + 
                        base[i] + " , and top is: " + top[i]);
            } else if (newBase[i] > base[i]) {
                int j = N;
                for (int k = i; k <= N; k++) {
                    if (newBase[k] <= base[k]) {
                        j = k;
                        break;
                    }
                }
                for (int t = j - 1; t >= i; t--) {
                    int moveAmount = newBase[t] - base[t];
                    for (int h = top[t]; h >= base[t] + 1; h--) {
                        noMovements++;
                        if (!p70) {
                            noMovements70++;
                        }
                    }
                    base[t] = newBase[t];
                    top[t] = top[t] + moveAmount;

                }
                i = j - 1;
            } else {

                System.out.println("Bases are the same");
            }
        }
    }

    static int randomUniform() {
        int randInt = (int) (10 * Math.random());
        return randInt;
    }

    static int randomExp() {
        int st = 0;
        double[] skd = new double[N];
        double ranNum = Math.random();
        for (int i = 0; i < N; i++) {
            skd[i] = 1 - 1 / Math.pow(2, i + 1);
            if (i != 0) {
                if (ranNum < skd[i] && ranNum > skd[i - 1]) {
                    return i;
                }
            }
        }
        return st;
    }

    public static void main(String[] args) {
        int sumOfOverflows = 0;
        int sumOfMovements = 0;
        int sumOfMovements70 = 0;
        for (int uk = 0; uk < 10; uk++) {
            int numberOfOverflows = 0;
            int inputSize = 50;// can be 1, 20 or 50 for this data

            int[] inputArray = new int[1000];
            initialize(1000, 10, 0);//0,0.5,1

            System.out.println("REM is " + REM);
            try {
                while (REM > 0) {
                    if (uniform) {
                        K = randomUniform();
                    } else {
                        K = randomExp();
                    }
                    for (int i = 0; i < inputSize; i++) {
                        top[K]++;
                        System.out.println("New top of " + K + " is now " + top[K]);
                        if (top[K] > base[K + 1]) {
                            numberOfOverflows++;
                            System.out.println("DESIO SE OVERFLOOOOOOOOOOOOOOWWWW");
                            System.out.println("baza je K=" + K);
                            garwick();
                            if (nemaDalje) {
                                break;
                            }
                        }
                    }
                    if (REM <= 0) {
                        break;
                    }
                }
                System.out.println("Number of movement 70 is: " + noMovements70);
                System.out.println("Number of movement is: " + noMovements);
                System.out.println("Number of overflows is: " + numberOfOverflows);

            } catch (Exception e) {
                Logger.getLogger(GarwickAlgorithm.class.getName()).log(Level.SEVERE, null, e);
                System.err.println(e.getMessage());
            }
            System.out.println("Number of movement 70 is: " + noMovements70);
            System.out.println("Number of movement is: " + noMovements);
            System.out.println("Number of overflows is: " + numberOfOverflows);
            sumOfMovements += noMovements;
            sumOfMovements70 += noMovements70;
            sumOfOverflows += numberOfOverflows;

        }

        double averageMove = sumOfMovements / 10.0;
        double averageMove70 = sumOfMovements70 / 10.0;
        double averageOver = sumOfOverflows / 10.0;
        System.out.println("Average movements 70: \t" + averageMove70);
        System.out.println("Average movements: \t" + averageMove);
        System.out.println("Average overflows: \t" + averageOver);
    }
    static boolean uniform = false;

}
