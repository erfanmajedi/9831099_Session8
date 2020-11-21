package com.company;
import javax.swing.UIManager;

/**
 * the Main class manages the program and show a calculator.
 *
 * @author Erfan Majedi
 * @version 1.0
 * @since 2020.11.21
 */
public class Main {

    public static void main(String[] args) {

        //change the look-and-fell to Nimbus.
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (("Nimbus").equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

// If Nimbus is not available, you can set the GUI to another look and feel.

        }

        //make an instance of the CalculatorGUI.
        new CalculatorGUI();
    }
}