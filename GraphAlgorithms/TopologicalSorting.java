import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSorting {
    public static List<Integer> topologicalSort(List<List<Integer>> adjList) {
        int numNodes = adjList.size();
        int[] inDegree = new int[numNodes]; // Array to store the in-degree of each node
        List<Integer> sortedNodes = new ArrayList<>(); // List to store the topologically sorted nodes

        // Compute the in-degree of each node
        for (List<Integer> neighbors : adjList) {
            for (int neighbor : neighbors) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        // Enqueue nodes with in-degree 0
        for (int i = 0; i < numNodes; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Perform topological sorting using Kahn's algorithm
        while (!queue.isEmpty()) {
            int node = queue.poll();
            sortedNodes.add(node);

            for (int neighbor : adjList.get(node)) {
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (sortedNodes.size() != numNodes) {
            // Graph contains a cycle, topological sorting is not possible
            sortedNodes.clear();
        }

        return sortedNodes;
    }

    public static void main(String[] args) {
        int numNodes = 6; // Number of nodes in the graph

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            adjList.add(new ArrayList<>());
        }

        adjList.get(0).add(1);
        adjList.get(0).add(2);
        adjList.get(1).add(3);
        adjList.get(2).add(3);
        adjList.get(2).add(4);
        adjList.get(3).add(4);
        adjList.get(3).add(5);
        adjList.get(4).add(5);

        List<Integer> sortedNodes = topologicalSort(adjList);

        System.out.print("Topologically Sorted Nodes: ");
        for (int node : sortedNodes) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
