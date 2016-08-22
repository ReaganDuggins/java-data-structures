/*
 * Testor
 * Author: John M. Hunt
 * Tests the stack interface and implementations for the 
 * ADT stack lab
 */
import java.util.EmptyStackException;
//the above import was making the program ignore my custom EmptyStackException
public class Testor {
											//I added the throws statement so that Java would stop yelling at me for not handling an exception (which wouldn't be thrown where they thought it would but they don't know that)
	public static void main(String[] args){
		// create and test an ArrayStack
		Stack<Integer> s = new ArrayStack<Integer>(4);
		for (int i = 0; i < 4; i++) {
			s.push(i);
		}
		try {
			s.push(99);
		} catch (FullStackException e) {
			System.out.println("Stack full");
		}
		for (int i = 0; i < 4; i++) {
			int n = s.pop();
			System.out.println(n);
		}
		try {
			s.pop();
		} catch (EmptyStackException e) {
			System.out.println("Stack empty");
		}
		
		// create and test a linked list stack
		Stack<Integer> s1 = new LLStack<Integer>();
		for (int i = 0; i < 4; i++) {
			s1.push(i);
		}
		for (int i = 0; i < 4; i++) {
			int n = s1.pop();
			System.out.println(n);
		}
		try {
			s.pop();
		} catch (EmptyStackException e) {
			System.out.println("Stack empty");
		}
	}

}