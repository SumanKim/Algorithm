package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//램프
public class Main_1034 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] arr = new String[N];
		for(int i=0; i<N; i++) {
			arr[i] = br.readLine();
		}
		int K = Integer.parseInt(br.readLine());
		int rem = K % 2;
		K = K > M ? M : K;
		
		int ans = 0;
		for(int i=0; i<N; i++) {
			String str = arr[i];
			int zcnt = 0;
			for(int j=0; j<str.length(); j++) {
				if(str.charAt(j) == '0') zcnt++;
			}
			
			if(zcnt % 2 != rem || zcnt > K) continue;
			
			int count = 0;
			for(int j=0; j<N; j++) {
				if(str.equals(arr[j])) count++;
			}
			
			ans = Math.max(ans, count);
		}
		
		System.out.println(ans);
	}
}
