from collections import defaultdict

class GraphExplorer:
    def __init__(self, total_nodes):
        self.total_nodes = total_nodes
        self.connections = defaultdict(list)

    def link_nodes(self, source, target):
        self.connections[source].append(target)

    def _visit(self, node, seen):
        seen[node] = True
        print(f"Visited node: {node}")

        for neighbor in self.connections[node]:
            if not seen[neighbor]:
                self._visit(neighbor, seen)

    def begin_dfs(self, start_node):
        seen = [False] * self.total_nodes
        print(f"Starting DFS from node {start_node}:")
        self._visit(start_node, seen)

# Create and test the graph
network = GraphExplorer(9)
network.link_nodes(0, 2)
network.link_nodes(0, 4)
network.link_nodes(1, 0)
network.link_nodes(1, 5)
network.link_nodes(2, 3)
network.link_nodes(3, 6)
network.link_nodes(4, 5)
network.link_nodes(5, 7)
network.link_nodes(6, 8)
network.link_nodes(7, 8)

network.begin_dfs(1)
