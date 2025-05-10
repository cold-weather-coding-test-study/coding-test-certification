# https://www.acmicpc.net/problem/14225

import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

n = int(input())
nums = list(map(int, input().split(' ')))
comb = set()

def backtracking(res, idx):
    if idx==n:
        comb.add(res)
        return
    
    for i in range(idx, n):
        backtracking(res + nums[i], i+1)

backtracking(0, 0)

for i in range(1, 2000001):
    if i not in comb:
        print(i)
        sys.exit()