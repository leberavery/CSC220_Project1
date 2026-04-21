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