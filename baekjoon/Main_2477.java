package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//참외밭
public class Main_2477 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int hmax = 0, hidx = 0, wmax = 0, widx = 0;
		ArrayList<int[]> list = new ArrayList<>();
		for(int i=0; i<6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new int[] {a,b});
			if(a <= 2) {
				if(hmax < b) {
					hmax = b;
					hidx = i;
				}
			}
			else {
				if(wmax < b) {
					wmax = b;
					widx = i;
				}
			}
		}
		
		int idx = Math.max(hidx, widx);
		if(idx == hidx && widx == 0 && hidx == 5) idx = widx;
		if(idx == widx && hidx == 0 && widx == 5) idx = hidx;
		
		int idx1 = (idx+2)%6, idx2 = (idx+3)%6;
		int v1 = list.get(idx1)[1], v2 = list.get(idx2)[1];
		
		int res = K*(hmax*wmax - v1*v2);
		
		System.out.println(res);
	}

}
