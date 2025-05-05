import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

n,m = map(int, input().split())
nums = list(map(int, input().split()))
nums.sort()

visited = [False]*n
# 같은 level에서 중복 수 방지
prev = -1
def dfs(idx, arr):
    if len(arr) == m:
        print(*arr)
        return
    
    prev = -1
    for i in range(idx+1, n):
        if not visited[i] and nums[i]!= prev:
            visited[i] = True

            dfs(i, arr+[nums[i]])

            visited[i]=False
            prev = nums[i] #같은 level에서 전 노드 값을 기억(n번째 자리수에 중복 수가 오는 것을 방지)

dfs(-1, [])