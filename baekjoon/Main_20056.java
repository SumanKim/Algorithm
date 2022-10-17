package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//마법사 상어와 파이어볼
public class Main_20056 {

	static class FireBall {
		int r, c, m, d, s;

		FireBall(int r, int c, int m, int d, int s) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}

	static int N, M, K, res;
	static int dr[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dc[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static ArrayList<FireBall> fbList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fbList.add(new FireBall(r, c, m, d, s));
			res += m;
		}

		while (K-- > 0) {
			sequence();
		}
		System.out.println(res);
	}

	private static void sequence() {
		// 모든 파이어볼 이동
		ArrayList<FireBall> tmp[][] = new ArrayList[N + 1][N + 1];
		for (FireBall fb : fbList) {
			int r = fb.r, c = fb.c, d = fb.d, m = fb.m, s = fb.s;
			int nr = r + (dr[d] * s) % N;
			int nc = c + (dc[d] * s) % N;
			if (nr <= 0) nr = N + nr;
			if (nc <= 0) nc = N + nc;
			if(nr > N) nr = nr - N;
			if(nc > N) nc = nc - N;

			if (tmp[nr][nc] == null) tmp[nr][nc] = new ArrayList<>();
			tmp[nr][nc].add(new FireBall(nr, nc, m, d, s));
		}

		fbList = new ArrayList<>();
		res = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(tmp[i][j] == null) continue;
				if (tmp[i][j].size() >= 2) {
					int dir = 1, m = 0, s = 0;
					int oddcnt = 0, evencnt = 0;
					for (int k = 0; k < tmp[i][j].size(); k++) {
						m += tmp[i][j].get(k).m;
						s += tmp[i][j].get(k).s;
						if (tmp[i][j].get(k).d % 2 == 0)
							evencnt++;
						else oddcnt++;
					}
					m /= 5;
					s /= tmp[i][j].size();
					if (oddcnt == 0 || evencnt == 0)
						dir = 0;

					if (m == 0) continue;
					tmp[i][j] = new ArrayList<>();
					for (int d = dir; d < 8; d += 2) {
						tmp[i][j].add(new FireBall(i,j,m,d,s));
						fbList.add(new FireBall(i,j,m,d,s));
						res += m;
					}

				}
				else if(tmp[i][j].size() == 1){
					int dir = tmp[i][j].get(0).d, m = tmp[i][j].get(0).m, s = tmp[i][j].get(0).s;
					fbList.add(new FireBall(i,j,m,dir,s));
					res += m;
				}
			}
		}

	}

}
