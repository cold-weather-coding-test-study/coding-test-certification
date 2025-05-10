import sys
from itertools import combinations

sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

V, P, L = map(int, input().split())
vil = list(map(int, input().split()))

min_total = float('inf')
best_comb = ()

for comb in combinations(vil, P):
    comb_set = set(comb)
    total_dis = 0

    for v in vil:
        if v in comb:
            continue

        min_dis = float('inf')
        for c in comb:
            d = abs(v-c)
            min_dis = min(min_dis, d, L-d)
        total_dis += min_dis
    
    if total_dis < min_total:
        min_total = total_dis
        best_comb = comb

print(min_total)
print(*best_comb)
