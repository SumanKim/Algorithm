package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

//이중 우선순위 큐 - treeMap 이용 or 두 개의 Heap & hashMap 사용
public class Main_7662 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			TreeMap<Integer, Integer> map = new TreeMap<>();
			int k = Integer.parseInt(br.readLine());
			for(int i=0; i<k; i++) {
				String input[] = br.readLine().split(" ");
				if(input[0].equals("I")) {
					int val = Integer.parseInt(input[1]);
					map.put(val, map.getOrDefault(val, 0)+1); //map에 없으면 1, 있으면 값+1 넣기
				}
				else {
					if(map.size() == 0) continue;
					
					int key;
					if(input[1].equals("1")) 
						key = map.lastKey();
					else 
						key = map.firstKey();
					
					if(map.put(key, map.get(key)-1) == 1)
						map.remove(key);
				}
			}
			
			if(map.size() == 0)
				sb.append("EMPTY\n");
			else 
				sb.append(map.lastKey()+" "+map.firstKey()+"\n");
		}
		System.out.println(sb.toString());
	}

}
