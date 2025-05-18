# https://www.acmicpc.net/problem/1926
import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

def dfs(x, y):
    a[x][y] = 0
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
    w = 1
    q = list()
    q.append([x, y])
    while q:
        x, y = q.pop()
        for i in range(4):
            nx = dx[i] + x
            ny = dy[i] + y
            if 0 <= nx < n and 0 <= ny < m and a[nx][ny] == 1:
                    q.append([nx, ny])
                    a[nx][ny] = 0
                    w += 1
    return w


n, m = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]
cnt = 0
ans = 0
for i in range(n):
    for j in range(m):
        if a[i][j] == 1:
            cnt += 1
            ans = max(dfs(i, j), ans)

print(cnt)
print(ans)