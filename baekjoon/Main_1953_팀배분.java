package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//subset 문제
public class Main_1953_팀배분 {
	
	static int n;
	static ArrayList<Integer> hate[];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		hate= new ArrayList[n];
		for(int i=0; i<n; i++) {
			hate[i] = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			for(int j=0; j<a; j++) {
				int b = Integer.parseInt(st.nextToken());
				hate[i].add(b);
			}
		}
		
		boolean h1[] = new boolean[n+1];
		boolean h2[] = new boolean[n+1];
		for(int i : hate[0])
			h1[i] = true;
		int v[] = new int[n+1];
		v[1] = 1;
		combi(2, v, h1, h2);
	}

	private static boolean combi(int cnt, int v[], boolean[] h1, boolean[] h2) {
		if(cnt == n+1) {
			StringBuilder sb = new StringBuilder();
			int count = 0;
			for(int i=1; i<=n; i++) {
				if(v[i] == 1) {
					count++;
					sb.append(i).append(" ");
				}
			}
			System.out.println(count);
			System.out.println(sb.toString());
			
			sb = new StringBuilder();
			for(int i=1; i<=n; i++) {
				if(v[i] == 2)
					sb.append(i).append(" ");
			}
			System.out.println(n-count);
			System.out.println(sb.toString());
			return true;
		}
		
		if (!h1[cnt]) {
			v[cnt] = 1;
			boolean[] tmp = Arrays.copyOf(h1, n+1);
			for(int i : hate[cnt-1])
				tmp[i] = true;
			if (combi(cnt + 1, v, tmp, h2))
				return true;
		}
		if (!h2[cnt]) {
			v[cnt] = 2;
			boolean[] tmp = Arrays.copyOf(h2, n+1);
			for(int i : hate[cnt-1])
				tmp[i] = true;
			if (combi(cnt + 1, v, h1, tmp))
				return true;
		}
		
		return false;
	}
}
