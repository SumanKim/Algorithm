package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20304 {

	private static int N, M, res;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<Integer>();
		int[] arr = new int[N+1];
		Arrays.fill(arr, -1);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			q.add(num);
			arr[num] = 0;
		}
		
		while(!q.isEmpty()) {
			int x = q.poll();
			// 2^20 > 1000000
			for(int i=0; i<20; i++) {
				int val = x ^ (1<<i);
				if(val > N || arr[val] >= 0) continue;
				arr[val] = arr[x] + 1;
				q.offer(val);
				res = Math.max(res, arr[val]);
			}
		}
		System.out.println(res);
	}

}
