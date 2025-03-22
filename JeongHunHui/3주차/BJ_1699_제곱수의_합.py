# 15:45
import math
n = int(input())
dp = [i for i in range(n+1)]
for i in range(4, n+1):
  val = float('inf')
  sq = math.floor(math.sqrt(i))
  for j in range(1,sq+1):
    val = min(val, dp[i-j**2]+1)
  dp[i] = val
print(dp[n])