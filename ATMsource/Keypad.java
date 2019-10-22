// Keypad.java
// Represents the keypad of the ATM
import java.util.Scanner; // program uses Scanner to obtain user input

public class Keypad
{
   private Scanner input; // reads data from the command line
                         
   // no-argument constructor initializes the Scanner
   public Keypad()
   {
      input = new Scanner( System.in );    
   } // end no-argument Keypad constructor

   // return an integer value entered by user 
   public int getInput()
   {
        boolean check; // error flag
        int input = 0; // the input value
      do { // data validation check
         try {
            check = false;
            input = this.input.nextInt(); // get the input value
         } catch (Exception e) {
            System.out.print("ERROR:Invalid input, please input an integer: "); //error message 
            skipErrorInput();
            check = true;
         }
      } while (check);
      return input;
   }// end method getInput

   public double getAmount() {
      boolean check; // error flag
      double input = 0; // the input value
      do { // data validation check
         try {
            check = false;
            input = this.input.nextDouble(); // get the input value
         } catch (Exception e) {
            System.out.print("ERROR:Invalid input, please input again: "); //error message 
            skipErrorInput();
            check = true;
         }
      } while (check);
      return input; // we assume that user enters an integer
   }// end method getAmount

   private void skipErrorInput() { // skip invalid input
	   input.nextLine();
   }// end method skipErrorInput
} // end class Keypad  



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