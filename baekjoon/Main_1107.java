package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//리모컨
public class Main_1107 {

	static int res = Integer.MAX_VALUE;
	static boolean[] broken;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		broken = new boolean[10];
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		if(M != 0)
			st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int x = Integer.parseInt(st.nextToken());
			broken[x] = true;
		}
		
		int res = Math.abs(N - 100);
		for(int i=0; i<=999999; i++) {
			String str = String.valueOf(i);
			int len = str.length();
			
			boolean flag = true;
			for(int j=0; j<len; j++) {
				if(broken[str.charAt(j)-'0']) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				int min = Math.abs(N - i) + len;
				res = Math.min(min, res);
			}
		}
		
		System.out.println(res);
	}
}
