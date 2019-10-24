//CurrentAccount.java
public class CurrentAccount extends Account {
	private static final double DEFAULT_OVERDRAWN_LIMIT = 10000.0;
	private double avilableOverdrawnLimit = DEFAULT_OVERDRAWN_LIMIT;

	// CurrentAccount constructor
	CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
		// initialize superclass variables
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		super.overdrawnAvailable = true;

		// initialize available overdrawn limit
		if (theAvailableBalance < 0) {
			avilableOverdrawnLimit = avilableOverdrawnLimit + theAvailableBalance;
		}
		
	}// end Transfer constructor

	@Override
	public void debit( double amount) {
		if (amount > super.totalBalance) {
			if ((amount - availableBalance) >= avilableOverdrawnLimit) {	
				avilableOverdrawnLimit = 0;	// set the overdrawn limit to 0
			}else avilableOverdrawnLimit = avilableOverdrawnLimit - amount; // set the overdrawn limit
			super.availableBalance -= amount ; // subtract from available balance
			super.totalBalance -= amount ; // subtract from total balance
		} else {
			super.availableBalance -= amount; // subtract from available balance
			super.totalBalance -= amount; // subtract from total balance
		}
	} // end method debit

	@Override
	public void credit( double amount ) {
		if (super.totalBalance <= 0) { // checking whether the account is being in debt or not
			avilableOverdrawnLimit = amount + avilableOverdrawnLimit; // set the overdrawn limit to the default value
		} 
		super.availableBalance += amount; // add to the available balance
		super.totalBalance += amount; // add to the total balance
		if ((avilableOverdrawnLimit) > DEFAULT_OVERDRAWN_LIMIT) {
			avilableOverdrawnLimit = DEFAULT_OVERDRAWN_LIMIT;	// set the overdrawn limit to the default value
		}
	}

	public void setOverdrawnLimit(double input) { // set overdrawn limit
		availableBalance = input;
	}

	public double getOverdrawnLimit() { // get overdrawn limit
		return avilableOverdrawnLimit;
	}
}
