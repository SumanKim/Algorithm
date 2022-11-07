package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_17271 {
	
	static int N, M;
	static long mod = 1000000007;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int i=0;
		BigInteger ans = BigInteger.ZERO;
		while(true) {
			if(N < i*(M-1)+i) break;
			BigInteger res = combi(N-i*(M-1), i++);
			ans = ans.add(res);
			ans = ans.mod(BigInteger.valueOf(mod));
		}
		System.out.println(ans.toString());
	}

	static BigInteger combi(int n, int m) {
		BigInteger nb = BigInteger.ONE;
		BigInteger mb = BigInteger.ONE;
		int v1 = n, v2 = 1;
		for(int i=0; i<m; i++) {
			nb = nb.multiply(BigInteger.valueOf(v1--));
			mb = mb.multiply(BigInteger.valueOf(v2++));
		}
		if(m == 0) return BigInteger.ONE;
		return nb.divide(mb).mod(BigInteger.valueOf(mod));
	}
}
