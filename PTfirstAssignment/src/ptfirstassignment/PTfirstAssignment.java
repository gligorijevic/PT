package ptfirstassignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Djordje Gligorijevic
 */
public class PTfirstAssignment {

    //Global variables
    public static int[] output;
    public static int[] bag;
    public static int bagStart = -1;
    public static int bagEnd = -1;

    //Initial data
    public static int n;
    public static int[] predCount;
    public static TsList[] successors;

    public static int printNo = 0;
    public static int topsortsNo = 0;

    public static boolean bagNotEmpty() {
        return bagStart <= bagEnd && (bagStart != -1 && bagEnd != -1);
    }

    public static void initializeBag() {
        for (int i = 0; i < predCount.length; i++) {
            int value = i + 1;
            if (bagStart == -1) {
                bag[++bagStart] = value;
            } else {
                bag[++bagEnd] = value;
            }
        }
    }

    public static void decreasePredCount(int val) {
        predCount[val - 1]--;
    }

    public static void addToOutput(int value) {
        for (int i = 0; i < output.length; i++) {
            if (output[i] == 0) {
                output[i] = value;
                return;
            }
        }
    }

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
                                successors[i - 1].addLast(new TsList(j, null));
                            } else {
                                successors[i - 1].addLast(new TsList(j, null));
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
                            successors[i - 1].addLast(new TsList(j, null));
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

    public static void topsorts() {
        if (bagNotEmpty()) {
            System.out.println("Bag is not empty.");
            while (bagStart <= bagEnd) {
                System.out.println("BagStart is: " + bagStart + ", and bagEnd is: " + bagEnd);
                //         Take it out of the Bag, put it in the output array,
                System.out.println("Adding " + bag[bagEnd] + " to output.");
                int lastAdded = bag[bagEnd];
                addToOutput(lastAdded);
                --bagEnd;
                //         traverse its succ list, reduce the pred count for 
                //         each successor, and if it goes to zero, put it in 
                //         the Bag
                System.out.println("Decreasing predCount of " + lastAdded + "'s successors by one.");
                if (successors[lastAdded - 1] != null) {
                    successors[lastAdded - 1].decreasePredecessorCount(predCount);
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
                        predCount[k] = -1; // A sta ako bas to necu?
                    }
                }
            }

            System.out.println("Recursive call");
            topsorts();
            
            
            
            //         Reverse the above
            
                
                //         traverse its succ list, reduce the pred count for 
                //         each successor, and if it goes to zero, put it in 
                //         the Bag

            
                //         Take it out of the Bag, put it in the output array,
        } else {
            System.out.println("Bag is empty.");
            //      Output the output array
            if (printNo < 50) {
                for (int i = 0; i < output.length; i++) {
                    System.out.println(output[i]);
                    printNo++;
                }
            }
            topsortsNo++;
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("######## READING DATA ########\n");
        readDataAndInitialize("C:\\Users\\gligo_000\\Desktop\\Temple\\Programming Techniques\\Topological Sort\\Assign1input1");

        System.out.println("######## LOADING DATA ########\n");

        System.out.println("Number of elements: " + output.length);
        for (int i = 0; i < predCount.length; i++) {
            System.out.println("Element " + (i + 1) + " has " + predCount[i] + " predecessors.");
        }
        for (int i = 0; i < successors.length; i++) {
            System.out.println("Element " + (i + 1) + " has next successors:");
            if (successors[i] != null) {
                TsList path = successors[i];
                while (path != null) {
                    System.out.println(path.value);
                    path = path.getNext();
                }
            } else {
                System.out.println("no successors");
            }
        }

        System.out.println("Initial bag: ");
        for (int i = 0; i < bag.length; i++) {
            System.out.println(bag[i]);
        }
        System.out.println("\n");
        System.out.println("######## FINDING TOPSORTS ########\n");

        topsorts();
    }

}
