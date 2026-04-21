/*
 * Code Authors: Avery Leber and Maya Thomas
 * Instructor: Dr. Bhuiyan   CSC220
 * Code Purpose: Perform an expression's mathematical operation depending on its operator.
*      Logic:
*      1. Determine which operator the expression has:
*          1a. Addition
*          1b. Subtraction
*          1c. Multiplication
*          1d. Division 
*              1di. If the right node is 0, throw an ArithmeticException: Divide by Zero Error
*      2. If node fits none of the cases above, throw an Unknown Operator Error
*      3. Return the result to Expression() or Term()
*/

public class Evaluator {
    public static int evaluate(Node node) {
        if (node.left == null && node.right == null) {
             try {
            int nodeInt = Integer.parseInt(node.value);
            return nodeInt;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("\nSyntax Error: Expected a number but received " + node.value);
            }
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
            default -> throw new Error("Error: Unknown Operator " + node.value + " in Expression");
        };
    }
}