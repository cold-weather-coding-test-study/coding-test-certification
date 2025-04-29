# 14:39
n = int(input())
times = [list(map(int, input().split())) for _ in range(n)]
times.sort(key = lambda nums : nums[1])
answer = times[0][1] - times[0][0]
for i in range(1, n):
  times[i][0] += times[i-1][0]
  answer = min(times[i][1] - times[i][0], answer)
  if answer < 0:
    answer = -1
    break
print(answer)