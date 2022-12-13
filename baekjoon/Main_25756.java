package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Main_25756 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		double val = 0;
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.0#####");
		for(int i=0; i<N; i++) {
			double potion = Integer.parseInt(st.nextToken())/100.0;
//			val = val + potion - val*potion;
			val = 1 - (1-val)*(1-potion);
//			String ans = String.format("%.6f", val*100);
//			val = Double.valueOf(ans)/100;
			sb.append(df.format(val*100)+"\n");
		}
		System.out.println(sb.toString());
	}

}
