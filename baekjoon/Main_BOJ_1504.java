package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//특정한 최단 경로
public class Main_BOJ_1504 {

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
	
	static final int INF = 99999999;
	static int N, E;
	static int v1, v2;
	static ArrayList<int[]> adj[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		for(int i=1; i<=N; i++)
			adj[i] = new ArrayList<>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new int[] {b,c});
			adj[b].add(new int[] {a,c});
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		int dis1_1[] = new int[N+1];
		dijkstra(dis1_1, 1);
		
		int dis1_2[] = new int[N+1];
		dijkstra(dis1_2, 1);
		
		int dis2_1[] = new int[N+1];
		dijkstra(dis2_1, v1);
		
		int dis2_2[] = new int[N+1];
		dijkstra(dis2_2, v2);
		
		int dis3_1[] = new int[N+1];
		dijkstra(dis3_1, v2);
		
		int dis3_2[] = new int[N+1];
		dijkstra(dis3_2, v1);
		
		int res1 = dis1_1[v1] + dis2_1[v2] + dis3_1[N];
		int res2 = dis1_2[v2] + dis2_2[v1] + dis3_2[N];
		int ans = Math.min(res1, res2);
		System.out.println(ans >= INF ? -1 : ans);
	}

	static void dijkstra(int dis[], int start) {
		Arrays.fill(dis, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean v[] = new boolean[N+1];
		dis[start] = 0;
		pq.add(new Node(start, 0));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(v[node.num]) continue; 
			v[node.num] = true;
			
			for(int[] cur : adj[node.num]) {
				int to = cur[0], val = cur[1];
				if(v[to] || dis[node.num] == INF) continue;
				if(dis[to] > dis[node.num] + val) {
					dis[to] = dis[node.num] + val;
					pq.add(new Node(to, dis[to]));
				}
			}
		}
	}
}
