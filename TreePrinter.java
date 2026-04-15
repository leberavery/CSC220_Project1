/**
 * TreePrinter displays the expression tree for the user.
 */
import java.util.*;

public class TreePrinter {

    public static void print(Node root, int level) {
        int height = getHeight(root);
        int width = (int) Math.pow(2, height) * 2;

        List<List<String>> levels = new ArrayList<>();

        for (int i = 0; i < height * 2; i++) {
            levels.add(new ArrayList<>(Collections.nCopies(width, " ")));
        }

        fill(root, levels, 0, width / 2, width / 4);

        System.out.println("Parse Tree:");

        for (int i = 0; i < levels.size(); i += 2) {
            // print node row
            System.out.println(join(levels.get(i)));

            // print / \ row if not last
            if (i + 1 < levels.size()) {
                System.out.println(join(levels.get(i + 1)));
            }
        }
    }

    private static void fill(Node node, List<List<String>> canvas, int row, int col, int offset) {
        if (node == null || row >= canvas.size()) return;

        write(canvas, row, col, node.value);

        if (offset < 1) offset = 1;

        // left child
        if (node.left != null) {
            canvas.get(row + 1).set(col - offset, "/");
            fill(node.left, canvas, row + 2, col - offset * 2, offset / 2);
        }

        // right child
        if (node.right != null) {
            canvas.get(row + 1).set(col + offset, "\\");
            fill(node.right, canvas, row + 2, col + offset * 2, offset / 2);
        }
    }

    private static void write(List<List<String>> canvas, int row, int col, String val) {
        List<String> line = canvas.get(row);
        for (int i = 0; i < val.length(); i++) {
            int c = col + i;
            if (c >= 0 && c < line.size()) {
                line.set(c, String.valueOf(val.charAt(i)));
            }
        }
    }

    private static String join(List<String> row) {
        StringBuilder sb = new StringBuilder();
        for (String s : row) sb.append(s);
        return sb.toString().replaceAll("\\s+$", "");
    }

    private static int getHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}