
// Keypad.java
// Represents the keypad of the ATM
// program uses Scanner to obtain user input
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Keypad extends JFrame {

   private JPanel buttonPanel; //screen 2 for put the button
   private JButton[] buttons;

   // no-argument constructor initializes the Keypad
   public Keypad() {
      super("ATM Input Helper");
      buttons = new JButton[2];
      buttonPanel = new JPanel();
      buttonPanel.setLayout( new GridLayout(1, buttons.length));
      buttons[0] = new JButton("OK");
      buttons[1] = new JButton("Clear");
      add(buttonPanel, BorderLayout.SOUTH);
   } // end no-argument Keypad constructor

   // return an integer value entered by user
   public int getInput()
   {
      boolean check; // error flag
      int input = 0; // the input value
      String message = null;
      do { // data validation check
         try {
            message = JOptionPane.showInputDialog("Please enter a valid value: ");
            check = false;
            input = Integer.parseInt(message); // get the input value
         } catch (Exception e) {
            if (message == null) {
               check = false;
               input = -1;
            } else {
               JOptionPane.showMessageDialog(null, "ERROR:Invalid input, please input an valid value: ", "ATM",JOptionPane.ERROR_MESSAGE); // error message
               check = true;
            }
         }
      } while (check);
      return input; // we assume that user enters an integer
   }// end method getInput

   public double getAmount() {

      boolean check; // error flag
      double input = 0; // the input value
      String message = null;
      do { // data validation check
         try {
            message = JOptionPane.showInputDialog("Please enter a valid value: ");
            check = false;
            input = Double.parseDouble(message); // get the input value
         } catch (Exception e) {
            if (message == null) {
               check = false;
               input = -1;
            } else {
               JOptionPane.showMessageDialog(null, "ERROR:Invalid input, please input an valid value: ", "ATM",JOptionPane.ERROR_MESSAGE); // error message
               check = true;
            }
         }
      } while (check);
      return input; // we assume that user enters an integer
   }// end method getAmount

   private class ButtonKey implements ActionListener
   {
      private String getKey = null;

      public ButtonKey(String button)
      {
         getKey = button;
      }

      public void actionPerformed(ActionEvent event) {
      }

      public Object clickReturn()
      {
         switch (getKey) {
            case "1":return (int) 1;
            case "2":return (int) 2;
            case "3":return (int) 3;
            case "4":return (int) 4;
            case "5":return (int) 5;
            case "6":return (int) 6;
            case "7":return (int) 7;
            case "8":return (int) 8;
            case "9":return (int) 9;
            case "0":return (int) 0;
            case ".":return (String) ".";
            default: return null;
         }
      }

   }
} // end class Keypad

/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/