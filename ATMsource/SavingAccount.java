
public class SavingAccount extends Account {
	private double interestRate = 0.001;
	private final String type = "Saving";
	
	SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
	}
	
	public String gettype()
	{
		return type;
	}

	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double rate) {
		interestRate = rate;
	}
	
	public void annualInterest(int timesOfInterest) {
		for (int i = 1; i <= timesOfInterest; i++) {
			credit(getTotalBalance() * interestRate);
		}
	}
}
