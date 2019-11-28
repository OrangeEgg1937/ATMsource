// Transaction.java
// Represents a transfer ATM transaction

public class Transfer extends Transaction
{
    private double amount; // amount to transfer
    private Keypad keypad; // reference to keypad
    private int transferAcctNum;
    
    // constant corresponding to menu option to cancel
    private final static int CANCELED = 0;
    
    // Transfer constructor
    public Transfer(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad)
    {
        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
      
        // initialize references to keypad
        keypad = atmKeypad;
    } // end Transfer constructor
    
    // perform transaction
    public void execute()
    {
        boolean fundTransferred = false; // fund was not transferred yet
        double availableBalance; // amount available for withdrawal
        
        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        int input;
        // loop until fund is transferred or the user cancels
        do
        {
            keypad.setEnable(0);
            keypad.clear();
            /* 1. Enter transfer acct num / Cancel transaction */
            screen.displayMessageLine("\nPlease enter the transfer account number: ");
            screen.displayMessageLine("\n0 - Cancel transaction\n");
            keypad.waiting();
            transferAcctNum = keypad.getInput();
            screen.displayReset();
            
            if (transferAcctNum == super.getAccountNumber())
            {
                screen.displayMessageLine("\nYou are not allowed to transfer to your own account.");
                continue;
            } // end if
            
            if (transferAcctNum != CANCELED)
            {
                if (bankDatabase.findUser(transferAcctNum) == false)
                {
                    screen.displayMessageLine("\nThe transfer account you entered does not exist.");
                    continue;
                } // end if
                
                /* Confirm acct num / Re-enter / Cancel transaction */
                do 
                {
                    keypad.clear();
                    screen.displayMessageLine("\nThe transfer account number you entered is: " + transferAcctNum);
                    screen.displayMessageLine("\n1 - Confirm");
                    screen.displayMessageLine("2 - Re-enter");
                    screen.displayMessageLine("0 - Cancel transaction\n");
                    keypad.waiting();
                    input = keypad.getInput();
                    screen.displayReset();
                    switch (input)
                    {
                        case 1:
                        {
                            do
                            {
                                if (bankDatabase.supportOverdrawn(getAccountNumber())){
                                    screen.displayMessage("\nThis is current account, the available overdrawn limit: ");
                                    screen.displayDollarAmount(bankDatabase.accountOverdrawnLimit(getAccountNumber()));
                                 }
                                /* 2. Enter transfer amount */
                                screen.displayMessageLine("\nPlease enter the transfer amount: ");
                                screen.displayMessageLine("\n0 - Cancel transaction\n");
                                
                                do {
                                    keypad.clear();
                                    keypad.setEnable(1);
                                    keypad.waiting();
                                	amount = keypad.getAmount();
                                	if (amount < 0)
                                		screen.displayMessageLine("\nInvalid input. Try again.\n" );
                                }while (amount < 0);
                                
                                if (amount != CANCELED)
                                {
                                    /* Check if user account has enough money */
                                    if (bankDatabase.supportOverdrawn(getAccountNumber())){
                                        if (bankDatabase.getAvailableBalance(getAccountNumber()) < 0) {
                                           availableBalance = bankDatabase.accountOverdrawnLimit(getAccountNumber());
                                        } else availableBalance =  bankDatabase.accountOverdrawnLimit(getAccountNumber()) + bankDatabase.getAvailableBalance(getAccountNumber());
                                    } else availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
                                    
                                    if (amount <= availableBalance && amount > 0)
                                    {
                                        /* Confirm / Re-enter / Cancel transaction */
                                        keypad.clear();
                                        keypad.setDisable();
                                        keypad.setEnable(0);
                                        screen.displayMessageLine(" ");
                                        screen.displayMessage("The transfer amount is: " );
                                        screen.displayDollarAmount(amount);
                                        screen.displayMessageLine("\n1 - Confirm");
                                        screen.displayMessageLine("2 - Re-enter");
                                        screen.displayMessageLine("0 - Cancel transaction\n");
                                        keypad.waiting();
                                        input = keypad.getInput();
                                        screen.displayReset();
                                        switch (input)
                                        {
                                            case 1:
                                            {
                                                /* 3. Process Transfer*/
                                                // update the account involved to reflect transfer
                                                bankDatabase.debit(getAccountNumber(), amount);
                                                bankDatabase.credit(transferAcctNum, amount);
                                                fundTransferred = true; // fund was transferred

                                                /* 4. Prompt success or not + yes/no receipt */
                                                keypad.clear();
                                                keypad.setEnable(0);
                                                screen.displayMessageLine("\nTransfer succeed. Do you need receipt?");
                                                screen.displayMessageLine("\n1 - Yes");
                                                screen.displayMessageLine("0 - No\n");
                                                keypad.waiting();
                                                input = keypad.getInput();

                                                /* 5. Prompt user to take the debit card (and receipt) */
                                                do
                                                {
                                                    if (input == 1)
                                                    screen.displayMessageLine("\nPlease take your debit card and receipt now." );
                                                    else if (input == 0)
                                                        screen.displayMessageLine("\nPlease take your debit card now." );
                                                    else
                                                        screen.displayMessageLine("\nInvalid selection. Try again.");
                                                } while (input > 1);
                                            }
                                            case 2:
                                                break;
                                            case CANCELED:
                                                screen.displayMessageLine("\nCanceling transaction...");
                                                return; // return to main menu because user canceled
                                            default:
                                                screen.displayMessageLine("\nInvalid selection. Try again.");
                                        } // end switch
                                    } // end if
                                    else
                                        screen.displayMessageLine("\nInsufficient funds in your account." +
                                                                  "\n\nPlease choose a smaller amount." );
                                } // end if
                                else
                                {
                                    screen.displayMessageLine("\nCanceling transaction...");
                                    return; // return to main menu because user canceled
                                } // end else
                            } while (fundTransferred == false);
                        }
                        case 2:
                            break;
                        case CANCELED:
                            screen.displayMessageLine("\nCanceling transaction...");
                            return; // return to main menu because user canceled
                        default:
                            screen.displayMessageLine("\nInvalid selection. Try again.");
                    } // end switch
                } while (fundTransferred == false && input != 2);
            } // end if
            else
            {
                screen.displayMessageLine("\nCanceling transaction...");
                return; // return to main menu because user canceled
            } // end else
        } while (fundTransferred == false);
    } // end method execute
} // end class Transfer
