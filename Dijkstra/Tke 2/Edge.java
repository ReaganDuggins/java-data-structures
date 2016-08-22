public class Edge{
	public String name;
	public Vertex v0;
	public Vertex v1;
	public double dist;
	
	public Edge(String name, Vertex v0, Vertex v1){
		
		this.name = name;
		this.v0 = v0;
		this.v1 = v1;
		
		dist = calcDist(v0.lat, v0.lon, v1.lat, v1.lon);
		
	}
	
	public String toString(){
		return "(" + v0.name + " - " + v1.name + "): " + "[" + dist + "]";
	}
	

	
	//Following method was written by Dr. Hunt of Covenant College//
	double calcDist(double lat1, double lon1, double lat2, double lon2){
		// calcDist take two latitude / longitude pairs and returns the
		// distance between them in KILOMETERS
		double R = 6371; //km
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double radDiffLat = Math.toRadians(lat2-lat1);
		double radDiffLon = Math.toRadians(lon2-lon1);
		
		double a = Math.sin(radDiffLat/2) * Math.sin(radDiffLat/2) +
				Math.cos(radLat1) * Math.cos(radLat2) *
				Math.sin(radDiffLon/2) * Math.sin(radDiffLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a),  Math.sqrt(1-a));
		
		return R * c;
	}
}