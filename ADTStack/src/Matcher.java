
public class Matcher {
	
	
	
	public MatchRtnCode matcher(String str) {
		
		LLStack<Character> lls = new LLStack<Character>();
		
		
		for(int i = 0; i < str.length(); i++){ // loop through the characters
			if(str.charAt(i) == 'a' || str.charAt(i) == 'b' || str.charAt(i) == '=')
				continue;
			if(str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '['){
				lls.push(str.charAt(i));
			}else{
				
				char popped = 0;
				if(lls.isEmpty()){
					return MatchRtnCode.TOO_MANY_CLOSED_SYMBOLS;
				}
				popped = lls.pop();
				if(popped == '('){
					if(str.charAt(i) != ')')
						return MatchRtnCode.UNEXPECTED_SYMBOL;
				}else if( popped == '{' || popped == '['){
					if(str.charAt(i) != popped+2)
						return MatchRtnCode.UNEXPECTED_SYMBOL;
				}
				
			}
		}
		if(!lls.isEmpty())
			return MatchRtnCode.TOO_MANY_OPEN_SYMBOLS;
		
		return MatchRtnCode.GOOD_STRING;
				
		
	}
	
	
}
