package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//나는야 포켓몬 마스터 이다솜
public class Main_1620 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String poketmon[] = new String[N+1];
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=1; i<=N; i++) {
			poketmon[i] = br.readLine();
			map.put(poketmon[i], i);
		}
		for(int i=0; i<M; i++) {
			String str = br.readLine();
			char c = str.charAt(0);
			if(c >= '0' && c <= '9') {
				int idx = Integer.parseInt(str);
				sb.append(poketmon[idx]).append("\n");
			}
			else {
				int idx = map.get(str);
				sb.append(idx).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
