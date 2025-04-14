# 18:39
r1, c1, r2, c2 = map(int, input().split())
l = max(abs(r1),abs(r2),abs(c1),abs(c2))
max_x, max_y = c2-c1, r2-r1
board = [[0]*(max_x+1) for _ in range(max_y+1)]
dirs = [(1,0),(0,-1),(-1,0),(0,1)]

current_dir = 0
x, y = l, l
count = 1

if c1 <= x-l <= c2 and r1 <= y-l <= r2:
  board[y-l-r1][x-l-c1] = count

for i in range(1, l*2+1):
  for _ in range(2):
    dx, dy = dirs[current_dir]
    for _ in range(i):
      count += 1
      x, y = x+dx, y+dy
      if c1 <= x-l <= c2 and r1 <= y-l <= r2:
        board[y-l-r1][x-l-c1] = count
    current_dir = (current_dir+1)%4

dx, dy = dirs[current_dir]
for _ in range(l*2):
  count += 1
  x, y = x+dx, y+dy
  if c1 <= x-l <= c2 and r1 <= y-l <= r2:
    board[y-l-r1][x-l-c1] = count

max_num = 0
for i in range(len(board)):
  for j in range(len(board[0])):
    max_num = max(board[i][j], max_num)
num_len = len(str(max_num))

for y in range(max_y+1):
  nums = []
  for x in range(max_x+1):
    nums.append(str(board[y][x]).rjust(num_len, ' '))
  print(' '.join(nums))