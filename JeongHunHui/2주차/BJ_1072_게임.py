# 16:49
import math
from decimal import Decimal
x, y = map(int, input().split())

def get_win_rate(n):
  return math.floor(Decimal(y+n)/Decimal(x+n)*100)

z = get_win_rate(0)

def get_answer():
  if z >= 99:
    return -1
  small = 1
  big = x
  while small != big:
    mid = (big + small) // 2
    win_rate = get_win_rate(mid)
    if win_rate <= z:
      small = mid+1
    else:
      big = mid
  return small

print(get_answer())