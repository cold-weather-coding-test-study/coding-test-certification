# 16:58
from collections import deque

# 물건의 개수
n = int(input())
# 비교 횟수
m = int(input())

graph = [[0 for _ in range(n+1)] for _ in range(n+1)]

# 위, 아래
for _ in range(m):
  heavy, light = map(int, input().split())
  graph[heavy][light] = 1
  graph[light][heavy] = -1

def search(direction, start):
  answer = 0
  dq = deque([start])
  is_visited = set([start])
  while dq:
    start = dq.pop()
    for target in range(1, n+1):
      if graph[start][target] == direction and target not in is_visited:
        answer += 1
        is_visited.add(target)
        dq.appendleft(target)
  return answer

for i in range(1, n+1):
  a = search(1, i)
  b = search(-1, i)
  answer = n-a-b-1
  print(answer)