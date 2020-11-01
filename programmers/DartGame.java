package ex;

public class DartGame {

	public static void main(String[] args) {
		String dartResult = "";
		int answer = 0;
		char[] cha = dartResult.toCharArray();
		int tmp = 0;
		int count = 0;
		int[] score = {0, 0, 0, 0};
		
		for (int i = 0; i < cha.length; i++) {
			if(cha[i] >= 48 && cha[i] <= 57) {
				if (cha[i] - cha[i+1] == 1) {
					tmp = cha[i] - 39;
					i++;
				} else tmp = cha[i] -48;
				count ++;
			} else if (cha[i] >='D' && cha[i] <= 'T') {
				if(cha[i] == 'S') score[count] = tmp;
				else if(cha[i] == 'D') score[count] = tmp * tmp;
				else score[count] = tmp * tmp *tmp;
			} else if (cha[i] == '*') {
				score[count] *= 2;
				score[count - 1] *= 2;
			} else if (cha[i] == '#') {
				score[count] *= -1;
			}
		}
		
		for ( int i= 0; i<score.length; i++) {
			answer += score[i];
		}
	}

}
