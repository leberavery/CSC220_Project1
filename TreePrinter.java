/*
 * Code Authors: Avery Leber and Maya Thomas
 * Instructor: Dr. Bhuiyan   CSC220
 * Code Purpose: Displays the expression tree for the user.
 *          Logic:
 *              1. Determine the height of the expression tree.
 *              2. Based on the height, determine the width of the expression tree.
 *              3. Create a 2D list of Strings to act as a coordinate grid to format the expression tree.
 *              4. Use preorder traversal to map the expression tree onto the grid.
 *              5. Convert the list of Strings into a single String and clean up output.
*/

import java.util.*;

public class TreePrinter {
    public static void print(Node root, int level) {
        int height = getHeight(root);
        int width = (int) Math.pow(2, height) * 2;      // Ensure that the last level of the expression tree has enough room

        List<List<String>> levels = new ArrayList<>();     

        for (int i = 0; i < height * 2; i++) {
            levels.add(new ArrayList<>(Collections.nCopies(width, " ")));
        }

        fill(root, levels, 0, width / 2, width / 4);

        System.out.println("Parse Tree:");

        for (int i = 0; i < levels.size(); i += 2) {
            System.out.println(join(levels.get(i)));
            if (i + 1 < levels.size()) {
                System.out.println(join(levels.get(i + 1)));
            }
        }
    }

        /**
         * Purpose: Use preorder traversal to map the tree onto the grid.
         *      Logic:
         *          1. The root should be printed at the top row in the middle of the column.
         *          2. Using the value of @param offset determine how far left or right the child node should be printed.
         *          3. Halve the value of @param offset for every @param row completed.
        **/
        private static void fill(Node node, List<List<String>> canvas, int row, int col, int offset) {
        if (node == null || row >= canvas.size()) return;

        write(canvas, row, col, node.value);

        int nextRow = row + 2;
        
        if (node.left != null) {
            canvas.get(row + 1).set(col - offset / 2, "/");
            fill(node.left, canvas, nextRow, col - offset, offset / 2);
        }

        if (node.right != null) {
            canvas.get(row + 1).set(col + offset / 2, "\\");
            fill(node.right, canvas, nextRow, col + offset, offset / 2);
        }
    }

    /**
     * Purpose: A helper method of fill() that handles character placement.
     *      Logic: 
     *          1. If a node contains multiple characters (ie., "10"), adjust the @param col value to account for it.
     *          2. Otherwise, append single-digit characters to the given @param col value.
    **/
    private static void write(List<List<String>> canvas, int row, int col, String val) {
        List<String> line = canvas.get(row);
        for (int i = 0; i < val.length(); i++) {
            int c = col + i;
            if (c >= 0 && c < line.size()) {
                line.set(c, String.valueOf(val.charAt(i)));
            }
        }
    }

    /*
     * Purpose: Converts the list of strings (the rows of the coordinate grid) into a single printable String.
     *          Trims whitespace.
    */
    private static String join(List<String> row) {
        StringBuilder sb = new StringBuilder();
        for (String s : row) sb.append(s);
        return sb.toString().replaceAll("\\s+$", "");
    }

    /* 
     * Purpose: Recursively discovers the "height" of the tree.
     *      Logic:
     *          The height of the tree will determine how to format the String representation of the expression tree.
     *          1. Traverse the expression tree until the farthest leaf node from the root is found.
     *          2. Every time a new node is travelled to, increase the height counter by 1.
    */
    private static int getHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}