/*
1. Initialize the flow on each edge to 0.

2. While there is an augmenting path P in the residual graph Gf:
     - Find the minimum residual capacity along path P, let it be minCapacity.
     - Update the flow along each edge of path P:
         - For each edge (u, v) in P:
             - Add minCapacity to the flow of edge (u, v).
             - Subtract minCapacity from the flow of the reverse edge (v, u).

3. Output the maximum flow found in the network.


 */
// Edmund Carp Algorithm
// Uses BFS
 import java.util.*;

class Edge {
    int source, destination, capacity;

    Edge(int source, int destination, int capacity) {
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
    }
}

public class FordFulkerson {
    // Breadth-First Search (BFS) to find an augmenting path
    private static boolean bfs(int[][] residualGraph, int numVertices, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[numVertices];
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Explore all adjacent vertices of the current vertex
            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && residualGraph[current][v] > 0) {
                    queue.add(v);
                    parent[v] = current;
                    visited[v] = true;
                }
            }
        }

        // Return true if there is a path from source to sink in the residual graph
        return visited[sink];
    }

    // Ford-Fulkerson algorithm
    private static int fordFulkerson(int[][] graph, int numVertices, int source, int sink) {
        // Create a copy of the input graph as the residual graph
        int[][] residualGraph = new int[numVertices][numVertices];
        for (int u = 0; u < numVertices; u++) {
            System.arraycopy(graph[u], 0, residualGraph[u], 0, numVertices);
        }

        int[] parent = new int[numVertices]; // Array to store the parent of each vertex
        int maxFlow = 0; // Initialize the maximum flow

        // Find augmenting paths and update the residual graph until no more augmenting paths exist
        while (bfs(residualGraph, numVertices, source, sink, parent)) {
            int minCapacity = Integer.MAX_VALUE;

            // Find the minimum capacity of the augmenting path
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                minCapacity = Math.min(minCapacity, residualGraph[u][v]);
            }

            // Update the residual capacities and reverse edges along the augmenting path
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph[u][v] -= minCapacity;
                residualGraph[v][u] += minCapacity;
            }

            // Add the minimum capacity to the maximum flow
            maxFlow += minCapacity;
        }

        // Return the maximum flow
        return maxFlow;
    }

    public static void main(String[] args) {
        int numVertices = 6;
        int source = 0;
        int sink = 5;

        // Create the input graph with capacities
        int[][] graph = {
            {0, 16, 13, 0, 0, 0},
            {0, 0, 10, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
        };

        // Apply the Ford-Fulkerson algorithm to find the maximum flow
        int maxFlow = fordFulkerson(graph, numVertices, source, sink);
        System.out.println("Maximum flow: " + maxFlow);
    }
}

 