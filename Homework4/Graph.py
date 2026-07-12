import json

class Node:
    def __init__(self, name):
        self.name = name
        self.edges = []
        self.pre = None
        self.post = None
        self.in_degree = 0

    def __repr__(self):
        out = self.name
        # for edge in self.edges:
        #     out += f'└> {edge.end.name}\n'
        return out

class Edge:
    def __init__(self, start : Node, end : Node, directed : bool):
        self.start = start
        self.end = end
        self.directed = directed

    def __repr__(self):
        return f'{self.start.name} -> {self.end.name}'

class Graph:
    def __init__(self, root : Node | None = None):
        self.root = root
        self.nodes = {}

    def order_nodes(self):
        return sorted(self.nodes.values(), key=lambda node: node.post, reverse=True)
    
    def calculate_in_degree(self):
        for node in self.nodes.values():
            for edge in node.edges:
                edge.end.in_degree += 1
    
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