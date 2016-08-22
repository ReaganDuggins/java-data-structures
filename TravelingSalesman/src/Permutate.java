public class Permutate {
	public static String perms;
	public static long numPerms;
	public static void permuteString(String beginningString, String endingString) {
	    if (endingString.length() <= 1){
	    	numPerms++;
	    	if(numPerms % 1000 == 0) System.out.println(numPerms);
	    	perms += ("A" + beginningString + endingString + "A;");
	    }else{
	    	for (int i = 0; i < endingString.length(); i++) {
	    		String newString = endingString.substring(0, i) + endingString.substring(i + 1);
	            permuteString(beginningString + endingString.charAt(i), newString);
	        }
	    }
    }
    public static void main(String[] args) {
    	permuteString("", "BCDE");
    	System.out.println(perms.split(";")[0].substring(1));
    }
}