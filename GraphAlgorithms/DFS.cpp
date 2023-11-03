#include <iostream>
#include <vector>
#include <stack>

using namespace std;

// Function to perform DFS traversal
void dfsTraversal(vector<vector<int>>& graph, int start, vector<bool>& visited) {
    stack<int> stk;
    stk.push(start);

    while (!stk.empty()) {
        int curr = stk.top();
        stk.pop();

        if (visited[curr]) {
            continue;
        }

        visited[curr] = true;
        cout << curr << " "; // Process the current vertex

        for (int neighbor : graph[curr]) {
            if (!visited[neighbor]) {
                stk.push(neighbor);
            }
        }
    }
}

// Function to initialize and call DFS traversal
void dfs(vector<vector<int>>& graph, int start) {
    int n = graph.size(); // Number of vertices in the graph
    vector<bool> visited(n, false); // Array to track visited vertices

    cout << "DFS traversal: ";
    dfsTraversal(graph, start, visited);
}

int main() {
    int n; // Number of vertices
    int m; // Number of edges

    n = 6; // Number of vertices in the graph
    m = 7; // Number of edges in the graph

    vector<vector<int>> graph(n); // Adjacency list

    // Define the edges of the graph
    graph[0] = {1, 2};
    graph[1] = {0, 3, 4};
    graph[2] = {0, 4};
    graph[3] = {1, 4, 5};
    graph[4] = {1, 2, 3, 5};
    graph[5] = {3, 4};

    int startVertex = 0; // Starting vertex for DFS

    dfs(graph, startVertex);

    return 0;
}
