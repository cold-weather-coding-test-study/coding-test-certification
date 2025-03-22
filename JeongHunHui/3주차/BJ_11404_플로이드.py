# 16:54

# 도시 수
n = int(input())
# 버스 수
m = int(input())

graph = [[float('inf')]*(n+1) for _ in range(n+1)]
for i in range(n+1):
  graph[i][i] = 0

for _ in range(m):
  s, f, d = map(int, input().split())
  graph[s][f] = min(d, graph[s][f])

for k in range(1,n+1):
  for i in range(1,n+1):
    for j in range(1,n+1):
      graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

for nums in graph[1:]:
  print(' '.join([str(n) if n != float('inf') else '0' for n in nums[1:]]))