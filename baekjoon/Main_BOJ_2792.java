package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//보석 상자 - 이분 탐색 문제
public class Main_BOJ_2792 {

	static int N, M;
	static int gem[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		gem = new int[M];
		int max = 0;
		for(int i=0; i<M; i++) {
			gem[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, gem[i]);
		}
		
		//lower_bound 구하기 - 최소의 질투심을 구해야 하므로
		int start = 0, end = max;
		while(start < end) { 
			int mid = (start+end)/2;
			if(mid == 0) {
				start = 1;
				break;
			}
			int res = 0;
			for(int i=0; i<M; i++) {
				int val = gem[i]%mid == 0 ? gem[i]/mid : gem[i]/mid + 1;
				res += val;
			}
			if(res > N)
				start = mid + 1;
			else
				end = mid;
		}
		
		System.out.println(start);
	}
}
