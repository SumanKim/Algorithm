package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//트리의 지름
public class Main_BOJ_1167_ans {

	static int V, root, MAX, res;
	static ArrayList<int[]> adj[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		adj = new ArrayList[V+1];
		for(int i=0; i<V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			adj[a] = new ArrayList<>();
			while(true) {
				String str = st.nextToken();
				if(str.equals("-1")) break;
				int b = Integer.parseInt(str);
				int c = Integer.parseInt(st.nextToken());
				adj[a].add(new int[] {b,c});
			}
		}
		
		dfs(0,1,0); //임의의 노드에서 가장 먼 노드를 구함
		res = dfs(0,root,0); //그 노드에서 가장 먼 노드까지의 거리가 정답
		System.out.println(res);
	}
	
	static int dfs(int pNum, int thisNum, int sum) {
		if(MAX < sum) {
			MAX = sum;
			root = thisNum;
		}
		
		int max = 0;
		for(int[] cur : adj[thisNum]) {
			int to = cur[0], dis = cur[1];
			if(to == pNum) continue;
			int val = dfs(thisNum, to, sum+dis);
			max = Math.max(max, dis+val);
		}
		return max;
	}

}
