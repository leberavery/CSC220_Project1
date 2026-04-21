/**
 * Code Authors: Avery Leber and Maya Thomas
 * Instructor: Dr. Bhuiyan   CSC220
 * Code Purpose: A helper class for ASTGenerator.java, Evaluator.java, MiniCompiler.java, and TreePrinter.java.
 *      Logic: 
 *          A Node represents a single element in the expression tree.
 *          Leaf nodes store numbers.
 *          Internal nodes store operators.
 *          @param value is an accessor to the current node's data.
 *          @param left is an accessor to the current node's left child.
 *          @param right is an accessor to the current node's right child.
**/

public class Node {
    String value;
    Node left, right;

    public Node(String value) {
        this.value = value;
    }

    public Node(String value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}