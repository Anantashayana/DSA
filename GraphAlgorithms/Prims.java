import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Edge {
    int destination;
    int weight;

    public Edge(int dest, int weight) {
        this.destination = dest;
        this.weight = weight;
    }
}

class Node {
    int vertex;
    int key;

    public Node(int v, int k) {
        this.vertex = v;
        this.key = k;
    }
}

class Graph {
    int V;
    List<List<Edge>> adjList;

    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight));
    }
}

public class Prims {
    static class CompareNodes implements java.util.Comparator<Node> {
        public int compare(Node a, Node b) {
            return a.key - b.key;
        }
    }

    public static void primMST(Graph graph, int startVertex) {
        int V = graph.V;

        // Priority queue to store vertices and their keys
        PriorityQueue<Node> pq = new PriorityQueue<>(new CompareNodes());

        // Array to store the parent of each vertex in the MST
        int[] parent = new int[V];

        // Array to store the key value of each vertex
        int[] key = new int[V];

        // Array to track whether a vertex is included in the MST
        boolean[] inMST = new boolean[V];

        // Initialize key values to infinity and inMST array to false
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            inMST[i] = false;
        }

        // Insert the start vertex into the priority queue
        pq.offer(new Node(startVertex, 0));
        key[startVertex] = 0;

        // Loop until all vertices are processed
        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;

            // Mark the current vertex as visited
            inMST[u] = true;

            // Iterate through all adjacent vertices of u
            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                // If v is not in MST and weight of (u,v) is smaller than current key of v
                if (!inMST[v] && weight < key[v]) {
                    // Update the key value of v and parent of v
                    key[v] = weight;
                    parent[v] = u;
                    pq.offer(new Node(v, key[v]));
                }
            }
        }

        // Print the edges of the minimum spanning tree
        System.out.println("Minimum Spanning Tree Edges:");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i);
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices in the graph

        // Create an adjacency list representation of the graph
        Graph graph = new Graph(V);

        // Add edges to the graph
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        int startVertex = 0; // Starting vertex for Prim's algorithm

        // Find the minimum spanning tree using Prim's algorithm
        primMST(graph, startVertex);
    }
}
