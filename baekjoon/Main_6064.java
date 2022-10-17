package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_6064 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			String input[] = br.readLine().split(" ");
			int M = Integer.parseInt(input[0]);
			int N = Integer.parseInt(input[1]);
			int A = Integer.parseInt(input[2]) - 1; //모듈러스 연산을 위해 -> 범위 1~M에서 0~M-1로 떨어뜨리기
			int B = Integer.parseInt(input[3]) - 1;
			
			//M*x+A == N*y+B -> 모듈러스로 계산하기
			boolean flag = true;
			for(int i=A; i<M*N; i+=M) {
				if(i%N == B) {
					System.out.println(i+1);
					flag = false;
					break;
				}
			}
			if(flag)
				System.out.println(-1);
		}
	}
}
