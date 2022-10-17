package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//줄 세우기 - Topological Sort 문제
public class Main_BOJ_2252 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> adj[] = new ArrayList[N+1];
		int degree[] = new int[N+1];
		for(int i=1; i<=N; i++)
			adj[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			degree[b]++;
		}
		
		ArrayList<Integer> res = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++)
			if(degree[i] == 0) q.add(i);
		//N개의 정점에 대해
		for(int i=0; i<N; i++) {
//			//싸이클이 존재하는 경우
//			if(q.isEmpty()) {
//				System.out.println("cycle exist!");
//				break;
//			}
			
			int cur = q.poll();
			res.add(cur);
			for(int t : adj[cur]) {
				if(--degree[t] == 0)
					q.add(t);
			}
		}
		
		for(int i : res)
			System.out.print(i+" ");
		System.out.println();
	}

}
