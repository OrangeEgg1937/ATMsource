
public class SavingAccount extends Account {
	private static double interestRate = 0.001; // we assume every accounts' interest rate is the same 

	SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
	}

	@Override
	public void debit( double amount ) {
		refresh();
		super.availableBalance -= amount; // subtract from available balance
		super.totalBalance -= amount; // subtract from total balance
	} // end method debit

	@Override
	public void credit( double amount ) // override credit method from account
	{
		refresh();
	   	super.availableBalance += amount; // add to the available balance
	   	super.totalBalance += amount; // add to total balance
	} // end method credit

	public void refresh() // add the profit to the balance
	{
		long now = System.currentTimeMillis(); // get the current time
		long temp;
		if (now - lastModifiedTime >= 5000) { // = 0.001s
			temp = (now - lastModifiedTime)/(5000*10); // find how many interest should be added
			for (int i = 1; i <=temp ; i++) {
				totalBalance = totalBalance * (1+interestRate) * 100; 
				availableBalance = availableBalance * (1+interestRate) * 100; // add the profit to the balance
			}
			lastModifiedTime = now; // save the time
		}
	}
	
	@Override
	public double getTotalBalance(){ // override getTotalBalance method from account
		refresh();
		return totalBalance;
	}

	@Override
	public double getAvailableBalance(){ // override getAvailableBalance method from account
		refresh();
		return availableBalance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double rate) {
		interestRate = rate;
	}

}
