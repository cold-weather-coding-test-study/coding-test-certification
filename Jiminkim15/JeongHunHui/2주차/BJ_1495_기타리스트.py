# 14:30
N, S, M = map(int, input().split())
changes = list(map(int, input().split()))

# dp[i][v] = i번째 곡까지 연주했을 때, v 볼륨으로 연주할 수 있으면 True, 아니면 False
dp = [[False] * (M + 1) for _ in range(N + 1)]
dp[0][S] = True

for i in range(1, N + 1):
  change = changes[i - 1]
  for volume in range(M + 1):
    if dp[i - 1][volume]:
      if volume + change <= M:
        dp[i][volume + change] = True
      if volume - change >= 0:
        dp[i][volume - change] = True

result = -1
for volume in range(M, -1, -1):
    if dp[N][volume]:
      result = volume
      break

print(result)