package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1253 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		int ans=0;
		for(int i=0; i<N; i++) {
			int low = 0, high = N-1;
			while(true) {
				if(low == i) low++;
				if(high == i) high--;
				
				if(low >= high) break;
				
				if(A[low]+A[high] == A[i]) {
					ans++;
					break;
				}
				else if(A[low]+A[high] > A[i]) high--;
				else low++;
			}
		}
		
		System.out.println(ans);
	}

}
