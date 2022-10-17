package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//연결 요소의 개수 - flood fill
public class Main_11724 {

	static int res = 0;
	static boolean v[];
	static ArrayList<Integer> adj[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		for(int i=1; i<=N; i++) 
			adj[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		v = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			if(v[i]) continue;
			dfs(i);
			res++;
		}
		System.out.println(res);
	}

	private static void dfs(int cur) {
		v[cur] = true;
		for(int next : adj[cur]) {
			if(v[next]) continue;
			v[next] = true;
			dfs(next);
		}
	}
}
