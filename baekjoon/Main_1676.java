package baekjoon;

import java.util.Scanner;

//팩토리얼 0의 개수 - 2와 5가 곱해질 때마다 0의 개수가 늘어남 -> 5가 곱해지는 개수를 구해주면 되는 문제
public class Main_1676 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = 0;
		
		//ex) 25! => 5,10,15,20,25에서 5번, 25에서 한번 더 -> 총 6개
		while(N/5 > 0) {
			cnt += N/5;
			N /= 5;
		}
		
		System.out.println(cnt);
	}

}
