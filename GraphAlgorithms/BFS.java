import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Graph {
    private int numNodes;
    private List<List<Integer>> adjList;

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        adjList = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
    }

    public List<Integer> getAdjacentNodes(int node) {
        return adjList.get(node);
    }

    public int getNumNodes() {
        return numNodes;
    }
}

public class BFS {
    public static void bfs(Graph graph, int startNode, int target) {
        int numNodes = graph.getNumNodes();
        boolean[] visited = new boolean[numNodes];
        Queue<Integer> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            System.out.print(node + " "); // Process or visit the current node

            if (node == target) {
                System.out.println("\nTarget found!");
                return;
            }

            List<Integer> neighbors = graph.getAdjacentNodes(node);

            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        System.out.println("\nTarget not found!");
    }

    public static void main(String[] args) {
        int numNodes = 8; // Number of nodes in the graph

        Graph graph = new Graph(numNodes);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);

        int startNode = 0; // Starting node for BFS
        int target = 7; // Target number to search

        System.out.print("BFS Traversal: ");
        bfs(graph, startNode, target);
    }
}
