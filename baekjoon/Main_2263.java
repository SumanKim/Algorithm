package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2263 {

	static int cnt;
	static int[] pre, in, post;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		pre = new int[n];
		in = new int[n];
		post = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			in[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			post[i] = Integer.parseInt(st.nextToken());
		
		findPre(0,n-1,0,n-1);
		
		for(int i=0; i<n; i++)
			System.out.print(pre[i]+" ");
		System.out.println();
	}

	private static void findPre(int is, int ie, int ps, int pe) {
		if(is > ie || ps > pe) {
			return;
		}
		
		int root = post[pe];
		int mid = (is+ie)/2;
		for(int i=is; i<=ie; i++) {
			if(in[i] == root) { 
				mid = i;
				break;
			}
		}
		int num1 = mid-is;
		int num2 = ie-mid;
		pre[cnt++] = root;
		
		findPre(is, mid-1, ps, ps+num1-1);
		findPre(mid+1, ie, pe-num2, pe-1);
	}
}
