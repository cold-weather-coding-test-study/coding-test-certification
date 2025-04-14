# 01:17
people_count, party_count = map(int, input().split())

graph = [[False]*(people_count+1) for _ in range(people_count+1)]

real_nums = set([num for num in map(int, input().split())][1:])

parties = []
for _ in range(party_count):
  party = [num for num in map(int, input().split())][1:]
  parties.append(set(party))
  count = len(party)
  for i in range(count):
    for j in range(i, count):
      if i != j:
        graph[party[i]][party[j]] = True
        graph[party[j]][party[i]] = True

is_visited = [False]*(people_count+1)
new_real_nums = set()

def dfs(start):
  new_real_nums.add(start)
  is_visited[start] = True
  for i in range(1, people_count+1):
    if graph[start][i] and not is_visited[i]:
      dfs(i)

for num in real_nums:
  dfs(num)

answer = 0
for party in parties:
  if len(new_real_nums.intersection(party)) == 0:
    answer += 1

print(answer)