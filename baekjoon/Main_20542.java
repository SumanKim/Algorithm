package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

//받아쓰기
public class Main_20542 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String wrote = br.readLine();
		String answer = br.readLine();
		int dp[][] = new int[n+1][m+1];
		for(int i=0; i<=n; i++) {
			for(int j=0; j<=m; j++) {
				if(i == 0) 
					dp[i][j] = j;
				else if(j == 0) 
					dp[i][j] = i;
				else {
					if(wrote.charAt(i-1) == answer.charAt(j-1) || wrote.charAt(i-1) == 'i' && answer.charAt(j-1) == 'j' 
							|| wrote.charAt(i-1) == 'i' && answer.charAt(j-1) == 'l' || wrote.charAt(i-1) == 'v' && answer.charAt(j-1) == 'w')
						dp[i][j] = dp[i-1][j-1];
					else
						dp[i][j] = Math.min(dp[i-1][j-1]+1, Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
				}
			}
		}
		
		System.out.println(dp[n][m]);
	}

}
