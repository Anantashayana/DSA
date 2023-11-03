/*

1. Set the distance of the source vertex to 0 in the distance matrix.
2. While there are unvisited vertices, repeat the following steps:
3. Select the vertex u with the minimum distance from the distance matrix among the unvisited vertices. 
4. Mark u as visited.
5. Traverse all the neighbors of u.
6. For each neighbor vertex v, calculate the total distance from the source to v through u by adding 
the distance from the source to u and the cost from u to v.
7. If the total distance is smaller than the current distance stored in the distance matrix for v, 
update the distance of v to the new smaller distance.

*/
/*
procedure dijkstra(G, source):
Q = priority_queue()
distance[] = infinity
Q.enqueue(source)
distance[source] = 0
while Q is not empty
    u <- nodes in Q with minimum distance[]
    remove u from the Q
    for all edges from u to v in G.adjacentEdges(v) do
        if distance[u] + cost[u][v] < distance[v]
            distance[v] = distance[u] + cost[u][v]
            Q.enqueue(v)
        end if
    end for
end while
Return distance
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    int vertex;
    int distance;

    Node(int v, int d) {
        vertex = v;
        distance = d;
    }

    public int compareTo(Node other) {
        return Integer.compare(distance, other.distance);
    }
}

public class Dijkstras {
    static final int INF = Integer.MAX_VALUE;

    public static List<Integer> dijkstra(List<List<Pair<Integer, Integer>>> graph, int source) {
        int n = graph.size();

        int[] distance = new int[n];
        Arrays.fill(distance, INF);
        distance[source] = 0;

        boolean[] visited = new boolean[n];

        // **If Priority Queue is used ** //
        // PriorityQueue<Node> pq = new PriorityQueue<>();
        // pq.offer(new Node(source, 0));

        // while (!pq.isEmpty()) {
        //     Node node = pq.poll();

        while (true) {
            int minDist = INF;
            int minVertex = -1;

            // Find the unvisited vertex with the minimum distance
            for (int i = 0; i < n; i++) {
                if (!visited[i] && distance[i] < minDist) {
                    minDist = distance[i];
                    minVertex = i;
                }
            }

            if (minVertex == -1) {
                break; // All vertices have been visited
            }

            visited[minVertex] = true;

            // Update distances of neighbors
            for (Pair<Integer, Integer> neighbor : graph.get(minVertex)) {
                int next = neighbor.first;
                int weight = neighbor.second;
                int totalDist = distance[minVertex] + weight;

                if (totalDist < distance[next]) {
                    distance[next] = totalDist;
                }
            }
        }  //End of While

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(distance[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 6; // Number of vertices
        int m = 9; // Number of edges

        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Define the edges and weights of the graph
        graph.get(0).add(new Pair<>(1, 2));
        graph.get(0).add(new Pair<>(2, 5));
        graph.get(1).add(new Pair<>(0, 2));
        graph.get(1).add(new Pair<>(2, 2));
        graph.get(1).add(new Pair<>(3, 1));
        graph.get(2).add(new Pair<>(0, 5));
        graph.get(2).add(new Pair<>(1, 2));
        graph.get(2).add(new Pair<>(3, 2));
        graph.get(2).add(new Pair<>(4, 1));
        graph.get(3).add(new Pair<>(1, 1));
        graph.get(3).add(new Pair<>(2, 2));
        graph.get(3).add(new Pair<>(4, 4));
        graph.get(3).add(new Pair<>(5, 3));
        graph.get(4).add(new Pair<>(2, 1));
        graph.get(4).add(new Pair<>(3, 4));
        graph.get(4).add(new Pair<>(5, 3));
        graph.get(5).add(new Pair<>(3, 3));
        graph.get(5).add(new Pair<>(4, 3));

        int sourceVertex = 0; // Source vertex for Dijkstra's algorithm

        // Perform Dijkstra's algorithm
        List<Integer> distances = dijkstra(graph, sourceVertex);

        // Print the shortest distances from the source vertex to all other vertices
        System.out.println("Shortest distances from vertex " + sourceVertex + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("Vertex " + i + ": " + distances.get(i));
        }
    }
}

class Pair<T1, T2> {
    public T1 first;
    public T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}

