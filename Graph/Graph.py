from collections import defaultdict


class Graph:
    def __init__(self):
        self.graph = defaultdict(list)

    def add_vertex(self, vertex):
        self.graph[vertex]

    def remove_vertex(self, vertex):
        del self.graph[vertex]
        for vertices in self.graph.values():
            if vertex in vertices:
                vertices.remove(vertex)

    def add_edge(self, source, destination):
        self.graph[source].append(destination)
        self.graph[destination].append(source)

    def remove_edge(self, source, destination):
        if destination in self.graph[source]:
            self.graph[source].remove(destination)
        if source in self.graph[destination]:
            self.graph[destination].remove(source)

    def is_adjacent(self, vertex1, vertex2):
        return vertex2 in self.graph[vertex1]

    def get_neighbors(self, vertex):
        return self.graph[vertex]

    def get_degree(self, vertex):
        return len(self.graph[vertex])

    def is_connected(self):
        if not self.graph:
            return False

        visited = set()
        queue = [next(iter(self.graph.keys()))]
        while queue:
            vertex = queue.pop(0)
            if vertex not in visited:
                visited.add(vertex)
                queue.extend(self.graph[vertex])

        return len(visited) == len(self.graph)

    def find_shortest_path(self, source, destination):
        if source not in self.graph or destination not in self.graph:
            return []

        visited = set()
        queue = [[source]]
        while queue:
            path = queue.pop(0)
            current_vertex = path[-1]
            if current_vertex == destination:
                return path
            if current_vertex not in visited:
                visited.add(current_vertex)
                for neighbor in self.graph[current_vertex]:
                    new_path = list(path)
                    new_path.append(neighbor)
                    queue.append(new_path)

        return []

    def traverse_graph_bfs(self):
        if not self.graph:
            return

        visited = set()
        queue = [next(iter(self.graph.keys()))]
        while queue:
            vertex = queue.pop(0)
            if vertex not in visited:
                visited.add(vertex)
                print(vertex, end=" ")
                queue.extend(self.graph[vertex])

        print()

    def traverse_graph_dfs(self):
        if not self.graph:
            return

        visited = set()
        stack = [next(iter(self.graph.keys()))]
        while stack:
            vertex = stack.pop()
            if vertex not in visited:
                visited.add(vertex)
                print(vertex, end=" ")
                stack.extend(self.graph[vertex])

        print()

    def has_cycle_util(self, current_vertex, visited, parent):
        visited.add(current_vertex)
        for neighbor in self.graph[current_vertex]:
            if neighbor not in visited:
                if self.has_cycle_util(neighbor, visited, current_vertex):
                    return True
            elif neighbor != parent:
                return True

        return False

    def has_cycle(self):
        if not self.graph:
            return False

        visited = set()
        for vertex in self.graph:
            if vertex not in visited and self.has_cycle_util(vertex, visited, None):
                return True

        return False

    def print_graph(self):
        for vertex, neighbors in self.graph.items():
            print(f"{vertex}: {neighbors}")


# Example usage
graph = Graph()

# Adding vertices
graph.add_vertex(0)
graph.add_vertex(1)
graph.add_vertex(2)
graph.add_vertex(3)
graph.add_vertex(4)

# Adding edges
graph.add_edge(0, 1)
graph.add_edge(0, 4)
graph.add_edge(1, 2)
graph.add_edge(1, 3)
graph.add_edge(1, 4)
graph.add_edge(2, 3)
graph.add_edge(3, 4)

# Removing a vertex
graph.remove_vertex(2)

# Removing an edge
graph.remove_edge(0, 4)

# Printing the graph
print("Graph:")
graph.print_graph()

# Testing operations
print("Adjacent(1, 3):", graph.is_adjacent(1, 3))
print("Neighbors of 1:", graph.get_neighbors(1))
print("Degree of 3:", graph.get_degree(3))
print("Is connected:", graph.is_connected())
print("Shortest path from 0 to 3:", graph.find_shortest_path(0, 3))
print("BFS traversal:", end=" ")
graph.traverse_graph_bfs()
print("DFS traversal:", end=" ")
graph.traverse_graph_dfs()
print("Has cycle:", graph.has_cycle())
