package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17244 {

	static int N, M, res = Integer.MAX_VALUE;
	static char map[][];
	static int sx, sy, ex, ey;
	static ArrayList<int[]> stuff = new ArrayList<>();
	static int nums[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[M][N];
		for(int i=0; i<M; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'S') {
					sx = i;
					sy = j;
				}
				else if(map[i][j] == 'E') {
					ex = i;
					ey = j;
				}
				else if(map[i][j] == 'X') {
					stuff.add(new int[] {i,j});
				}
			}
		}
		
		nums = new int[stuff.size()][2];
		perm(0, 0);
		System.out.println(res);
	}

	private static void perm(int cnt, int flag) {
		if(cnt == stuff.size()) {
			int x = sx, y = sy;
			int sum = 0;
			for(int i=0; i<stuff.size(); i++) {
				int val = bfs(x, y, nums[i][0], nums[i][1]);
				sum += val;
				x = nums[i][0];
				y = nums[i][1];
			}
			sum += bfs(x, y, ex, ey);
			res = Math.min(res, sum);
			return;
		}
		
		for(int i=0; i<stuff.size(); i++) {
			if((flag & 1<<i) != 0) continue;
			nums[cnt][0] = stuff.get(i)[0];
			nums[cnt][1] = stuff.get(i)[1];
			perm(cnt+1, flag | 1<<i);
		}
	}
	
	private static int bfs(int x, int y, int tx, int ty) {
		Queue<int[]> q = new LinkedList<>();
		boolean v[][] = new boolean[M][N];
		q.offer(new int[] {x,y,0});
		v[x][y] = true;
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int r = cur[0], c = cur[1], cnt = cur[2];
			
			if(r == tx && c == ty)
				return cnt;
			
			for(int i=0; i<4; i++) {
				int nx = r + dx[i];
				int ny = c + dy[i];
				if(nx < 0 || nx >= M || ny < 0 || ny >= N || v[nx][ny] || map[nx][ny] == '#') continue;
				q.offer(new int[] {nx,ny,cnt+1});
				v[nx][ny] = true;
			}
		}
		
		return -1;
	}
}
