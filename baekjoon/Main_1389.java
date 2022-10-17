package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//케빈 베이컨의 6단계 법칙
public class Main_1389 {

	static int N, M, min = Integer.MAX_VALUE, res = 0;
	static ArrayList<Integer> adj[];
	static int[] kevin_bacon;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		kevin_bacon = new int[N+1];
		for(int i=1; i<=N; i++) 
			adj[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) continue;
				kevin_bacon[i] += bfs(i, j);
			}
			if(min > kevin_bacon[i]) {
				min = kevin_bacon[i];
				res = i;
			}
		}
		
		System.out.println(res);
	}

	private static int bfs(int start, int goal) {
		int res = 0;
		Queue<int[]> q = new LinkedList<>();
		boolean v[] = new boolean[N+1];
		v[start] = true;
		q.add(new int[] {start, 0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int num = cur[0], cnt = cur[1];
			
			if(num == goal) {
				res = cnt;
				break;
			}
			
			for(int i : adj[num]) {
				if(v[i]) continue;
				v[i] = true;
				q.add(new int[] {i, cnt+1});
			}
		}
		
		return res;
	}
}
