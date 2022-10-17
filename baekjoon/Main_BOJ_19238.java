package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//스타트 택시
public class Main_BOJ_19238 {

	static class Customer implements Comparable<Customer>{
		int no, row, col, dis = -1;
		int gx, gy;
		
		Customer(int no, int row, int col, int gx, int gy){
			this.no = no;
			this.row = row;
			this.col = col;
			this.gx = gx;
			this.gy = gy;
		}
		
		public int compareTo(Customer c) {
			if(this.dis == c.dis && this.row == c.row)
				return Integer.compare(c.col, this.col);
			else if(this.dis == c.dis)
				return Integer.compare(c.row, this.row);
			return Integer.compare(c.dis, this.dis);
		}
	}
	
	static int N, M, tx, ty;
	static int fuel; //남은 연료의 양
	static int[][] map;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static ArrayList<Customer> clist = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		String str[] = br.readLine().split(" ");
		tx = Integer.parseInt(str[0]);
		ty = Integer.parseInt(str[1]);
		
		for(int i=2; i<M+2; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[a][b] = i;
			clist.add(new Customer(i,a,b,c,d));
		}
		
		while(!clist.isEmpty()) {
			Customer next = nextCus();
			if(next.dis == -1) {
				System.out.println("-1");
				return;
			}
			
			fuel -= next.dis;
			if(fuel <= 0) {
				System.out.println("-1");
				return;
			}
			int sx = next.row, sy = next.col;
			int gx = next.gx, gy = next.gy;
			int dis = bfs(sx, sy, gx, gy);
			if(dis == -1 || dis > fuel) {
				System.out.println("-1");
				return;
			}
			fuel += dis;
			tx = gx;
			ty = gy;
		}
		System.out.println(fuel);
	}

	//다음 승객 결정하기
	static Customer nextCus() {
		findDis(tx, ty);
		Collections.sort(clist);
		return clist.remove(clist.size()-1);
	}
	
	static void findDis(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		boolean v[][] = new boolean[N+1][N+1];
		q.add(new int[] {sx, sy, 0});
		v[sx][sy] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1], cnt = cur[2];
			
			if(map[x][y] >= 2) {
				for(Customer c : clist) {
					if(c.no == map[x][y])
						c.dis = cnt;
				}
			}
			
			for(int d=0; d<4; d++) {
				int nx = x + dx[d], ny = y + dy[d];
				if(nx <= 0 || nx > N || ny <= 0 || ny > N || v[nx][ny] || map[nx][ny] == 1) continue;
				v[nx][ny] = true;
				q.add(new int[] {nx, ny, cnt+1});
			}
		}
	}
	
	//시작점에서 도착점까지 최단거리 구하기
	static int bfs(int sx, int sy, int gx, int gy) {
		int res = -1;
		Queue<int[]> q = new LinkedList<>();
		boolean v[][] = new boolean[N+1][N+1];
		q.add(new int[] {sx, sy, 0});
		v[sx][sy] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1], cnt = cur[2];
			
			if(x == gx && y == gy) {
				res = cnt;
				break;
			}
			
			for(int d=0; d<4; d++) {
				int nx = x + dx[d], ny = y + dy[d];
				if(nx <= 0 || nx > N || ny <= 0 || ny > N || v[nx][ny] || map[nx][ny] == 1) continue;
				v[nx][ny] = true;
				q.add(new int[] {nx, ny, cnt+1});
			}
		}
		
		return res;
	}
}
