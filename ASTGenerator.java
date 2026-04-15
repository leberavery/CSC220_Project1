/**
 * ASTGenerator constructs the expression tree using recursive descent parsing.
 *
 */
import java.util.*;

public class ASTGenerator {
    private LinkedList<String> tokens;

    public ASTGenerator(List<String> tokens) {
        this.tokens = new LinkedList<>(tokens);
    }

    public Node build() {
        return parseExpression();
    }

    private Node parseExpression() {
        Node left = parseTerm();

        while (!tokens.isEmpty() &&
              (tokens.getFirst().equals("+") || tokens.getFirst().equals("-"))) {

            String op = tokens.removeFirst();
            Node right = parseTerm();
            left = new Node(op, left, right);
        }

        return left;
    }

    private Node parseTerm() {
        Node left = parseFactor();

        while (!tokens.isEmpty() &&
              (tokens.getFirst().equals("*") || tokens.getFirst().equals("/"))) {

            String op = tokens.removeFirst();
            Node right = parseFactor();
            left = new Node(op, left, right);
        }

        return left;
    }

    private Node parseFactor() {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Unexpected end of expression");
        }

        String token = tokens.getFirst();

        if (token.equals("(")) {
            tokens.removeFirst();
            Node node = parseExpression();

            if (tokens.isEmpty() || !tokens.getFirst().equals(")")) {
                throw new RuntimeException("Missing closing parenthesis");
            }

            tokens.removeFirst();
            return node;
        }

        tokens.removeFirst();
        return new Node(token);
    }
}