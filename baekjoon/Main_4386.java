package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main_4386 {

	static int parent[];
	
	static class Star implements Comparable<Star>{
		int from, to;
		double dis;
		
		Star(int from, int to, double dis){
			this.from = from;
			this.to = to;
			this.dis = dis;
		}
		
		public int compareTo(Star o) {
			return Double.compare(this.dis, o.dis);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<double[]> stars = new ArrayList<>();
		parent = new int[n];
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			stars.add(new double[] {Double.parseDouble(str[0]), Double.parseDouble(str[1])});
			parent[i] = i;
		}
		
		PriorityQueue<Star> edges = new PriorityQueue<>();
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				double val = Math.sqrt(Math.pow(stars.get(i)[0]-stars.get(j)[0], 2) + Math.pow(stars.get(i)[1]-stars.get(j)[1], 2));
				edges.add(new Star(i,j,val));
			}
		}
		
		double ans = 0.0;
		while(!edges.isEmpty()) {
			Star star = edges.poll();
			if(find(star.from) == find(star.to)) continue;
			union(star.from, star.to);
			ans += star.dis;
		}
		System.out.printf("%.2f\n", ans);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y =	find(y);
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}

	static int find(int x) {
		if(x == parent[x])
			return x;
		return find(parent[x]);
	}
}
