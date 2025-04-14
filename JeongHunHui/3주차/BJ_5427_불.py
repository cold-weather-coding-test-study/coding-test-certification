# 22:41
from collections import deque
case_count = int(input())
cases = []
for _ in range(case_count):
  x, y = map(int, input().split())
  board = [[c for c in input()] for _ in range(y)]
  cases.append((board, x, y))

dirs = [(0,1),(0,-1),(1,0),(-1,0)]

def calculate_escape_time(board, x, y):
  fire_poses = deque()
  man_poses = deque()
  is_visited = [[False]*x for _ in range(y)]
  for cx in range(x):
    for cy in range(y):
      c = board[cy][cx]
      if c == '*':
        fire_poses.append((cx, cy, 1))
      elif c == '@':
        man_poses.append((cx, cy, 1))
        board[cy][cx] = '.'
  
  current_time = 0

  while man_poses:
    cx, cy, time = man_poses.popleft()
    if cx == 0 or cx == x-1 or cy == 0 or cy == y-1:
      return time

    if time > current_time:
      current_time += 1
      # 불이 번짐
      while fire_poses:
        f_cx, f_cy, f_time = fire_poses.popleft()
        if f_time > current_time:
          fire_poses.appendleft((f_cx, f_cy, f_time))
          break
        for dx, dy in dirs:
          nx, ny = f_cx+dx, f_cy+dy
          if 0<=nx<x and 0<=ny<y and board[ny][nx] == '.':
            board[ny][nx] = '*'
            fire_poses.append((nx,ny,f_time+1))

    # 이동
    for dx, dy in dirs:
      nx, ny = cx+dx, cy+dy
      if 0<=nx<x and 0<=ny<y and board[ny][nx] == '.' and not is_visited[ny][nx]:
        man_poses.append((nx,ny,time+1))
        is_visited[ny][nx] = True
    
  return 'IMPOSSIBLE'

for board, x, y in cases:
  print(calculate_escape_time(board, x, y))