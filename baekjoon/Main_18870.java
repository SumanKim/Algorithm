package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

//좌표 압축
public class Main_18870 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int input[] = new int[N];
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(st.nextToken());
			input[i] = x;
			set.add(x);
		}
		
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i : set) {
			arr.add(i);
		}
		Collections.sort(arr);
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<arr.size(); i++) {
			map.put(arr.get(i), i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(map.get(input[i])+" ");
		}
		sb.append("\n");
		System.out.println(sb.toString());
	}

}
