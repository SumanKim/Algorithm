package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20922 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] a = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			a[i] = Integer.parseInt(st.nextToken());
		
		int cnt[] = new int[100001];
		int ans = 0;
		int low=0, high=0;
		while(low <= high && high < N) {
			if(cnt[a[high]] >= K) {
				cnt[a[low++]]--;
			}
			else {
				cnt[a[high++]]++;
				ans = Math.max(ans, high-low);
			}
		}
		System.out.println(ans);
	}

}
