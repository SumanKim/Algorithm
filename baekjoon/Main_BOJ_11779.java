package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//최소비용 구하기2 - dijkstra
public class Main_BOJ_11779 {

	static class City implements Comparable<City>{
		int num, val;
		
		City(int num, int val) {
			this.num = num;
			this.val = val;
		}
		
		public int compareTo(City c) {
			return Integer.compare(this.val, c.val);
		}
	}
	
	static int N, M;
	static int start, end;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		ArrayList<City> adj[] = new ArrayList[N+1];
		for(int i=0; i<=N; i++)
			adj[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new City(b,c));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> path[] = new ArrayList[N+1];
		PriorityQueue<City> pq = new PriorityQueue<>();
		boolean v[] = new boolean[N+1];
		int dis[] = new int[N+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		for(int i=0; i<=N; i++)
			path[i] = new ArrayList<>();
		path[start].add(start);
		dis[start] = 0;
		pq.add(new City(start,0));
		while(!pq.isEmpty()) {
			City city = pq.poll();
			if(v[city.num]) continue;
			v[city.num] = true;
			
			if(city.num == end) break;
			
			for(City c : adj[city.num]) {
				if(v[c.num]) continue;
				if(dis[c.num] > dis[city.num] + c.val) {
					dis[c.num] =  dis[city.num] + c.val;
					pq.add(new City(c.num, dis[c.num]));
					path[c.num] = new ArrayList<>(path[city.num]);
					path[c.num].add(c.num);
				}
			}
		}
		
		System.out.println(dis[end]);
		System.out.println(path[end].size());
		for(int i : path[end])
			System.out.print(i+" ");
	}

}
