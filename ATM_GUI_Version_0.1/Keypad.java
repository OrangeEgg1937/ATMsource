// Keypad.java
// Represents the keypad of the ATM
// program uses Scanner to obtain user input
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Keypad extends JFrame {

   private JButton[] buttons;
   private String title = "ATM Input Helper";
   private String temp1 = null;
   private String temp2 = null;
   private JPasswordField passwordField;
   private JTextField userIDField;
   private OK_Key handler;
   public boolean passed = true;
   public int input1 = 0;
   public int input2 = 0;

   JLabel title1 = new JLabel("Bank Account ID: ");
   JLabel title2 = new JLabel("Passward: ");
   JLabel title3 = new JLabel("");

   // no-argument constructor initializes the Keypad
   public Keypad() {
      super("ATM Input Helper");
      buttons = new JButton[2];
      
      setLayout( new GridLayout(8, 2));
      buttons[0] = new JButton("OK");
      buttons[1] = new JButton("Clear");
      passwordField = new JPasswordField();
      userIDField = new JTextField();

      setSize(800, 600);
      setLocationRelativeTo(null);

      handler = new OK_Key();
      buttons[0].addActionListener( handler );
      add(title1);
      add(userIDField);
      add(title2);
      add(passwordField);
      add(title3);
      add(buttons[0]);
      add(buttons[1]);
      setVisible(true);

   }
   // end no-argument Keypad constructor

   // return an integer value entered by user

   public void loginHandle(){
      while(passed){
      setVisible(true);
      }
      setVisible(false);
   }

   public int getInput()
   {
      boolean check; // error flag
      int input = 0; // the input value
      String message = null;
      do { // data validation check
         try {
            message = JOptionPane.showInputDialog(null,"Please enter a valid value: ",title);
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

   public int getInput(String showMessage)
   {
      boolean check; // error flag
      int input = 0; // the input value
      String message = null;
      
      do { // data validation check
         try {
            message = JOptionPane.showInputDialog(null,showMessage,title,JOptionPane.INFORMATION_MESSAGE);
            check = false;
            input = Integer.parseInt(message); // get the input value
         } catch (Exception e) {
            if (message == null) {
               check = false;
               input = -1;
            } else {
               JOptionPane.showMessageDialog(null, "ERROR:Invalid input, "+showMessage, "ATM",JOptionPane.ERROR_MESSAGE); // error message
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

   private class OK_Key implements ActionListener
   {
      public void actionPerformed(ActionEvent event) {
         temp1 = userIDField.getText();
         temp2 = passwordField.getText();
         input1 = Integer.parseInt(temp1);
         input2 = Integer.parseInt(temp2);
         passed = false;
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