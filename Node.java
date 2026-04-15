/**
 * Node represents a single element in the expression tree.
 *
 * - Leaf nodes store numbers.
 * - Internal nodes store operators.
 */
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