/**
 * Satallite Class
 * <p>
 * Reads out the current coordinates of the device.
 *
 * @author Liam Vinson
 * @version Sprint 1
 */
package xtrek;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Satallite extends ModeView {

    final static String FILE_NAME = "/dev/cu.usbmodem1421";
    final static int BUFF_SIZE = 1024;

    public Satallite(JFrame frame) {
        super(frame);
    }

    /*
     * Reader.
     */
    private static void reader(String fileName, SatallitePanel panel) {
        try {
            FileInputStream in = new FileInputStream(new File(fileName));

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            while (true) {
                line = br.readLine();
                if (line == null) {
                    Thread.sleep(500);
                } else {
                    if (line.startsWith("$GPGLL")) {
                        String[] splits = line.split(",");
                        String value1 = splits[1];
                        String value2 = splits[2];
                        if (value1.equals("") || value2.equals("")) {
                            panel.setLabel1("No signal recieved");
                            panel.setLabel2("");
                        } else {
                            panel.setLabel1(value1);
                            panel.setLabel2(value2);
                        }
                        
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

    /*
     * OSX Ublox7 reader.
     */
    public static void main(String[] argv) {
        JFrame frame = new JFrame();
        SatallitePanel panel = new SatallitePanel();
        frame.setSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        
        frame.setVisible(true);
        
        reader(FILE_NAME, panel);
    }

    @Override
    public void displayMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}