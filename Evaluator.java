/**
 * Evaluator recursively computes the value of the expression tree.
 */
public class Evaluator {
    public static int evaluate(Node node) {
        if (node.left == null && node.right == null) {
            return Integer.parseInt(node.value);
        }

        int left = evaluate(node.left);
        int right = evaluate(node.right);

        return switch (node.value) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> {
                if (right == 0) throw new ArithmeticException("Divide by zero");
                yield left / right;
            }
            default -> throw new RuntimeException("Unknown operator: " + node.value);
        };
    }
}