
public class Teller {
	
	private boolean isBusy;
	private int busyStart;
	private Visit visit;
	
	public Teller(boolean busy){
		isBusy = busy;
	}
	
	
	public boolean isBusy() {
		return isBusy;
	}
	public void setBusy(boolean busy) {
		this.isBusy = busy;
	}
	public String toString(){
		return "Customer: " + visit;
	}


	public Visit getVisit() {
		return visit;
	}


	public void setVisit(Visit visit) {
		if(visit == null){
			isBusy = false;
		}else{
			isBusy = true;
		}
		this.visit = visit;
		
	}


	public int getBusyStart() {
		return busyStart;
	}


	public void setBusyStart(int busyStart) {
		this.busyStart = busyStart;
	}
	
}
