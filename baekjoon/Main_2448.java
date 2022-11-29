import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main_2448 {

    static int N;
    static String grid[];

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        grid = new String[N+1];
        for(int i=1; i<=N; i++)
            grid[i] = "";
        int tmp = N/3, grade = 1;
        while(tmp != 1){
            tmp /= 2;
            grade++;
        }
        star(1, N, grade);

        int val = N-1;
        for(int i=1; i<=N; i++){
            char[] str = new char[val];
            Arrays.fill(str, ' ');
            grid[i] += String.valueOf(str);
            val--;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            sb.append(grid[i]+"\n");
        }
        System.out.println(sb.toString());
    }

    static void star(int n, int m, int k){ // n: 시작 층 수 m: 끝 층 수 k: 단계
        if(k == 1){
            grid[n] += "  *";
            grid[n+1] += " * *";
            grid[n+2] += "*****";
        }
        else{
            int space = 3*(int)Math.pow(2, k-2);
            char[] str = new char[space];
            Arrays.fill(str, ' ');
            for (int i = n; i < n+(m-n+1)/2; i++)
                grid[i] += String.valueOf(str);

            star(n, n+(m-n+1)/2-1, k-1);
            star(n+(m-n+1)/2, m, k-1);
            int tmp = space;
            for(int i=n+(m-n+1)/2; i<=m; i++){
                str = new char[tmp];
                Arrays.fill(str, ' ');
                tmp--;
                grid[i] += String.valueOf(str);
            }
            star(n+(m-n+1)/2, m, k-1);
        }
    }
}
