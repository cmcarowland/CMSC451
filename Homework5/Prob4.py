from Graph import *

import argparse

def dijkstra(graph, start_name, verbose=False):
    dist = {name: float("inf") for name in graph.nodes}
    via = {name: None for name in graph.nodes}
    dist[start_name] = 0

    visited = set()
    spt_edges = []

    if verbose:
        print(f"Start at {start_name}\n")

    while len(visited) < len(graph.nodes):
        fringe = {name: d for name, d in dist.items() if name not in visited}
        current_name = min(fringe, key=fringe.get)
        current = graph.nodes[current_name]
        visited.add(current_name)

        if via[current_name] is not None:
            spt_edges.append(via[current_name])

        if verbose:
            print(f"Add {current_name} to tree"
                  + (f" via {via[current_name]}" if via[current_name] else "")
                  + f" (distance {dist[current_name]})")
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
            new_distance = dist[current_name] + edge.cost
            if new_distance < dist[neighbor]:
                old = dist[neighbor]
                dist[neighbor] = new_distance
                via[neighbor] = edge
                if verbose:
                    old_str = old if old != float('inf') else 'inf'
                    print(f"    update {neighbor}: {old_str} -> {new_distance} (via {edge})")

        if verbose:
            print()

    return dist, spt_edges

parser = argparse.ArgumentParser()
parser.add_argument("file_name")
parser.add_argument("-v", "--verbose", action="store_true")
args = parser.parse_args()

graph = Graph.load(args.file_name)
dist, spt_edges = dijkstra(graph, start_name='A', verbose=args.verbose)

print("Shortest path tree edges in order added:")
for edge in spt_edges:
    print(f"  {edge}")
print()

pred = {edge.end.name: edge.start.name for edge in spt_edges}

def reconstruct_path(name):
    path = [name]
    while path[-1] in pred:
        path.append(pred[path[-1]])
    return list(reversed(path))

print("Shortest paths from A:")
for name in sorted(dist):
    path = reconstruct_path(name)
    print(f"  {name}: {' -> '.join(path)} (distance {dist[name]})")
