# 15:56
from collections import deque

graphs = []
while True:
  w, h = map(int, input().split())
  if w == 0 and h == 0:
    break
  graphs.append([list(map(int, input().split())) for _ in range(h)])

dirs = [(1,0),(-1,0),(0,1),(0,-1),(1,-1),(1,1),(-1,-1),(-1,1)]

for graph in graphs:
  w, h = len(graph[0]), len(graph)
  is_visited = [[False]*w for _ in range(h)]
  answer = 0
  for y in range(h):
    for x in range(w):
      if graph[y][x] != 1 or is_visited[y][x]:
        continue
      answer += 1
      is_visited[y][x] = True
      dq = deque([(x, y)])
      while dq:
        tx, ty = dq.popleft()
        for dx, dy in dirs:
          nx = dx + tx
          ny = dy + ty
          if 0 <= nx < w and 0 <= ny < h:
            if not is_visited[ny][nx] and graph[ny][nx] == 1:
              is_visited[ny][nx] = True
              dq.append((nx, ny))
  print(answer)