# 12:38
n = int(input())
input_str = input()
nums = []
index = 0
for i in range(n):
  new_nums = []
  for j in range(n):
    if j < i:
      new_nums.append(None)
    else:
      new_nums.append(input_str[index])
      index += 1
  nums.append(new_nums)
answers = []
real_answers = None

def check(new_sum, num):
  if num == '-':
    return new_sum < 0
  if num == '+':
    return new_sum > 0
  return new_sum == 0

def is_valid():
  l = len(answers)
  for i in range(l):
    new_sum = 0
    for j in range(i, l):
      new_sum += answers[j]
      num = nums[i][j]
      if not check(new_sum, num):
        return False
  return True

def backtracking(depth):
  global real_answers
  if real_answers:
    return
  if not is_valid():
    return
  if depth == n:
    real_answers = answers
    print(' '.join(list(map(str, real_answers))))
    return
  for i in range(-10, 11):
    answers.append(i)
    backtracking(depth+1)
    answers.pop()

backtracking(0)