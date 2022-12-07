import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main_1918 {

    static Map<Character, Integer> priority = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<input.length(); i++){
            char c = input.charAt(i);
            if(c >= 'A' && c <= 'Z')
                sb.append(c);
            else{
                if(c == ')') {
                    while(stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                } else if(c == '(') {
                    stack.push(c);
                } else {
                    while(!stack.isEmpty() && stack.peek() != '(' && priority.get(stack.peek()) >= priority.get(c)){
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
        }

        while(!stack.isEmpty())
            sb.append(stack.pop());
        System.out.println(sb);
    }
}
