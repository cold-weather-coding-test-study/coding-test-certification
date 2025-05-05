# 14:22

# 오른쪽이나 위쪽으로만 이동 가능
# -> 아예 못 가는 경우도 존재
# 그리고, 가야하는 순서가 존재

# n = 세로, m = 가로, a = 아이템 수, b = 장애물 수
n, m, a, b = map(int, input().split())

items = [(1,1)]+[tuple(map(int, input().split())) for _ in range(a)]+[(n,m)]
items.sort(key=lambda pos : (pos[0], pos[1]))

graph = [[0]*(m+1) for _ in range(n+1)]

for _ in range(b):
  y, x = map(int, input().split())
  graph[y][x] = -1

def update_route_at_graph(start, dest):
  start_y, start_x = start
  dest_y, dest_x = dest
  for y in range(start_y, dest_y+1):
    for x in range(start_x, dest_x+1):
      if graph[y][x] != 0:
        continue
      if graph[y][x-1] != -1:
        graph[y][x] += graph[y][x-1]
      if graph[y-1][x] != -1:
        graph[y][x] += graph[y-1][x]

def solution():
  for i in range(1, len(items)):
    if items[i][0] < items[i-1][0] or items[i][1] < items[i-1][1]:
      return 0
  # item[i-1]에서 item[i]로 이동하는 경우의 수들 계산
  # 그 경우의 수들 전부 곱하기

  # 일단, 귀찮으니까, 장애물과 아이템의 위치는 겹치지 않고, 출발점과 도착점에는 장애물이 없다고 가정
  graph[1][1] = 1
  for i in range(len(items)-1):
    update_route_at_graph(items[i], items[i+1])
    if graph[items[i+1][0]][items[i+1][1]] <= 0:
      break
  return graph[n][m]

print(solution())