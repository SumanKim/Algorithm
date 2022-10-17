package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//주사위 윷놀이
public class Main_BOJ_17825 {

	private static final int END = 32;
	
	private static class Node {
		int no, val;
		Node next = null, blue_next = null;
		
		Node(int no, int val){
			this.no = no;
			this.val = val;
		}
	}
	
	private static int res;
	private static int[] loc = new int[4]; //말의 위치
	private static int[] cmd = new int[10];
	private static Node[] board = new Node[33];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<10; i++)
			cmd[i] = Integer.parseInt(st.nextToken());
		
		//링크드 리스트 구축하기
		Node node = new Node(0,0);
		board[0] = node;
		ArrayList<Node> blueNode = new ArrayList<>();
		for(int i=1; i<=20; i++) {
			if(node.no == 5 || node.no == 10 || node.no == 15)
				blueNode.add(node);
			node.next = new Node(i, node.val+2);
			node = node.next;
			board[i] = node;
		}
		
		int idx = 21;
		Node center = new Node(29,25);
		board[29] = center;
		//10
		node = blueNode.get(0);
		node.blue_next = new Node(idx, 13);
		node = node.blue_next;
		board[idx++] = node;
		for(int i=idx; i<idx+2; i++) {
			node.next = new Node(i, node.val+3);
			node = node.next;
			board[i] = node;
		}
		idx += 2;
		node.next = center;
		
		//20
		node = blueNode.get(1);
		node.blue_next = new Node(idx, 22);
		node = node.blue_next;
		board[idx++] = node;
		node.next = new Node(idx, node.val+2);
		node = node.next;
		board[idx++] = node;
		node.next = center;
		
		//30
		node = blueNode.get(2);
		node.blue_next = new Node(idx, 28);
		node = node.blue_next;
		board[idx++] = node;
		for(int i=idx; i<idx+2; i++) {
			node.next = new Node(i, node.val-1);
			node = node.next;
			board[i] = node;
		}
		node.next = center;
		
		//25
		idx = 30;
		node = center;
		for(int i=idx; i<idx+2; i++) {
			node.next = new Node(i, node.val+5);
			node = node.next;
			board[i] = node;
		}
		node.next = board[20];
		board[20].next = new Node(END, 0);
		//링크드 리스트 구축 끝
		
		//경우의 수 탐색하여 점수의 최댓값 구하기
		perm(0, 0);
		System.out.println(res);
	}

	//중복 순열
	private static void perm(int cnt, int tot) {
		if(cnt == 10) {
			res = Math.max(res, tot);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int cur = loc[i];
			if(cur == END) continue;
			int next;
			Node node = board[cur];
			
			//시작 지점이 파란 칸인 경우
			if(cur == 5 || cur == 10 || cur == 15) {
				node = node.blue_next;
				for(int k=0; k<cmd[cnt]-1; k++) {
					if(node.next != null)
						node = node.next;
				}
			}
			else {
				for(int k=0; k<cmd[cnt]; k++) { //해당 칸수만큼 이동
					if(node.next != null)
						node = node.next;
				}
			}
			next = node.no;
			
			//가려는 곳에 말이 있으면 못감
			boolean flag = true;
			if(next != END) {
				for(int k=0; k<4; k++) {
					if(i==k || loc[k] == 0) continue;
					if(next == loc[k]) {
						flag = false;
						break;
					}
				}
			}
			if(!flag) continue;
			
			loc[i] = next;
			perm(cnt+1, tot+node.val);
			loc[i] = cur;
		}
	}
}
