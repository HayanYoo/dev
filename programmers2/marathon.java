package programmers;

import java.util.*;
public class marathon {

	public static void main(String[] args) {
		String answer = "";
		
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		
		ArrayList<String> list_participant = new ArrayList<>();
		ArrayList<String> list_completion = new ArrayList<>();
		
		for ( String part : participant) {
			list_participant.add(part);
		}
		
		for ( String com : completion) {
			list_completion.add(com);
		}
		list_completion.add("zzz");
		
		Collections.sort(list_participant);
		Collections.sort(list_completion);
		
		System.out.println(list_participant);
		System.out.println(list_completion);
		
		for(int  i = 0;  i < list_participant.size(); i++) {
			if(!list_participant.get(i).equals(list_completion.get(i))) {
				answer = list_participant.get(i);
				break;
			}
		}
		
		System.out.println(answer);

	}

}
