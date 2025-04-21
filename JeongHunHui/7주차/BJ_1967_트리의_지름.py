# 15:18
# 1. 엣지 노드들을 찾는다.
# 2. 그 엣지 노드에서 dfs로 가장 긴 경로를 찾는다.
# 3. 그 중 최댓값을 구한다.

import sys

sys.setrecursionlimit(100000)

n = int(input())
graph = [[] for _ in range(n)]
for _ in range(n-1):
  parent_node, child_node, weight = map(int, input().split())
  graph[parent_node-1].append((child_node-1, weight))
  graph[child_node-1].append((parent_node-1, weight))

edges = set()
for i, data in enumerate(graph):
  if len(data) == 1:
    edges.add(i)

answer = 0
def dfs(node, previous_node, total_weight):
  global answer
  if node in edges:
    answer = max(answer, total_weight)
    return
  for next_node, weight in graph[node]:
    if previous_node != next_node:
      dfs(next_node, node, total_weight + weight)

for edge in edges:
  next_node, weight = graph[edge][0]
  dfs(next_node, edge, weight)

print(answer)