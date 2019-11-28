// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction
{
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser
   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

   // Withdrawal constructor
   public Withdrawal( int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser )
   {
      // initialize superclass variables
      super( userAccountNumber, atmScreen, atmBankDatabase );
      
      // initialize references to keypad and cash dispenser
      keypad = atmKeypad;
      cashDispenser = atmCashDispenser;
   } // end Withdrawal constructor

   // perform transaction
   public void execute()
   {
      boolean cashDispensed = false; // cash was not dispensed yet
      double availableBalance; // amount available for withdrawal

      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase(); 
      Screen screen = getScreen();
      
      // loop until cash is dispensed or the user cancels
      do
      {
         // Display available overdraw amount if it is current account
         if (bankDatabase.supportOverdrawn(getAccountNumber())){
            screen.displayMessage("\nThis is current account, the available overdrawn limit: ");
            screen.displayDollarAmount(bankDatabase.accountOverdrawnLimit(getAccountNumber()));
         }
         // obtain a chosen withdrawal amount from the user 
         amount = displayMenuOfAmounts();
         
         // check whether user chose a withdrawal amount or canceled
         if ( amount != CANCELED )
         {
            
            /* Check if user account has enough money */
            if (bankDatabase.supportOverdrawn(getAccountNumber())){
               if (bankDatabase.getAvailableBalance(getAccountNumber()) <= 0) {
                  availableBalance = bankDatabase.accountOverdrawnLimit(getAccountNumber());
               } else availableBalance = bankDatabase.accountOverdrawnLimit(getAccountNumber()) + bankDatabase.getAvailableBalance(getAccountNumber());
           } else availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
      
            // check whether the user has enough money in the account 
            if ( amount <= availableBalance )
            {   
               // check whether the cash dispenser has enough money
               if ( cashDispenser.isSufficientCashAvailable( amount ) )
               {
                  // update the account involved to reflect withdrawal
                  bankDatabase.debit( getAccountNumber(), amount );
                  
                  cashDispenser.dispenseCash( amount ); // dispense cash
                  cashDispensed = true; // cash was dispensed

                  // instruct user to take cash
                  showMoney();
                  screen.displayMessageLine( 
                     "\nPlease take your cash now." );
               } // end if
               else // cash dispenser does not have enough cash
                  screen.displayMessageLine( 
                     "\nInsufficient cash available in the ATM." +
                     "\n\nPlease choose a smaller amount." );
            } // end if
            else // not enough money available in user's account
            {
               screen.displayMessageLine( 
                  "\nInsufficient funds in your account." +
                  "\n\nPlease choose a smaller amount." );
            } // end else
         } // end if
         else // user chose cancel menu option 
         {
            screen.displayMessageLine( "\nCanceling transaction..." );
            
            return; // return to main menu because user canceled
         } // end else
      } while ( !cashDispensed );
   } // end method execute

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts()
   {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int amounts[] = { 0,100,500,1000 };

      // loop while no valid choice has been made
      while ( userChoice == 0 )
      {
         // display the menu
         keypad.setEnable(1);
         keypad.setDisable(11);
         keypad.clear();
         screen.displayReset();
         screen.displayMessageLine( "Withdrawal Menu:" );
         screen.displayMessageLine( "1 - $100" );
         screen.displayMessageLine( "2 - $500" );
         screen.displayMessageLine( "3 - $1000" );
         screen.displayMessageLine( "6 - Cancel transaction" );
         screen.displayMessageLine("If other, directly input the values");
         keypad.waiting();
         int input = keypad.getInput(); // get user input through keypad
         screen.displayReset();
         screen.displayMessageLine( "===================================================================" );
         keypad.clear();
         keypad.setDisable();
         keypad.setEnable(0, 1);
         int confirmation = 1;  

         switch ( input ) //Confirmation
         {
            case 1: 
            screen.displayMessageLine( "Are you sure withdraw $HKD 100 ?" );
            screen.displayMessageLine("1 - Yes");
            screen.displayMessageLine("2 - No");
            screen.displayMessageLine( "===================================================================" );
            keypad.waiting();
            confirmation = keypad.getInput();
            break;
            case 2:
            screen.displayMessageLine( "Are you sure withdraw $HKD 500 ?" );
            screen.displayMessageLine("1 - Yes");
            screen.displayMessageLine("2 - No");
            screen.displayMessageLine( "===================================================================" );
            keypad.waiting();
            confirmation = keypad.getInput();
            break;
            case 3: 
            screen.displayMessageLine( "Are you sure withdraw $HKD 1000 ?" );
            screen.displayMessageLine("1 - Yes");
            screen.displayMessageLine("2 - No");
            screen.displayMessageLine( "===================================================================" );
            keypad.waiting();
            confirmation = keypad.getInput();
            break;       
            default:
            	if (input%100==0) {
               userChoice=input;
               screen.displayMessageLine( "Are you sure withdraw $HKD " + input +" ?");
               screen.displayMessageLine("1 - Yes");
               screen.displayMessageLine("2 - No");
               screen.displayMessageLine( "===================================================================" );
               keypad.waiting();
               confirmation = keypad.getInput();
            	}
         } // end switch

         if (confirmation == 1) {
            switch ( input )
         {
            case 1: // if the user chose a withdrawal amount 
            case 2: // (i.e., chose option 1, 2 or 3), return the
            case 3: // corresponding amount from amounts array
               userChoice = amounts[ input ]; // save user's choice
               keypad.setDisable();
               break;       
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               keypad.setDisable();
               break;
            default: // the user did not enter a value from 1-3 & 6
            	if (input%100==0) {
               userChoice=input;
               keypad.setDisable();
            	} // check whether the user's input is divisibe by 100
		// make sure the ATM can output the money
            	else
            	{
                  keypad.clear();
                  keypad.setDisable();
               screen.displayMessageLine( 
                  "\nInvalid selection. Try again." );
               }
               screen.displayMessageLine( "Loading to the Withdrawal menu..." );
               screen.displayMessageLine( "Please Click OK to back" );
               keypad.waiting();
         } // end switch
      } else {
         keypad.clear();
         keypad.setDisable();
         screen.displayReset();
         screen.displayMessageLine( "===================================================================" );
         screen.displayMessageLine( "                            CANCELED                               " );
         screen.displayMessageLine( "===================================================================" );
         screen.displayMessageLine( "Loading to the Withdrawal menu..." );
         screen.displayMessageLine( "Please Click OK to back" );
         keypad.waiting();
         userChoice = 0;
      }
      // end while
         }
        
      return userChoice; // return withdrawal amount or CANCELED
   } // end method displayMenuOfAmounts
   
   public void showMoney()	
   {	
	   int temp=amount;	
	   int a=0,b=0,c=0;	

	   while(temp>=1000)	
	   {	
		 temp-=1000;	
		   a=a+1;	
	   }	
	   while(temp>=500)	
	   {	
		   temp-=500;	
		   b=b+1;	
	   }	
	   while(temp>=100)	
	   {	
		   temp-=100;	
		   c=c+1;	
      }	
     Screen screen = getScreen();
     keypad.clear();
     screen.displayReset();
     screen.displayMessageLine("You get: ");
     screen.displayMessageLine("HKD$100 x "+Integer.toString(c));
     screen.displayMessageLine("HKD$500 x "+Integer.toString(b));
     screen.displayMessageLine("HKD$1000 x "+Integer.toString(a));
     screen.displayMessageLine("==================================================");
   }

} // end class Withdrawal



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
