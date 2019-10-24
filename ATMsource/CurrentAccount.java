
public class CurrentAccount extends Account {
	private double avilableOverdrawnLimit = 10000;

	CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
		super.overdrawnAvailable = true;
	}

	@Override
	public void debit( double amount) {
		if (amount > totalBalance) {
			super.availableBalance -= amount - avilableOverdrawnLimit; // subtract from available balance
			super.totalBalance -= amount - avilableOverdrawnLimit; // subtract from total balance
			avilableOverdrawnLimit = amount - avilableOverdrawnLimit;
		} else {
			super.availableBalance -= amount; // subtract from available balance
			super.totalBalance -= amount; // subtract from total balance
		}
	} // end method debit

	@Override
	public void credit( double amount ) {
		if (super.totalBalance < 0) { // checking whether the account is being in debt or not
			avilableOverdrawnLimit = amount + avilableOverdrawnLimit; // set the overdrawn limit to the default value
		} 
		super.availableBalance += amount; // add to the available balance
		super.totalBalance += amount; // add to the total balance
	}
	
	public void setOverdrawnLimit(double input) {
		availableBalance = input;
	}

	public double getOverdrawnLimit() {
		return avilableOverdrawnLimit;
	}
}
