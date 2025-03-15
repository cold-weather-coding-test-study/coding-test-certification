# 문제 풀이 ...
# 이거 bfs or dfs로 탐색해서 그림이 몇 개인지 구한다
import sys
sys.setrecursionlimit(10**6)

n, m = map(int, input().split())

draw_paper = [list(map(int, input().split())) for _ in range(n)]

def dfs(x, y):
    if x < 0 or x >= n or y < 0 or y >= m:
        return 0
    if draw_paper[x][y] == 0:
        return 0

    # 현재 셀 방문 처리 및 넓이 1추가
    draw_paper[x][y] = 0
    count = 1
    count += dfs(x-1, y)
    count += dfs(x+1, y)
    count += dfs(x, y-1)
    count += dfs(x, y+1)
    return count

picture_count = 0
max_area = 0

for i in range(n):
    for j in range(m):
        if draw_paper[i][j] == 1:
            picture_count += 1
            area = dfs(i, j)
            max_area = max(max_area, area)

print(picture_count)
print(max_area)


