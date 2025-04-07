# 15:16
# n개의 다른 숫자로 구성된 집합 x
# 길이가 2n인 수열 s
# x에 들어있는 모든 수는 s에 2번 등장
# x에 등장하는 수가 i라면 s에서 두 번 등장하는 i사이에는 수가 i개 존재

n = int(input())
x = sorted(list(map(int, input().split())))

is_visited = [False]*n
answer = [-1]

def backtracking(i, nums):
  global answer
  if answer != [-1]:
    return
  if i == n*2:
    answer = nums[:]
    return
  if nums[i] != -1:
    backtracking(i+1, nums)
  else:
    for idx, num in enumerate(x):
      new_idx = i+1+num
      if not is_visited[idx] and new_idx < n*2 and nums[new_idx] == -1:
        is_visited[idx] = True
        nums[i] = num
        nums[i+1+num] = num
        backtracking(i+1, nums)
        is_visited[idx] = False
        nums[i] = -1
        nums[i+1+num] = -1

backtracking(0, [-1]*(n*2))

print(' '.join(map(str, answer)))