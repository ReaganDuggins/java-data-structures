
public class Radix {

	Queue[] queues;
	Queue inq;
	
	public Radix(Queue q){
		queues = new Queue[28];//0 is blank, apostrophe is 27
		for(int i = 0; i < queues.length; i++){
			queues[i] = new Queue();
		}
		inq = q;
	}
	
	public Queue sort(){
		
		Queue outq = new Queue();
		
		for(int i = 5; i >= 0; i--){
			while(!inq.isEmpty()){
				String s = inq.remove();
				if(s.length()-1 < i){
					//if this word is too short, place it in queue 0
					queues[0].add(s);
				}else{
					int x = s.charAt(i);
					if( x > 96 ){
						System.out.println(s.charAt(i));
						queues[s.charAt(i)-96].add(s);
					}else{
						queues[27].add(s);
					}
				}
			}
			for(int j = 0; j < queues.length; j++){
				while(!queues[j].isEmpty()){
					inq.add(queues[j].remove());
				}
			}
		}
		//empty queues into outq
		
		return inq;
	}
	
	public static void main(String args[]){
		Queue q0 = new Queue();
		q0.add("gamma");
		q0.add("d");
		q0.add("alphb");
		q0.add("balk");
		q0.add("zed");
		q0.add("tanifa");
		q0.print();
		System.out.println("\n-------------");
		q0 = new Radix(q0).sort();
		q0.print();
	}
}

