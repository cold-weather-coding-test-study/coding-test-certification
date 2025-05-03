#  https://www.acmicpc.net/problem/2411
import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

n,m,a,b = map(int, input().split())

graph = [['']*(m+1) for _ in range(n+1)]
stars = [[1, 1]]

for _ in range(a):
    y, x =  map(int, input().split())
    graph[y][x] = 'O'
    stars.append([y,x])

for _ in range(b):
    y, x =  map(int, input().split())
    graph[y][x] = 'X'

stars.sort()
stars.append([n, m])

dp = [[0]*(m+1) for _ in range(n+1)]
dp[1][1] = 1

#시작점, 끝점, 별 = 지나야하는 위치를 기준으로 탐색
for i in range(1, len(stars)):
    sy, sx = stars[i-1]
    ey, ex = stars[i]

    for y in range(sy, ey+1):
        for x in range(sx, ex+1):
            if graph[y][x] == 'X' or (y == sy and x == sx):
                continue
            if y > sy:
                dp[y][x] += dp[y - 1][x]
            if x > sx:
                dp[y][x] += dp[y][x - 1]

print(dp[n][m])