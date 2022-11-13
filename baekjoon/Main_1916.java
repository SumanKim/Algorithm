package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1916 {

	static class Bus implements Comparable<Bus>{
		int to, dis;
		
		Bus(int to, int dis){
			this.to = to;
			this.dis = dis;
		}
		
		public int compareTo(Bus o){
			return Integer.compare(this.dis, o.dis);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		List<Bus> adj[] = new ArrayList[N+1];
		for(int i=1; i<=N; i++)
			adj[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			String input[] = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			adj[a].add(new Bus(b,c));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		boolean v[] = new boolean[N+1];
		int dis[] = new int[N+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		pq.offer(new Bus(start, 0));
		while(!pq.isEmpty()) {
			Bus bus = pq.poll();
			int to = bus.to, dist = bus.dis;
			if(v[to]) continue;
			v[to] = true;
			
			for(Bus b : adj[to]) {
				if(v[b.to]) continue;
				dis[b.to] = Math.min(dis[b.to], dist + b.dis);
				pq.offer(new Bus(b.to, dis[b.to]));
			}
		}
		
		System.out.println(dis[goal]);
	}

}
