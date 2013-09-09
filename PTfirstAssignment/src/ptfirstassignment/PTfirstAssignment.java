package ptfirstassignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 *
 * @author Djordje Gligorijevic
 */
public class PTfirstAssignment {

    //Global variables
    public static int[] output;
    public static int[] bag;
    public static int bagStart = -1;
    public static int bagEnd = -1;
    public static int outEnd = -1;

    //Initial data
    public static int n;
    public static int[] predCount;
    public static TsList[] successors;

    //Utility variables
    public static int printNo = 0;
    public static int topsortsNo = 0;

    /**
     * This method checks whether the bag is empty or not.
     *
     * @return false if bag is empty and true otherwise.
     */
    public static boolean bagNotEmpty() {
        return bagStart <= bagEnd && (bagStart != -1 && bagEnd != -1);
    }

    /**
     * This method decreases predecessor count of all elements contained in list
     * passed through input.
     *
     * @param element first element of TsList list.
     */
    static void decreasePredecessorCount(TsList element) {
        while (element != null) {
            predCount[element.getValue() - 1]--;
            if (predCount[element.getValue() - 1] == 0) {
                bag[++bagEnd] = element.getValue();
            }
            element = element.getNext();
        }
    }

    /**
     * This method increases predecessor count of all elements contained in list
     * passed through input.
     *
     * @param element first element of TsList list.
     */
    private static void increasePredecessorCount(TsList element) {
        while (element != null) {
            predCount[element.getValue() - 1]++;
            element = element.getNext();
        }
    }

    /**
     * Reads data from the input file, initializes predecessor counts and
     * creates successor lists for each element. Also initializes bag structure
     * with first "free" elements from data.
     *
     * @param dataLocation String that contains information where is the input
     * data located
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void readDataAndInitialize(String dataLocation) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dataLocation));
        String line = null;
        int i = 0;
        int j = 0;
        while ((line = reader.readLine()) != null) {
            if (!line.equals("")) {
                String[] parts = line.split(" ");
                if (parts.length == 1) {
                    if (output == null) {
                        n = Integer.parseInt(parts[0]);
                        output = new int[n];
                        bag = new int[n];
                        predCount = new int[n];
                        successors = new TsList[n];
                    } else {
                        i = Integer.parseInt(parts[0]);
                        line = reader.readLine();
                        j = Integer.parseInt(line);

                        if (i != 0 && j != 0) {
                            predCount[j - 1] += 1;
                            if (successors[i - 1] == null) {
                                successors[i - 1] = new TsList(j, null);
                            } else {
//                                successors[i - 1].addLast(new TsList(j, null));
                                successors[i - 1].addSecond(new TsList(j, null));
                            }
                        }
                    }
                } else {
                    if (!"0".equals(parts[0]) && !"0".equals(parts[1])) {
                        i = Integer.parseInt(parts[0]);
                        j = Integer.parseInt(parts[1]);
                        predCount[j - 1] += 1;
                        if (successors[i - 1] == null) {
                            successors[i - 1] = new TsList(j, null);
                        } else {
//                            successors[i - 1].addLast(new TsList(j, null));
                            successors[i - 1].addSecond(new TsList(j, null));
                        }
                    }
                }
            }
        }
        for (int k = 0; k < predCount.length; k++) {
            if (predCount[k] == 0) {
                if (!bagNotEmpty()) {
                    bagStart = 0;
                    bagEnd = 0;
                    bag[bagStart] = k + 1;
                } else {
                    bag[++bagEnd] = k + 1;
                }
                predCount[k] = -1;
            }
        }
    }

    /**
     *
     * This method generates all topological sorts of set S that are consistent
     * with relation R. Output of this method is written into file where fist
     * fifty topological sorts are printed and finally total number of all sorts
     * is printed as well.
     */
    public static void topsorts() {
        if (bagNotEmpty()) {
            int iterator = bagStart - 1;
            while (bagStart <= bagEnd) {
                if (iterator >= bagEnd) {
                    break;
                }
                iterator++;
                int localBagEnd = bagEnd;

                //         Take it out of the Bag, put it in the output array,
                int lastAdded = bag[iterator];
                bag[iterator] = bag[bagStart];
                output[++outEnd] = lastAdded;
                bagStart++;
                //         traverse its succ list, reduce the pred count for 
                //         each successor, and if it goes to zero, put it in 
                //         the Bag
                decreasePredecessorCount(successors[lastAdded - 1]);

                topsorts();

                //         Reverse the above
                //         traverse its succ list, reduce the pred count for 
                //         each successor
                //         Take it out of the Bag, put it in the output array,
                bagStart--;
                outEnd--;
                bagEnd = localBagEnd;
                bag[iterator] = lastAdded;
                increasePredecessorCount(successors[lastAdded - 1]);
            }
        } else {
            try {
                //      Output the output array
                PrintWriter out = null;
                out = new PrintWriter(new BufferedWriter(new FileWriter(("src/data/result9"), true)));
                if (printNo < 50) {
                    out.print(printNo + " : ");
                    for (int i = 0; i < output.length; i++) {
                        int j = output[i];
                        out.print(j);
                        out.print(" ");
                    }
                    printNo++;
                    out.println();
                    out.close();
                }
                topsortsNo++;
            } catch (IOException ex) {
                Logger.getLogger(PTfirstAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        readDataAndInitialize("src/data/Assign1input9");

//        System.out.println("Number of elements: " + output.length);
//        for (int i = 0; i < predCount.length; i++) {
//            System.out.println("Element " + (i + 1) + " has " + predCount[i] + " predecessors.");
//        }
//        for (int i = 0; i < successors.length; i++) {
//            System.out.println("Element " + (i + 1) + " has next successors:");
//            if (successors[i] != null) {
//                TsList path = successors[i];
//                while (path != null) {
//                    System.out.println(path.value);
//                    path = path.getNext();
//                }
//            } else {
//                System.out.println("no successors");
//            }
//        }
//
//        System.out.println("Initial bag: ");
//        for (int i = 0; i < bag.length; i++) {
//            System.out.println(bag[i]);
//        }
        topsorts();
        PrintWriter out = null;
        out = new PrintWriter(new BufferedWriter(new FileWriter(("src/data/result9"), true)));
        out.write("There are " + topsortsNo + " topsorts.");
        out.flush();
        out.close();
    }

}
