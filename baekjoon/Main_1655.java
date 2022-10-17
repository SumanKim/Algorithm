package baekjoon;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1655 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder(); //출력 시간 감축을 위해 사용
		int N = sc.nextInt();
		PriorityQueue<Integer> max_heap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> min_heap = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			int input = sc.nextInt();
			//1)max_heap, min_heap순으로 번갈아 가며 넣는다.
			if(max_heap.size() == min_heap.size())
				max_heap.offer(input);
			else min_heap.offer(input);
			
			//2)max_heap의 head가 min_heap의 head보다 크다면 각자 poll한 것을 바꾸어 offer해준다.(swap)
			if(!max_heap.isEmpty() && !min_heap.isEmpty()) {
				if(max_heap.peek() > min_heap.peek()) {
					int tmp = max_heap.poll();
					max_heap.offer(min_heap.poll());
					min_heap.offer(tmp);
				}
			}
			//3)max_heap의 head를 출력한다.
			sb.append(max_heap.peek()+"\n");
		}
		System.out.println(sb.toString());
	}

}
