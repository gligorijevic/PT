/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author djordje
 */
public class InputGenerator {

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/data/Assign1Input11", true)));
        out.print("30\n");
        for (int i = 1; i < 30; i++) {
            out.println((i) + " " + (i + 1));
        }
        out.print("0 0");
        out.close();
    }
}
