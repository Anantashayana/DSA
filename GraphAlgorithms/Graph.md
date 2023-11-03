# General Terms
- A spanning tree is a subset of a graph that includes all the graph's vertices and some of the edges of the original graph, intending to have no cycles. The edges in a spanning tree are called "branch edges," while the edges not in the spanning tree are called "cycle edges".
-  A minimum spanning tree is a subset of a graph with the same number of vertices as the graph and edges equal to the number of vertices -1. It also has a minimal cost for the sum of all edge weights in a spanning tree. It is a way of finding the most economical way to connect a set of vertices.
-  Rank of a node



- DFS traversal is a general process of visiting and exploring all vertices in a graph, while DFS search is a specific application of DFS that focuses on finding a particular target or verifying its presence.

# Dijkstra's
- We can find shortest path using Breadth First Search (BFS) searching algorithm. This algorithm works fine, but the problem is, it assumes the cost of traversing each path is same, that means the cost of each edge is same. Dijkstra's algorithm helps us to find the shortest path where the cost of each path is not the same.
- Now, does Dijkstra's Algorithm work when there's a negative edge? If there's a negative cycle, then infinity loop will occur, as it will keep reducing the cost every time. Even if there is a negative edge, Dijkstra won't work, unless we return right after the target is popped. But then, it won't be a Dijkstra algorithm. We'll need Bellman–Ford algorithm for processing negative edge/cycle.
- In BFS, we didn't need to visit any node twice. We only checked if a node is visited or not. If it was not visited, we in queue and instead of updating it with visited, we relax or update the new edge.
pushed the node in queue, marked it as visited and incremented the distance by 1. In Dijkstra, we can push a node
- The complexity of BFS is O(log(V+E)) where V is the number of nodes and E is the number of edges. For Dijkstra,
the complexity is similar, but sorting of Priority Queue takes O(logV). So the total complexity is: O(Vlog(V)+E)

# Kruskal's  
Time Complexity: O(E*log(E))
Space Complexity: O(E)
- It is used to discover the shortest path between two points in a connected weighted graph(Undireceted).
-  Kruskal’s algorithm sorts all the edges in increasing order of their edge weights and keeps adding nodes to the tree only if the chosen edge does not form any cycle. Also, it picks the edge with a minimum cost at first and the edge with a maximum cost at last. Hence, you can say that the Kruskal algorithm makes a locally optimal choice, intending to find the global optimal solution. That is why it is called a Greedy Algorithm. 

### Algorithm
Step 1: Sort all edges in increasing order of their edge weights.
Step 2: Pick the smallest edge.
Step 3: Check if the new edge creates a cycle or loop in a spanning tree.
Step 4: If it doesn’t form the cycle, then include that edge in MST. Otherwise, discard it.
Step 5: Repeat from step 2 until it includes |V| - 1 edges in MST.

The applications of Kruskal's algorithm are -
- Kruskal's algorithm can be used to layout electrical wiring among cities.
- It can be used to lay down LAN connections.

## Union Find Algorithm
- The Find operation returns the set of elements to which the given element (argument) belongs, whereas the Union operation merges two disjoint sets.
- You need to divide the provided graph G(V, E) into three separate sets while building the Minimum Spanning Tree using Kruskal's approach. The first contains edge weight values, the second has a tree hierarchy for distinct nodes, and the third includes the rank of all nodes. By using Union and Find operations, it joins the distinct nodes which are treated as different trees themselves to formulate a minimum spanning tree. 