/*
* Code Authors: Avery Leber and Maya Thomas
* Instructor: Dr. Bhuiyan   CSC220
* Code Purpose: Checks whether the expression has the correct grammar using recursive descent parsing.
*      Grammar: 
*       E → E + T | E - T | T 
*       T → T * F | T / F | F
*       F → (E) | number
*      Logic: 
*       1. The parser calls Expression(), which accepts an expression list make up of String tokens.
*       2. Expression() calls Term() which calls Factor(). 
*           2a. Expression() handles addition and subtraction.
*           2b. Term() handles multiplication and division.
*           2c. Factor() handles parantheses and numbers.
*      3. If this program runs without throwing any errors, the expression is gramatically valid and can be solved.
*/

import java.util.*;

public class Parser {
    /*
     * Purpose: First method the parser calls. Handles addition and subtraction within the expression.
     *  Logic: 
     *  One expression list is made up of several individual expressions. 
     *  Every expression is made up of three parts: a left term, an operator, and a right term.
     *  1. Find the first left term of the expression list by calling Term().
     *  2. If the expression list is not empty and the head of the linked list is "*" or "/":   
     *      2a. Store the operator in a pointer
     *      2b. Remove the operator from the head of the expression list
     *      2c. Find the right node of the expression by calling Term()
     *      2d. Find the value of the sum/difference of left and right by calling combine() and assign it to the left node
     *  3. Return the left node
    */
    public double Expression(List<String> input) {
        double left = Term(input);
        while (!input.isEmpty() && (input.getFirst().equals("+") || input.getFirst().equals("-"))) {
            String operator = input.getFirst();
            pop(input, operator);
            double right = Term(input);
            left = combine(operator, left, right);
        }
        return left;
    }

    /*
     * Purpose: Handles multiplication and division.
     *      Logic: 
     *      One expression list is made up of several individual expressions. 
     *      Every expression is made up of three parts: a left term, an operator, and a right term.
     *      1. Find the left term of the expression by calling Factor().
     *      2. If the expression list is not empty and the head of the linked list is "*" or "/":   
     *          2a. Store the operator in a pointer
     *          2b. Remove the operator from the head of the expression list
     *          2c. Find the right node of the expression by calling Factor()
     *          2d. Find the value of the sum/difference of left and right by calling combine() and assign it to the left node
     *      3. Return left node to Expression()
    */
    public double Term(List<String> input) {
        double left = Factor(input);

        while (!input.isEmpty() && ((input.getFirst().equals("*") || input.getFirst().equals("/")))) {
            String operator = input.getFirst();
            pop(input, operator);
            double right = Factor(input);
            left = combine(operator, left, right);
        }
        return left;
    }

    /*
     * Purpose: Handles parentheses and numbers.
     *      Logic: 
     *      One expression list is made up of several individual expressions. 
     *      Every expression is made up of three parts: a left term, an operator, and a right term.
     *      1. Find the right node of the expression 
     *      2. If the right node is a parenthesis, a new individual expression has begun.
     *          2a. Remove the left parenthesis from the expression list.
     *          2b. Start a new Expression() recursive descent to handle the expression inside of the parentheses.
     *          2c. Remove the right parenthesis from the expression list.
     *      3. If the right node is not a parenthesis, call match() to verify that the node is a number.
     *      4. Return number to Term()
    */
    public double Factor(List<String> input) {
        double left;
        String right = input.getFirst();
        if (right.equals("(")) {
            pop(input, "(");
            left = Expression(input);
            pop(input, ")");
            return left;
        } else {
            return match(input);
        }
    }

    /*
     * Purpose: Helper method used by the Factor() method to determine if the next token is valid.
     *      Logic:
     *      1. Check if tokens list is empty. If so, throw Exception.
     *      2. "pop" the current token and set it equal to the currentToken pointer
     *      3. If the currentToken pointer is equal to the expectedToken, return it. Otherwise, throw Exception.
    */
    public String pop(List<String> input, String expectedToken) {
        if (input.isEmpty()) throw new RuntimeException("Error: Expression list ended unexpectedly"); 

        String currentToken = input.removeFirst(); 

        if (currentToken.equals(expectedToken)) return currentToken;
        else throw new RuntimeException("Syntax Error: Invalid expression formatting");
    }

    /*
     * Purpose: Helper method used by Factor() to determine if a given node is a number.
     *      Logic:
     *      1. Attempt to convert the head of the expression list into a double
     *      2. If it succeeds, remove the node from the linked list and return it to Factor()
     *      3. If it does not succeed, throw a Syntax Error: The program expected a number but did not receive one.
    */
    public double match(List<String> input) {
        String node = input.getFirst();

        try {
            double nodeDouble = Double.parseDouble(node);

            input.removeFirst();
            return nodeDouble;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Syntax Error: Expected a number but received " + node);
        }

    }

    /*
     * Purpose: Perform an expression's mathematical operation depending on its operator.
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
    public double combine(String operator, double left, double right) {
        double result;
        switch (operator) {
            case "+" -> result = left + right;
            case "-" -> result = left - right;
            case "*" -> result = left * right;
            case "/" -> { if (right == 0) throw new ArithmeticException("Arithmetic Error: Cannot Divide by Zero");
                          result = left / right; }
            default -> throw new Error("Error: Unknown Operator " + operator + " in Expression");
        }
        return result;
    }
}
