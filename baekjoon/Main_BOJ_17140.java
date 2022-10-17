package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 이차원 배열과 연산
public class Main_BOJ_17140 {

	private static class Num implements Comparable<Num>{
		int val, cnt;
		
		Num(int val, int cnt){
			this.val = val;
			this.cnt = cnt;
		}
		
		public int compareTo(Num n) {
			if(this.cnt == n.cnt)
				return Integer.compare(this.val, n.val);
			else
				return Integer.compare(this.cnt, n.cnt);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> A = new ArrayList<>();
		for(int i=0; i<=3; i++) 
			A.add(new ArrayList<>());
		for(int i=1; i<=3; i++) {
			st = new StringTokenizer(br.readLine());
			A.get(i).add(0);
			A.get(i).add(Integer.parseInt(st.nextToken()));
			A.get(i).add(Integer.parseInt(st.nextToken()));
			A.get(i).add(Integer.parseInt(st.nextToken()));
		}
		
		int rowSize = 3, colSize = 3;
		int res = 0;
		while(res <= 100) {
			if(A.size() > r && A.get(r).size() > c && A.get(r).get(c) == k) break;
			if(rowSize >= colSize) { //R 연산
				int max = 0;
				for(int i=1; i<=rowSize; i++) {
					ArrayList<Num> list = new ArrayList<>();
					int v[] = new int[101];
					for(int j=1; j<=colSize; j++) {
						if(A.get(i).size() <= j) continue;
						if(A.get(i).get(j) == 0) continue;
						v[A.get(i).get(j)]++;
					}
					for(int a=1; a<=100; a++) {
						if(v[a] > 0)
							list.add(new Num(a,v[a]));
					}
					Collections.sort(list);
					ArrayList<Integer> nlist = new ArrayList<>();
					nlist.add(0);
					for(Num num : list) {
						nlist.add(num.val);
						nlist.add(num.cnt);
					}
					A.remove(i);
					A.add(i,nlist);
					max = Math.max(max, nlist.size()-1);
				}
				colSize = max;
			}
			else { //C 연산
				int max = 0;
				for(int j=1; j<=colSize; j++) {
					ArrayList<Num> list = new ArrayList<>();
					int v[] = new int[101];
					for(int i=1; i<=rowSize; i++) {
						if(A.get(i).size() <= j) continue;
						if(A.get(i).get(j) == 0) continue;
						v[A.get(i).get(j)]++;
					}
					for(int a=1; a<=100; a++) {
						if(v[a] > 0)
							list.add(new Num(a,v[a]));
					}
					Collections.sort(list);
					ArrayList<Integer> nlist = new ArrayList<>();
					for(Num num : list) {
						nlist.add(num.val);
						nlist.add(num.cnt);
					}
					for(int i=0; i<nlist.size(); i++) {
						if(A.size() <= i+1) { //행이 모자란 경우
							A.add(new ArrayList<>());
						}
						if(A.get(i+1).size() <= j) { //열이 모자란 경우
							while(A.get(i+1).size() <= j)
								A.get(i+1).add(0);
						}
						A.get(i+1).set(j,nlist.get(i));
					}
					for(int i=nlist.size(); i<rowSize; i++) {
						if(A.get(i+1).size() > j)
							A.get(i+1).set(j,0);
					}
					max = Math.max(max, nlist.size());
				}
				rowSize = max;
			}
			res++;
		}
		
		if(res > 100)
			System.out.println("-1");
		else 
			System.out.println(res);
	}

}
