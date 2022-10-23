package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2406 {

	static int[][] graph;
	static int[] parent;
	static int X;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<int[]> list = new ArrayList<>();
		parent = new int[n];
		for(int i=0; i<n; i++)
			parent[i] = i;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			union(a,b);
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[2]-b[2]));
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			if(i == 0) continue;
			for(int j=0; j<n; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(j == 0 || i >= j) continue;
				pq.add(new int[] {i,j,val});
			}
		}
		
		while(!pq.isEmpty()){
			int cur[] = pq.poll();
			if(find(cur[0]) != find(cur[1])) {
				union(cur[0], cur[1]);
				list.add(new int[] {cur[0]+1, cur[1]+1});
				X += cur[2];
			}
		}
		System.out.println(X+" "+list.size());
		for(int[] i : list) {
			System.out.println(i[0]+" "+i[1]);
		}
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		// 숫자가 작은 쪽으로 합친다
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}

	static int find(int x) {
		if(parent[x] == x) 
			return x;
		return find(parent[x]);
	}
}
