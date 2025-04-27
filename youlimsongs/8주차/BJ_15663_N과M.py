# https://www.acmicpc.net/problem/15663

n, m = map(int, input().split())
nums = list(map(int, input().split()))
nums.sort()

visited = [False] * n
answer = []

def dfs(arr):
    if len(arr) == m:
        answer.append(arr)
        return
    
    prev = -1
    for i in range(n):
        if visited[i]:
            continue
        if prev == nums[i]:
            continue
        
        visited[i] = True
        dfs(arr+[nums[i]])
        visited[i] = False
        prev = nums[i]

dfs([])

for seq in answer:
    print(*seq)