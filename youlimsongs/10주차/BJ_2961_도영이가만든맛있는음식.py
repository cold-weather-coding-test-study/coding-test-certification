import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
from itertools import combinations
input = sys.stdin.readline
ingre = []
n = int(input())
for _ in range(n):
    s, b = map(int, input().split())
    ingre.append((s,b))

res = 1e10

for cnt in range(1, n+1):
    for cmb in combinations(ingre, cnt):
        s_mul = 1
        b_sum = 0
        for s, b in cmb:
            s_mul *= s
            b_sum += b
        res = min(res, abs(s_mul-b_sum))

print(res)
