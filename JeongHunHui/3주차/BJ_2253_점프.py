# 22:05
n, m = map(int, input().split())
can_go = set(int(input()) for _ in range(m))

# i번 돌에 j칸 점프해서 도착
# dp[i][j] = min(dp[i-j][j-1], dp[i-j][j], dp[i-j][j+1])+1
dp = [[float('inf')]*(int((2*n)**0.5)+2) for _ in range(n+1)]
dp[1][0] = 0

for i in range(2, n+1):
  if i in can_go:
    continue
  for j in range(1, int((2*i)**0.5)+1):
    dp[i][j] = min(dp[i-j][j-1], dp[i-j][j], dp[i-j][j+1])+1
answer = min(dp[n])
print(answer if answer != float('inf') else -1)