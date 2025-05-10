# https://www.acmicpc.net/problem/9742

import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

for line in sys.stdin:
    line = line.strip()
    if not line:
        continue

    arr, idx = line.split()
    idx = int(idx)

    comb = []

    visited = [False]*len(arr)
    def backtracking(res):
        if len(res) == len(arr):
            comb.append(res)
            return
        
        for i in range(len(arr)): 
            if not visited[i]:
                visited[i] = True
                backtracking(res+arr[i])
                visited[i] = False

    backtracking('')

    if idx <= len(comb):
        print(f"{arr} {idx} = {comb[idx - 1]}")
    else:
        print(f"{arr} {idx} = No permutation")