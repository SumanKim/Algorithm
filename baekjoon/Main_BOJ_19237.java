package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//어른 상어
public class Main_BOJ_19237 {

	static class Shark {
		int no, smell, dir;
		boolean isShark; //상어인지 냄새인지 여부
		
		Shark(int no, int smell, boolean isShark){
			this.no = no;
			this.smell = smell;
			this.isShark = isShark;
		}
		
		Shark(int no, int smell, int dir, boolean isShark){
			this.no = no;
			this.smell = smell;
			this.dir = dir;
			this.isShark = isShark;
		}
	}
	
	static int N, M, k, res, cnt;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static Shark map[][];
	static int prio[][][];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		cnt = M; //남은 상어 수
		map = new Shark[N][N];
		prio = new int[M+1][4][4];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(val != 0) 
					map[i][j] = new Shark(val, k, true);
			}
		}
		String[] input = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == null) continue;
				map[i][j].dir = Integer.parseInt(input[map[i][j].no-1])-1;
			}
		}
		for(int i=1; i<=M; i++) {
			for(int j=0; j<4; j++) {
				st = new StringTokenizer(br.readLine());
				prio[i][j][0] = Integer.parseInt(st.nextToken())-1;
				prio[i][j][1] = Integer.parseInt(st.nextToken())-1;
				prio[i][j][2] = Integer.parseInt(st.nextToken())-1;
				prio[i][j][3] = Integer.parseInt(st.nextToken())-1;
			}
		}
		
		while(cnt > 1) {
			move();
			if(++res > 1000) {
				System.out.println("-1");
				return;
			}
		}
		System.out.println(res);
	}

	static void move() {
		ArrayList<Shark>[][] tmp = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = new ArrayList<>();
				if(map[i][j] == null) continue;
				tmp[i][j].add(new Shark(map[i][j].no, map[i][j].smell, map[i][j].dir, map[i][j].isShark));
			}
		}
		
		//냄새를 남기며 상어가 이동
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == null || !map[i][j].isShark) continue;
				int no = map[i][j].no, dir = map[i][j].dir, smell = map[i][j].smell;
				boolean done = false;
				//첫번째로, 냄새가 없는 칸을 탐색
				for(int d=0; d<4; d++) {
					int nx = i + dx[prio[no][dir][d]];
					int ny = j + dy[prio[no][dir][d]];
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					if(map[nx][ny] == null) {
						tmp[nx][ny].add(new Shark(no, smell, prio[no][dir][d], true));
						tmp[i][j].set(0, new Shark(no, smell, false));
						done = true;
						break;
					}
				}
				if(!done) {
					//두번째로, 자신의 냄새가 있는 칸을 선택
					for(int d=0; d<4; d++) {
						int nx = i + dx[prio[no][dir][d]];
						int ny = j + dy[prio[no][dir][d]];
						if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						if(map[nx][ny].no == map[i][j].no) {
							tmp[nx][ny].set(0, new Shark(no, smell, prio[no][dir][d], true));
							tmp[i][j].set(0, new Shark(no, smell, false));
							break;
						}
					}
				}
			}
		}
		
		//겹치는 상어 제거
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(tmp[i][j].isEmpty()) {
					map[i][j] = null;
				}
				else if(tmp[i][j].size() == 1) {
					Shark s = tmp[i][j].get(0);
					map[i][j] = new Shark(s.no, s.smell, s.dir, s.isShark);
				}
				else { //한 칸에 여러마리인 경우 - 가장 작은 번호의 상어 제외하고 모두 내쫒김
					cnt -= tmp[i][j].size()-1;
					Shark boss = null;
					int min = Integer.MAX_VALUE;
					for(Shark s : tmp[i][j]) {
						if(min > s.no) {
							min = s.no;
							boss = s;
						}
					}
					map[i][j] = boss;
				}
			}
		}
		
		//냄새가 1씩 빠짐
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == null || map[i][j].isShark) continue;
				map[i][j].smell--;
				if(map[i][j].smell == 0)
					map[i][j] = null;
			}
		}
		
	}
}
