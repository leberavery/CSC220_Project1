/**
 * Code Authors: Avery Leber and Maya Thomas
 * Instructor: Dr. Bhuiyan   CSC220
 * Code Purpose: Breaks the String input expression into a list of individual characters.
 *                @return token 
 *      Logic:
 *      1. Parse every character of the expression
 *      2. If the character is a digit, add it to the "current" pointer. Add the complete number to the @param tokens list.
 *             2a. Ensures multi-digit numbers are kept together.
 *      3. If the character is whitespace, add it to the list to separate numbers.
**/
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
      public List<String> tokenizer(String expression) {
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