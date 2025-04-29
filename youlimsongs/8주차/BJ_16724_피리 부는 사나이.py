#그래프 탐색하면서 아래 옆 위로 이동하는 함수
#한 지점에서 잡으면서 그래프 탐색 -> 안전존 1개
#방문안한 지점 잡아서 또 탐색 -> 안전존 1개

n,m = map(int, input().split())
graph = []
for _ in range(n):
    graph.append(list(input()))

visited = [[False]*m for _ in range(n)]
cnt = 0

def check_dir(i,j):
    match graph[i][j]:
        case 'U':
            return (i-1, j)
        case 'D':
            return (i+1, j)
        case 'R':
            return (i, j+1)
        case 'L':
            return (i, j-1)

def dfs(y, x):
    visited[y][x] = 1
    ny, nx = check_dir(y, x)

    if visited[ny][nx] == 0:
        dfs(ny, nx)
    elif visited[ny][nx] == 1:  #사이클 완성
        global cnt
        cnt += 1

    visited[y][x] = 2  # 방문 마침

for i in range(n):
    for j in range(m):
        if visited[i][j] == 0:
            dfs(i,j)

print(cnt)
