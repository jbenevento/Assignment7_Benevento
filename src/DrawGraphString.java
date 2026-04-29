public class DrawGraphString {
    public static void main(String[] args) {

        String s = "ANT CUN BOG AMA DC TOL SAN";
        String[] nodes = s.split(" ");

        int n = nodes.length;
        int[][] adj = new int[n][n];

        for (int i = 0; i < n; i++) {

            int right = (2 * i + 1) % n;
            int left = (2 * i + 2) % n;

            adj[i][right] = 1;
            adj[i][left] = 1;
        }

        // Print column labels
        System.out.print("    ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%5s", nodes[i]);
        }
        System.out.println();

        // Print adjacency matrix with row labels
        for (int i = 0; i < n; i++) {
            System.out.printf("%5s", nodes[i]);
            for (int j = 0; j < n; j++) {
                System.out.printf("%5d", adj[i][j]);
            }
            System.out.println();
        }
    }
}

/*
ChatGPT for debugging and formating
 */

