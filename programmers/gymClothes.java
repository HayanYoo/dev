package programmers;

public class gymClothes {

	public static void main(String[] args) {
// 이중 포문
/*
 * 
		int n = 5;
		int[] lost = {2,4}; 
		int[] reserve = {1, 3, 5};
		
		int answer = 0;
		

		int getCloth_stu = 0;
		int hasCloth_stu = n - lost.length;
		
		// 도난당한 사람이 여분소지자일경우 : 빌려줄수 없음
		for(int i =0; i<lost.length; i++) {
			for(int j=0; j<reserve.length; j++) {
				if(lost[i] == reserve[j]) {
					hasCloth_stu++;
					lost[i] = -1;	// 번호를 제외시키기 위해 음수사용
					reserve[j] = -1;
					break;
				}
			}
		}
		
		// 여분 빌려준 여분소지자 -> 더이상 빌려줄수 없다
		for(int i=0; i<lost.length; i++) {
			for(int j=0; j<reserve.length; j++) {
				if(lost[i]==reserve[j]-1 || lost[i]==reserve[j]+1) {
					getCloth_stu++;
					reserve[j] = -1;	// 이제 못빌려준다
					break;
				}
			}
		}

		answer = hasCloth_stu + getCloth_stu;
*/
		
// 단일 포문		
		int n = 5;
		int[] lost = {2,4}; 
		int[] reserve = {1, 3, 5};
		
		int[] people = new int[n]; // {0, 0, 0, 0, 0};
		int answer = n;
		
		for (int l : lost)
			people[l-1]--;	// {0, -1, 0, -1, 0}
		for (int r : reserve) 
			people[r-1]++;	// {1, -1, 1, -1, 1}
			
		for ( int i =0; i < people.length; i++) {
			if(people[i] == -1 ) {
				if (i-1>=0 && people[i-1] == 1) {
					people[i]++;
					people[i-1]--;
				} else if (i+1 < people.length && people[i+1] == 1) {
					people[i]++;
					people[i+1]--;
				}else answer--;
			}
		}
		
		System.out.println(answer);
	}

}
