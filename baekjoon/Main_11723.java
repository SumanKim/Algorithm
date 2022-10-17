package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

//집합
public class Main_11723 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String com = st.nextToken();
			if(com.equals("add")) {
				int val = Integer.parseInt(st.nextToken());
				set.add(val);
			}
			else if(com.equals("remove")) {
				int val = Integer.parseInt(st.nextToken());
				set.remove(val);
			}
			else if(com.equals("check")) {
				int val = Integer.parseInt(st.nextToken());
				if(set.contains(val)) sb.append("1\n");
				else sb.append("0\n");
			}
			else if(com.equals("toggle")) {
				int val = Integer.parseInt(st.nextToken());
				if(set.contains(val)) set.remove(val);
				else set.add(val);
			}
			else if(com.equals("all")) {
				set = new HashSet<>();
				for(int k=1; k<=20; k++) {
					set.add(k);
				}
			}
			else if(com.equals("empty")) {
				set = new HashSet<>();
			}
		}
		System.out.println(sb.toString());
	}

}
