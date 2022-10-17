package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//bfs 방식
public class Main_1953_팀배분2 {
	
	static int n;
	static int hate[][];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		hate= new int[n+1][n+1];
		int v[] = new int[n+1];
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			for(int j=0; j<a; j++) {
				int b = Integer.parseInt(st.nextToken());
				hate[i][b] = hate[b][i] = 1;
			}
		}
		
		for(int i=1; i<=n; i++) {
			if(v[i] != 0) continue;
			v[i] = 1;
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(i);
			while(!q.isEmpty()) {
				int cur = q.poll();
				for(int j=1; j<=n; j++) {
					if(v[j] != 0) continue;
					if(hate[cur][j] == 1) {
						v[j] = v[cur]*-1;
						q.offer(j);
					}
				}
			}
		}
		
		int cnt1 = 0, cnt2 = 0;
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		for(int i=1; i<=n; i++) {
			if(v[i] == 1) {
				cnt1++;
				sb1.append(i).append(" ");
			}
			else if(v[i] == -1) {
				cnt2++;
				sb2.append(i).append(" ");
			}
		}
		
		System.out.println(cnt1);
		System.out.println(sb1);
		System.out.println(cnt2);
		System.out.println(sb2);
	}

}
