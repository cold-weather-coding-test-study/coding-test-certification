# 23:26
n = int(input())
files = sorted(list(map(int, input().split())))

# f1 >= f2 * 0.9
answer = 0
for i in range(n):
  file = files[i]
  l, r = i, n
  while l+1 < r:
    mid = (l+r) // 2
    if files[mid] * 0.9 <= file:
      l = mid
    else:
      r = mid
  answer += l - i
print(answer)