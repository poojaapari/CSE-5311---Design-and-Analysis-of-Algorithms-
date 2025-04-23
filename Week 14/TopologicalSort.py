from collections import defaultdict

class TopologicalSort:
    def __init__(self, total_vertices):
        self.V = total_vertices
        self.graph = defaultdict(list)

    def add_connection(self, from_node, to_node):
        self.graph[from_node].append(to_node)

    def _depth_first_topo(self, node, visited, output):
        visited[node] = True
        for adjacent in self.graph[node]:
            if not visited[adjacent]:
                self._depth_first_topo(adjacent, visited, output)
        output.append(node)

    def run_sort(self):
        visited = [False] * self.V
        result = []

        for vertex in range(self.V):
            if not visited[vertex]:
                self._depth_first_topo(vertex, visited, result)

        print("Topological Sort Result:", end=" ")
        while result:
            print(result.pop(), end=" ")

# --- Test Code with Modified Input ---
ts = TopologicalSort(7)
edges = [
    (6, 3),
    (6, 1),
    (5, 0),
    (5, 2),
    (3, 4),
    (4, 1),
    (2, 1)
]

for u, v in edges:
    ts.add_connection(u, v)

ts.run_sort()
