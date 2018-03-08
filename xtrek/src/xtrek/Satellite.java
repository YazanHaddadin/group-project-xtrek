/**
 * Satellite Class
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

public class Satellite extends Mode {

    final static int BUFF_SIZE = 1024;

    public Satellite(JFrame frame) {
        //super(frame);
    }

    /*
     * Reader.
     */
    private static void reader(String fileName, SatellitePanel panel) {
        while(true){
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
                            System.out.println(line);
                            String[] splits = line.split(",");
                            String value1 = splits[1];
                            String direction1 = splits[2];
                            String value2 = splits[3];
                            String direction2 = splits[4];
                            if (value1.equals("") || value2.equals("")) {
                                panel.setLabel1("No signal recieved");
                                panel.setLabel2("");
                            } else {
                                Float processed1 = Float.parseFloat(value1)/100;
                                Float processed2 = Float.parseFloat(value2)/100;
                                String output1 = String.format("%.4f", processed1);
                                String output2 = String.format("%.4f", processed2);

                                panel.setLabel1(output1 + " " + direction1);
                                panel.setLabel2(output2 + " " + direction2);
                            }

                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
                panel.setLabel1("Device not connected");
                panel.setLabel2("");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex1) {
                    System.out.println(ex1);
                }
            }
            
        }
    }

    /*
     * OSX Ublox7 reader.
     */
    public static void main(String[] argv) {
        JFrame frame = new JFrame();
        SatellitePanel panel = new SatellitePanel();
        frame.setSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        
        frame.setVisible(true);
        
        reader(Constants.dongleLocation, panel);
    }

    @Override
    public void displayMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void selected(ButtonEvent evt) {

    }

    @Override
    public void plus(ButtonEvent evt) {

    }

    @Override
    public void minus(ButtonEvent evt) {

    }
}