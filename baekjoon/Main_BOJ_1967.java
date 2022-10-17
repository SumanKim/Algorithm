package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//트리의 지름
public class Main_BOJ_1967 {

	static int n, pivot = 1, MAX;
	static ArrayList<int[]> adj[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n == 1) {
			System.out.println(0);
			return;
		}
		adj = new ArrayList[10001];
		for(int i=1; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(adj[a] == null) adj[a] = new ArrayList<>();
			adj[a].add(new int[] {b,c});
			if(adj[b] == null) adj[b] = new ArrayList<>();
			adj[b].add(new int[] {a,c});
		}
		
		dfs(0,1,0); //루트 노트부터 시작
		int res = dfs(0,pivot,0);
		System.out.println(res);
	}

	private static int dfs(int pnum, int cnum, int sum) {
		if(MAX < sum) {
			MAX = sum;
			pivot = cnum;
		}
		
		int max = sum;
		for(int[] cur : adj[cnum]) {
			int to = cur[0], dis = cur[1];
			if(to == pnum) continue;
			max = Math.max(max, dfs(cnum, to, sum + dis));
		}
		return max;
	}
}
