// Screen.java
// Represents the screen of the ATM
import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame 
{

   private String screenText = "";

   private JTextArea showingText;
   private JButton button[];
   
   private JPanel westPanel;
   private JPanel eastPanel;

   //constructor initializes the Screen
   //The constructor will insert a JPanel from keypad
   public Screen(JPanel keypadPanel){
      super("ATM");
      button = new JButton[2];
    
      // initializes GUI components
      showingText = new JTextArea(10,10);
      showingText.setEditable(false);
      showingText.setBackground(Color.ORANGE);
      button[0] = new JButton("<=");
      button[1] = new JButton("=>");
      eastPanel = new JPanel();
      westPanel = new JPanel();

      // initializes Layout
      eastPanel.setLayout(new GridLayout(9,1));
      westPanel.setLayout(new GridLayout(9,1));
      setLayout(new BorderLayout());
      setSize(400, 500);
      setVisible(true);
      setLocationRelativeTo(null);

      // Adding components into layout
      eastPanel.add(button[0]);
      westPanel.add(button[1]);
      add(eastPanel, BorderLayout.EAST);
      add(westPanel, BorderLayout.WEST);
      add(showingText, BorderLayout.CENTER);
      add(keypadPanel, BorderLayout.SOUTH);
      setBackground(Color.LIGHT_GRAY);
   }

   // displays a error message
   public void displayErroeMessage(String message) {
      JOptionPane.showMessageDialog(null, message, "ATM Screen",JOptionPane.ERROR_MESSAGE); 
   }

   // displays a message without a carriage return
   public void displayMessage( String message ) 
   {
      screenText = screenText + message;
      showingText();
   } // end method displayMessage

   // display a message with a carriage return
   public void displayMessageLine( String message ) 
   {
      screenText = screenText + message + "\n";
      showingText();
   } // end method displayMessageLine

   // display a dollar amount
   public void displayDollarAmount( double amount )
   {
     screenText = screenText + "HKD$ "+amount;
     showingText();
   } // end method displayDollarAmount
   
   public void displayReset(){
      screenText = "";
      showingText();
   }

   private void showingText(){
      showingText.setText(screenText);
   }
} // end class Screen



/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/