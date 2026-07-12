from collections import deque

from Graph import *
visited = []
tree_edges = []
forward_edges = []
back_edges = []
cross_edges = []

timing = 0

def is_forward_edge(edge):
    if edge.start.pre < edge.end.pre < edge.end.post < edge.start.post:
        return True

    return False

def is_back_edge(edge):
    if edge.end.pre < edge.start.pre < edge.start.post < edge.end.post:
        return True

    return False

def is_cross_edge(edge):
    if edge.end.pre < edge.end.post < edge.start.pre < edge.start.post:
        return True

    return False

def dfs(node):
    global timing

    visited.append(node)
    if node.pre is None:
        node.pre = timing
        timing += 1

    for edge in node.edges:
        if edge.end not in visited:
            tree_edges.append(edge)
            dfs(edge.end)
    
    if node.post is None:
        node.post = timing
        timing += 1  

def dfs_forrest(node, depth=0):
    visited.append(node)
    if depth == 0:
        out = f'({node})\n  └> '
    else:
        out = f'{node}'
        
    for edge in node.edges:
        if edge.end not in visited:
            if depth > 0:
                out += ' -> '
            out += dfs_forrest(edge.end, depth + 1)

    return out

def main():
    global visited

    graph = Graph.load('Graph.json')
    dfs(graph.root)
    ordered_nodes = graph.order_nodes()
    transposed = graph.transpose()

    visited = []
    for node in ordered_nodes:
        if transposed.nodes[node] in visited:
            continue

        print(dfs_forrest(transposed.nodes[node]))

if __name__ == "__main__":
    main()