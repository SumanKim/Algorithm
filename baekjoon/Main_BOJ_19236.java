package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//청소년 상어
public class Main_BOJ_19236 {

	static class Fish {
		int no, dir;
		int x, y;
		
		Fish(int no, int dir, int x, int y){
			this.no = no;
			this.dir = dir;
			this.x = x;
			this.y = y;
		}
	}
	
	static int res;
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
	static int[][] info = new int[17][4]; //모든 물고기들의 좌표, 방향 정보, 먹혔는지 여부
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish[][] grid = new Fish[4][4];
		for(int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken())-1;
				grid[i][j] = new Fish(a,b,i,j);
				info[a][0] = i;
				info[a][1] = j;
				info[a][2] = b;
			}
		}
		
		Fish shark = new Fish(0, grid[0][0].dir, 0, 0);
		//첫번째 칸에 있는 물고기 잡아먹음
		res = grid[0][0].no;
		info[grid[0][0].no][3] = 1;
		grid[0][0] = shark;
				
		//물고기가 이동하는 것부터 진행
		ArrayList<Fish> list = move(grid, shark);
		dfs(list, res, shark, grid, 1);
		System.out.println(res);
	}

	//상어가 먹을 수 있는 물고기의 리스트 리턴
	static ArrayList<Fish> move(Fish[][] map, Fish shark) {
		ArrayList<Fish> list = new ArrayList<>();
		
		//map과 info 동기화
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(map[i][j] == null) continue;
				int no = map[i][j].no, dir = map[i][j].dir;
				info[no][0] = i;
				info[no][1] = j;
				info[no][2] = dir;
			}
		}
		
		//번호가 작은 물고기부터 순서대로 이동
		for(int i=1; i<=16; i++) {
			if(info[i][3] == 1) continue; //해당 번호의 물고기가 이미 잡아 먹힌 경우
			
			int x = info[i][0], y = info[i][1], dir = info[i][2];
			int nx, ny;
			while(true) {
				nx = x + dx[dir];
				ny = y + dy[dir];
				//이동하려는 칸이 경계를 넘거나, 상어가 있는 경우
				if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || (map[nx][ny] != null && map[nx][ny].no == 0)) {
					dir = (dir+1)%8;
					info[i][2] = dir;
				}
				else{
					Fish f = map[nx][ny];
					info[i][0] = nx;
					info[i][1] = ny;
					if(f == null) { //이동하려는 칸이 빈칸일 때
						map[nx][ny] = new Fish(i, dir, nx, ny);
						map[x][y] = null;
					}
					else { //이동하려는 칸에 물고기가 있을 때
						info[f.no][0] = x;
						info[f.no][1] = y;
						map[nx][ny] = new Fish(i, dir, nx, ny);
						map[x][y] = new Fish(f.no, f.dir, x, y);
					}
					break;
				}
			}
		}
		
		//상어가 먹을 수 있는 물고기 리스트 채우기
		int sx = shark.x, sy = shark.y, sdir = shark.dir;
		int nx = sx, ny = sy;
		while(true) {
			nx += dx[sdir];
			ny += dy[sdir];
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) break;
			if(map[nx][ny] == null) continue;
			list.add(new Fish(map[nx][ny].no, map[nx][ny].dir, nx, ny)); // 값 복사하여 넣기
		}
		
		return list;
	}
	
	static void dfs(ArrayList<Fish> candidate, int sum, Fish shark, Fish[][] map, int cnt) {
		if(candidate.isEmpty()) {
			res = Math.max(res, sum);
			return;
		}
		
		for(Fish f : candidate) {
			int x = f.x, y = f.y;
			int val = f.no;
			int sx = shark.x, sy = shark.y, sdir = shark.dir;
			Fish[][] tmp = new Fish[4][4]; //map을 값복사
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(map[i][j] == null) continue;
					Fish fish = new Fish(map[i][j].no, map[i][j].dir, i, j);
					tmp[i][j] = fish;
				}
			}
			
			//원래 상어가 있던 곳을 빈칸으로
			tmp[sx][sy] = null;
			info[val][3] = 1;
			//상어가 해당 물고기를 잡아 먹음
			Fish s = new Fish(0, f.dir, x, y);
			tmp[x][y] = s;
			//물고기가 움직이고, 새로운 물고기의 리스트 주기
			ArrayList<Fish> list = move(tmp, s);
			dfs(list, sum+val, s, tmp, cnt+1);
			info[val][3] = 0;
		}
	}

}
