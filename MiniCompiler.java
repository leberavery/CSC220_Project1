import java.util.*;
public class MiniCompiler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Input your Expression: ");
        String expression = sc.nextLine();

        sc.close();
        
        /*
        * Tokenizer
        */
        List<String> tokens = tokenizer(expression);
        System.out.print("Tokens: [");
        int i = 0;
        for (String t : tokens) {
            if (i < tokens.size() - 1) System.out.print(t + ", ");
            else System.out.print(t);
            i++;
        }
        System.out.print("]\n");

        /*
        * Parser
        */
        Parser parse = new Parser();
        parse.Expression(new LinkedList<>(tokens));
        /*
        * Expression Tree Generator
        */
        ASTGenerator generator = new ASTGenerator(new LinkedList<>(tokens));
        Node ast = generator.build();
        
        TreePrinter.print(ast, 0);
        
        int result = Evaluator.evaluate(ast);
        System.out.println("Evaluation Result: "+ result);
        
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
