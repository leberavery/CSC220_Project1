/*
 * Code Authors: Avery Leber and Maya Thomas
 * Instructor: Dr. Bhuiyan   CSC220
 * Code Purpose: Accepts a mathematical expression from the user and solves it.
 *          Logic:
 *              1. Tokenizer: Break up the String input into a list of tokens.
 *              2. Validator: Ensure that the input has the correct grammar and is solvable.
 *              3. Tree Generator: Build an expression tree.
 *              4. Evaluate the expression.
 *              5. Ouput tokens, expression tree, and result.
*/

import java.util.*;
public class MiniCompiler {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      System.out.print("Input your Expression: ");
      String expression = sc.nextLine();

      sc.close();
      
      // Tokenizer
      Tokenizer tokenizer = new Tokenizer();
      List<String> tokens = tokenizer.tokenizer(expression);
      // Output Tokens:
      System.out.print("Tokens: [");
      int i = 0;
      for (String t : tokens) {
          if (i < tokens.size() - 1) System.out.print(t + ", ");
          else System.out.print(t);
          i++;
      }
      System.out.print("]\n");

      // Validator and Tree Builder
      ASTGenerator generator = new ASTGenerator(new LinkedList<>(tokens));
      Node ast = generator.build();
      // Output Tree:
      TreePrinter.print(ast, 0);
      
      // Evaluator
      int result = Evaluator.evaluate(ast);
      // Output Result:
      System.out.println("Evaluation Result: "+ result);  
    }
}
