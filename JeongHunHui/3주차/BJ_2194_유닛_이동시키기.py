# 14:30
from collections import deque

# n,m = 맵 크기
# a,b = 유닛 크기
# k = 장애물 수
n, m, a, b, k = map(int, input().split())
board = [[True]*m for _ in range(n)]
is_visited = [[float('inf')]*m for _ in range(n)]
for _ in range(k):
  r, c = map(int, input().split())
  board[r-1][c-1] = False
start_pos = tuple([int(c)-1 for c in input().split()])
finish_pos = tuple([int(c)-1 for c in input().split()])

dirs = [(0,1),(0,-1),(1,0),(-1,0)]

def get_check_poses(dr, dc, r, c):
  poses = []
  if dr == 1:
    for i in range(b):
      poses.append((r+a, c+i))
  elif dr == -1:
    for i in range(b):
      poses.append((r-1, c+i))
  elif dc == 1:
    for i in range(a):
      poses.append((r+i, c+b))
  else:
    for i in range(a):
      poses.append((r+i, c-1))
  return poses

# 이동할 곳 장애물 확인, 없으면 이동
# bfs 같음
start_r, start_c = start_pos
finish_r, finish_c = finish_pos
dq = deque([(start_r, start_c, 0)])
is_visited[start_r][start_c] = 0
answer = -1
while dq:
  r, c, d = dq.popleft()
  if r == finish_r and c == finish_c:
    answer = d
    break
  for dr, dc in dirs:
    poses = get_check_poses(dr, dc, r, c)
    nr, nc = r+dr, c+dc
    if all(0<=tr<n and 0<=tc<m and board[tr][tc] for tr, tc in poses):
      if is_visited[nr][nc] > d+1:
        is_visited[nr][nc] = d+1
        dq.append((nr, nc, d+1))

print(answer)