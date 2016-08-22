/*
 * Pair holds 2 generic values referred to as First and Second
 * and allows access to them
 */
public interface Pair<F, S> {
	F getFirst();
	S getSecond();
	void setFirst(F f);
	void setSecond(S s);
}