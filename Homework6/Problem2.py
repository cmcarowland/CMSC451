from Matrix1 import matrix
import argparse

INF = float("inf")


def print_matrix(label, m):
    print(label)
    row_labels = [chr(ord("a") + i) for i in range(len(m))]
    print("".join(f"{h:^4}" for h in [""] + row_labels))
    for label_, row in zip(row_labels, m):
        cells = "".join(f"{('inf' if x == INF else x):^4}" for x in row)
        print(f"{label_:^4}{cells}")
    print()


parser = argparse.ArgumentParser()
parser.add_argument("-v", "--verbose", action="store_true")
args = parser.parse_args()

n = len(matrix[0])

# Build the weight matrix: 0 on the diagonal, edge weight 1 where an edge
# exists, infinity where there is no edge.
local_matrix = [
    [0 if i == j else (1 if matrix[i][j] else INF) for j in range(n)]
    for i in range(n)
]

print_matrix("Base Matrix", local_matrix)

# Floyd's algorithm for shortest path lengths
for k in range(n):
    for i in range(n):
        for j in range(n):
            local_matrix[i][j] = min(
                local_matrix[i][j], local_matrix[i][k] + local_matrix[k][j]
            )

    if args.verbose:
        print_matrix(f"After pass k={k}:", local_matrix)

print_matrix("Floyd’s:", local_matrix)