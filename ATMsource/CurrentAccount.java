
public class CurrentAccount extends Account {
	private int overdrawnLimit = -10000;
	private final String type = "Current";
	
	CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
		super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
	}
	
	public String gettype()
	{
		return type;
	}	
	
	public double getCurrentAccount() {
		return overdrawnLimit;
	}
	
	public void setCurrentAccount(int limit) {
		overdrawnLimit = limit;
	}
	
	public void checkLimitOfTotalBalance() {
		if (getTotalBalance() < overdrawnLimit)
			System.out.println("You cannot exceed the Overdrawn limit HK$10,000.");
	}
}
