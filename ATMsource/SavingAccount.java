
public class SavingAccount extends Account {
	private double interestRate = 0.001;
	
	SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double rate) {
		interestRate = rate;
	}
	
	public void annualInterest() {
		credit(getTotalBalance() * interestRate);
	}
}
