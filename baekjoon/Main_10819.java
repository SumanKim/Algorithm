package baekjoon;

import java.util.Scanner;

public class Main_10819 {

	static int N, res;
	static int A[];
	static int nums[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new int[N];
		nums = new int[N];
		for(int i=0; i<N; i++)
			A[i] = sc.nextInt();
		
		perm(0,0);
		System.out.println(res);
	}

	private static void perm(int cnt, int flag) {
		if(cnt == N) {
			int val = 0;
			for(int i=0; i<N-1; i++) 
				val += Math.abs(nums[i]-nums[i+1]);
			res = Math.max(res, val);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((flag & 1<<i) != 0) continue;
			nums[cnt] = A[i];
			perm(cnt+1, flag | 1<<i);
		}
	}
}
