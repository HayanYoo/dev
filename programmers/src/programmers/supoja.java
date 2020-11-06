package programmers;

import java.util.*;

public class supoja {

	public static void main(String[] args) {
		int[] answers = { 1, 3, 2, 4, 2 };
		
		int[] answer = {};
        
        int[] st1 = {1, 2, 3, 4, 5};
        int[] st2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] st3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] cnt = new int[3];
        
        for(int i =0; i < answers.length; i++){
            if(answers[i] == st1[i%5]){
                cnt[0]++;
            }
            if(answers[i] == st2[i%8]){
                cnt[1]++;
            }
            if(answers[i] == st3[i%10]){
                cnt[2]++;
            }            
            
        }

        int winner = cnt[0];
        for(int i=0; i<cnt.length; i++){
            if(winner < cnt[i]){
                winner = cnt[i];
            }
        }
        
        List<Integer> win = new ArrayList<>();
        
        for (int i =0; i<cnt.length; i++){
            if(winner == cnt[i]){
                win.add(i);
            }
        }
        
        answer = new int[win.size()];
        
        for(int i = 0; i<win.size(); i++){
            answer[i]= win.get(i)+1;
        }
    
        System.out.println(Arrays.toString(answer));
	}

}
