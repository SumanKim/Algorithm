package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1,2,3 더하기
public class Main_9095 {

	static int res;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			res = 0;
			int n = Integer.parseInt(br.readLine());
			get_res(n, 0);
			sb.append(res+"\n");
		}
		System.out.println(sb.toString());
	}

	private static void get_res(int goal, int cur) {
		if(cur > goal) return;
		
		if(cur == goal) {
			res++;
			return;
		}
		
		for(int i=1; i<=3; i++) {
			int val = cur + i;
			get_res(goal, val);
		}
	}
}
