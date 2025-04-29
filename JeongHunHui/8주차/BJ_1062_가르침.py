# 14:12
from itertools import combinations as comb

n, k = map(int, input().split())
basic_c_set = set(['a','n','t','i','c'])
c_set = set()
words = []
for _ in range(n):
  temp_set = set()
  for c in input()[4:-4]:
    if c not in basic_c_set:
      temp_set.add(c)
  words.append(temp_set)
  c_set |= temp_set

def solution():
  nk = k-len(basic_c_set)
  if nk < 0:
    return 0
  
  c_comb = []
  if len(c_set) < nk:
    c_comb = [c_set]
  elif nk == 0:
    c_comb = [set()]
  else:
    c_comb = [set(c) for c in comb(c_set, nk)]
  
  answer = 0
  for new_set in c_comb:
    temp_answer = 0
    for word in words:
      if word.issubset(new_set):
        temp_answer += 1
    answer = max(temp_answer, answer)
  return answer

print(solution())