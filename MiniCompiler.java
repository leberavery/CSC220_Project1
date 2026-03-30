import java.util.*;
public class MiniCompiler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input your Expression: ");
        String expression = sc.nextLine();

        sc.close();

        List<String> tokens = tokenizer(expression);
        
        System.out.print("Tokens: [");
        for (String t : tokens) {
        
            System.out.print(t + ", ");
        }
        System.out.print("]");
    }

    public static List<String> tokenizer(String expression) {
        List<String> tokens = new ArrayList<>();
        String current = "";

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            //adds numbers or letters
            if (Character.isLetterOrDigit(c)) {
                current += c;
            }
            //adds operators, parenthesis, etc.
            else {
                if (!current.isEmpty()) {
                    tokens.add(current);
                    current = "";
                }

                if (!Character.isWhitespace(c)) {
                    tokens.add(Character.toString(c));
                }
            }
        }
        //Adds any left over tokens to the list
        if (!current.isEmpty()) {
            tokens.add(current);
        }

        return tokens;
    }
}