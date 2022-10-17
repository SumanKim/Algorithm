package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//싸이버개강총회
public class Main_19583 {
	
	static String S, E, Q;
	static int start, end, quit;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = st.nextToken();
		E = st.nextToken();
		Q = st.nextToken();
		
		String time[] = S.split(":");
		start = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
		time = E.split(":");
		end = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
		time = Q.split(":");
		quit = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
		
		HashMap<String, Boolean> smap = new HashMap<>();
		HashMap<String, Boolean> emap = new HashMap<>();
		String line = "";
		while((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			time = st.nextToken().split(":");
			String name = st.nextToken();
//			System.out.println("time: "+time[0]+":"+time[1]+", name: "+name);
			int now = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
			if(now > 0 && now <= start) {
				smap.put(name, true);
			}
			else if(now >= end && now <= quit) {
				emap.put(name, true);
			}
		}
		
		int cnt = 0;
		for(String s : smap.keySet()) {
			if(emap.get(s) != null) cnt++;
		}
		System.out.println(cnt);
	}

}
