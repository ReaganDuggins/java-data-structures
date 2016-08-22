
public class Radix {

	Queue[] queues;
	
	public Radix(){
		queues = new Queue[10];
		for(int i = 0; i < queues.length; i++){
			queues[i] = new Queue();
		}
	}
	
	public int getXPlace(int n, int x){
		//returns the x'th place digit of n
		//x should be a multiple of 10, or the number 1
		// 1 gives rightmost digit, 10 the second rightmost, etc.
		if(x < 1) x = 1;
		return (n/x) % 10;
	}
	
	public int highestPlace(int n){
		int x = 10;
		while(x < n){
			x *= 10;
		}
		return x / 10;
	}
	
	public Queue sort(Queue inq){
		int largest = inq.getLargest();
		int place = 1;
		while(place <= largest){
			//go until the largest number has been handled
			while(!inq.isEmpty()){
				//empty inq into buckets
				int num = inq.remove();
				queues[getXPlace(num,place)].add(num);
			}
			place *= 10;
			for(int i = 0; i < queues.length; i++){
				//empty each queue in queues back into inq
				while(!queues[i].isEmpty()){
					inq.add(queues[i].remove());
				}
			}
		}
		return inq;
	}
	
	public static void main(String args[]){
		Queue q0 = new Queue();
		q0.add(325);
		q0.add(901);
		q0.add(474);
		q0.add(7);
		q0.add(72);
		q0.print();
		System.out.println("\n-------------");
		q0 = new Radix().sort(q0);
		q0.print();
	}
}

