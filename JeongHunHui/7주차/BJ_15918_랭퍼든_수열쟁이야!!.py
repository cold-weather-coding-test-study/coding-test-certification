# 14:45
n, x, y = map(int, input().split())

nums = [0] * (2 * n)
target_num = y-x-1
nums[x-1] = target_num
nums[y-1] = target_num
num_set = set([target_num])

answer = 0
def backtracking(depth):
  global answer
  if depth == 2 * n:
    answer += 1
    return
  if nums[depth] != 0:
    backtracking(depth+1)
    return
  for i in range(1, n+1):
    next_idx = depth + i + 1
    if i not in num_set and next_idx < 2*n and nums[next_idx] == 0:
      num_set.add(i)
      nums[depth] = i
      nums[next_idx] = i
      backtracking(depth + 1)
      nums[depth] = 0
      nums[next_idx] = 0
      num_set.remove(i)
backtracking(0)
print(answer)