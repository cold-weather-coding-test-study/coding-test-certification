# 21:01
from collections import deque

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

directions = [(0,1),(0,-1),(1,0),(-1,0)]

def search(x, y):
  dq = deque([(x,y)])
  answer = 0
  while dq:
    x, y = dq.popleft()
    answer += 1
    for dx, dy in directions:
      nx, ny = x+dx, y+dy
      if 0<=nx<m and 0<=ny<n and graph[ny][nx] == 1:
        graph[ny][nx] = 0
        dq.append((nx, ny))
  return answer

answers = []

for x in range(m):
  for y in range(n):
    if graph[y][x] == 1:
      graph[y][x] = 0
      answers.append(search(x, y))

print(len(answers))
answers.append(0)
print(max(answers))