// Account.java
// Represents a bank account

public abstract class Account 
{
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   protected double availableBalance; // funds available for withdrawal
   protected double totalBalance; // funds available + pending deposits
   protected boolean interestAvailable = false;
   protected boolean overdrawnAvailable = false; 
   protected long lastModifiedTime = System.currentTimeMillis();

   // Account constructor initializes attributes
   public Account( int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance )
   {
      accountNumber = theAccountNumber;
      pin = thePIN;
      availableBalance = theAvailableBalance;
      totalBalance = theTotalBalance;
   } // end Account constructor

   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN( int userPIN )
   {
      if ( userPIN == pin )
         return true;
      else
         return false;
   } // end method validatePIN
   
   // returns available balance
   public double getAvailableBalance()
   {
      return availableBalance;
   } // end getAvailableBalance

   // returns the total balance
   public double getTotalBalance()
   {
      return totalBalance;
   } // end method getTotalBalance

   // credits an amount to the account
   public abstract void credit( double amount );

   // debits an amount from the account
   public abstract void debit( double amount );

   // returns account number
   public int getAccountNumber()
   {
      return accountNumber;  
   } // end method getAccountNumber

   // return false if the account does not support interest
   public boolean hasInterest(){
      return interestAvailable;
   }

   // return false if the account does not support overdrawn
   public boolean hasOverdrawn(){
      return overdrawnAvailable;
   }
} // end class Account

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