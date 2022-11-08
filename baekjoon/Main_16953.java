package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16953 {

	static long A, B;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		bt(A, 0);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans+1);
	}
	
	static void bt(long a, int cnt) {
		if(a == B) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(a*2 <= B) bt(a*2, cnt+1);
		if(a*10+1 <= B) bt(a*10+1, cnt+1);
		return;
	}
}
