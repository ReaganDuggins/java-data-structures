
public class Visit {

	private int arrivalTime;
	private int serviceStart;
	private int serviceEnd;
	private int visitLength;
	private int waitTime;
	public Visit(int arrive, int visLen){
		setArrivalTime(arrive);
		setTimeNeeded(visLen);
		setServiceStart(-1);
		serviceEnd = -1;
	}

	public String toString(){
		return "Visit at " + arrivalTime + " for " + visitLength + ", ";
	}


	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getTimeNeeded() {
		return visitLength;
	}

	public void setTimeNeeded(int timeNeeded) {
		this.visitLength = timeNeeded;
	}

	public int getServiceEnd() {
		return serviceEnd;
	}

	public void setServiceEnd(int serviceEnd) {
		this.serviceEnd = serviceEnd;
	}

	public int getServiceStart() {
		return serviceStart;
	}

	public void setServiceStart(int serviceStart) {
		this.serviceStart = serviceStart;
	}

	public int getWaitTime() {
		return getServiceStart()-getArrivalTime();
	}
	
}
