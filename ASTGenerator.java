/**
* Code Authors: Avery Leber and Maya Thomas
* Instructor: Dr. Bhuiyan   CSC220
* Code Purpose: 
        1. Checks whether the expression has the correct grammar using recursive descent parsing.
*           Grammar: 
*           E → E + T | E - T | T 
*           T → T * F | T / F | F
*           F → (E) | number
        2. Builds an expression tree.
*       Logic: 
*       1. The parser calls Expression(), which accepts an expression list make up of String tokens.
*       2. Expression() calls Term() which calls Factor(). 
*           2a. Expression() handles addition and subtraction.
*           2b. Term() handles multiplication and division.
*           2c. Factor() handles parantheses and numbers.
*      3. If this program runs without throwing any errors, 
*         the expression is gramatically valid and can be solved. 
*         If valid, @return map of expression tree. 
**/

import java.util.*;

public class ASTGenerator {
    private final LinkedList<String> tokens;

    public ASTGenerator(List<String> tokens) {
        this.tokens = new LinkedList<>(tokens);
    }

    /*
     * Purpose: Parses the given expression, determines its validity, and maps the tree.     
    */
    public Node build() {
        return parseExpression();
    }

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