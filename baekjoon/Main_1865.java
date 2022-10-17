package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//웜홀 - 벨만 포드 알고리즘
public class Main_1865 {
	
	static class Node implements Comparable<Node>{
		int num, dis;
		
		Node(int num, int dis){
			this.num = num;
			this.dis = dis;
		}
		
		public int compareTo(Node o) {
			return Integer.compare(this.dis, o.dis);
		}
	}
	
	static int N, M, W, start;
	static ArrayList<int[]> adj[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			adj = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				String str[] = br.readLine().split(" ");
				int a = Integer.parseInt(str[0]);
				int b = Integer.parseInt(str[1]);
				int c = Integer.parseInt(str[2]);
				adj[a].add(new int[] {b,c});
				adj[b].add(new int[] {a,c});
			}
			for(int i=0; i<W; i++) {
				String str[] = br.readLine().split(" ");
				int a = Integer.parseInt(str[0]);
				int b = Integer.parseInt(str[1]);
				int c = Integer.parseInt(str[2]);
				adj[a].add(new int[] {b,-c});
			}
			
			boolean isS = false;
			for(int i=1; i<=N; i++) { //N개의 정점에 대하여 벨만 포드
				if(bellmanford(i)) {
					isS = true;
					break;
				}
			}
			
			if(isS)
				sb.append("YES\n");
			else 
				sb.append("NO\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean bellmanford(int s) {
		int dist[] = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s] = 0;
		
		boolean isUpdated = false;
		for(int i=0; i<N; i++) {
			isUpdated = false;
			for(int j=1; j<=N; j++) {
				for(int arr[] : adj[j]) {
					int to = arr[0], val = arr[1];
					
					if(dist[j] == Integer.MAX_VALUE) continue;
					
					if(dist[to] > dist[j] + val) {
						dist[to] = dist[j] + val;
						isUpdated = true;
						if(i == N-1)
							return true;
					}
				}
			}
			
			if(!isUpdated) break;
		}
		
		return false;
	}
}
