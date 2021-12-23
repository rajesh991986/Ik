package graphs;
import java.io.*;
        import java.util.*;
        import java.text.*;
        import java.math.*;
        import java.util.regex.*;

class TwoDArray {


    /*
     * Complete the function below.
     */
    static class Node {
        int x;
        int y;
        int keys;
        Node prev;
        public Node(int x, int y, Node prev) {
            this.x = x;
            this.y = y;
            this.prev = prev;
        }
    }
    private static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] find_shortest_path(String[] grid) {
        char[][] matrix = getMatrix(grid);
        int m = matrix.length;
        int n = matrix[0].length;
        int x = -1;
        int y = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '@') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        List<int[]> result = helper(matrix, x, y);
        return result.toArray(new int[result.size()][]);
    }
    private static List<int[]> helper(char[][] matrix, int row, int col) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][][] visited = new boolean[m][n][1 << 10];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(row, col, null));
        visited[row][col][0] = true;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int[] dir : DIRS) {
                int x = cur.x + dir[0];
                int y = cur.y + dir[1];
                Node next = new Node(x, y, cur);
                if (!isValid(matrix, x, y, cur, next)) {
                    continue;
                }
                if (matrix[x][y] == '+') {
                    List<int[]> result = new ArrayList<>();
                    result.add(new int[] {x, y});
                    while (cur != null) {
                        result.add(new int[] {cur.x, cur.y});
                        cur = cur.prev;
                    }
                    Collections.reverse(result);
                    return result;
                }
                if (!visited[x][y][next.keys]) {
                    queue.offer(next);
                    visited[x][y][next.keys] = true;
                }
            }
        }
        return new ArrayList<int[]>();
    }
    private static boolean isValid(char[][] matrix, int x, int y, Node cur, Node next) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == '#') {
            return false;
        }
        if (matrix[x][y] >= 'A' && matrix[x][y] <= 'J') {
            if ((cur.keys >> (matrix[x][y] - 'A') & 1) == 0) {
                return false;
            }
        }
        next.keys = cur.keys;
        if (matrix[x][y] >= 'a' && matrix[x][y] <= 'j') {
            int nextKeys = (1 << (matrix[x][y] - 'a'));
            next.keys |= nextKeys;
        }
        return true;
    }
    private static char[][] getMatrix(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        char[][] matrix = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = grid[i].charAt(j);
            }
        }
        return matrix;
    }


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count =0;
        int[][] res;
        int grid_size = 5;
        String[] grid = new String[grid_size];
        grid[0] = "+B...";
        grid[1] ="####.";
        grid[2] ="##b#.";
        grid[3] ="a...A";
        grid[4] ="##@##";
        String[] grid2 = new String[2];
        grid2[0] = ".dj##.f.j#efejj..@e#+G.c.";
        grid2[1] = ".hdI#.#aAghficDe#J.CGa.ba";

        res = find_shortest_path(grid2);
        int res_rowLength = res.length;
        for(int res_i = 0; res_i < res_rowLength; res_i++) {
            for(int res_j = 0; res_j < res[res_i].length; res_j++) {
                bw.write(String.valueOf(res[res_i][res_j]) + " ");
            }
            bw.newLine();
        }

        bw.close();
    }
}

