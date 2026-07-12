import json

class Node:
    def __init__(self, name):
        self.name = name
        self.edges = []

    def __repr__(self):
        out = self.name + '\n'
        for edge in self.edges:
            out += f'└> {edge.end.name}\n'
        return out

class Edge:
    def __init__(self, start : Node, end : Node, directed : bool):
        self.start = start
        self.end = end
        self.directed = directed

class Graph:
    def __init__(self, root : Node | None = None):
        self.root = root
        self.nodes = {}
    
    @staticmethod
    def load(fileName : str):
        with open(fileName, 'r') as iFile:
            data = json.load(iFile)

        graph = Graph()
        for node in data:
            graph.nodes[node] = Node(node)

        for node, edges in data.items():
            for edge in edges:
                graph.nodes[node].edges.append(Edge(graph.nodes[node], graph.nodes[edge], True))
                
            graph.nodes[node].edges.sort(key=lambda e: e.end.name)

        graph.root = graph.nodes[min(graph.nodes)]
        return graph

    def __repr__(self):
        out = ""
        for node in self.nodes.values():
            out += str(node)
            out += '\n'

        return out