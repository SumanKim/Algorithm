package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_17837 {

	static int N, K, max = 1, turn;
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	static int grid[][];
	static ArrayList<Horse> map[][];
	static ArrayList<Horse> list = new ArrayList<>();
	
	private static class Horse {
		int x, y;
		int no, dir;
		
		Horse(int x, int y, int no, int dir){
			this.x = x;
			this.y = y;
			this.no = no;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		map = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = new ArrayList<>();
			}
		}
		int no = 0;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			Horse h = new Horse(r, c, ++no, d);
			map[r][c].add(h);
			list.add(h);
			max = Math.max(max, map[r][c].size());
		}
		
		while(++turn <= 1000) {
			for(Horse horse : list) {
				int x = horse.x, y = horse.y;
				int nx = x + dx[horse.dir];
				int ny = y + dy[horse.dir];
				int size = map[x][y].size();
				int idx = -1;
				for(int i=0; i<size; i++) {
					if(map[x][y].get(i).no == horse.no) idx = i;
				}
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || grid[nx][ny] == 2) { //파란색인 경우
					int d = horse.dir;
					if(d % 2 == 0) d++;
					else d--;
					horse.dir = d;
					
					int nnx = x + dx[d];
					int nny = y + dy[d];
					if(nnx < 0 || nnx >= N || nny < 0 || nny >= N || grid[nnx][nny] == 2) continue; //반대 방향도 파란색이거나 벗어날 때
					else if(grid[nnx][nny] == 0) { //반대 방향이 흰색인 경우
						for(int i=idx; i<size; i++) {
							Horse h = map[x][y].get(i);
							h.x = nnx;
							h.y = nny;
							map[nnx][nny].add(h);
						}
						max = Math.max(max, map[nnx][nny].size());
						
						ArrayList<Horse> nlist = new ArrayList<>();
						for(int i=0; i<idx; i++) 
							nlist.add(map[x][y].get(i));
						map[x][y] = nlist;
					}
					else if(grid[nnx][nny] == 1) { //반대 방향이 빨간색인 경우
						for(int i=size-1; i>=idx; i--) {
							Horse h = map[x][y].get(i);
							h.x = nnx;
							h.y = nny;
							map[nnx][nny].add(h);
						}
						max = Math.max(max, map[nnx][nny].size());
						
						ArrayList<Horse> nlist = new ArrayList<>();
						for(int i=0; i<idx; i++) 
							nlist.add(map[x][y].get(i));
						map[x][y] = nlist;
					}
				}
				else if(grid[nx][ny] == 0) { //흰색인 경우	
					for(int i=idx; i<size; i++) {
						Horse h = map[x][y].get(i);
						h.x = nx;
						h.y = ny;
						map[nx][ny].add(h);
					}
					max = Math.max(max, map[nx][ny].size());
					
					ArrayList<Horse> nlist = new ArrayList<>();
					for(int i=0; i<idx; i++) 
						nlist.add(map[x][y].get(i));
					map[x][y] = nlist;
				}
				else if(grid[nx][ny] == 1) { //빨간색인 경우
					for(int i=size-1; i>=idx; i--) {
						Horse h = map[x][y].get(i);
						h.x = nx;
						h.y = ny;
						map[nx][ny].add(h);
					}
					max = Math.max(max, map[nx][ny].size());
					
					ArrayList<Horse> nlist = new ArrayList<>();
					for(int i=0; i<idx; i++) 
						nlist.add(map[x][y].get(i));
					map[x][y] = nlist;
				}
				
				//턴이 진행되는 중에 말이 4개 이상 쌓이면 게임 종료
				if(max >= 4) {
					System.out.println(turn);
					return;
				}
			}

		}
		System.out.println("-1");
	}

}
