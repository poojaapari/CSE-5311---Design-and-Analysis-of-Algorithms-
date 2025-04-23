class Link:
    def __init__(self, a, b, cost):
        self.a = a
        self.b = b
        self.cost = cost

class NodeGroup:
    def __init__(self, leader, level):
        self.leader = leader
        self.level = level

def locate(groups, node):
    if groups[node].leader != node:
        groups[node].leader = locate(groups, groups[node].leader)
    return groups[node].leader

def connect(groups, u, v):
    u_root = locate(groups, u)
    v_root = locate(groups, v)

    if groups[u_root].level < groups[v_root].level:
        groups[u_root].leader = v_root
    elif groups[u_root].level > groups[v_root].level:
        groups[v_root].leader = u_root
    else:
        groups[v_root].leader = u_root
        groups[u_root].level += 1

def compute_min_spanning_tree(total_nodes, connection_list):
    connection_list.sort(key=lambda link: link.cost)
    groups = [NodeGroup(i, 0) for i in range(total_nodes)]

    chosen = []
    edge_index = 0
    count = 0

    while count < total_nodes - 1:
        current = connection_list[edge_index]
        edge_index += 1

        root_a = locate(groups, current.a)
        root_b = locate(groups, current.b)

        if root_a != root_b:
            chosen.append(current)
            connect(groups, root_a, root_b)
            count += 1

    total_cost = 0
    print("Edges selected for MST (node1 - node2 : weight):")
    for link in chosen:
        print(f"{link.a} - {link.b} : {link.cost}")
        total_cost += link.cost
    print("Total minimum cost:", total_cost)

# Example input with different structure
nodes = 7
connections = [
    Link(0, 1, 2),
    Link(0, 3, 1),
    Link(1, 2, 3),
    Link(1, 3, 3),
    Link(2, 4, 5),
    Link(3, 4, 4),
    Link(2, 5, 7),
    Link(4, 5, 6),
    Link(4, 6, 8),
    Link(5, 6, 9)
]

compute_min_spanning_tree(nodes, connections)
