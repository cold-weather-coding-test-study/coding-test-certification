# https://www.acmicpc.net/problem/15664
# https://www.acmicpc.net/problem/15666
import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

n,m = map(int, input().split())
nums = list(map(int, input().split()))
nums.sort()

def backtracking(arr, idx):
    if len(arr)==m:
        print(*arr)
        return
    
    prev = -1

    for i in range(idx, n):
        if nums[i] == prev:
            continue

        backtracking(arr+[nums[i]], i)

        prev = nums[i]

backtracking([], 0)