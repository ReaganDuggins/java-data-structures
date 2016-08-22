
public class Edge {
	String name;
	private double length;
	private int startV;
	private int endV;
	public Edge nextEdge;
	
	public Edge(String name, int start, int end){
		this.name = name;
		startV = start;
		endV = end;
	}
	
	
	
	public String toString(){
		String s = "(" + name + ", " + startV + "->" + endV + ")";
		return s;
	}
	
	public double getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getStartV() {
		return startV;
	}
	public void setStartV(int startV) {
		this.startV = startV;
	}
	public int getEndV() {
		return endV;
	}
	public void setEndV(int endV) {
		this.endV = endV;
	}
}
