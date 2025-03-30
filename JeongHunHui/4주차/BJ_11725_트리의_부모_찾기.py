# 16:38
from collections import deque
n = int(input())
nodes = [[] for _ in range(n+1)]
is_visited = [False for _ in range(n+1)]
for _ in range(n-1):
  node1, node2 = map(int, input().split())
  nodes[node1].append(node2)
  nodes[node2].append(node1)

is_visited[1] = True
dq = deque([])
for node in nodes[1]:
  dq.append((node, 1))
  is_visited[node] = True

answers = [None]*(n+1)
while dq:
  node, parent_node = dq.popleft()
  answers[node] = parent_node
  for target_node in nodes[node]:
    if not is_visited[target_node]:
      is_visited[target_node] = True
      dq.append((target_node, node))

for answer in answers[2:]:
  print(answer)