
public class Vertex {
	public double lat;
	public double lon;
	public String name;
	
	public Vertex(String n, double la, double lo){
		lat = la;
		lon = lo;
		name = n;
	}
	
	public String toString(){
		return name + "(" + lat + ", " + lon + ")";
	}
}
