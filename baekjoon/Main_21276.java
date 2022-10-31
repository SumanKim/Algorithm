package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21276 {

	static int N;
	static int[] person;
	static List<Integer> child[]; // 해당 번호의 사람의 후손
	static List<Integer> directChild[]; // 해당 번호의 사람의 자식
	static int ancCnt[]; // 자기 조상이 몇명인지
	static Map<String, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String person[] = new String[N];
		child = new List[N];
		directChild = new List[N];
		ancCnt = new int[N];
		for(int i=0; i<N; i++) {
			child[i] = new ArrayList<>();
			directChild[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			person[i] = st.nextToken();
		
		Arrays.sort(person);
		for(int i=0; i<N; i++) 
			map.put(person[i], i);
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken(), b = st.nextToken();
			int aidx = map.get(a), bidx = map.get(b);
			child[bidx].add(aidx);
			ancCnt[aidx]++;
		}
		
		List<String> ancestor = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		for(int i=0; i<N; i++) {
			if(ancCnt[i] == 0) {
				ancestor.add(person[i]);
				q.offer(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(ancestor.size()+"\n");
		for(String s : ancestor)
			sb.append(s+" ");
		sb.append("\n");
		
		// directChild 구하기
		while(!q.isEmpty()) {
			int pidx = q.poll();
			for(int next : child[pidx]) {
				if(--ancCnt[next] == 0) {
					q.offer(next);
					directChild[pidx].add(next);
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			sb.append(person[i]+" ");
			sb.append(directChild[i].size()+" ");
			Collections.sort(directChild[i]);
			for(int j : directChild[i]) 
				sb.append(person[j]+" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
