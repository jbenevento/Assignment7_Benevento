import java.util.Scanner;

public class FourCycles {

    public static void main(String[] args) {

//        int[][] test1 = {
//                {0, 2, 0, 1},
//                {0, 0, 3, 0},
//                {4, 0, 0, 5},
//                {0, 6, 0, 0}
//        };
//
//        int[][] graph = test1;
//        int n = graph.length;

        Scanner scnr = new Scanner(System.in);

        int n = scnr.nextInt();   // n number of vertices
        int[][] graph = new int[n][n];

        // Read adjacency matrix (weights or 0 if no edge)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scnr.nextInt();
            }
        }

        System.out.println("Cycles of length 4:");

        // 4 node combinations
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != i) {

                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j) {

                            for (int l = 0; l < n; l++) {
                                if (l != i && l != j && l != k) {

                                    // Check edges to form a cycle i -> j -> k -> l -> i
                                    if (graph[i][j] != 0 &&
                                            graph[j][k] != 0 &&
                                            graph[k][l] != 0 &&
                                            graph[l][i] != 0) {

                                        System.out.println(i + " -> " + j + " -> " + k + " -> " + l + " -> " + i);
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        scnr.close();
    }
}