/*
subalgo makeSet(v: a node):
    v.parent = v <- make a new tree rooted at v
 
subalgo findSet(v: a node):
    if v.parent == v:
        return v
    return findSet(v.parent)
subalgo unionSet(v, u: nodes):
    vRoot = findSet(v)
    uRoot = findSet(u)
    uRoot.parent = vRoot
algorithm kruskalMST(G: a graph):
    sort Gs edges by their value
    for each node n in G:
        makeSet(n)
    for each edge e in G:
        if findSet(e.first) != findSet(e.second):
            unionSet(e.first, e.second)
*/
/*
algorithm kruskalMST(G: a graph)
    sort Gs edges by their value
    MST = an empty graph
    for each edge e in G:
        if adding e to MST does not create a cycle:
            add e to MST
*/


import java.util.Arrays;
import java.util.Comparator;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Subset {
    int parent, rank;

    public Subset(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }
}

class Graph {
    int V, E;
    Edge[] edges;

    public Graph(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new Edge[E];
    }
}

public class kruskal {
    private static int findParent(Subset[] subsets, int i) {
        // Find the parent of a subset (with path compression)
        if (subsets[i].parent != i) {
            subsets[i].parent = findParent(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    private static void unionSubsets(Subset[] subsets, int x, int y) {
        // Union of two subsets (by rank)
        int xroot = findParent(subsets, x);
        int yroot = findParent(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    private static boolean compareEdges(Edge a, Edge b) {
        // Comparator function for sorting edges by weight
        return a.weight < b.weight;
    }

    private static void findMinimumSpanningTree(Graph graph) {
        int V = graph.V;
        Edge[] result = new Edge[V];  // Stores the result minimum spanning tree
        Subset[] subsets = new Subset[V];

        // Initialize subsets
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Sort edges in ascending order of weight
        Edge[] edges = graph.edges;
        Arrays.sort(edges, Comparator.comparingInt(a -> a.weight));

        int e = 0;  // Index for the result array
        int i = 0;  // Index for the sorted edges array

        while (e < V - 1 && i < graph.E) {
            Edge nextEdge = edges[i++];

            int x = findParent(subsets, nextEdge.src);
            int y = findParent(subsets, nextEdge.dest);

            // If including the next edge doesn't create a cycle, add it to the result
            if (x != y) {
                result[e++] = nextEdge;
                unionSubsets(subsets, x, y);
            }
        }

        // Print the minimum spanning tree
        System.out.println("Minimum Spanning Tree:");
        for (int j = 0; j < e; j++) {
            System.out.println(result[j].src + " -- " + result[j].dest + " : " + result[j].weight);
        }
    }

    public static void main(String[] args) {
        // Create a graph
        int V = 4; // Number of vertices
        int E = 5; // Number of edges
        Graph graph = new Graph(V, E);

        // Add edges to the graph
        graph.edges[0] = new Edge(0, 1, 10);
        graph.edges[1] = new Edge(0, 2, 6);
        graph.edges[2] = new Edge(0, 3, 5);
        graph.edges[3] = new Edge(1, 3, 15);
        graph.edges[4] = new Edge(2, 3, 4);

        // Find and print the minimum spanning tree
        findMinimumSpanningTree(graph);
    }
}
