package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//DSLR -> L, R 부분을 String 연산으로 할 경우에 시간초과!
public class Main_9019 {
	
	static class Register{
		int num;
		String command;
		
		Register(int num, String command){
			this.num = num;
			this.command = command;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			Queue<Register> q = new LinkedList<>();
			boolean v[] = new boolean[10000];
			q.add(new Register(A, ""));
			v[A] = true;
			while(!q.isEmpty()) {
				Register r = q.poll();
				int num = r.num;
				String command = r.command;
				
				if(num == B) {
					sb.append(command+"\n");
					break;
				}
				
				//D
				int n = 2*num % 10000;
				if(!v[n]) {
					v[n] = true;
					q.add(new Register(n, command+"D"));
				}
				
				//S
				n = num;
				if(--n < 0) n = 9999;
				if(!v[n]) {
					v[n] = true;
					q.add(new Register(n, command+"S"));
				}
				
				//L
				n = num%1000*10 + num/1000;
				if(!v[n]) {
					v[n] = true;
					q.add(new Register(n, command+"L"));
				}
				
				//R
				n = num%10*1000 + num/10;
				if(!v[n]) {
					v[n] = true;
					q.add(new Register(n, command+"R"));
				}
			}
		}
		
		System.out.println(sb.toString());
	}

}
