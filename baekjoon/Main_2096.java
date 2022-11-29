import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int input[][] = new int[N][3];
        int max[][] = new int[N][3];
        int min[][] = new int[N][3];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if(i == 0)
                    max[i][j] = min[i][j] = input[i][j];
            }
        }

        for(int i=1; i<N; i++)
            Arrays.fill(min[i], Integer.MAX_VALUE);
        for(int i=1; i<N; i++){
            for(int j=0; j<3; j++){
                for(int d=-1; d<=1; d++){
                    int k = j+d;
                    if(k < 0 || k > 2) continue;
                    max[i][j] = Math.max(max[i][j], max[i-1][k]+input[i][j]);
                    min[i][j] = Math.min(min[i][j], min[i-1][k]+input[i][j]);
                }
            }
        }

        int maxAns = 0, minAns = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            maxAns = Math.max(maxAns, max[N-1][i]);
            minAns = Math.min(minAns, min[N-1][i]);
        }
        System.out.println(maxAns+" "+minAns);
    }
}
