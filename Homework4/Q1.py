from collections import deque

from Graph import *

def bfs(graph):
    visited = set()
    tree_edges = []
    queue = deque()

    queue.append(graph.root)
    visited.add(graph.root)

    while len(queue) > 0:
        currentNode = queue.popleft()
        for edge in currentNode.edges:
            if edge.end not in visited:
                visited.add(edge.end)
                queue.append(edge.end)
                tree_edges.append((edge.start.name, edge.end.name))
    
    return tree_edges

def main():
    graph = Graph.load('Graph.json')
    print(bfs(graph))

if __name__ == "__main__":
    main()