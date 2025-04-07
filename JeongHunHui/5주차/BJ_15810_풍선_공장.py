# 14:24
import heapq as hq
# n = 스태프 수, m = 풍선의 개수
n, m = map(int, input().split())

balloons = [(num, num) for num in list(map(int, input().split()))]
hq.heapify(balloons)

count = 0
while True:
  new_time, original_time = hq.heappop(balloons)
  count += 1
  if count == m:
    print(new_time)
    break
  hq.heappush(balloons, (new_time + original_time, original_time))