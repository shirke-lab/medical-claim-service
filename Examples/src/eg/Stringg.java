package eg;

public class Stringg {
	public static void main(String[] args) {
	StringBuilder sb=new StringBuilder() ;
	
	for(int i=0;i<=100;i++) {
		sb.append(i);
		}
	System.out.println(sb);
	String s11="hi EAY im a AEAEAE java developer";
	s11=s11.toLowerCase();
	int countVowels =0;
	for(int j=0;j<s11.length();j++) {
		char x;
		x=s11.charAt(j);
		
		if((x=='a') ||(x=='e')||(x=='i')||(x=='o')||(x=='u')) {
		countVowels +=1;
		}
	}
	System.out.println(countVowels);
	}
}
	
	
	
