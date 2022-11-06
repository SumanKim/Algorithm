package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16987 {

	static int N, ans;
	static int[][] eggs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		eggs = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i][0] = Integer.parseInt(st.nextToken());
			eggs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		System.out.println(ans);
	}
	
	static void dfs(int cur, int cnt) {
		if(cur == N) {
			ans = Math.max(ans, cnt);
			return;
		}
		
		if(eggs[cur][0] <= 0 || cnt == N-1) {
			dfs(cur+1, cnt);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(i == cur || eggs[i][0] <= 0) continue;
			int curLimit = eggs[cur][0], curWeight = eggs[cur][1];
			int iLimit = eggs[i][0], iWeight = eggs[i][1];
			
			int val = 0;
			eggs[cur][0] -= iWeight;
			eggs[i][0] -= curWeight;
			if(eggs[cur][0] <= 0) val++;
			if(eggs[i][0] <= 0) val++;
			
			dfs(cur+1, cnt+val);
			
			eggs[cur][0] = curLimit;
			eggs[i][0] = iLimit;
		}
	}

}
