from Graph import *

import argparse
from collections import defaultdict

def kruskals(graph, verbose=False):
    parent = {name: name for name in graph.nodes}

    def find(name):
        while parent[name] != name:
            parent[name] = parent[parent[name]]
            name = parent[name]
        return name

    def union(a, b):
        root_a, root_b = find(a), find(b)
        if root_a == root_b:
            return False
        parent[root_a] = root_b
        return True

    def equivalence_classes():
        groups = defaultdict(list)
        for name in graph.nodes:
            groups[find(name)].append(name)
        return [sorted(g) for g in sorted(groups.values(), key=lambda g: min(g))]

    edges = []
    seen = set()
    for node in graph.nodes.values():
        for edge in node.edges:
            key = tuple(sorted((edge.start.name, edge.end.name)))
            if key not in seen:
                seen.add(key)
                edges.append(edge)
    edges.sort(key=lambda e: (e.cost, e.start.name, e.end.name))

    queue = list(edges)
    mst_edges = []
    total_cost = 0

    if verbose:
        print("Priority queue:", [f"{e.start.name}-{e.end.name} ({e.cost})" for e in queue])
        print("Initial equivalence relation:", equivalence_classes())
        print()

    step = 1
    while queue:
        edge = queue.pop(0)
        name = f"{edge.start.name}-{edge.end.name} ({edge.cost})"

        if verbose:
            print(f"Step {step}: remove {name} from priority queue")

        if union(edge.start.name, edge.end.name):
            mst_edges.append(edge)
            total_cost += edge.cost
            if verbose:
                print(f"  {edge.start.name} and {edge.end.name} are in different sets -> add {name} to MST")
                print(f"  Equivalence relation: {equivalence_classes()}")
        else:
            if verbose:
                print(f"  {edge.start.name} and {edge.end.name} are already in the same set -> discard {name}")
                print(f"  Equivalence relation (unchanged): {equivalence_classes()}")

        if verbose:
            print()
        step += 1

    return mst_edges, total_cost

parser = argparse.ArgumentParser()
parser.add_argument("file_name")
parser.add_argument("-v", "--verbose", action="store_true")
args = parser.parse_args()

graph = Graph.load(args.file_name)
mst_edges, total_cost = kruskals(graph, verbose=args.verbose)

print("MST edges in order added:")
for edge in mst_edges:
    print(f"  {edge}")
print(f"Total cost: {total_cost}")
