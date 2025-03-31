# 16:39
n, l, w, h = map(int, input().split())

def check(a):
  return (l // a) * (w // a) * (h // a) >= n

left, right = 0, min(l, w, h)

for _ in range(100):
  mid = (left+right) / 2
  if check(mid):
    left = mid
  else:
    right = mid

print(left)