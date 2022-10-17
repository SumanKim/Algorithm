package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//이상한 술집
public class Main_13702 {

	static int N, K;
	static int pot[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		pot = new int[N];
		int max = 0;
		for(int i=0; i<N; i++) {
			pot[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, pot[i]);
		}
		
		long start = 1, end = max;
		while(start <= end) {
			long mid = (start+end) / 2;
			int cnt = 0;
			for(int i=0; i<pot.length; i++) {
				cnt += pot[i]/mid;
			}
			
			if(cnt < K) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		
		System.out.println(end);
	}

}
