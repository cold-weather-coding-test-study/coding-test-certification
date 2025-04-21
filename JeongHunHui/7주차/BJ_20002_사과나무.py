# 15:11
n = int(input())
nums = [[0]*(n+1)]
for _ in range(n):
  nums.append([0]+list(map(int, input().split())))

s = [[0]*(n+1) for _ in range(n+1)]
for y in range(1, n+1):
  for x in range(1, n+1):
    s[y][x] = s[y-1][x] + s[y][x-1] - s[y-1][x-1] + nums[y][x]

answer = -float('inf')
# x,y가 우측 하단 모서리인 크기가 k인 정사각형의 크기
for y in range(1, n+1):
  for x in range(1, n+1):
    for k in range(1, min(x, y) + 1):
      answer = max(answer, s[y][x] - s[y-k][x] - s[y][x-k] + s[y-k][x-k])
print(answer)