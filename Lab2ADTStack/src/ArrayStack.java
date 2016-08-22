import java.util.EmptyStackException;
public class ArrayStack<E> implements Stack<E> {
	
	private int size;
	private int nextEmpty;
	private E[] stack;
	
	public ArrayStack(int size){
		this.size = size;
		this.nextEmpty = 0;
		stack = (E[]) new Object[size];
	}
	
	public void push(E e) throws FullStackException{
		if(nextEmpty < size){
			stack[nextEmpty]  = e;
			nextEmpty++;
		}
		else
			throw new FullStackException();
	}

	public E pop() throws EmptyStackException {
		if(isEmpty()){
			throw new EmptyStackException();
		}else{
			E e = stack[nextEmpty-1];
			nextEmpty--;
			return e;
		}
	}

	public boolean isEmpty() {
		if(nextEmpty > 0)
			return false;
		else
			return true;
	}

}
