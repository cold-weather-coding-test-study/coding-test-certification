# 17:22

# n = 운동 시간
# m = 지침 지수
n, m = map(int, input().split())
distances = [0]+[int(input()) for _ in range(n)]

# 쉬게 되면 0까지 쉬어야함
# 끝날 때는 0이여야함

# dp 같은데요?
# dp[i][j] -> i분에 지침지수가j일 때 이동한 거리
dp = [[0]*(m+1) for _ in range(n+1)]
for i in range(n):
  d = distances[i+1]
  for j in range(min(m+1, i+1)):
    # 올라간다 -> dp[i+1][j+1] =  dp[i][j]+d
    # 내려간다 -> dp[i+j][0] =  max(dp[i+j][0], dp[i][j])
    if j == 0:
      dp[i+1][0] = max(dp[i][0], dp[i+1][0])
      dp[i+1][1] = dp[i][0]+d
    else:
      if i+j <= n:
        dp[i+j][0] = max(dp[i+j][0], dp[i][j])
      if j < m:
        dp[i+1][j+1] = dp[i][j]+d
print(dp[n][0])