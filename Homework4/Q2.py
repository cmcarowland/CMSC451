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

def classify_edges(node):
    visited.append(node)
    print(f'{node.name} Timings pre/post {node.pre}/{node.post}')
    for edge in node.edges:
        if edge not in tree_edges:
            if is_forward_edge(edge):
                forward_edges.append(edge)
            elif is_back_edge(edge):
                back_edges.append(edge)
            elif is_cross_edge(edge):
                cross_edges.append(edge)

        if edge.end not in visited:
            classify_edges(edge.end)

def main():
    global visited

    graph = Graph.load('Graph.json')
    dfs(graph.root)
    visited = []
    classify_edges(graph.root)

    print(f' Tree Edges   : {tree_edges}')
    print(f' Forward Edges: {forward_edges}')
    print(f' Back Edges   : {back_edges}')
    print(f' Cross Edges  : {cross_edges}')

if __name__ == "__main__":
    main()