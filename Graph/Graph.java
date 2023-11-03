import java.util.*;

class Graph {
    private int numVertices;
    private List<List<Integer>> adjacencyList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addVertex() {
        numVertices++;
        adjacencyList.add(new ArrayList<>());
    }

    public void removeVertex(int vertex) {
        if (vertex < numVertices) {
            adjacencyList.remove(vertex);
            numVertices--;

            for (List<Integer> neighbors : adjacencyList) {
                for (int i = 0; i < neighbors.size(); i++) {
                    int neighbor = neighbors.get(i);
                    if (neighbor == vertex) {
                        neighbors.remove(i);
                        break;
                    } else if (neighbor > vertex) {
                        neighbors.set(i, neighbor - 1);
                    }
                }
            }
        }
    }

    public void addEdge(int source, int destination) {
        if (source < numVertices && destination < numVertices) {
            adjacencyList.get(source).add(destination);
            adjacencyList.get(destination).add(source);
        }
    }

    public void removeEdge(int source, int destination) {
        if (source < numVertices && destination < numVertices) {
            adjacencyList.get(source).remove(Integer.valueOf(destination));
            adjacencyList.get(destination).remove(Integer.valueOf(source));
        }
    }

    public boolean isAdjacent(int vertex1, int vertex2) {
        if (vertex1 < numVertices && vertex2 < numVertices) {
            return adjacencyList.get(vertex1).contains(vertex2);
        }
        return false;
    }

    public List<Integer> getNeighbors(int vertex) {
        if (vertex < numVertices) {
            return adjacencyList.get(vertex);
        }
        return new ArrayList<>();
    }

    public int getDegree(int vertex) {
        if (vertex < numVertices) {
            return adjacencyList.get(vertex).size();
        }
        return 0;
    }

    public boolean isConnected() {
        if (numVertices == 0) {
            return false;
        }

        boolean[] visited = new boolean[numVertices];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[0] = true;
        queue.add(0);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            for (int neighbor : adjacencyList.get(currentVertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        for (boolean isVisited : visited) {
            if (!isVisited) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> findShortestPath(int source, int destination) {
        if (source >= numVertices || destination >= numVertices) {
            return new ArrayList<>();
        }

        boolean[] visited = new boolean[numVertices];
        int[] previous = new int[numVertices];
        Arrays.fill(previous, -1);

        visited[source] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            if (currentVertex == destination) {
                List<Integer> shortestPath = new ArrayList<>();
                int vertex = destination;
                while (vertex != -1) {
                    shortestPath.add(vertex);
                    vertex = previous[vertex];
                }
                Collections.reverse(shortestPath);
                return shortestPath;
            }

            for (int neighbor : adjacencyList.get(currentVertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    previous[neighbor] = currentVertex;
                    queue.add(neighbor);
                }
            }
        }
        return new ArrayList<>();
    }

    public void traverseGraphBFS() {
        if (numVertices == 0) {
            return;
        }

        boolean[] visited = new boolean[numVertices];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                visited[i] = true;
                queue.add(i);

                while (!queue.isEmpty()) {
                    int currentVertex = queue.poll();
                    System.out.print(currentVertex + " ");

                    for (int neighbor : adjacencyList.get(currentVertex)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    public void traverseGraphDFS() {
        if (numVertices == 0) {
            return;
        }

        boolean[] visited = new boolean[numVertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                stack.push(i);

                while (!stack.isEmpty()) {
                    int currentVertex = stack.pop();

                    if (!visited[currentVertex]) {
                        visited[currentVertex] = true;
                        System.out.print(currentVertex + " ");

                        for (int neighbor : adjacencyList.get(currentVertex)) {
                            if (!visited[neighbor]) {
                                stack.push(neighbor);
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    public boolean hasCycle() {
        if (numVertices == 0) {
            return false;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && hasCycleUtil(i, visited, -1)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleUtil(int currentVertex, boolean[] visited, int parent) {
        visited[currentVertex] = true;

        for (int neighbor : adjacencyList.get(currentVertex)) {
            if (!visited[neighbor]) {
                if (hasCycleUtil(neighbor, visited, currentVertex)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            for (int neighbor : adjacencyList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
// }

// public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        // Adding edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // Adding a vertex
        graph.addVertex();

        // Removing an edge
        graph.removeEdge(0, 4);

        // Removing a vertex
        graph.removeVertex(2);

        // Printing the graph
        System.out.println("Graph:");
        graph.printGraph();

        // Testing operations
        System.out.println("Adjacent(1, 3): " + graph.isAdjacent(1, 3));
        System.out.println("Neighbors of 1: " + graph.getNeighbors(1));
        System.out.println("Degree of 3: " + graph.getDegree(3));
        System.out.println("Is connected: " + graph.isConnected());
        System.out.println("Shortest path from 0 to 3: " + graph.findShortestPath(0, 3));
        System.out.print("BFS traversal: ");
        graph.traverseGraphBFS();
        System.out.print("DFS traversal: ");
        graph.traverseGraphDFS();
        System.out.println("Has cycle: " + graph.hasCycle());
    }
}
