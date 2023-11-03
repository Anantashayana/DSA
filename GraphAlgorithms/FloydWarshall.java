import java.util.Arrays;

public class FloydWarshall {
    private static final int INF = Integer.MAX_VALUE;

    public static void floydWarshall(int[][] graph, int numVertices) {
        // Create a copy of the input graph to store the intermediate results
        int[][] dist = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, numVertices);
        }

        // Compute shortest distances between all pairs of vertices
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    // If vertex k is an intermediate vertex that leads to a shorter path,
                    // update the distance from vertex i to vertex j
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the shortest distances
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int numVertices = 4;
        int[][] graph = {
            {0, 5, INF, 10},
            {INF, 0, 3, INF},
            {INF, INF, 0, 1},
            {INF, INF, INF, 0}
        };

        floydWarshall(graph, numVertices);
    }
}
