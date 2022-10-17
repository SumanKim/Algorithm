package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//2048(Easy)
public class Main_BOJ_12100 {

	static int N, res;
	static int map[][];
	static int[] nums;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		nums = new int[5];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				res = Math.max(res, map[i][j]);
			}
		}
		
		perm(0);
		System.out.println(res);
	}

	//중복순열
	private static void perm(int cnt) {
		if(cnt == 5) {
			move();
			return;
		}
		
		for(int i=0; i<4; i++) { //0:상 1:하 2:좌 3:우
			nums[cnt] = i;
			perm(cnt+1);
		}
	}
	
	private static void move() {
		int tmp[][] = new int[N][];
		for(int i=0; i<N; i++)
			tmp[i] = Arrays.copyOf(map[i], N);
		
		for(int a=0; a<5; a++) {
			int dir = nums[a];
			if(dir == 0) { //상
				//한쪽으로 밀기
				for(int j=0; j<N; j++) {
					Queue<Integer> q = new LinkedList<>();
					for(int i=0; i<N; i++) {
						if(tmp[i][j] != 0) q.add(tmp[i][j]);
					}
					if(q.size() == N) continue;
					for(int i=0; i<N; i++) {
						if(q.isEmpty()) tmp[i][j] = 0;
						else tmp[i][j] = q.poll();
					}
				}
				
				//합치기
				for(int j=0; j<N; j++) {
					Queue<Integer> q = new LinkedList<>();
					for(int i=0; i<N; i++) {
						if(tmp[i][j] == 0) continue;
						if(i != N-1 && tmp[i][j] == tmp[i+1][j]) {
							q.add(tmp[i][j]*2);
							res = Math.max(res, tmp[i][j]*2);
							i++;
						}
						else q.add(tmp[i][j]);
					}
					for(int i=0; i<N; i++) {
						if(q.isEmpty()) tmp[i][j] = 0;
						else tmp[i][j] = q.poll();
					}
				}
			}
			else if(dir == 1) { //하
				//한쪽으로 밀기
				for(int j=0; j<N; j++) {
					Queue<Integer> q = new LinkedList<>();
					for(int i=N-1; i>=0; i--) {
						if(tmp[i][j] != 0) q.add(tmp[i][j]);
					}
					if(q.size() == N) continue;
					for(int i=N-1; i>=0; i--) {
						if(q.isEmpty()) tmp[i][j] = 0;
						else tmp[i][j] = q.poll();
					}
				}
				
				//합치기
				for(int j=0; j<N; j++) {
					Queue<Integer> q = new LinkedList<>();
					for(int i=N-1; i>=0; i--) {
						if(tmp[i][j] == 0) continue;
						if(i != 0 && tmp[i][j] == tmp[i-1][j]) {
							q.add(tmp[i][j]*2);
							res = Math.max(res, tmp[i][j]*2);
							i--;
						}
						else q.add(tmp[i][j]);
					}
					for(int i=N-1; i>=0; i--) {
						if(q.isEmpty()) tmp[i][j] = 0;
						else tmp[i][j] = q.poll();
					}
				}
			}
			else if(dir == 2) { //좌
				//한쪽으로 밀기
				for(int i=0; i<N; i++) {
					Queue<Integer> q = new LinkedList<>();
					for(int j=0; j<N; j++) {
						if(tmp[i][j] != 0) q.add(tmp[i][j]);
					}
					if(q.size() == N) continue;
					for(int j=0; j<N; j++) {
						if(q.isEmpty()) tmp[i][j] = 0;
						else tmp[i][j] = q.poll();
					}
				}
				
				//합치기
				for(int i=0; i<N; i++) {
					Queue<Integer> q = new LinkedList<>();
					for(int j=0; j<N; j++) {
						if(tmp[i][j] == 0) continue;
						if(j != N-1 && tmp[i][j] == tmp[i][j+1]) {
							q.add(tmp[i][j]*2);
							res = Math.max(res, tmp[i][j]*2);
							j++;
						}
						else q.add(tmp[i][j]);
					}
					for(int j=0; j<N; j++) {
						if(q.isEmpty()) tmp[i][j] = 0;
						else tmp[i][j] = q.poll();
					}
				}
			}
			else if(dir == 3) { //우
				//한쪽으로 밀기
				for(int i=0; i<N; i++) {
					Queue<Integer> q = new LinkedList<>();
					for(int j=N-1; j>=0; j--) {
						if(tmp[i][j] != 0) q.add(tmp[i][j]);
					}
					if(q.size() == N) continue;
					for(int j=N-1; j>=0; j--) {
						if(q.isEmpty()) tmp[i][j] = 0;
						else tmp[i][j] = q.poll();
					}
				}
				
				//합치기
				for(int i=0; i<N; i++) {
					Queue<Integer> q = new LinkedList<>();
					for(int j=N-1; j>=0; j--) {
						if(tmp[i][j] == 0) continue;
						if(j != 0 && tmp[i][j] == tmp[i][j-1]) {
							q.add(tmp[i][j]*2);
							res = Math.max(res, tmp[i][j]*2);
							j--;
						}
						else q.add(tmp[i][j]);
					}
					for(int j=N-1; j>=0; j--) {
						if(q.isEmpty()) tmp[i][j] = 0;
						else tmp[i][j] = q.poll();
					}
				}
			}
		}
	}

}
