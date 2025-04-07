# 22:01
n = int(input())
buildings = list(map(int, input().split()))

def can_see(start, target):
  height = buildings[start]
  target_height = buildings[target]
  search_dir = 1 if target - start > 0 else -1
  dx = (height - target_height) / (start - target)
  x = start + search_dir
  while x != target:
    current_height = buildings[x] - target_height
    if dx == 0:
      if current_height >= 0:
        break
    elif current_height >= dx * (x-target):
      break
    x += search_dir
  return x == target

answer = 0
for i in range(n):
  count = 0
  for j in range(i+1, n):
    if can_see(i, j):
      count += 1
  for j in range(i-1, -1, -1):
    if can_see(i, j):
      count += 1
  answer = max(count, answer)
print(answer)