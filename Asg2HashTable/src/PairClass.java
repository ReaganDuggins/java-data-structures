
public class PairClass<F, S> implements Pair<F, S>{

	F f;
	S s;
	
	
	public PairClass(F ff, S ss){
		f = ff;
		s = ss;
	}
	
	public PairClass(F ff){
		f = ff;
	}
	
	public F getFirst() {
		return f;
	}

	
	public S getSecond() {
		return s;
	}

	
	public void setFirst(Object f) {
		this.f = (F)f;
	}

	
	public void setSecond(Object s) {
		this.s = (S)s;
	}
	
	public void print(){
		System.out.println("[First: " + f + "  Second: " + s + "]");
	}
	
	public String toString(){
		return "[First: " + f + "  Second: " + s + "]";
	}

}
