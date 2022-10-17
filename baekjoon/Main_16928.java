package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16928 {

	static int N, M;
	static int[] warp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		warp = new int[101];
	
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			warp[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			warp[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		Queue<int[]> q = new LinkedList<int[]>();
		boolean v[] = new boolean[101];
		q.offer(new int[] {1,0}); //칸 번호, 이동 횟수
		v[1] = true;
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int x = cur[0], cnt = cur[1];
			if(warp[x] != 0) {
				x = warp[x];
			}
			
			if(x == 100) {
				System.out.println(cnt);
				return;
			}
			
			//주사위 굴리기
			for(int i=1; i<=6; i++) {
				int nx = x + i;
				if(nx > 100 || v[nx]) continue;
				q.offer(new int[] {nx, cnt+1});
				v[nx] = true;
			}
		}
	}

}
