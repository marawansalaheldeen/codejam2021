import java.util.Scanner;

public class Solution {
	
	private static void resolveToken(char[] input, int start, int end, int x, int y){
		// case 1: if all are question marks
		if(input[start] == '?' && input[end] == '?'){
			while(start<=end){
				input[start] = 'C';
				start ++;
			}
		// case 2: question mark(s) in beginning
		}else if(input[start] == '?'){
			while(start<end){
				input[start] = input[end];
				start ++;
			}
		// case 3: question mart(s) at the end
		}else if(input[end] == '?'){
			while(end>start){
				input[end] = input[start];
				end --;
			}
		// case 4: questin mark(s) in the meddle with equivalent Cs or Js at end or start
		}else if(input[start] == input[end]){
			char startValue = input[start];
			start ++;
			while(start<end){
				input[start] = startValue;
				start ++;
			}
		// case 5: Mixed Cs and Js at beginning and end
		} else {
			start ++;
			while(start<end){
				input[start] = 'C';
				start ++;
			}	
		}
	}
	
	private static int calculateCost(char[] input, int x, int y){
		int cost = 0;
		for(int i=1;i<input.length ; i++){
			if(input[i] != input[i-1]){
				if(input[i] == 'C'){
					cost += y;
				}else{
					cost += x;
				}
			}
		}
		return cost;
	}
	
	private static int calculateMoonsAndUmbrellaCost(char[] input, int x, int y){
		int i=0;
		while(i<input.length){
			if(input[i] == '?'){
				int tokenStart = i - 1;
				if (i==0){
					tokenStart = i;
				}
				i++;
				while(i<input.length && input[i] == '?'){
					i++;
				}
				int tokenEnd = i;
				if(i == input.length){
					tokenEnd = i - 1;
				}
				
				resolveToken(input, tokenStart, tokenEnd, x, y);
			}
			i++;
		}
		return calculateCost(input, x, y);
	}
	
	
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int numTestCases = in.nextInt();
		in.nextLine();
        for(int i = 0 ; i < numTestCases ; i++){
            String line = in.nextLine();
			InputToken inputToken = new InputToken(line);
			inputToken.process();
			
			int x = inputToken.x;
			int y = inputToken.y;
			char[] input = inputToken.input;
			int answer = calculateMoonsAndUmbrellaCost(input, x, y);
            System.out.println("Case #" + (i + 1) + ": " + answer);
        }
    }
	
	private static class InputToken{
		private String inputStr;
		public int x;
		public int y;
		public char[] input;
		private int index = 0;
		private InputToken(String inputStr){
			this.inputStr = inputStr;
		}
		
		private int getNextTokenAsInt(){
		String num = "";
		for (; index<inputStr.length(); index++){
		     if(inputStr.charAt(index) == ' '){
				 index++;
                return Integer.parseInt(num);
            } else {
                num += inputStr.charAt(index);
            }	
		}
		return Integer.parseInt(num);
		}
		
		private char[] getNextTokenAsCharArray(int length){
			char[] charArray = new char[length];
			for (int j=0; index<inputStr.length(); index++, j++){
				 charArray[j] = inputStr.charAt(index);	
			}
			return charArray;
		}
		
		public void process(){
			x = getNextTokenAsInt();
			y = getNextTokenAsInt();
			input = getNextTokenAsCharArray(inputStr.length() - index);
		}
	}
}
