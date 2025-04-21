# 14:00
n, m = map(int, input().split())
woods = []
for _ in range(n):
  woods.append(list(map(int, input().split())))

dirs = [[(-1, 0), (0, 1)], [(-1, 0), (0, -1)], [(1, 0), (0, -1)], [(1, 0), (0, 1)]]
is_visited = set()

answer = 0

def get_next_pos(x, y):
  x += 1
  if x == m:
    return (0, y+1)
  return (x, y)

def backtracking(x, y, power):
  nx, ny = get_next_pos(x, y)
  global answer
  if (x, y) in is_visited:
    backtracking(nx, ny, power)
    return
  if y == n:
    answer = max(answer, power)
    return
  for d1, d2 in dirs:
    d1x, d1y = d1
    d2x, d2y = d2
    n1x, n1y = x+d1x, y+d1y
    n2x, n2y = x+d2x, y+d2y
    if 0 <= n1x < m and 0 <= n2x < m and 0 <= n1y < n and 0 <= n2y < n and (n1x, n1y) not in is_visited and (n2x, n2y) not in is_visited:
      is_visited.add((n1x, n1y))
      is_visited.add((n2x, n2y))
      is_visited.add((x, y))
      backtracking(nx, ny, power + (woods[y][x] * 2) + woods[n1y][n1x] + woods[n2y][n2x])
      is_visited.remove((n1x, n1y))
      is_visited.remove((n2x, n2y))
      is_visited.remove((x, y))
  backtracking(nx, ny, power)
backtracking(0,0,0)
print(answer)