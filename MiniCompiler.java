import java.util.*;
public class MiniCompiler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input your Expression: ");
        String expression = sc.nextLine();

        sc.close();
        
        /*
        * Tokenizer
        */
        List<String> tokens = tokenizer(expression);
        
        System.out.print("Tokens: [");
        for (String t : tokens) {
        
            System.out.print(t + " ");
        }
        System.out.print("]");

        /*
        * Parser
        */
        System.out.println("\nTesting Validator...");
        Parser parse = new Parser();
        parse.Expression(new LinkedList<>(tokens));
        System.out.println("Validator Success!: No Errors Thrown");

        /*
        * Expression Tree Generator
        */
        ASTGenerator generator = new ASTGenerator(new LinkedList<>(tokens));
        Node ast = generator.build();
        
        System.out.println("ROOT: " + ast.value);
        System.out.println("LEFT: " + ast.left.value);
        System.out.println("RIGHT: " + ast.right.value);
        
        System.out.println("\nExpression Tree: ");
        
        TreePrinter.print(ast, 0);
        
        int result = Evaluator.evaluate(ast);
        System.out.println("\nResult: "+result);
        
    }

    public static List<String> tokenizer(String expression) {
      List<String> tokens = new ArrayList<>();
       String current = "";

     for (int i = 0; i < expression.length(); i++) {
           char c = expression.charAt(i);

         if (Character.isDigit(c)) {
               current += c;
         } else {
               if (!current.isEmpty()) {
                 tokens.add(current);
                   current = "";
               }

               if (!Character.isWhitespace(c)) {
                  tokens.add(Character.toString(c));
               }
         }
       }

       if (!current.isEmpty()) {
          tokens.add(current);
       }

       return tokens;
   }
}
