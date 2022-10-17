package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

//최소 힙
public class Main_1927 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int val = Integer.parseInt(br.readLine());
			if(val == 0) {
				if(pq.isEmpty())
					sb.append("0");
				else
					sb.append(pq.poll());
				sb.append("\n");
			}
			else {
				pq.add(val);
			}
		}
		System.out.println(sb.toString());
	}

}
