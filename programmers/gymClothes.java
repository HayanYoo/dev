package programmers;

public class gymClothes {

	public static void main(String[] args) {
// ���� ����
/*
 * 
		int n = 5;
		int[] lost = {2,4}; 
		int[] reserve = {1, 3, 5};
		
		int answer = 0;
		

		int getCloth_stu = 0;
		int hasCloth_stu = n - lost.length;
		
		// �������� ����� ���м������ϰ�� : �����ټ� ����
		for(int i =0; i<lost.length; i++) {
			for(int j=0; j<reserve.length; j++) {
				if(lost[i] == reserve[j]) {
					hasCloth_stu++;
					lost[i] = -1;	// ��ȣ�� ���ܽ�Ű�� ���� �������
					reserve[j] = -1;
					break;
				}
			}
		}
		
		// ���� ������ ���м����� -> ���̻� �����ټ� ����
		for(int i=0; i<lost.length; i++) {
			for(int j=0; j<reserve.length; j++) {
				if(lost[i]==reserve[j]-1 || lost[i]==reserve[j]+1) {
					getCloth_stu++;
					reserve[j] = -1;	// ���� �������ش�
					break;
				}
			}
		}

		answer = hasCloth_stu + getCloth_stu;
*/
		
// ���� ����		
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
