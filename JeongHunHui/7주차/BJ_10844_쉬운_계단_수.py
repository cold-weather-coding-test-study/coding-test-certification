# 14:37
# i로 시작하는 j자리 계단 수의 개수 (0 <= i <= 9)
# dp[i][j] = dp[i-1][j-1] + dp[i+1][j-1]

n = int(input())
dp = [[0] * (n+1) for _ in range(10)]
NUM = 1000000000
for i in range(10):
  dp[i][1] = 1
for j in range(2, n+1):
  for i in range(10):
    if i == 0:
      dp[i][j] = dp[i+1][j-1]
    elif i == 9:
      dp[i][j] = dp[i-1][j-1]
    else:
      dp[i][j] = dp[i+1][j-1] + dp[i-1][j-1]
    dp[i][j] %= NUM

print(sum([dp[i][n] for i in range(1, 10)]) % NUM)