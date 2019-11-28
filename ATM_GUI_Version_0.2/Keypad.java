// Keypad.java
// Represents the keypad of the ATM
// program uses Scanner to obtain user input
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class Keypad{

   private boolean ispasswordField = false;
   private boolean sendAction = false;
   private String userInput = "";  // for normal input
   private String userInput2 = ""; // for passwords input
   private JPanel keyPadPanel;
   private JPanel numberKeyPanel;
   private JPanel keyButtonsPanel;
   private JButton[] numberbuttons;
   private JButton[] keyButtons;
   private String title = "ATM Input Helper";
   private JPasswordField passwordField;
   private JTextField userInputField;
   private NumberKey handler;
   private Backspace backspace_key;
   private ClearKey clearKey;
   private OK_Key okKey;
   public boolean passed = true;
   public int input1 = 0;
   public int input2 = 0;


   //constructor initializes the Keypad
   public Keypad() {

      // initializes GUI components
      numberbuttons = new JButton[12];       
      keyButtons = new JButton[3];
      keyPadPanel = new JPanel();
      keyButtonsPanel = new JPanel();
      numberKeyPanel = new JPanel();
      userInputField = new JTextField(userInput);
      passwordField = new JPasswordField();
      handler = new NumberKey();
      backspace_key = new Backspace();
      clearKey = new ClearKey();
      okKey = new OK_Key();

      // initializes Layout
      keyPadPanel.setLayout(new BorderLayout());
      numberKeyPanel.setLayout(new GridLayout(4, 3));
      keyButtonsPanel.setLayout(new GridLayout(4, 1));
      userInputField.setEditable(false);
      passwordField.setEditable(false);

      // Adding components into layout
      keyPadPanel.add(userInputField, BorderLayout.NORTH);
      for (int i = 0; i < numberbuttons.length-3; i++) {
         numberbuttons[i] = new JButton(String.valueOf(i+1));
         numberbuttons[i].addActionListener(handler);
         numberKeyPanel.add(numberbuttons[i]);
      }
      numberbuttons[9] = new JButton("0");
      numberbuttons[10] = new JButton("00");
      numberbuttons[11] = new JButton(".");
      numberKeyPanel.add(numberbuttons[9]);
      numberKeyPanel.add(numberbuttons[10]);
      numberKeyPanel.add(numberbuttons[11]);
      keyButtons[0] = new JButton("OK");
      keyButtons[0].addActionListener(okKey);
      keyButtonsPanel.add(keyButtons[0]);
      keyButtons[1] = new JButton("Backspace");
      keyButtons[1].addActionListener(backspace_key);
      keyButtonsPanel.add(keyButtons[1]);
      keyButtons[2] = new JButton("Cancel");
      keyButtons[2].addActionListener(clearKey);
      keyButtonsPanel.add(keyButtons[2]);
      keyPadPanel.add(numberKeyPanel, BorderLayout.CENTER);
      keyPadPanel.add(keyButtonsPanel, BorderLayout.EAST);
   }
   // end no-argument Keypad constructor

   // return an integer value entered by user

   public void idHandle(){
      userInput2 = "";
      if (ispasswordField) {
         keyPadPanel.remove(passwordField);
         keyPadPanel.add(userInputField, BorderLayout.NORTH);
         ispasswordField = false;
         keyPadPanel.revalidate();
         keyPadPanel.repaint();
      }
      waiting();
   }

   public void clear(){
      userInput = "";
      keyPadPanel.revalidate();
      keyPadPanel.repaint();
      userInputField.setText(userInput);
   }

   public void passwordHandle(){
      keyPadPanel.remove(userInputField);
      keyPadPanel.add(passwordField, BorderLayout.NORTH);
      keyPadPanel.revalidate();
      keyPadPanel.repaint();
      waiting();
      if (ispasswordField) {
         keyPadPanel.remove(passwordField);
         keyPadPanel.add(userInputField, BorderLayout.NORTH);
         ispasswordField = false;
         keyPadPanel.revalidate();
         keyPadPanel.repaint();
      }
      clear();
   }

   public void waiting(){
      while (!sendAction) {
         System.out.print("");
      }
      sendAction = false;
   }

   public int getPassword(){
      return Integer.parseInt(userInput2);
   }

   public JPanel getPanel(){
      return keyPadPanel;
   }

   public int getInput(){
      return Integer.parseInt(userInput);
   }

   /*public int getInput()
   {
      int returning = 0;
      boolean check = false; // error flag
      do { // data validation check
         try {
            check = false;
            returning = Integer.parseInt(userInput); // get the input value
         } catch (Exception e) {
            
         }
      } while (check);
      return returning; // we assume that user enters an integer
   }// end method getInput*/

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

   private class OK_Key implements ActionListener{
      public void actionPerformed(ActionEvent event) {
      if (userInput != "") {
         ispasswordField = true;
      }   
      sendAction = true;
   }
}
   
   private class NumberKey implements ActionListener
   {
      public void actionPerformed(ActionEvent event) { 
         for (int i = 0; i < numberbuttons.length-3; i++) {
            if(event.getSource() == numberbuttons[i]){
               if (ispasswordField) {
                  userInput2 = userInput2 + numberbuttons[i].getText();
                  break;
               }else{
               userInput = userInput + numberbuttons[i].getText();
               break;
               }
            }
         }
         if (ispasswordField) {
            passwordField.setText(userInput2);
         }else{
            userInputField.setText(userInput);
         }
      }
   }

   private class ClearKey implements ActionListener
   {
      public void actionPerformed(ActionEvent event) { 
         if (ispasswordField) {
            userInput2 = "";
            passwordField.setText(userInput2);
         }else{
            userInput = "";
            userInputField.setText(userInput);
         }
      }
   }

   private class Backspace implements ActionListener
   {
      public void actionPerformed(ActionEvent event) { 
         if (ispasswordField && userInput2 != "") {
            userInput2 = userInput2.substring(0, userInput2.length()-1);
            passwordField.setText(userInput2);
         }else if(userInput != ""){
            userInput = userInput.substring(0, userInput.length()-1);
            userInputField.setText(userInput);
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