//import java.util.Scanner;

public class WeaklyConnected {

    public static void main(String[] args) {

        // Weak test
        int[][] test1 = {
                {0, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {0, 0, 1, 0}
        };

        // Not weak test
        int[][] test2 = {
                {0,1,0,0},
                {0,0,0,0},
                {0,0,0,1},
                {0,0,0,0}
        };

        int[][] adj = test1;

        // n number of nodes in the graph
        int n = adj.length;
        boolean[] visited = new boolean[n];

        dfs(0, adj, visited);

        // Check if all nodes were visited
        for (boolean v : visited) {
            if (!v) {
                System.out.println("Not Weakly Connected");
                return;
            }
        }
        System.out.println("Weakly Connected");

    }

    private static void dfs(int node, int[][] adj, boolean[] visited) {

        visited[node] = true; // Mark current node visited

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i] && (adj[node][i] == 1 || adj[i][node] == 1)) {
                dfs(i, adj, visited);
            }
        }
    }
}

/*
Sources:
https://www.geeksforgeeks.org/dsa/adjacency-matrix/
https://www.geeksforgeeks.org/dsa/depth-first-search-or-dfs-for-a-graph/
https://www.geeksforgeeks.org/dsa/implementation-of-dfs-using-adjacency-matrix/
https://www.youtube.com/watch?v=B28xAWEerK8
https://www.youtube.com/watch?v=EvV30rWKOqo
https://web.stanford.edu/class/archive/cs/cs161/cs161.1172/CS161Lecture09.pdf
ChatGPT for tests and debugging
 */