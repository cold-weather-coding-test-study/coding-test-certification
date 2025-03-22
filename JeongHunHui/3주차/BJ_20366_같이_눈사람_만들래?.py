# 15:34
n = int(input())
snows = sorted(list(map(int, input().split())))

answer = float('inf')

for i in range(n-3):
  for j in range(i+3, n):
    snow_man = snows[i] + snows[j]
    l, r = i+1, j-1
    while l < r:
      target_snow_man = snows[l] + snows[r]
      answer = min(answer, abs(snow_man-target_snow_man))
      if snow_man == target_snow_man:
        break
      if snow_man > target_snow_man:
        l += 1
      else:
        r -= 1

print(answer)