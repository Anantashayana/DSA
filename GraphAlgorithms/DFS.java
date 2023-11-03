import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Graph class
class Graph {
    int numNodes;
    private List<List<Integer>> adjList;

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        adjList = new ArrayList<>();
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
}

// DFS algorithm
class DFS {
    public static void dfs(Graph graph, int startNode, int target) {
        int numNodes = graph.numNodes;
        boolean[] visited = new boolean[numNodes];
        Stack<Integer> stack = new Stack<>();

        // Mark the start node as visited and push it to the stack
        visited[startNode] = true;
        stack.push(startNode);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            System.out.print(node + " ");  // Process or visit the current node

            if (node == target) {
                System.out.println("\nTarget found!");
                return;
            }

            // Get the adjacent nodes of the current node
            List<Integer> neighbors = graph.getAdjacentNodes(node);

            // Visit unvisited neighbors and push them to the stack
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }

        System.out.println("\nTarget not found!");
    }
// }

// // Main class
// public class Main {
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

        int startNode = 0; // Starting node for DFS
        int target = 7; // Target number to search

        System.out.print("DFS Traversal: ");
        DFS.dfs(graph, startNode, target);
    }
}
