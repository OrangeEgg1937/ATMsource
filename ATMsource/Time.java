
public class Time {
	private static long startTime;
	private static long endTime;
	
	public static void setStartTime() {
		startTime = System.currentTimeMillis()/1000;
	}
	
	public static void setEndTime() {
		endTime = System.currentTimeMillis()/1000;
	}
	
	public static long getEndTime() {
		return(endTime);
	}
	
	public static long getStartTime() {
		return(startTime);
	}
	
	public static int compareTime() {
		int time = 0;
		setEndTime();
		if (endTime - startTime >=5) {
			time = (int)((endTime - startTime)/5);
			setStartTime();
			return time;
		}
		return(0);
	}
}
