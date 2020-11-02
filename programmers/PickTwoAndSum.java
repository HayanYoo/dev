package ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PickTwoAndSum {

	public static void main(String[] args) {
		int[] numbers = { 2, 1, 3, 4, 1 };
		int[] answer = {};

		 HashSet<Integer> set = new HashSet<>();
			

			for (int i = 0; i < numbers.length; i++) {
				for (int j = i+1; j < numbers.length; j++) {
					set.add(numbers[i] + numbers[j]);
				}
			}

			ArrayList<Integer> list = new ArrayList<>(set);
			
			answer = new int[list.size()];
			
			for (int i =0; i < list.size(); i++) {
				answer[i] = list.get(i);
			}
			
			Arrays.sort(answer);
	        

	}

}
