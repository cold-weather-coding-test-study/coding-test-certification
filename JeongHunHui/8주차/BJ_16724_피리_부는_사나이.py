# 15:59
# 사이클 찾기
# 1. 방향 따라 탐색 시작
# 2. 탐색한 경로를 집합으로 저장
# 3. 그러다가 이미 갔던 곳이 포함되면 사이클
# 4. 갔던 곳 전부 방문 처리
n, m = map(int, input().split())
graph = [[c for c in input()] for _ in range(n)]
is_visited = [[False] * m for _ in range(n)]
dir_dict = {'U': (0, -1), 'D': (0, 1), 'L': (-1, 0), 'R': (1, 0)}

def search(x, y):
  visited_pos = set()
  is_unique = True
  while (x, y) not in visited_pos:
    if is_visited[y][x]:
      is_unique = False
      break
    visited_pos.add((x, y))
    dx, dy = dir_dict[graph[y][x]]
    x += dx
    y += dy
  for nx, ny in visited_pos:
    is_visited[ny][nx] = True
  return is_unique

answer = 0
for x in range(m):
  for y in range(n):
    if not is_visited[y][x]:
      is_unique = search(x, y)
      if is_unique:
        answer += 1
print(answer)