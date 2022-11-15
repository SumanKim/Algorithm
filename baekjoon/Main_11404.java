package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_11404 {

	public static void main(String[] args) throws IOException{
		final int MAX = 999999999;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int adj[][] = new int[n+1][n+1];
		for(int i=1; i<=n; i++)
			Arrays.fill(adj[i], MAX);
		for(int i=0; i<m; i++) {
			String input[] = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			adj[a][b] = Math.min(adj[a][b], c);
		}
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				if(i == k) continue;
				for(int j=1; j<=n; j++) {
					if(i == j) continue;
					adj[i][j] = Math.min(adj[i][j], adj[i][k]+adj[k][j]);
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(adj[i][j] == MAX)
					System.out.print("0 ");
				else
					System.out.print(adj[i][j]+" ");
			}
			System.out.println();
		}
	}

}
