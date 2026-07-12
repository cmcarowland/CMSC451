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
    node.pre = timing
    timing += 1

    for edge in node.edges:
        if edge.end not in visited:
            tree_edges.append(edge)
            dfs(edge.end)
    
    node.post = timing
    timing += 1  

def kahns(queue):
    order = []
    while len(queue) > 0:
        currentNode = queue.popleft()
        order.append(currentNode)
        for edge in currentNode.edges:
            edge.end.in_degree -= 1
            if edge.end.in_degree == 0:
                queue.append(edge.end)

    return order

def main():
    global visited

    graph = Graph.load('Graph_No_Back.json')
    dfs(graph.root)
    ordered_nodes = graph.order_nodes()
    print(ordered_nodes)
    graph.calculate_in_degree()

    queue = deque()
    for node in sorted(graph.nodes.values(), key=lambda n: n.name):
        print(f'{node.name} Timings pre/post {node.pre}/{node.post}')
        if node.in_degree == 0:
            queue.append(node)

    print(kahns(queue))
    

if __name__ == "__main__":
    main()