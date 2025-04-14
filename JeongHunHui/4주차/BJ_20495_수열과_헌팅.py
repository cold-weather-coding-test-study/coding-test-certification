# 22:17
n = int(input())
nums = [tuple(map(int, input().split())) for _ in range(n)]
min_nums, max_nums = [], []
for num, val in nums:
  min_nums.append(num-val)
  max_nums.append(num+val)
min_nums.sort()
max_nums.sort()

def bs(min_num, max_num):
  answers = []
  l, r = 0, n-1
  while l < r:
    mid = (l+r)//2
    if min_num > max_nums[mid]:
      l = mid+1
    else:
      r = mid
  if min_num > max_nums[l]:
    l += 1
  answers.append(l+1)
  l, r = 0, n-1
  while l < r:
    mid = (l+r)//2
    if max_num >= min_nums[mid]:
      l = mid+1
    else:
      r = mid
  if max_num >= min_nums[l]:
    l += 1
  answers.append(l)
  return answers

for num, val in nums:
  min_num, max_num = num-val, num+val
  answer1, answer2 = bs(min_num, max_num)
  print(f'{answer1} {answer2}')