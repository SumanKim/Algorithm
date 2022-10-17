package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//컨베이어 벨트 위의 로봇
public class Main_20055 {

	static int N, K, num, step;
	static int[] A;
	static boolean[] robot;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[2 * N + 1];
		robot = new boolean[2 * N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 2 * N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			if (A[i] == 0)
				num++;
		}

		while (num < K) {
			// 컨베이어 벨트 회전
			rotateBelt();

			// 로봇 이동
			moveRobot();

			// 로봇 올리기
			if (!robot[1] && A[1] != 0) {
				robot[1] = true;
				if (--A[1] == 0)
					num++;
				q.add(1);
			}

			step++;
		}
		System.out.println(step);
	}

	private static void rotateBelt() {
		int atmp = A[2 * N];
		for (int i = 2 * N; i > 1; i--) {
			A[i] = A[i - 1];
			robot[i] = robot[i - 1];
		}
		A[1] = atmp;
		robot[1] = robot[N] = false;

		Queue<Integer> tmp = new LinkedList<>();
		while (!q.isEmpty()) {
			int k = q.poll() + 1;
			if (k != N)
				tmp.add(k);
		}
		q = tmp;
	}

	private static void moveRobot() {
		Queue<Integer> tmp = new LinkedList<>();
		while (!q.isEmpty()) {
			int k = q.poll();
			int next = k + 1;
			if (robot[next] || A[next] == 0) {
				tmp.add(k);
				continue;
			}

			robot[next] = true;
			robot[k] = false;
			if (--A[next] == 0)
				num++;
			if (next == N) {
				robot[next] = false;
				continue;
			}
			tmp.add(next);
		}
		q = tmp;
	}
}
