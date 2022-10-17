package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

//듣보잡
public class Main_1764 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			map.put(br.readLine(), 1);
		}
		for(int i=0; i<M; i++) {
			String str = br.readLine();
			if(map.get(str) == null)
				map.put(str, 1);
			else
				map.put(str, 2);
		}
		
		ArrayList<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(String s : map.keySet()) {
			if(map.get(s) == 2) {
				cnt++;
				list.add(s);
			}
		}
		Collections.sort(list);
		System.out.println(cnt);
		for(String s : list) {
			System.out.println(s);
		}
	}

}
