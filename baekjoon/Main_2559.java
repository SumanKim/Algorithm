package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//수열
public class Main_2559 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int sum[] = new int[N];
		sum[0] = arr[0];
		for(int i=1; i<N; i++) {
			sum[i] = sum[i-1] + arr[i];
		}
		
		int res = sum[K-1];
		for(int i=K; i<N; i++) {
			res = Math.max(res, sum[i]-sum[i-K]);
		}
		System.out.println(res);
	}

}
