// BankDatabase.java
// Represents the bank account information database 

public class BankDatabase
{
   private Account accounts[]; // array of Accounts
   
   // no-argument BankDatabase constructor initializes accounts
   public BankDatabase()
   {
      accounts = new Account[ 7 ]; // 7 accounts for testing
      accounts[ 0 ] = new SavingAccount( 1, 1, 10000.0, 10000.0 );
      accounts[ 1 ] = new SavingAccount( 2, 1, 5000.0, 10000.0 );
      accounts[ 2 ] = new SavingAccount( 3, 1, 0.0, 0.0 );
      accounts[ 3 ] = new CurrentAccount( 4, 1, 10000.0, 10000.0 ); 
      accounts[ 4 ] = new CurrentAccount( 5, 1, 5000.0, 10000.0 ); 
      accounts[ 5 ] = new CurrentAccount( 6, 1, 0.0, 0.0 ); 
      accounts[ 6 ] = new CurrentAccount( 7, 1, -5000.0, -5000.0 );  
   } // end no-argument BankDatabase constructor
   
   // retrieve Account object containing specified account number
   private Account getAccount( int accountNumber )
   {
      // loop through accounts searching for matching account number
      for ( Account currentAccount : accounts )
      {
         // return current account if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return currentAccount;
      } // end for

      return null; // if no matching account was found, return null
   } // end method getAccount

   // determine whether user-specified account number and PIN match
   // those of an account in the database
   public boolean authenticateUser( int userAccountNumber, int userPIN )
   {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount( userAccountNumber );

      // if account exists, return result of Account method validatePIN
      if ( userAccount != null )
         return userAccount.validatePIN( userPIN );
      else
         return false; // account number not found, so return false
   } // end method authenticateUser

    public boolean findUser(int userAccountNumber)
    {
        // attempt to retrieve the account with the account number
        Account userAccount = getAccount( userAccountNumber );

        // if account exists, return true. Else, return false
        return userAccount != null;
    } // end method findUser
   
   // return available balance of Account with specified account number
   public double getAvailableBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getAvailableBalance();
   } // end method getAvailableBalance

   // return total balance of Account with specified account number
   public double getTotalBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getTotalBalance();
   } // end method getTotalBalance

   // credit an amount to Account with specified account number
   public void credit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).credit( amount );
   } // end method credit

   // debit an amount from of Account with specified account number
   public void debit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).debit( amount );
   } // end method debit
   
   // check the account has support overdrawn or not
   public boolean supportOverdrawn(int userAccountNumber)
   {
      return getAccount( userAccountNumber ).hasOverdrawn();
   } // end method supportOverdrawn
   
   public double accountOverdrawnLimit(int userAccountNumber)
   {
      if  (supportOverdrawn(userAccountNumber)){
      return ((CurrentAccount) getAccount(userAccountNumber)).getOverdrawnLimit();
      }else return 0;
   } 
   
   // check the account has support interest or not
   public boolean supportInterest(int userAccountNumber)
   {
      return getAccount( userAccountNumber ).hasInterest();
   } // end method supportInterest
   
} // end class BankDatabase



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
