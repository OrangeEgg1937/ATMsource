// Keypad.java
// Represents the keypad of the ATM
// program uses Scanner to obtain user input
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Keypad{

   private boolean ispasswordField = false;
   private boolean sendAction = false;
   private boolean login_state = false;
   private boolean isOnline = true;
   private boolean isRe_enter = false;
   private String userInput = "";  // for normal input
   private String userInput2 = ""; // for passwords input
   private JPanel keyPadPanel;
   private JPanel numberKeyPanel;
   private JPanel keyButtonsPanel;
   private JButton[] numberbuttons;
   private JButton[] keyButtons;
   private JPasswordField passwordField;
   private JTextField userInputField;
   private NumberKey handler;
   private Backspace backspace_key;
   private ClearKey clearKey;
   private OK_Key okKey;
   private ZeroKey zKey;
   private DotKey dotkey;

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
      zKey = new ZeroKey();
      dotkey = new DotKey();

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
      numberbuttons[9].addActionListener(zKey);
      numberbuttons[10] = new JButton("00");
      numberbuttons[10].addActionListener(zKey);
      numberbuttons[11] = new JButton(".");
      numberbuttons[11].addActionListener(dotkey);
      
      numberKeyPanel.add(numberbuttons[9]);
      numberKeyPanel.add(numberbuttons[10]);
      numberKeyPanel.add(numberbuttons[11]);
      keyButtons[0] = new JButton("OK");
      keyButtons[0].addActionListener(okKey);
      keyButtonsPanel.add(keyButtons[0]);
      keyButtons[1] = new JButton("Backspace");
      keyButtons[1].addActionListener(backspace_key);
      keyButtonsPanel.add(keyButtons[1]);
      keyButtons[2] = new JButton("Clear");
      keyButtons[2].addActionListener(clearKey);
      keyButtons[0].setBackground(Color.green); //ok key
      keyButtons[1].setBackground(Color.red); //backspace
      keyButtons[2].setBackground(Color.yellow); //clear? cancel?

      keyButtonsPanel.add(keyButtons[2]);
      keyPadPanel.add(numberKeyPanel, BorderLayout.CENTER);
      keyPadPanel.add(keyButtonsPanel, BorderLayout.EAST);
   }
   // end no-argument Keypad constructor

   //method for login (ID part)
   public void idHandle(){
      setDisable(11);
      setDisable(10);
      login_state = true;
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

   public void requireEnter(){
      isRe_enter = true;
   }

   //method for login (password part)
   public void passwordHandle(){
      userInput2 = "";
      passwordField.setText(userInput2);
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
      login_state = false;
   }

   //clear the input field
   public void clear(){
      userInput = "";
      keyPadPanel.revalidate();
      keyPadPanel.repaint();
      userInputField.setText(userInput);
   }

   //Waiting for user to input
   public void waiting(){
      while (!sendAction) {
         System.out.print("");
      }
      sendAction = false;
   }

   //return the password to ATM
   public int getPassword(){
      return Integer.parseInt(userInput2);
   }

   //return the Keypad UI to screen
   public JPanel getPanel(){
      return keyPadPanel;
   }

   //return the input
   public int getInput(){
      return Integer.parseInt(userInput);
   }

   public double getAmount(){
      return Double.parseDouble(userInput);
   }
   //set the number pad disable
   public void setDisable(){
      for (int i = 0; i < numberbuttons.length; i++) {
         numberbuttons[i].setEnabled(false);
      }
      keyButtons[1].setEnabled(false);
      keyButtons[2].setEnabled(false);
      isOnline = false;
   }
   
   //overload, disable a specific key
   public void setDisable(int temp){
      numberbuttons[temp].setEnabled(false);
   }

   //enable the keypad
   public void setEnable(int temp){
      if (temp == 1) {
         for (int i = 0; i < numberbuttons.length; i++) {
            numberbuttons[i].setEnabled(true);
         }
         keyButtons[1].setEnabled(true);
         keyButtons[2].setEnabled(true);
         isOnline = true;
      }else{
         for (int i = 0; i < numberbuttons.length-2; i++) {
            numberbuttons[i].setEnabled(true);
         }
         keyButtons[1].setEnabled(true);
         keyButtons[2].setEnabled(true);
         isOnline = true;
      }
      
   }

   private class ZeroKey implements ActionListener{
      public void actionPerformed(ActionEvent event) {
         String temp = "";
         if (event.getSource() == numberbuttons[9]){
            temp = "0";
         }else temp = "00";

         if (ispasswordField) {
            userInput2 = userInput + temp;
            passwordField.setText(userInput2);
         }else{
            userInput = userInput + temp;
            userInputField.setText(userInput);
         }
      }
   }

   private class DotKey implements ActionListener{
      public void actionPerformed(ActionEvent event) {
         String temp = ".";
         if (ispasswordField) {
            userInput2 = userInput + temp;
            passwordField.setText(userInput2);
         }else{
            userInput = userInput + temp;
            userInputField.setText(userInput);
         }
      }
   }

   private class OK_Key implements ActionListener{
      public void actionPerformed(ActionEvent event) {
      if (userInput != "" && login_state == true) {
         ispasswordField = true;
      } 
      if (userInput != "" || !isOnline || isRe_enter) {
         sendAction = true;  
         isRe_enter = false;
      }
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