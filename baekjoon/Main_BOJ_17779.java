package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//게리맨더링2 - 2차원 배열 구현 문제
public class Main_BOJ_17779 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] A = new int[N+1][N+1];
		int total = 0;
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				total += A[i][j];
			}
		}
		
		int res = Integer.MAX_VALUE;
		for(int x=1; x<=N; x++) {
			for(int y=1; y<=N; y++) {
				for(int d1=1; y-d1>=1; d1++) {
					for(int d2=1; x+d1+d2<=N && y+d2<=N; d2++) {
						int sum1=0, sum2=0, sum3=0, sum4=0, sum5=0;
						int min = Integer.MAX_VALUE, max = 0;
						for(int r=1; r<=N; r++) {
							for(int c=1; c<=N; c++) {
								if(r>=1 && r<x+d1 && c>=1 && c<=y) { //1번 선거구
									if(r+c < x+y) sum1 += A[r][c];
								}
								else if(r>=1 && r<=x+d2 && c>y && c<=N) { //2번 선거구
									if(r<x) sum2 += A[r][c];
									else {
										int p = r-x;
										if(r+c > x+y+2*p)
											sum2 += A[r][c];
									}
								}
								else if(r>=x+d1 && r<=N && c>=1 && c<y-d1+d2) { //3번 선거구
									if(r>x+d1+d2) sum3 += A[r][c];
									else {
										int p = r-x-d1;
										if(r+c < x+y+2*p)
											sum3 += A[r][c];
									}
								}
								else if(r>x+d2 && r<=N && c>=y-d1+d2 && c<=N) { //4번 선거구
									if(r+c > x+y+2*d2) sum4 += A[r][c];
								}
							}
						}
						sum5 = total - sum1 - sum2 - sum3 - sum4;
						min = Math.min(sum1, Math.min(sum2, Math.min(sum3, Math.min(sum4, sum5))));
						max = Math.max(sum1, Math.max(sum2, Math.max(sum3, Math.max(sum4, sum5))));
						res = Math.min(res, max-min);
					}
				}
			}
		}
		System.out.println(res);
	}

}
