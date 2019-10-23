
public class SavingAccount extends Account {
	private double interestRate = 0.001;

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
	public void credit( double amount )
	{
		refresh();
	   	super.availableBalance += amount; // add to the available balance
	   	super.totalBalance += amount; // add to total balance
	} // end method credit

	public void refresh() // add the profit to the balance
	{
		long now = System.currentTimeMillis(); // get the current time
		long temp;
		if (now - lastModifiedTime >= 90000) {
			temp = (now - lastModifiedTime)/90000; // find how many interest should be added
			for (int i = 1; i <=temp ; i++) {
				totalBalance = totalBalance * (1+interestRate); 
				availableBalance = availableBalance * (1+interestRate); // add the profit to the balance
			}
			lastModifiedTime = now; // save the time
		}
	}
	
	//Overload
	public double getTotalBalance(){
		refresh();
		return totalBalance;
	}

	//Overload
	public double getAvailableBalance(){
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
