import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main_2448_2 {
    static int N;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        String map[] = new String[N];
        map[0] = "  *  ";
        map[1] = " * * ";
        map[2] = "*****";
        for(int k=1; 3*(int)Math.pow(2,k) <= N; k++){
            makeStar(map, k);
        }

        for(int i=0; i<N; i++)
            System.out.println(map[i]);
    }

    static void makeStar(String[] map, int k){
        int bottom = 3*(int)Math.pow(2,k);
        int middle = bottom/2;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<middle; i++)
            sb.append(" ");
        for(int i=0; i<middle; i++) {
            map[i + middle] = map[i] + " " + map[i];
            map[i] = sb.toString()+ map[i] + sb.toString();
        }
    }
}
