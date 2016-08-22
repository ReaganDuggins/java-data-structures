public class Vertex{
	public String name;
	public double lat;
	public double lon;
	public Vertex prev;
	public Edge pree;
	
	public Vertex(String name, double lat, double lon){
		this.name = name;
		this.lat = lat;
		this.lon = lon;
	}
	
	public String toString(){
		if(prev == null) return name + "[]: " + lat + ", " + lon;
		return name + "[" + prev.name + "]: " + lat + ", " + lon;
	}
	
}