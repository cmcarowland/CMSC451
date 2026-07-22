from Matrix1 import matrix
import argparse
import copy


def print_matrix(label, m):
    print(label)
    for row in m:
        print(" ".join(str(x) for x in row))
    print()


parser = argparse.ArgumentParser()
parser.add_argument("-v", "--verbose", action="store_true")
args = parser.parse_args()

local_matrix = copy.deepcopy(matrix)

print_matrix("Base Matrix", local_matrix)
n = len(matrix[0])

# Add reflexive relation: every vertex relates to itself
for i in range(n):
    local_matrix[i][i] = 1

if args.verbose:
    print_matrix("After reflexive closure:", local_matrix)

# Warshall's algorithm for transitive closure
for k in range(n):
    for i in range(n):
        for j in range(n):
            local_matrix[i][j] = local_matrix[i][j] or (local_matrix[i][k] and local_matrix[k][j])

    if args.verbose:
        print_matrix(f"After pass k={k}:", local_matrix)

print_matrix("Warshall’s:", local_matrix)