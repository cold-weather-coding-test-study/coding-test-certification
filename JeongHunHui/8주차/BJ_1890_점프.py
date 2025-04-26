# 15:02
n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
counts = [[0]*n for _ in range(n)]
counts[0][0] = 1

def calculate_count(x, y):
  count = 0
  for nx in range(x):
    if nx + graph[y][nx] == x:
      count += counts[y][nx]
  for ny in range(y):
    if ny + graph[ny][x] == y:
      count += counts[ny][x]
  return count

for i in range(n):
  counts[i][i] += calculate_count(i, i)
  for x in range(i+1, n):
    counts[i][x] = calculate_count(x, i)
  for y in range(i+1, n):
    counts[y][i] = calculate_count(i, y)

print(counts[n-1][n-1])