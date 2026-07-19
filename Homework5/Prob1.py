from Graph import *

import argparse

def prims_min(graph, start_name=None, verbose=False):
    start = graph.nodes[start_name] if start_name is not None else graph.root

    dist = {name: float('inf') for name in graph.nodes}
    via = {name: None for name in graph.nodes}
    dist[start.name] = 0

    visited = set()
    mst_edges = []
    total_cost = 0

    if verbose:
        print(f"Start at {start.name}\n")

    while len(visited) < len(graph.nodes):
        fringe = {name: d for name, d in dist.items() if name not in visited}
        current_name = min(fringe, key=fringe.get)
        current = graph.nodes[current_name]
        visited.add(current_name)

        if via[current_name] is not None:
            mst_edges.append(via[current_name])
            total_cost += dist[current_name]

        if verbose:
            print(f"Add {current_name} to tree"
                  + (f" via {via[current_name]}" if via[current_name] else "")
                  + f" (cost {dist[current_name]})")
            print(f"  Tree so far: {sorted(visited)}")
            remaining = {n: d for n, d in fringe.items() if n != current_name}
            table = ", ".join(
                f"{n}={d if d != float('inf') else 'inf'}"
                for n, d in sorted(remaining.items())
            )
            print(f"  Fringe distances: {table}")

        for edge in current.edges:
            neighbor = edge.end.name
            if neighbor in visited:
                continue
            if edge.cost < dist[neighbor]:
                old = dist[neighbor]
                dist[neighbor] = edge.cost
                via[neighbor] = edge
                if verbose:
                    old_str = old if old != float('inf') else 'inf'
                    print(f"    update {neighbor}: {old_str} -> {edge.cost} (via {edge})")

        if verbose:
            print()

    return mst_edges, total_cost

parser = argparse.ArgumentParser()
parser.add_argument("file_name")
parser.add_argument("-v", "--verbose", action="store_true")
args = parser.parse_args()

graph = Graph.load(args.file_name)
mst_edges, total_cost = prims_min(graph, start_name='F', verbose=args.verbose)

print("MST edges in order added:")
for edge in mst_edges:
    print(f"  {edge}")
print(f"Total cost: {total_cost}")
