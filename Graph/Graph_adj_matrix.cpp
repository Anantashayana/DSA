#include <iostream>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

class Graph {
    int numVertices;
    vector<vector<int>> adjacencyMatrix;

public:
    Graph(int numVertices) {
        this->numVertices = numVertices;
        adjacencyMatrix.resize(numVertices, vector<int>(numVertices, 0));
    }

    void addEdge(int source, int destination) {
        if (source < numVertices && destination < numVertices) {
            adjacencyMatrix[source][destination] = 1;
            adjacencyMatrix[destination][source] = 1;
        }
    }

    void removeEdge(int source, int destination) {
        if (source < numVertices && destination < numVertices) {
            adjacencyMatrix[source][destination] = 0;
            adjacencyMatrix[destination][source] = 0;
        }
    }

    void addVertex() {
        numVertices++;
        adjacencyMatrix.resize(numVertices, vector<int>(numVertices, 0));
        for (int i = 0; i < numVertices - 1; i++) {
            adjacencyMatrix[i].resize(numVertices);
        }
    }

    void removeVertex(int vertex) {
        if (vertex < numVertices) {
            adjacencyMatrix.erase(adjacencyMatrix.begin() + vertex);
            for (auto& row : adjacencyMatrix) {
                row.erase(row.begin() + vertex);
            }
            numVertices--;
        }
    }

    void breadthFirstSearch(int startVertex) {
        if (startVertex >= numVertices) {
            return;
        }

        vector<bool> visited(numVertices, false);
        queue<int> q;
        q.push(startVertex);
        visited[startVertex] = true;

        while (!q.empty()) {
            int currentVertex = q.front();
            q.pop();
            cout << currentVertex << " ";

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (adjacencyMatrix[currentVertex][neighbor] == 1 && !visited[neighbor]) {
                    q.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        cout << endl;
    }

    void depthFirstSearch(int startVertex) {
        if (startVertex >= numVertices) {
            return;
        }

        vector<bool> visited(numVertices, false);
        stack<int> s;
        s.push(startVertex);
        visited[startVertex] = true;

        while (!s.empty()) {
            int currentVertex = s.top();
            s.pop();
            cout << currentVertex << " ";

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (adjacencyMatrix[currentVertex][neighbor] == 1 && !visited[neighbor]) {
                    s.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        cout << endl;
    }

    void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                cout << adjacencyMatrix[i][j] << " ";
            }
            cout << endl;
        }
    }
};

int main() {
    Graph graph(5);

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
    cout << "Graph:" << endl;
    graph.printGraph();

    // Performing BFS starting from vertex 0
    cout << "BFS starting from vertex 0: ";
    graph.breadthFirstSearch(0);

    // Performing DFS starting from vertex 0
    cout << "DFS starting from vertex 0: ";
    graph.depthFirstSearch(0);

    return 0;
}
