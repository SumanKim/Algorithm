package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_6443 {

	static int N;
	static char[] word, result, mx;
	static boolean[] v;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			word = br.readLine().toCharArray();
			int len = word.length;
			result = new char[len];
			mx = new char[len];
			v = new boolean[len];
			Arrays.sort(word);
			dfs(len, 0);
		}
		System.out.println(sb.toString());
	}
	
	static void dfs(int len, int depth) throws IOException{
		if(depth == len) {
			sb.append(String.valueOf(result)+"\n");
			return;
		}
		
		mx[depth] = 0;
		for(int i=0; i<len; i++) {
			if(v[i]) continue;
			if(mx[depth] >= word[i]) continue; // 중복 순열 제거
			
			mx[depth] = word[i];
			v[i] = true;
			result[depth] = word[i];
			dfs(len, depth+1);
			v[i] = false;
		}
	}
}
