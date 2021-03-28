import java.util.Scanner;

public class Solution {
    private static int[] parseArray(int itemsCount, String array){
        int[] list = new int[itemsCount];
        String num = "";
        int j = 0;
        for(int i = 0; i < array.length() ; i++){
            if(array.charAt(i) == ' '){
                list[j] = Integer.parseInt(num);
                j++;
                num = "";
            } else {
                num += array.charAt(i);
            }
        }
        list[j] = Integer.parseInt(num);
        return list;
    }
	
	private static int smallestPosition(int i, int[] list){
		int smallestNum = list[i];
		int smallestPosition = i;
		for (int j = i + 1; j < list.length ; j++){
			if (list[j] < list[smallestPosition]){
				smallestNum = list[j];
				smallestPosition = j;
			}
		}
		return smallestPosition;
	}
	
	private static void reverseItem(int[] list, int i, int j){
		list[i] = list[i] + list[j];
		list[j] = list[i] - list[j];
		list[i] = list[i] - list[j];
	}
	
	private static void reverse(int[] list, int i, int j){
		while(i < j){
			reverseItem(list, i, j);
			i ++;
			j --;
		}
	}
    
    private static int reversesort(int[] list){
		int cost = 0;
		for(int i = 0 ; i < list.length - 1 ; i++){
			int j = smallestPosition(i, list);
			reverse(list, i, j);
			cost += j - i + 1;
		}
        return cost;
    }
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int numTestCases = in.nextInt();
        for(int i = 0 ; i < numTestCases ; i++){
            int itemsCount = in.nextInt();
			in.nextLine();
            String array = in.nextLine();
            int[] list = parseArray(itemsCount, array);
            int answer = reversesort(list);
            System.out.println("Case #" + (i + 1) + ": " + answer);
        }
    }
}
