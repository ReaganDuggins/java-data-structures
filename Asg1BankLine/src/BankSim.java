/**
 * Author: Reagan Duggins
 * This program simulates the line at a bank. Given the number of tellers, length of day, visits expected per day, etc. it will calculate the average
 * longest wait time for a customer on any given day, and the average wait time for any given customer on a given day.
 */

import java.util.Random;


public class BankSim {
	PriQueue<Visit> visits = new PriQueue<Visit>();
	long numTrials;
	int dayLength;
	int visitsPerDay;
	int maxVisitLength;
	int clock;
	Visit[] tellers;
	int longestWait;
	int semiTotalWait;
	double avgWait;
	double avgLongestWait;
	
	BankSim(long nt, int dl, int vpd, int mvl, int t){
		//init variables
		numTrials = nt;
		dayLength = dl;
		visitsPerDay = vpd;
		maxVisitLength = mvl;
		tellers = new Visit[t];
		visits = generateVisits();
		longestWait = 0;
		semiTotalWait = 0;
		avgWait = 0.0;
	}
	
	public PriQueue<Visit> generateVisits(){
		Random r = new Random();
		PriQueue<Visit> vis = new PriQueue<Visit>();
		for(int i = 0; i < visitsPerDay; i++){
			//randomly generate each visit and put them into the queue
			Visit v = new Visit(r.nextInt(dayLength),r.nextInt(maxVisitLength)+1);
			vis.insert(v.getArrivalTime(), v);
		}
		return vis;
	}
	
	int getEmptyTeller(){
		if(tellers.length < 1) return -1;
		for(int i = 0; i < tellers.length; i++){
			if(tellers[i] == null) return i;
		}
		return -1;
	}
	
	void endVisits(){
		for(int i = 0; i < tellers.length; i++){
			if(tellers[i] != null && clock >= (tellers[i].getServiceStart()+tellers[i].getTimeNeeded())){
				//if the customer has been at a teller long enough then remove them
				if(tellers[i].getWaitTime() > longestWait) longestWait = tellers[i].getWaitTime();
				semiTotalWait += tellers[i].getWaitTime();
				tellers[i] = null;
			}
		}
	}
	
	boolean isBusyTeller(){
		for(int i = 0; i < tellers.length; i++){
			if(tellers[i] != null){
				return true;
			}
		}
		return false;
	}
	
	void trial(){
		clock = 0;
		while(clock < dayLength || isBusyTeller()){
			//loop through each minute of the day
			while(getEmptyTeller() != -1 && !visits.isEmpty() && visits.peek().getArrivalTime() <= clock){
				int t = getEmptyTeller();
				tellers[t] = visits.remove();
				tellers[t].setServiceStart(clock);
			}
			endVisits();
			clock++;
		}
	}
	
	void simulate(){
		for(int i = 0; i < numTrials; i++){
			visits = generateVisits();
			trial();
			avgWait += semiTotalWait;
			semiTotalWait = 0;
			avgLongestWait += longestWait;
			longestWait = 0;
			
		}
		avgWait /= numTrials;
		avgLongestWait /= numTrials;
		System.out.println("AvgWait: " + avgWait + "\nAvgLongestWait: " + avgLongestWait);
	}
	
	/**************main*******************/
	
	public static void main(String args[]){
		BankSim bs = new BankSim(1000,300,40,7,3);
		bs.simulate();
	}
	
}
