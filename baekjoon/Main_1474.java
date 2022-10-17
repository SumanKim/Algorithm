package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//밑 줄
public class Main_1474 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String words[] = new String[N];
		int dash[] = new int[N];
		int length = 0;
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
			length += words[i].length();
		}
		
		int val = M - length;
		int cnt = val/(N-1);
		Arrays.fill(dash, cnt);
		
		cnt = val - cnt*(N-1);
		boolean v[] = new boolean[N];
		for(int i=1; i<N; i++) {
			if(cnt == 0) break;
			if(words[i].charAt(0) >= 'a' && words[i].charAt(0) <= 'z') {
				v[i] = true;
				dash[i]++;
				cnt--;
			}
		}
		for(int i=N-1; i>0; i--) {
			if(cnt == 0) break;
			if(v[i]) continue;
			dash[i]++;
			cnt--;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			if(i != 0) {
				for(int k=0; k<dash[i]; k++)
					sb.append("_");
			}
			sb.append(words[i]);
		}
		System.out.println(sb.toString());
	}

}
