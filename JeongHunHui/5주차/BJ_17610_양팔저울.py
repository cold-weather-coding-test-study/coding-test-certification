# 16:00
# 최대 3^13 => 1,594,323

n = int(input())
nums = list(map(int, input().split()))
num_set = set()

def backtracking(depth, s):
  if depth == n:
    if s > 0:
      num_set.add(s)
    return
  # 선택 x
  backtracking(depth+1, s)
  # 더하기
  backtracking(depth+1, s+nums[depth])
  # 빼기
  backtracking(depth+1, s-nums[depth])

backtracking(0, 0)
print(sum(nums)-len(num_set))