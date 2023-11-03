#include <iostream>
#include <vector>
#include <queue>
#include <stack>
#include <algorithm>

using namespace std;

class Graph {
    int numVertices;
    vector<vector<int>> adjacencyList;

public:
    Graph(int numVertices) {
        this->numVertices = numVertices;
        adjacencyList.resize(numVertices);
    }

    void addVertex() {
        numVertices++;
        adjacencyList.resize(numVertices);
    }

    void removeVertex(int vertex) {
        if (vertex < numVertices) {
            adjacencyList.erase(adjacencyList.begin() + vertex);
            for (auto& list : adjacencyList) {
                for (auto it = list.begin(); it != list.end(); ++it) {
                    if (*it == vertex) {
                        list.erase(it);
                        break;
                    }
                    else if (*it > vertex) {
                        (*it)--;
                    }
                }
            }
            numVertices--;
        }
    }

    void addEdge(int source, int destination) {
        if (source < numVertices && destination < numVertices) {
            adjacencyList[source].push_back(destination);
            adjacencyList[destination].push_back(source);
        }
    }

    void removeEdge(int source, int destination) {
        if (source < numVertices && destination < numVertices) {
            for (auto it = adjacencyList[source].begin(); it != adjacencyList[source].end(); ++it) {
                if (*it == destination) {
                    adjacencyList[source].erase(it);
                    break;
                }
            }

            for (auto it = adjacencyList[destination].begin(); it != adjacencyList[destination].end(); ++it) {
                if (*it == source) {
                    adjacencyList[destination].erase(it);
                    break;
                }
            }
        }
    }

    bool isAdjacent(int vertex1, int vertex2) {
        if (vertex1 < numVertices && vertex2 < numVertices) {
            for (int neighbor : adjacencyList[vertex1]) {
                if (neighbor == vertex2) {
                    return true;
                }
            }
        }
        return false;
    }

    vector<int> getNeighbors(int vertex) {
        if (vertex < numVertices) {
            return adjacencyList[vertex];
        }
        return {};
    }

    int getDegree(int vertex) {
        if (vertex < numVertices) {
            return adjacencyList[vertex].size();
        }
        return 0;
    }

    bool isConnected() {
        if (numVertices == 0) {
            return true;
        }

        vector<bool> visited(numVertices, false);
        queue<int> q;
        q.push(0);
        visited[0] = true;

        while (!q.empty()) {
            int currentVertex = q.front();
            q.pop();

            for (int neighbor : adjacencyList[currentVertex]) {
                if (!visited[neighbor]) {
                    q.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        for (bool v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    vector<int> findShortestPath(int source, int destination) {
        if (source < numVertices && destination < numVertices) {
            vector<bool> visited(numVertices, false);
            vector<int> previous(numVertices, -1);
            queue<int> q;
            q.push(source);
            visited[source] = true;

            while (!q.empty()) {
                int currentVertex = q.front();
                q.pop();

                if (currentVertex == destination) {
                    break;
                }

                for (int neighbor : adjacencyList[currentVertex]) {
                    if (!visited[neighbor]) {
                        q.push(neighbor);
                        visited[neighbor] = true;
                        previous[neighbor] = currentVertex;
                    }
                }
            }

            if (previous[destination] == -1) {
                return {};
            }

            vector<int> shortestPath;
            int currentVertex = destination;
            while (currentVertex != -1) {
                shortestPath.push_back(currentVertex);
                currentVertex = previous[currentVertex];
            }
            reverse(shortestPath.begin(), shortestPath.end());
            return shortestPath;
        }
        return {};
    }

    void depthFirstSearch(int startVertex) {
        if (startVertex < numVertices) {
            vector<bool> visited(numVertices, false);
            stack<int> s;
            s.push(startVertex);
            visited[startVertex] = true;

            while (!s.empty()) {
                int currentVertex = s.top();
                s.pop();
                cout << currentVertex << " ";

                for (int neighbor : adjacencyList[currentVertex]) {
                    if (!visited[neighbor]) {
                        s.push(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
            cout << endl;
        }
    }

    void breadthFirstSearch(int startVertex) {
        if (startVertex < numVertices) {
            vector<bool> visited(numVertices, false);
            queue<int> q;
            q.push(startVertex);
            visited[startVertex] = true;

            while (!q.empty()) {
                int currentVertex = q.front();
                q.pop();
                cout << currentVertex << " ";

                for (int neighbor : adjacencyList[currentVertex]) {
                    if (!visited[neighbor]) {
                        q.push(neighbor);
                        visited[neighbor] = true;
                    }
                }
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

    // Testing operations
    cout << "Adjacent(1, 3): " << graph.isAdjacent(1, 3) << endl;
    cout << "Adjacent(0, 2): " << graph.isAdjacent(0, 2) << endl;

    cout << "Neighbors of vertex 1: ";
    vector<int> neighbors = graph.getNeighbors(1);
    for (int neighbor : neighbors) {
        cout << neighbor << " ";
    }
    cout << endl;

    cout << "Degree of vertex 1: " << graph.getDegree(1) << endl;

    cout << "Is the graph connected? " << graph.isConnected() << endl;

    cout << "Shortest path from vertex 0 to vertex 3: ";
    vector<int> shortestPath = graph.findShortestPath(0, 3);
    for (int vertex : shortestPath) {
        cout << vertex << " ";
    }
    cout << endl;

    cout << "DFS starting from vertex 0: ";
    graph.depthFirstSearch(0);

    cout << "BFS starting from vertex 0: ";
    graph.breadthFirstSearch(0);

    return 0;
}
