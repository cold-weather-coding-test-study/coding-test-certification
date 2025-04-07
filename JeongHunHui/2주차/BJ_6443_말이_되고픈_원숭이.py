# 15:05
from collections import deque
horse_count = int(input())
w, h = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(h)]
dirs = [(1,0),(-1,0),(0,1),(0,-1)]
horse_dirs = [(-2,-1),(-2,1),(-1,-2),(-1,2),(1,-2),(1,2),(2,-1),(2,1)]

def is_in(x, y):
  return 0 <= x < w and 0 <= y < h

visit_infos = [[[0]*(horse_count+1) for _ in range(w)] for _ in range(h)]

dq = deque([(0,0,horse_count)])
answer = -1
while dq:
  x, y, count = dq.popleft()
  if x == w-1 and y == h-1:
    answer = visit_infos[y][x][count]
    break
  if count > 0:
    for dx, dy in horse_dirs:
      nx, ny = x+dx, y+dy
      if is_in(nx, ny) and graph[ny][nx] == 0 and visit_infos[ny][nx][count-1] == 0:
        visit_infos[ny][nx][count-1] = visit_infos[y][x][count] + 1
        dq.append((nx,ny,count-1))
  for dx, dy in dirs:
    nx, ny = x+dx, y+dy
    if is_in(nx, ny) and graph[ny][nx] == 0 and visit_infos[ny][nx][count] == 0:
      visit_infos[ny][nx][count] = visit_infos[y][x][count] + 1
      dq.append((nx,ny,count))

print(answer)