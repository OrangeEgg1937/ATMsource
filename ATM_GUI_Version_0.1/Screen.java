// Screen.java
// Represents the screen of the ATM
import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame 
{
   private int messageLine = 0;
   private String title = "ATM Screen";
   private JLabel[] labels;

   public Screen(){
      super("ATM");
      setLayout(new GridLayout(14, 1));
      setSize(1200, 600);
      setVisible(true);
      setLocationRelativeTo(null);
      labels = new JLabel[6];
      for (int i = 0; i < labels.length; i++) {
         labels[i] = new JLabel();
         labels[i].setFont(labels[i].getFont ().deriveFont (24.0f));
         add(labels[i]);
      }
   }

   // displays a error message
   public void displayErroeMessage(String message) {
      JOptionPane.showMessageDialog(null, message, "ATM Screen",JOptionPane.ERROR_MESSAGE); 
   }

   // displays a message without a carriage return
   public void displayMessage( String message ) 
   {
      labels[messageLine].setText(message);
      messageLine = messageLine + 1;
   } // end method displayMessage

   // display a message with a carriage return
   public void displayMessageLine( String message ) 
   {
      displayReset();
      labels[messageLine].setText(message);;
      messageLine++;
   } // end method displayMessageLine

   // display a dollar amount
   public void displayDollarAmount( double amount )
   {
      JOptionPane.showMessageDialog(null, "HKD$%,.2f"+amount, title, JOptionPane.INFORMATION_MESSAGE );
   } // end method displayDollarAmount
   
   public void displayReset(){
      for (int i = 0; i < messageLine; i++) {
         labels[i].setText("");
      }
      messageLine = 0;
   }

   public void removeALine(){
      labels[messageLine].setText("");
      messageLine = messageLine - 1;
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